/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package br.com.deyvidfernandes.customer.producer.dto;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class Email extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6705592765588053280L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Email\",\"namespace\":\"br.com.deyvidfernandes.customer.producer.dto\",\"fields\":[{\"name\":\"ClienteId\",\"type\":[\"int\",\"null\"]},{\"name\":\"email\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Email> ENCODER =
      new BinaryMessageEncoder<Email>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Email> DECODER =
      new BinaryMessageDecoder<Email>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Email> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Email> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Email> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Email>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Email to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Email from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Email instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Email fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private Integer ClienteId;
   private CharSequence email;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Email() {}

  /**
   * All-args constructor.
   * @param ClienteId The new value for ClienteId
   * @param email The new value for email
   */
  public Email(Integer ClienteId, CharSequence email) {
    this.ClienteId = ClienteId;
    this.email = email;
  }

  public SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public Object get(int field$) {
    switch (field$) {
    case 0: return ClienteId;
    case 1: return email;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: ClienteId = (Integer)value$; break;
    case 1: email = (CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'ClienteId' field.
   * @return The value of the 'ClienteId' field.
   */
  public Integer getClienteId() {
    return ClienteId;
  }


  /**
   * Sets the value of the 'ClienteId' field.
   * @param value the value to set.
   */
  public void setClienteId(Integer value) {
    this.ClienteId = value;
  }

  /**
   * Gets the value of the 'email' field.
   * @return The value of the 'email' field.
   */
  public CharSequence getEmail() {
    return email;
  }


  /**
   * Sets the value of the 'email' field.
   * @param value the value to set.
   */
  public void setEmail(CharSequence value) {
    this.email = value;
  }

  /**
   * Creates a new Email RecordBuilder.
   * @return A new Email RecordBuilder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Creates a new Email RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Email RecordBuilder
   */
  public static Builder newBuilder(Builder other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * Creates a new Email RecordBuilder by copying an existing Email instance.
   * @param other The existing instance to copy.
   * @return A new Email RecordBuilder
   */
  public static Builder newBuilder(Email other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * RecordBuilder for Email instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Email>
    implements org.apache.avro.data.RecordBuilder<Email> {

    private Integer ClienteId;
    private CharSequence email;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.ClienteId)) {
        this.ClienteId = data().deepCopy(fields()[0].schema(), other.ClienteId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.email)) {
        this.email = data().deepCopy(fields()[1].schema(), other.email);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing Email instance
     * @param other The existing instance to copy.
     */
    private Builder(Email other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.ClienteId)) {
        this.ClienteId = data().deepCopy(fields()[0].schema(), other.ClienteId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.email)) {
        this.email = data().deepCopy(fields()[1].schema(), other.email);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'ClienteId' field.
      * @return The value.
      */
    public Integer getClienteId() {
      return ClienteId;
    }


    /**
      * Sets the value of the 'ClienteId' field.
      * @param value The value of 'ClienteId'.
      * @return This builder.
      */
    public Builder setClienteId(Integer value) {
      validate(fields()[0], value);
      this.ClienteId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'ClienteId' field has been set.
      * @return True if the 'ClienteId' field has been set, false otherwise.
      */
    public boolean hasClienteId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'ClienteId' field.
      * @return This builder.
      */
    public Builder clearClienteId() {
      ClienteId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'email' field.
      * @return The value.
      */
    public CharSequence getEmail() {
      return email;
    }


    /**
      * Sets the value of the 'email' field.
      * @param value The value of 'email'.
      * @return This builder.
      */
    public Builder setEmail(CharSequence value) {
      validate(fields()[1], value);
      this.email = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'email' field has been set.
      * @return True if the 'email' field has been set, false otherwise.
      */
    public boolean hasEmail() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'email' field.
      * @return This builder.
      */
    public Builder clearEmail() {
      email = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Email build() {
      try {
        Email record = new Email();
        record.ClienteId = fieldSetFlags()[0] ? this.ClienteId : (Integer) defaultValue(fields()[0]);
        record.email = fieldSetFlags()[1] ? this.email : (CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Email>
    WRITER$ = (org.apache.avro.io.DatumWriter<Email>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Email>
    READER$ = (org.apache.avro.io.DatumReader<Email>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.ClienteId == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeInt(this.ClienteId);
    }

    if (this.email == null) {
      out.writeIndex(1);
      out.writeNull();
    } else {
      out.writeIndex(0);
      out.writeString(this.email);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 0) {
        in.readNull();
        this.ClienteId = null;
      } else {
        this.ClienteId = in.readInt();
      }

      if (in.readIndex() != 0) {
        in.readNull();
        this.email = null;
      } else {
        this.email = in.readString(this.email instanceof Utf8 ? (Utf8)this.email : null);
      }

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 0) {
            in.readNull();
            this.ClienteId = null;
          } else {
            this.ClienteId = in.readInt();
          }
          break;

        case 1:
          if (in.readIndex() != 0) {
            in.readNull();
            this.email = null;
          } else {
            this.email = in.readString(this.email instanceof Utf8 ? (Utf8)this.email : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










