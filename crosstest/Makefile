all: v250 v261 v300b2

v250: v2.5.0/foo.jar v2.5.0/bar.jar v2.5.0/baz.jar
v261: v2.6.1/foo.jar v2.6.1/bar.jar v2.6.1/baz.jar
v300b2: v3.0.0-beta-2/foo.jar v3.0.0-beta-2/bar.jar v3.0.0-beta-2/baz.jar


v2.5.0/foo.jar: src/foo.proto src/UseFoo.java
	mkdir -p crosstest
	cp src/UseFoo.java crosstest
	v2.5.0/protoc --proto_path=src --java_out=. src/foo.proto
	javac -cp ../v2.5.0/protobuf.jar:../deps/deps.jar crosstest/FooProtos.java crosstest/UseFoo.java
	jar cf v2.5.0/foo.jar crosstest/FooProtos*.class crosstest/UseFoo*.class

v2.6.1/foo.jar: src/foo.proto src/UseFoo.java
	mkdir -p crosstest
	cp src/UseFoo.java crosstest
	v2.6.1/protoc --proto_path=src --java_out=. src/foo.proto
	javac -cp ../v2.6.1/protobuf.jar:../deps/deps.jar crosstest/FooProtos.java crosstest/UseFoo.java
	jar cf v2.6.1/foo.jar crosstest/FooProtos*.class crosstest/UseFoo*.class

v3.0.0-beta-2/foo.jar: src/foo.proto src/UseFoo.java
	mkdir -p crosstest
	cp src/UseFoo.java crosstest
	v3.0.0-beta-2/protoc --proto_path=src --java_out=. src/foo.proto
	javac -cp ../v3.0.0-beta-2/protobuf.jar:../deps/deps.jar crosstest/FooProtos.java crosstest/UseFoo.java
	jar cf v3.0.0-beta-2/foo.jar crosstest/FooProtos*.class crosstest/UseFoo*.class

v2.5.0/bar.jar: src/bar.proto src/UseBar.java
	mkdir -p crosstest
	cp src/UseBar.java crosstest
	v2.5.0/protoc --proto_path=src --java_out=. src/bar.proto
	javac -cp ../v2.5.0/protobuf.jar:../deps/deps.jar:v2.5.0/foo.jar crosstest/BarProtos.java crosstest/UseBar.java
	jar cf v2.5.0/bar.jar crosstest/BarProtos*.class crosstest/UseBar*.class

v2.6.1/bar.jar: src/bar.proto src/UseBar.java
	mkdir -p crosstest
	cp src/UseBar.java crosstest
	v2.6.1/protoc --proto_path=src --java_out=. src/bar.proto
	javac -cp ../v2.6.1/protobuf.jar:../deps/deps.jar:v2.6.1/foo.jar crosstest/BarProtos.java crosstest/UseBar.java
	jar cf v2.6.1/bar.jar crosstest/BarProtos*.class crosstest/UseBar*.class

v3.0.0-beta-2/bar.jar: src/bar.proto src/UseBar.java
	mkdir -p crosstest
	cp src/UseBar.java crosstest
	v3.0.0-beta-2/protoc --proto_path=src --java_out=. src/bar.proto
	javac -cp ../v3.0.0-beta-2/protobuf.jar:../deps/deps.jar:v3.0.0-beta-2/foo.jar crosstest/BarProtos.java crosstest/UseBar.java
	jar cf v3.0.0-beta-2/bar.jar crosstest/BarProtos*.class crosstest/UseBar*.class

v2.5.0/baz.jar: src/baz.proto src/UseBaz.java
	mkdir -p crosstest
	cp src/UseBaz.java crosstest
	v2.5.0/protoc --proto_path=src --java_out=. src/baz.proto
	javac -cp ../v2.5.0/protobuf.jar:../deps/deps.jar:v2.5.0/foo.jar:v2.5.0/bar.jar crosstest/BazProtos.java crosstest/UseBaz.java
	jar cf v2.5.0/baz.jar crosstest/BazProtos*.class crosstest/UseBaz*.class

v2.6.1/baz.jar: src/baz.proto src/UseBaz.java
	mkdir -p crosstest
	cp src/UseBaz.java crosstest
	v2.6.1/protoc --proto_path=src --java_out=. src/baz.proto
	javac -cp ../v2.6.1/protobuf.jar:../deps/deps.jar:v2.6.1/foo.jar:v2.6.1/bar.jar crosstest/BazProtos.java crosstest/UseBaz.java
	jar cf v2.6.1/baz.jar crosstest/BazProtos*.class crosstest/UseBaz*.class

v3.0.0-beta-2/baz.jar: src/baz.proto src/UseBaz.java
	mkdir -p crosstest
	cp src/UseBaz.java crosstest
	v3.0.0-beta-2/protoc --proto_path=src --java_out=. src/baz.proto
	javac -cp ../v3.0.0-beta-2/protobuf.jar:../deps/deps.jar:v3.0.0-beta-2/foo.jar:v3.0.0-beta-2/bar.jar crosstest/BazProtos.java crosstest/UseBaz.java
	jar cf v3.0.0-beta-2/baz.jar crosstest/BazProtos*.class crosstest/UseBaz*.class
