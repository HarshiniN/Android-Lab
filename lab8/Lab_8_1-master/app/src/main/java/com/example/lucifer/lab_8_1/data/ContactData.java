package com.example.lucifer.lab_8_1.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adarsh on 20/05/2017.
 */

public class ContactData implements Parcelable {
    private int _id;

    private String name;
    private String phone_no;
    private String email;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {  return name; }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactData(String name, String phone_no, String email){
        this.name = name;
        this.phone_no = phone_no;
        this.email = email;
    }


    public ContactData(){
        _id = -1;
        name = phone_no = email = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactData(Parcel source){
        _id = source.readInt();
        name = source.readString();
        phone_no = source.readString();
        email = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(phone_no);
        dest.writeString(email);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Object createFromParcel(Parcel source) {
            return new ContactData(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new ContactData[size];
        }
    };

    public void setid(int id) {
        this._id = id;
    }
}
