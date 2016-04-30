Test the ABI compatiblity between different protobuf versions.

For every version, we build 3 jar files:
  1. protobuf.jar: the protobuf Java runtime.
  2. generated.jar: the compiled Java generated code of test protos.
  3. test.jar: protobuf unit tests (they use the test protos).

To check whether a newer version is ABI compatible with an older version,
we run the old test.jar with the newer protobuf.jar. I.e.,

java -cp <new_version>/protobuf.jar:<old_version>/generated.jar:<old_version>/test.jar org.junit.runner.JUnitCore <tests>

and,

java -cp <new_version>/protobuf.jar:<new_version>/generated.jar:<old_version>/test.jar org.junit.runner.JUnitCore <tests>

If all tests pass, we consider the newer version backward compatible with the
older version.

NOTE: even after all tests pass, the two jar files are still not ABI compatible
in pure Java sense. For example, we may have public methods that are actually
protobuf internal and should only be accessed in protobuf generated classes. We
may change or remove these methods and don't guarantee ABI compatibility for
such cases.

Sub-directories:
* deps: test dependencies like junit.
* v2.5.0: compiled runtime/generated code/tests.
* v2.6.1: compiled runtime/generated code/tests.
* v3.0.0-beta-1: compiled runtime/generated code/tests.
* v3.0.0-beta-2: compiled runtime/generated code/tests.

* tests_v250: modified v2.5.0 unit tests. removed the ones that are
              intentionally broken after a runtime upgrade.
* v2.5.0c: mostly the same as v2.5.0 but the tests are from tests_v250.
* tests_v261: modified v2.6.1 unit tests. removed the ones that are
              intentionally broken after a runtime upgrade.
* v2.6.1c: mostly the same as v2.6.1 but the tests are from tests_v261.

Instructions:
  $ git submodule init
  $ git submodule update
  $ git clone https://github.com/google/protobuf
  $ ./build.sh
  $ ./run.sh v2.5.0c head
  $ ./run.sh v2.6.1c head
