package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-7-19.
 */

public class ExpertBean implements Parcelable,SearchBean{

    /**
     * code : H1-D1-D1
     * name : 华迪一号
     * skill : 擅长脊柱、关节常见病及疑难病与骨质疏松症的诊治。
     * qualification : 毕业于中国中医研究院研究生部，骨伤科主任医师，原广元市中医医院骨伤科主任，四川省骨伤专业委员会委员，中国中西医结合学会会员，司法鉴定专家，从事临床工作38年，主刀手术5000余例，在省、国家级杂志发表论文30余篇。
     * doctorTitle : 主任医师
     * gender : 男
     * age : 48
     * phoneNumber : 11012011900
     * address : 四川省成都市郫县区菁蓉镇展望东路66号创新创业6区4栋3单元
     * roomNumber :
     * imageUrl : doctor_1.png
     * appointment : 是
     */

    /**
     * 医生编号
     */
    @SerializedName("code")
    private String code;
    /**
     * 姓名
     */
    @SerializedName("name")
    private String name;
    /**
     * 擅长技能
     */
    @SerializedName("skill")
    private String skill;

    @SerializedName("qualification")
    private String qualification;
    @SerializedName("doctorTitle")
    private String doctorTitle;
    @SerializedName("gender")
    private String gender;
    @SerializedName("age")
    private int age;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("address")
    private String address;
    @SerializedName("roomNumber")
    private String roomNumber;
    @SerializedName("imageUrl")
    private String imageUrl;
    /**
     * 是否可预约
     */
    @SerializedName("appointment")
    private String appointment;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getSummary() {
        return skill;
    }

    @Override
    public int getType() {
        return TYPE_DOCTOR;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeString(this.skill);
        dest.writeString(this.qualification);
        dest.writeString(this.doctorTitle);
        dest.writeString(this.gender);
        dest.writeInt(this.age);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.address);
        dest.writeString(this.roomNumber);
        dest.writeString(this.imageUrl);
        dest.writeString(this.appointment);
    }

    public ExpertBean() {
    }

    protected ExpertBean(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.skill = in.readString();
        this.qualification = in.readString();
        this.doctorTitle = in.readString();
        this.gender = in.readString();
        this.age = in.readInt();
        this.phoneNumber = in.readString();
        this.address = in.readString();
        this.roomNumber = in.readString();
        this.imageUrl = in.readString();
        this.appointment = in.readString();
    }

    public static final Creator<ExpertBean> CREATOR = new Creator<ExpertBean>() {
        @Override
        public ExpertBean createFromParcel(Parcel source) {
            return new ExpertBean(source);
        }

        @Override
        public ExpertBean[] newArray(int size) {
            return new ExpertBean[size];
        }
    };
}
