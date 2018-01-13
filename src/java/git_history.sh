#!/bin/sh

set -e

# mkdir repo

# cp -r 1/trunk/* repo/
cd repo
# echo "target\nlog/\n" > .gitignore
# git init
# git remote add origin git@github.com:paul-hammant/little_java_branch_by_abstraction_demo.git
git reset --hard 06889352960adc5fd73bb16c9fc5a324223f0632
git tag -d 1
git tag -d 2
git tag -d 3
git tag -d 4
git tag -d 5
# git add .
# git commit -m "As Jooby's 'quickstart project generator' made it"
cd ..
cp -r 2/trunk/* repo/
cd repo
git add .
git commit -m "1. Hair color added that's stringified (regretably in hindsight)"
git tag 1
cd ..
cp -r 3/trunk/* repo/
cd repo
git add .
git commit -m "2. Extract hard-coded string version of hair color to a factory - introducing the abstraction (and go live)"
git tag 2
cd ..
cp -r 4/trunk/* repo/
cd repo
git add .
git commit -m "3. New second integer-based impl of 'hair color' without deleting the old impl (and go live)"
git tag 3
cd ..
cp -r 5/trunk/* repo/
cd repo
git add .
git rm src/main/java/com/mycompany/HairColorFactory.java
git rm src/main/java/com/mycompany/HairColorFactoryImpl.java
git rm src/main/java/com/mycompany/NewHairColorFactoryImpl.java
git commit -m "4. Rename 'hair color factory' to more general purpose 'branch by abstraction factory' (and go live)"
git tag 4
cd ..
cp -r 6/trunk/* repo/
cd repo
git add .
git rm conf/application_release4.conf
git rm src/main/java/com/mycompany/Release3.java
git commit -m "5. Remove the hair color abstraction a while after release as we're passed roll back now (and go live)"
cd ..
cp -r 7/trunk/* repo/
cd repo
git add .
git commit -m "5. Remove the hair color abstraction a while after release as we're passed roll back now (and go live)" --amend
cd ..
cp -r 8/trunk/* repo/
cd repo
git add .
git commit -m "5. Remove the hair color abstraction a while after release as we're passed roll back now (and go live)" --amend
git tag 5
git push -f
cd ..

