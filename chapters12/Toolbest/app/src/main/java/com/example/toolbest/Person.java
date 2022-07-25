package com.example.toolbest;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Person implements Parcelable {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //写出name与age
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Parcelable.Creator<Person>CREATOR=new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            Person person=new Person();
            //读取name与age
            person.name=source.readString();
            person.age=source.readInt();
            return person;
        }



        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
