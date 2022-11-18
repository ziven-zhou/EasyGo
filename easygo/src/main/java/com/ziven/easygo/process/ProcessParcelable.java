package com.ziven.easygo.process;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

/**
 * @author Ziven
 */
@Keep
public class ProcessParcelable implements Parcelable {

    private String stringMessage;
    private boolean booleanMessage;
    private int intMessage;
    private long longMessage;
    private float floatMessage;
    private double doubleMessage;
    private final int what;

    public ProcessParcelable(int what) {
        this.what = what;
    }

    public ProcessParcelable(Parcel in) {
        int[] intArray = new int[2];
        in.readIntArray(intArray);
        what = intArray[0];
        switch (what) {
            case ProcessCommunication.WHAT_STRING:
                setMessage(in.readString());
                break;
            case ProcessCommunication.WHAT_BOOLEAN:
                setMessage(intArray[1] != 0);
                break;
            case ProcessCommunication.WHAT_INT:
                setMessage(intArray[1]);
                break;
            case ProcessCommunication.WHAT_LONG:
                setMessage(in.readLong());
                break;
            case ProcessCommunication.WHAT_FLOAT:
                setMessage(in.readFloat());
                break;
            case ProcessCommunication.WHAT_DOUBLE:
                setMessage(in.readDouble());
                break;
            default:
                break;
        }
    }

    public int getWhat() {
        return what;
    }

    public String getStringMessage() {
        return stringMessage;
    }

    public ProcessParcelable setMessage(String stringMessage) {
        this.stringMessage = stringMessage;
        return this;
    }

    public boolean isBooleanMessage() {
        return booleanMessage;
    }

    public ProcessParcelable setMessage(boolean booleanMessage) {
        this.booleanMessage = booleanMessage;
        return this;
    }

    public int getIntMessage() {
        return intMessage;
    }

    public ProcessParcelable setMessage(int intMessage) {
        this.intMessage = intMessage;
        return this;
    }

    public long getLongMessage() {
        return longMessage;
    }

    public ProcessParcelable setMessage(long longMessage) {
        this.longMessage = longMessage;
        return this;
    }

    public float getFloatMessage() {
        return floatMessage;
    }

    public ProcessParcelable setMessage(float floatMessage) {
        this.floatMessage = floatMessage;
        return this;
    }

    public double getDoubleMessage() {
        return doubleMessage;
    }

    public ProcessParcelable setMessage(double doubleMessage) {
        this.doubleMessage = doubleMessage;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        switch (what) {
            case ProcessCommunication.WHAT_STRING:
                dest.writeIntArray(new int[] {what, what});
                dest.writeString(stringMessage);
                break;
            case ProcessCommunication.WHAT_BOOLEAN:
                dest.writeIntArray(new int[] {what, booleanMessage ? 1 : 0});
                break;
            case ProcessCommunication.WHAT_INT:
                dest.writeIntArray(new int[] {what, intMessage});
                break;
            case ProcessCommunication.WHAT_LONG:
                dest.writeIntArray(new int[] {what, what});
                dest.writeLong(longMessage);
                break;
            case ProcessCommunication.WHAT_FLOAT:
                dest.writeIntArray(new int[] {what, what});
                dest.writeFloat(floatMessage);
                break;
            case ProcessCommunication.WHAT_DOUBLE:
                dest.writeIntArray(new int[] {what, what});
                dest.writeDouble(doubleMessage);
                break;
            default:
                break;
        }
    }

    public static final Creator<ProcessParcelable> CREATOR = new Creator<ProcessParcelable>() {
        @Override
        public ProcessParcelable createFromParcel(Parcel in) {
            return new ProcessParcelable(in);
        }

        @Override
        public ProcessParcelable[] newArray(int size) {
            return new ProcessParcelable[size];
        }
    };
}
