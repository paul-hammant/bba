#!/bin/sh

set -e

cd 1/trunk/
mvn clean test
cd ../../2/trunk/
mvn clean test
cd ../../3/trunk/
mvn clean test
cd ../../4/trunk/
mvn clean test
cd ../../8/trunk/
mvn clean test
cd ../..