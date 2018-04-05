package com.example.lucifer.lab_8_3.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adarsh on 20/05/2017.
 */

public class MovieData implements Parcelable {
    private int _id;

    private String name;
    private String year;
    private String rating;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {  return name; }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public MovieData(String name, String year, String email){
        this.name = name;
        this.year = year;
        this.rating = email;
    }


    public MovieData(){
        _id = -1;
        name = year = rating = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieData(Parcel source){
        _id = source.readInt();
        name = source.readString();
        year = source.readString();
        rating = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(year);
        dest.writeString(rating);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public Object createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new MovieData[size];
        }
    };

    public void setid(int id) {
        this._id = id;
    }
}
