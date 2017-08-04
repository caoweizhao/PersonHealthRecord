package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-8-1.
 */

public class UserInfoBean implements Parcelable{

    /**
     * username : cwz
     * credits : 0积分
     * age : 22
     * gender : 男
     * phoneNumber :13560321319
     * address :
     * name :
     * iconImage :
     */


    /**
     * 账户
     */
    @SerializedName("username")
    private String username;
    /**
     * 积分
     */
    @SerializedName("credits")
    private String credits;
    /**
     *年龄
     */
    @SerializedName("age")
    private String age;
    /**
     * 性别
     */
    @SerializedName("gender")
    private String gender;
    /**
     * 电话
     */
    @SerializedName("phoneNumber")
    private String phoneNumber;
    /**
     * 地址
     */
    @SerializedName("address")
    private String address;
    /**
     * 姓名
     */
    @SerializedName("name")
    private String name;
    /**
     * 头像
     */
    @SerializedName("iconImage")
    private String iconImage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.credits);
        dest.writeString(this.age);
        dest.writeString(this.gender);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.address);
        dest.writeString(this.name);
        dest.writeString(this.iconImage);
    }

    public UserInfoBean() {
    }

    protected UserInfoBean(Parcel in) {
        this.username = in.readString();
        this.credits = in.readString();
        this.age = in.readString();
        this.gender = in.readString();
        this.phoneNumber = in.readString();
        this.address = in.readString();
        this.name = in.readString();
        this.iconImage = in.readString();
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel source) {
            return new UserInfoBean(source);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };
}
