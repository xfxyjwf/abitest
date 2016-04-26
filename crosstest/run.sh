#!/bin/bash

OLD=$1
NEW=$2

if [ ! -d "$OLD" -o ! -d "$NEW" ]; then
  echo "Cannot find directory $OLD or $NEW."
  exit 1
fi

java -cp ../deps/deps.jar:../$NEW/protobuf.jar:$OLD/foo.jar:$NEW/bar.jar:$OLD/baz.jar org.junit.runner.JUnitCore crosstest.UseFoo crosstest.UseBar crosstest.UseBaz
