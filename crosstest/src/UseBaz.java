package crosstest;

import junit.framework.TestCase;

import static crosstest.FooProtos.Foo;
import static crosstest.BarProtos.Bar;
import static crosstest.BazProtos.Baz;

import com.google.protobuf.ExtensionRegistry;

public class UseBaz extends TestCase {
  public void testBaz() throws Exception {
    Baz baz = Baz.newBuilder()
        .setFooValue(Foo.newBuilder().setValue(1234).build())
        .setFooType(Foo.Type.FOO1)
        .setBarValue(Bar.newBuilder().setValue(4321).build())
        .setBarType(Bar.Type.BAR2)
        .build();
    Baz other = Baz.parseFrom(baz.toByteString());
    assertEquals(1234, other.getFooValue().getValue());
    assertEquals(Foo.Type.FOO1, other.getFooType());
    assertEquals(4321, other.getBarValue().getValue());
    assertEquals(Bar.Type.BAR2, other.getBarType());
  }

  public void testFooBaz() throws Exception {
    Foo foo = Foo.newBuilder().setExtension(
        Baz.fooBaz, Baz.newBuilder().setFooType(Foo.Type.FOO2).build()).build();
    ExtensionRegistry registry = ExtensionRegistry.newInstance();
    BazProtos.registerAllExtensions(registry);
    Foo other = Foo.parseFrom(foo.toByteString(), registry);
    assertEquals(Foo.Type.FOO2, other.getExtension(Baz.fooBaz).getFooType());
  }

  public void testBarBaz() throws Exception {
    Bar bar = Bar.newBuilder().setExtension(
        Baz.barBaz, Baz.newBuilder().setBarType(Bar.Type.BAR1).build()).build();
    ExtensionRegistry registry = ExtensionRegistry.newInstance();
    BazProtos.registerAllExtensions(registry);
    Bar other = Bar.parseFrom(bar.toByteString(), registry);
    assertEquals(Bar.Type.BAR1, other.getExtension(Baz.barBaz).getBarType());
  }
}
