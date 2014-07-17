Stub-it
=======

Author: [Tommy Tynjä](http://twitter.com/tommysdk)<br/>
Language: Java/JVM<br/>
Requires: Java 8<br/>
<br/>
The goal of this project is to provide an easy way to create stubs for plain old java objects (POJOs). The ideal use case would be for testing, e.g. unit tests.<br/>

With Stub-it, it is possible to stub certain fields on a certain object. It uses pure reflection to serve its purpose. It is therefore required that the type of a stubbed field provide a no-argument constructor, unless being a class covered by the default value provider (com.tynja.stubit.Default). It currently provides default values for Integer, Long, Double, Float, BigInteger or BigDecimal objects. The field type must be a concrete implementation class, as interfaces cannot be instantiated. The default value provider does however provide default implementation values for all known subinterfaces of the java.util.Collection interface, as these are commonly used. Look in the com.tynja.stubit.Default class to see which classes are supported out of the box. The stubbed fields also requires matching setter- and getter-methods, which is a de facto standard pattern in Java.<br/>

Stub-it makes use of the Java Function object, a Java 8 language feature, to let the user tailor the predicate used to decide which object fields to provide stubs for.<br/>

Below is an example, where a stub is created for a JPA entity, where all fields annotated with @javax.persistence.Column and set as non-nullable, will be stubbed:
```java
PersonJpaEntity person = new PersonJpaEntity();
Function<Field, Boolean> nonNullableColumns = 
        (field -> field.getAnnotation(Column.class) != null 
                && !field.getAnnotation(Column.class).nullable());
Stub.withValuesProvidedFor(person, nonNullableColumns);
```

The project has no compile or runtime dependencies so you don't need to worry about Stub-it bringing in any other libraries than itself.<br/>

You can find the binaries for the released 0.1 version at: https://github.com/tommysdk/tommysdk.github.io/tree/master/binaries/com/tynja/test/stub-it/0.1. Features are covered in the README.md file for that version: https://github.com/tommysdk/stub-it/blob/stub-it-0.1/README.md.

The project is licensed under Apache License Version 2.0.<br/>

Happy stubbing!