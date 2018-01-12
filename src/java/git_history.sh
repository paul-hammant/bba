#!/bin/sh

set -e

mkdir repo

cp -r 1/trunk/* repo/
cd repo
echo "target\nlog/\n" > .gitignore
git init
git add .
git commit -m "As Jooby's 'quickstart project generator' made it"
cd ..
cp -r 2/trunk/* repo/
cd repo
git add .
git commit -m "1. Haircolor added that's stringified"
git tag 1
cd ..
cp -r 3/trunk/* repo/
cd repo
git add .
git commit -m "2. Move hard-coded string version of haircolor to a factory (introduce the abstraction)"
git tag 2
cd ..
cp -r 4/trunk/* repo/
cd repo
git add .
git commit -m "3. sdfsdf"
git tag 3
cd ..
cp -r 5/trunk/* repo/
cd repo
git add .
git commit -m "4. sfgsfgfdh"
git tag 4
cd ..
cp -r 6/trunk/* repo/
cd repo
git add .
git commit -m "5. dfsuigyiudfg"
git tag 5
cd ..
cp -r 7/trunk/* repo/
cd repo
git add .
git commit -m "6. zgzifug"
git tag 6
cd ..
