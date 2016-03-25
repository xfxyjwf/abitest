#!/bin/bash

OLD=$1
NEW=$2

if [ ! -d "$OLD" -o ! -d "$NEW" ]; then
  echo "Cannot find directory $OLD or $NEW."
  exit 1
fi

pushd $OLD/protobuf

java -cp ../../deps/deps.jar:../../$NEW/protobuf.jar:../generated.jar:../test.jar org.junit.runner.JUnitCore $(<../test.lst)

java -cp ../../deps/deps.jar:../../$NEW/protobuf.jar:../../$NEW/generated.jar:../test.jar org.junit.runner.JUnitCore $(<../test.lst)
