package crosstest;

import com.google.protobuf.ExtensionRegistry;
import junit.framework.TestCase;
import static crosstest.BarProtos.Bar;
import static crosstest.FooProtos.Foo;

public class UseBar extends TestCase {
  public void testBar() throws Exception {
    Bar bar = Bar.newBuilder()
        .setFooValue(Foo.newBuilder().setValue(1234).build())
        .setFooType(Foo.Type.FOO1)
        .build();
    Bar other = Bar.parseFrom(bar.toByteString());
    assertEquals(1234, other.getFooValue().getValue());
    assertEquals(Foo.Type.FOO1, other.getFooType());
  }

  public void testFooBar() throws Exception {
    Foo foo = Foo.newBuilder().setExtension(
        Bar.fooBar, Bar.newBuilder().setFooType(Foo.Type.FOO2).build()).build();
    ExtensionRegistry registry = ExtensionRegistry.newInstance();
    BarProtos.registerAllExtensions(registry);
    Foo other = Foo.parseFrom(foo.toByteString(), registry);
    assertEquals(Foo.Type.FOO2, other.getExtension(Bar.fooBar).getFooType());
  }
}
