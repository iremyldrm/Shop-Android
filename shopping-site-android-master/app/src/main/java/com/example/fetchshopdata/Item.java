package com.example.fetchshopdata;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    int id;
    String name;
    double price;
    String shortDescription;
    boolean isItemOfTheWeek;
    String imgPath;

    protected Item(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        shortDescription = in.readString();
        isItemOfTheWeek = in.readByte() != 0;
        imgPath = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean isItemOfTheWeek() {
        return isItemOfTheWeek;
    }

    public void setItemOfTheWeek(boolean itemOfTheWeek) {
        isItemOfTheWeek = itemOfTheWeek;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String toString(){
        return "ID: " + String.valueOf(id) + "\n" +"Name: "+ name + "\n" +"Price: "+ String.valueOf(price) + "\n" +"Short Description: "+ shortDescription + "\n" +"Image Path: " + imgPath +"\n------------------------------\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeString(shortDescription);
        parcel.writeByte((byte) (isItemOfTheWeek ? 1 : 0));
        parcel.writeString(imgPath);
    }
}
