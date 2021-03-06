#!/bin/bash

if [ ! -d "protobuf" ]; then
  git clone https://github.com/google/protobuf
  if [ ! -z "$1" ]; then
    cd protobuf
    git reset --hard $1
    cd ..
  fi
fi

# Build the newest protobuf jars.
pushd protobuf
if [ ! -f "src/protoc" ]; then
  ./autogen.sh
  ./configure
  make -j
fi

cd java
if [ -d core ]; then
  cd core
  JAVA_PATH=java/core
else
  JAVA_PATH=java
fi
if [ ! -f "test.lst" ]; then
  mvn package 2>&1 | tee /tmp/$$.mvn.log
  grep "^Running " /tmp/$$.mvn.log | sed "s/Running //" > test.lst
fi

cd target
mv *.jar protobuf.jar
javac -cp protobuf.jar `find generated-test-sources -name "*.java"`

cd generated-test-sources
find . -name "*.class" > ../a
cd ../test-classes
find . -name "*.class" > ../b
cd ..
cat > filter.py <<EOF
#!/bin/python

if __name__ == "__main__":
  a = open("a").readlines()
  b = open("b").readlines()
  c = [v for v in b if not v in a]
  open("c", "w").writelines(c)
EOF
python filter.py
cd test-classes
jar cf ../../generated.jar $(<../a)
jar cf ../../test.jar $(<../c)

popd  # protobuf

mkdir -p head
cp -f protobuf/$JAVA_PATH/test.lst head/test.lst
cp -f protobuf/$JAVA_PATH/target/protobuf.jar head/protobuf.jar
cp -f protobuf/$JAVA_PATH/generated.jar head/generated.jar
cp -f protobuf/$JAVA_PATH/test.jar head/test.jar
