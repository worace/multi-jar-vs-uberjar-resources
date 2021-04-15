#! /usr/bin/env bash

set -euo pipefail

echo "*** Running Example Script ***"
DIR=$(pwd)

echo "*** Publishing lib-a to local ~/.ivy2 ***"
cd $DIR/lib-a
sbt publishLocal

echo "*** Publishing lib-b to local ~/.ivy2 ***"
cd $DIR/lib-b
sbt publishLocal

echo "*** Running App Example Via SBT run (multi JAR) ***"
cd $DIR/app
sbt run

echo "*** Building Assembly JAR ***"
echo "---> This will fail because no merge strategy is set for pizza.txt <---"

sbt assembly
