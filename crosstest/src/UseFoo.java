package crosstest;

import junit.framework.TestCase;
import static crosstest.FooProtos.Foo;

public class UseFoo extends TestCase {
  public void testFoo() throws Exception {
    Foo foo = Foo.newBuilder().setValue(1234).build();
    Foo other = Foo.parseFrom(foo.toByteString());
    assertEquals(1234, other.getValue());
  }
}
