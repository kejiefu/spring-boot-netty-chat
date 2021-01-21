package com.mountain.im.connector.model.protobuf;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BaseMessage.proto

public final class BaseMessageProto {
  private BaseMessageProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface BaseMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:BaseMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *命令
     * </pre>
     *
     * <code>int32 cmd = 1;</code>
     * @return The cmd.
     */
    int getCmd();

    /**
     * <pre>
     *数据
     * </pre>
     *
     * <code>bytes data = 2;</code>
     * @return The data.
     */
    com.google.protobuf.ByteString getData();
  }
  /**
   * Protobuf type {@code BaseMessage}
   */
  public static final class BaseMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:BaseMessage)
      BaseMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use BaseMessage.newBuilder() to construct.
    private BaseMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private BaseMessage() {
      data_ = com.google.protobuf.ByteString.EMPTY;
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new BaseMessage();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private BaseMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              cmd_ = input.readInt32();
              break;
            }
            case 18: {

              data_ = input.readBytes();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return BaseMessageProto.internal_static_BaseMessage_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return BaseMessageProto.internal_static_BaseMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              BaseMessage.class, Builder.class);
    }

    public static final int CMD_FIELD_NUMBER = 1;
    private int cmd_;
    /**
     * <pre>
     *命令
     * </pre>
     *
     * <code>int32 cmd = 1;</code>
     * @return The cmd.
     */
    @Override
    public int getCmd() {
      return cmd_;
    }

    public static final int DATA_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString data_;
    /**
     * <pre>
     *数据
     * </pre>
     *
     * <code>bytes data = 2;</code>
     * @return The data.
     */
    @Override
    public com.google.protobuf.ByteString getData() {
      return data_;
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (cmd_ != 0) {
        output.writeInt32(1, cmd_);
      }
      if (!data_.isEmpty()) {
        output.writeBytes(2, data_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (cmd_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, cmd_);
      }
      if (!data_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, data_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof BaseMessage)) {
        return super.equals(obj);
      }
      BaseMessage other = (BaseMessage) obj;

      if (getCmd()
          != other.getCmd()) return false;
      if (!getData()
          .equals(other.getData())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + CMD_FIELD_NUMBER;
      hash = (53 * hash) + getCmd();
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getData().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static BaseMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static BaseMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static BaseMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static BaseMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static BaseMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static BaseMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static BaseMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static BaseMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static BaseMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static BaseMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static BaseMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static BaseMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(BaseMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code BaseMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:BaseMessage)
        BaseMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return BaseMessageProto.internal_static_BaseMessage_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return BaseMessageProto.internal_static_BaseMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                BaseMessage.class, Builder.class);
      }

      // Construct using BaseMessageProto.BaseMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        cmd_ = 0;

        data_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return BaseMessageProto.internal_static_BaseMessage_descriptor;
      }

      @Override
      public BaseMessage getDefaultInstanceForType() {
        return BaseMessage.getDefaultInstance();
      }

      @Override
      public BaseMessage build() {
        BaseMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public BaseMessage buildPartial() {
        BaseMessage result = new BaseMessage(this);
        result.cmd_ = cmd_;
        result.data_ = data_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof BaseMessage) {
          return mergeFrom((BaseMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(BaseMessage other) {
        if (other == BaseMessage.getDefaultInstance()) return this;
        if (other.getCmd() != 0) {
          setCmd(other.getCmd());
        }
        if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
          setData(other.getData());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        BaseMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (BaseMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int cmd_ ;
      /**
       * <pre>
       *命令
       * </pre>
       *
       * <code>int32 cmd = 1;</code>
       * @return The cmd.
       */
      @Override
      public int getCmd() {
        return cmd_;
      }
      /**
       * <pre>
       *命令
       * </pre>
       *
       * <code>int32 cmd = 1;</code>
       * @param value The cmd to set.
       * @return This builder for chaining.
       */
      public Builder setCmd(int value) {
        
        cmd_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *命令
       * </pre>
       *
       * <code>int32 cmd = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearCmd() {
        
        cmd_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <pre>
       *数据
       * </pre>
       *
       * <code>bytes data = 2;</code>
       * @return The data.
       */
      @Override
      public com.google.protobuf.ByteString getData() {
        return data_;
      }
      /**
       * <pre>
       *数据
       * </pre>
       *
       * <code>bytes data = 2;</code>
       * @param value The data to set.
       * @return This builder for chaining.
       */
      public Builder setData(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        data_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *数据
       * </pre>
       *
       * <code>bytes data = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearData() {
        
        data_ = getDefaultInstance().getData();
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:BaseMessage)
    }

    // @@protoc_insertion_point(class_scope:BaseMessage)
    private static final BaseMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new BaseMessage();
    }

    public static BaseMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<BaseMessage>
        PARSER = new com.google.protobuf.AbstractParser<BaseMessage>() {
      @Override
      public BaseMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BaseMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<BaseMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<BaseMessage> getParserForType() {
      return PARSER;
    }

    @Override
    public BaseMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_BaseMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_BaseMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\021BaseMessage.proto\"(\n\013BaseMessage\022\013\n\003cm" +
      "d\030\001 \001(\005\022\014\n\004data\030\002 \001(\014B\022B\020BaseMessageProt" +
      "ob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_BaseMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_BaseMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_BaseMessage_descriptor,
        new String[] { "Cmd", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
