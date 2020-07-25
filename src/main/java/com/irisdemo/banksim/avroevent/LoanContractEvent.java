/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.irisdemo.banksim.avroevent;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class LoanContractEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3089544246915611300L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LoanContractEvent\",\"namespace\":\"com.irisdemo.banksim.avroevent\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"eventDate\",\"type\":\"string\",\"logicalType\":\"timestamp-millis\"},{\"name\":\"account\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"double\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<LoanContractEvent> ENCODER =
      new BinaryMessageEncoder<LoanContractEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<LoanContractEvent> DECODER =
      new BinaryMessageDecoder<LoanContractEvent>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<LoanContractEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<LoanContractEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<LoanContractEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<LoanContractEvent>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this LoanContractEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a LoanContractEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a LoanContractEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static LoanContractEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long id;
  @Deprecated public java.lang.CharSequence eventDate;
  @Deprecated public java.lang.CharSequence account;
  @Deprecated public double amount;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public LoanContractEvent() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param eventDate The new value for eventDate
   * @param account The new value for account
   * @param amount The new value for amount
   */
  public LoanContractEvent(java.lang.Long id, java.lang.CharSequence eventDate, java.lang.CharSequence account, java.lang.Double amount) {
    this.id = id;
    this.eventDate = eventDate;
    this.account = account;
    this.amount = amount;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return eventDate;
    case 2: return account;
    case 3: return amount;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: eventDate = (java.lang.CharSequence)value$; break;
    case 2: account = (java.lang.CharSequence)value$; break;
    case 3: amount = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public long getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'eventDate' field.
   * @return The value of the 'eventDate' field.
   */
  public java.lang.CharSequence getEventDate() {
    return eventDate;
  }


  /**
   * Sets the value of the 'eventDate' field.
   * @param value the value to set.
   */
  public void setEventDate(java.lang.CharSequence value) {
    this.eventDate = value;
  }

  /**
   * Gets the value of the 'account' field.
   * @return The value of the 'account' field.
   */
  public java.lang.CharSequence getAccount() {
    return account;
  }


  /**
   * Sets the value of the 'account' field.
   * @param value the value to set.
   */
  public void setAccount(java.lang.CharSequence value) {
    this.account = value;
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public double getAmount() {
    return amount;
  }


  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(double value) {
    this.amount = value;
  }

  /**
   * Creates a new LoanContractEvent RecordBuilder.
   * @return A new LoanContractEvent RecordBuilder
   */
  public static com.irisdemo.banksim.avroevent.LoanContractEvent.Builder newBuilder() {
    return new com.irisdemo.banksim.avroevent.LoanContractEvent.Builder();
  }

  /**
   * Creates a new LoanContractEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new LoanContractEvent RecordBuilder
   */
  public static com.irisdemo.banksim.avroevent.LoanContractEvent.Builder newBuilder(com.irisdemo.banksim.avroevent.LoanContractEvent.Builder other) {
    if (other == null) {
      return new com.irisdemo.banksim.avroevent.LoanContractEvent.Builder();
    } else {
      return new com.irisdemo.banksim.avroevent.LoanContractEvent.Builder(other);
    }
  }

  /**
   * Creates a new LoanContractEvent RecordBuilder by copying an existing LoanContractEvent instance.
   * @param other The existing instance to copy.
   * @return A new LoanContractEvent RecordBuilder
   */
  public static com.irisdemo.banksim.avroevent.LoanContractEvent.Builder newBuilder(com.irisdemo.banksim.avroevent.LoanContractEvent other) {
    if (other == null) {
      return new com.irisdemo.banksim.avroevent.LoanContractEvent.Builder();
    } else {
      return new com.irisdemo.banksim.avroevent.LoanContractEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for LoanContractEvent instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LoanContractEvent>
    implements org.apache.avro.data.RecordBuilder<LoanContractEvent> {

    private long id;
    private java.lang.CharSequence eventDate;
    private java.lang.CharSequence account;
    private double amount;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.irisdemo.banksim.avroevent.LoanContractEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.eventDate)) {
        this.eventDate = data().deepCopy(fields()[1].schema(), other.eventDate);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.account)) {
        this.account = data().deepCopy(fields()[2].schema(), other.account);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.amount)) {
        this.amount = data().deepCopy(fields()[3].schema(), other.amount);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing LoanContractEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.irisdemo.banksim.avroevent.LoanContractEvent other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.eventDate)) {
        this.eventDate = data().deepCopy(fields()[1].schema(), other.eventDate);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.account)) {
        this.account = data().deepCopy(fields()[2].schema(), other.account);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.amount)) {
        this.amount = data().deepCopy(fields()[3].schema(), other.amount);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public long getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'eventDate' field.
      * @return The value.
      */
    public java.lang.CharSequence getEventDate() {
      return eventDate;
    }


    /**
      * Sets the value of the 'eventDate' field.
      * @param value The value of 'eventDate'.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder setEventDate(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.eventDate = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'eventDate' field has been set.
      * @return True if the 'eventDate' field has been set, false otherwise.
      */
    public boolean hasEventDate() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'eventDate' field.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder clearEventDate() {
      eventDate = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'account' field.
      * @return The value.
      */
    public java.lang.CharSequence getAccount() {
      return account;
    }


    /**
      * Sets the value of the 'account' field.
      * @param value The value of 'account'.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder setAccount(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.account = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'account' field has been set.
      * @return True if the 'account' field has been set, false otherwise.
      */
    public boolean hasAccount() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'account' field.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder clearAccount() {
      account = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public double getAmount() {
      return amount;
    }


    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder setAmount(double value) {
      validate(fields()[3], value);
      this.amount = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public com.irisdemo.banksim.avroevent.LoanContractEvent.Builder clearAmount() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoanContractEvent build() {
      try {
        LoanContractEvent record = new LoanContractEvent();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.eventDate = fieldSetFlags()[1] ? this.eventDate : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.account = fieldSetFlags()[2] ? this.account : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.amount = fieldSetFlags()[3] ? this.amount : (java.lang.Double) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<LoanContractEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<LoanContractEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<LoanContractEvent>
    READER$ = (org.apache.avro.io.DatumReader<LoanContractEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.id);

    out.writeString(this.eventDate);

    out.writeString(this.account);

    out.writeDouble(this.amount);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readLong();

      this.eventDate = in.readString(this.eventDate instanceof Utf8 ? (Utf8)this.eventDate : null);

      this.account = in.readString(this.account instanceof Utf8 ? (Utf8)this.account : null);

      this.amount = in.readDouble();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readLong();
          break;

        case 1:
          this.eventDate = in.readString(this.eventDate instanceof Utf8 ? (Utf8)this.eventDate : null);
          break;

        case 2:
          this.account = in.readString(this.account instanceof Utf8 ? (Utf8)this.account : null);
          break;

        case 3:
          this.amount = in.readDouble();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










