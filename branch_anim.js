/*
 * Animates the header artwork.
 *
 * The SVG is referenced by the pages as <img src="abstract_branch.svg">, which is
 * an opaque document - script cannot reach inside it. So we fetch the same file
 * and swap the <img> for the live markup, keeping one source of truth on disk.
 * If the fetch fails the <img> simply stays put and the header is still correct,
 * just still.
 *
 * The loop: 3s at rest, 2s displaced, 3s at rest, 2s displaced differently, and
 * so on. The trunk and its core stay anchored throughout - only the limbs move.
 * That is the point of the picture: work shifts around the trunk, the trunk holds.
 */
(function () {
    "use strict";

    var HOLD_MS = 3000;
    var MOVE_MS = 2000;

    // Centroids, so a limb turns about itself rather than about the canvas corner.
    var CENTROIDS = {
        "shard-blue-nw":    [213.6, 45.3],
        "shard-magenta-n":  [350.0, 49.1],
        "shard-yellow-n":   [457.4, 48.6],
        "shard-red-ne":     [746.2, 59.9],
        "shard-red-w":      [207.6, 92.1],
        "shard-blue-mid":   [337.5, 92.4],
        "shard-yellow-mid": [519.6, 87.6],
        "shard-yellow-sw":  [168.0, 151.3],
        "shard-red-s":      [417.9, 131.4],
        "shard-magenta-se": [637.7, 118.3],
        "shard-blue-se":    [815.6, 119.5]
    };

    function rnd(a, b) { return a + Math.random() * (b - a); }
    function pick(arr) { return arr[Math.floor(Math.random() * arr.length)]; }

    function xf(cx, cy, dx, dy, rot, scale) {
        return "translate(" + dx.toFixed(2) + " " + dy.toFixed(2) + ") " +
               "rotate(" + rot.toFixed(2) + " " + cx + " " + cy + ") " +
               "translate(" + cx + " " + cy + ") scale(" + scale.toFixed(3) + ") " +
               "translate(" + (-cx) + " " + (-cy) + ")";
    }

    /*
     * Each mode is a different idea of "abstract" - they are characters, not just
     * random numbers, so consecutive cycles feel deliberately unalike.
     */
    var MODES = {
        // Everything drifts outward from the trunk's spine, exploded-view style.
        explode: function (id, i, n, cx, cy) {
            var spineY = 92;
            var away = cy < spineY ? -1 : 1;
            return xf(cx, cy, rnd(-14, 14), away * rnd(16, 40), rnd(-16, 16), rnd(0.95, 1.12));
        },

        // A wave travels left to right; limbs lift in sequence by x position.
        wave: function (id, i, n, cx, cy) {
            var phase = cx / 943;
            var lift = Math.sin(phase * Math.PI * 2) * 26;
            return xf(cx, cy, 0, lift, lift * 0.5, 1);
        },

        // Everything spins on the spot. No translation at all.
        pinwheel: function (id, i, n, cx, cy) {
            return xf(cx, cy, 0, 0, pick([-1, 1]) * rnd(25, 75), rnd(0.9, 1.05));
        },

        // Limbs slide along the trunk's length, as if being filed.
        shear: function (id, i, n, cx, cy) {
            return xf(cx, cy, pick([-1, 1]) * rnd(20, 55), rnd(-5, 5), 0, 1);
        },

        // Alternate limbs shrink to almost nothing while others swell.
        breathe: function (id, i, n, cx, cy) {
            var s = (i % 2 === 0) ? rnd(0.25, 0.5) : rnd(1.15, 1.4);
            return xf(cx, cy, 0, 0, 0, s);
        },

        // Collapse toward the middle of the trunk - the opposite of explode.
        gather: function (id, i, n, cx, cy) {
            var tx = (471 - cx) * rnd(0.12, 0.3);
            var ty = (92 - cy) * rnd(0.2, 0.45);
            return xf(cx, cy, tx, ty, rnd(-10, 10), rnd(0.85, 1));
        },

        // Every limb goes somewhere of its own, no shared logic.
        scatter: function (id, i, n, cx, cy) {
            return xf(cx, cy, rnd(-45, 45), rnd(-30, 30), rnd(-40, 40), rnd(0.7, 1.25));
        },

        // A single limb leaves; the rest hold station. Quiet, and easy to miss.
        soloist: function (id, i, n, cx, cy, star) {
            if (i !== star) { return "translate(0 0)"; }
            return xf(cx, cy, rnd(-60, 60), rnd(-40, 40), rnd(-90, 90), rnd(0.8, 1.3));
        }
    };

    var MODE_NAMES = Object.keys(MODES);

    function start(svg) {
        var limbs = Array.prototype.slice.call(svg.querySelectorAll('#layer-shards > polygon'));
        if (!limbs.length) { return; }

        limbs.forEach(function (el) {
            el.style.transition = "transform " + MOVE_MS + "ms cubic-bezier(.34,.01,.28,1)";
            // The polygons carry absolute coordinates, so transforms must be applied
            // in the SVG user space rather than relative to a CSS box.
            el.style.transformBox = "view-box";
            el.style.transformOrigin = "0 0";
        });

        var queue = [];

        function nextMode() {
            if (!queue.length) {
                queue = MODE_NAMES.slice();
                // Shuffle, so every mode is seen once before any repeats.
                for (var i = queue.length - 1; i > 0; i--) {
                    var j = Math.floor(Math.random() * (i + 1));
                    var t = queue[i]; queue[i] = queue[j]; queue[j] = t;
                }
            }
            return queue.pop();
        }

        // 2s of travel out, then 3s held at rest once it has arrived back.
        function displace() {
            var mode = MODES[nextMode()];
            // Chosen once per cycle, for the modes that single a limb out.
            var star = Math.floor(Math.random() * limbs.length);
            limbs.forEach(function (el, i) {
                var c = CENTROIDS[el.id] || [471, 92];
                el.style.transform = mode(el.id, i, limbs.length, c[0], c[1], star);
            });
            setTimeout(home, MOVE_MS);
        }

        function home() {
            limbs.forEach(function (el) { el.style.transform = "translate(0 0)"; });
            setTimeout(displace, MOVE_MS + HOLD_MS);
        }

        // Start at rest, hold, then move.
        setTimeout(displace, HOLD_MS);
    }

    function inject() {
        var img = document.querySelector('img[src$="abstract_branch.svg"]');
        if (!img) { return; }

        // Someone who has asked for less motion gets the still image.
        if (window.matchMedia && window.matchMedia("(prefers-reduced-motion: reduce)").matches) {
            return;
        }

        fetch(img.getAttribute("src")).then(function (r) {
            return r.ok ? r.text() : Promise.reject(new Error(r.status));
        }).then(function (txt) {
            var doc = new DOMParser().parseFromString(txt, "image/svg+xml");
            var svg = doc.documentElement;
            if (!svg || svg.nodeName !== "svg") { return; }

            svg.setAttribute("width", "100%");
            svg.setAttribute("height", "auto");
            svg.style.display = "block";
            img.parentNode.replaceChild(svg, img);
            start(svg);
        }).catch(function () {
            /* Leave the <img> alone - a still header is a fine outcome. */
        });
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", inject);
    } else {
        inject();
    }
})();
