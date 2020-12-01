// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Person.proto

package cn.imppp.skin;

public final class PersonProtos {
  private PersonProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface PersonOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Person)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The name.
     */
    java.lang.String getName();
    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString
        getNameBytes();
  }
  /**
   * Protobuf type {@code Person}
   */
  public  static final class Person extends
      com.google.protobuf.GeneratedMessageLite<
          Person, Person.Builder> implements
      // @@protoc_insertion_point(message_implements:Person)
      PersonOrBuilder {
    private Person() {
      name_ = "";
    }
    public static final int NAME_FIELD_NUMBER = 1;
    private java.lang.String name_;
    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
      return name_;
    }
    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getNameBytes() {
      return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }
    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The name to set.
     */
    private void setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
    }
    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     */
    private void clearName() {
      
      name_ = getDefaultInstance().getName();
    }
    /**
     * <pre>
     * 格式：字段类型 + 字段名称 + 字段编号
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The bytes for name to set.
     */
    private void setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value.toStringUtf8();
    }

    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static cn.imppp.skin.PersonProtos.Person parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static cn.imppp.skin.PersonProtos.Person parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static cn.imppp.skin.PersonProtos.Person parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return (Builder) DEFAULT_INSTANCE.createBuilder();
    }
    public static Builder newBuilder(cn.imppp.skin.PersonProtos.Person prototype) {
      return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code Person}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          cn.imppp.skin.PersonProtos.Person, Builder> implements
        // @@protoc_insertion_point(builder_implements:Person)
        cn.imppp.skin.PersonProtos.PersonOrBuilder {
      // Construct using cn.imppp.skin.PersonProtos.Person.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <pre>
       * 格式：字段类型 + 字段名称 + 字段编号
       * </pre>
       *
       * <code>string name = 1;</code>
       * @return The name.
       */
      @java.lang.Override
      public java.lang.String getName() {
        return instance.getName();
      }
      /**
       * <pre>
       * 格式：字段类型 + 字段名称 + 字段编号
       * </pre>
       *
       * <code>string name = 1;</code>
       * @return The bytes for name.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString
          getNameBytes() {
        return instance.getNameBytes();
      }
      /**
       * <pre>
       * 格式：字段类型 + 字段名称 + 字段编号
       * </pre>
       *
       * <code>string name = 1;</code>
       * @param value The name to set.
       * @return This builder for chaining.
       */
      public Builder setName(
          java.lang.String value) {
        copyOnWrite();
        instance.setName(value);
        return this;
      }
      /**
       * <pre>
       * 格式：字段类型 + 字段名称 + 字段编号
       * </pre>
       *
       * <code>string name = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearName() {
        copyOnWrite();
        instance.clearName();
        return this;
      }
      /**
       * <pre>
       * 格式：字段类型 + 字段名称 + 字段编号
       * </pre>
       *
       * <code>string name = 1;</code>
       * @param value The bytes for name to set.
       * @return This builder for chaining.
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        copyOnWrite();
        instance.setNameBytes(value);
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Person)
    }
    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        java.lang.Object arg0, java.lang.Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new cn.imppp.skin.PersonProtos.Person();
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case BUILD_MESSAGE_INFO: {
            java.lang.Object[] objects = new java.lang.Object[] {
              "name_",
            };
            java.lang.String info =
                "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0208";
            return newMessageInfo(DEFAULT_INSTANCE, info, objects);
        }
        // fall through
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          com.google.protobuf.Parser<cn.imppp.skin.PersonProtos.Person> parser = PARSER;
          if (parser == null) {
            synchronized (cn.imppp.skin.PersonProtos.Person.class) {
              parser = PARSER;
              if (parser == null) {
                parser =
                    new DefaultInstanceBasedParser<cn.imppp.skin.PersonProtos.Person>(
                        DEFAULT_INSTANCE);
                PARSER = parser;
              }
            }
          }
          return parser;
      }
      case GET_MEMOIZED_IS_INITIALIZED: {
        return (byte) 1;
      }
      case SET_MEMOIZED_IS_INITIALIZED: {
        return null;
      }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:Person)
    private static final cn.imppp.skin.PersonProtos.Person DEFAULT_INSTANCE;
    static {
      Person defaultInstance = new Person();
      // New instances are implicitly immutable so no need to make
      // immutable.
      DEFAULT_INSTANCE = defaultInstance;
      com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
        Person.class, defaultInstance);
    }

    public static cn.imppp.skin.PersonProtos.Person getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Person> PARSER;

    public static com.google.protobuf.Parser<Person> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
