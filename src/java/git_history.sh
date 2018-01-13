#!/bin/sh

set -e

mkdir repo

cp -r 1/trunk/* repo/
cd repo
echo "target\nlog/\n" > .gitignore
git init
git remote add origin git@github.com:paul-hammant/little_java_branch_by_abstraction_demo.git
git add .
git commit -m "As Jooby's 'quickstart project generator' made it"
cd ..
cp -r 2/trunk/* repo/
cd repo
git add .
git commit -m "1. Hair color added that's stringified"
git tag 1
cd ..
cp -r 3/trunk/* repo/
cd repo
git add .
git commit -m "2. Move hard-coded string version of hair color to a factory (introduce the abstraction)"
git tag 2
cd ..
cp -r 4/trunk/* repo/
cd repo
git add .
git commit -m "3. New integer-based implementation of hair color (in addition to the old one)"
git tag 3
cd ..
cp -r 5/trunk/* repo/
cd repo
git add .
git commit -m "4. Release3 is out and Release4 is the new focus (of which hair color is a potential aspect amongst others)"
git tag 4
cd ..
cp -r 6/trunk/* repo/
cd repo
git add .
git rm conf/application_release4.conf
git rm src/main/java/com/mycompany/Release3.java
git commit -m "5. Release three has gone live with the new hair color implementation, remove its abstraction"
git tag 5
cd ..
cp -r 7/trunk/* repo/
cd repo
git add .
git commit -m "6. Remove hair color abstraction"
cd ..
cp -r 8/trunk/* repo/
cd repo
git add .
git commit -m "6. Remove hair color abstraction and improve tests" --amend
git tag 6
cd ..
