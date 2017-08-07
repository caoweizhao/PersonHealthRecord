package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017-8-5.
 */

public class CaseBean implements Comparable, Parcelable {

    /**
     * recordNumber : 1
     * name : 曹伟钊
     * age : 22
     * gender : 男
     * householdRegister : 广东
     * hospitalName : 三创谷华迪第一医院
     * doctorName : 华迪二号
     * time : 1501474585000
     * physicalCondition : null
     * prescriptionMedicine : null
     * treatmentSchedule : null
     * todoSomething : null
     */
    private String caseName;

    @SerializedName("recordNumber")
    private int recordNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("gender")
    private String gender;
    @SerializedName("householdRegister")
    private String householdRegister;
    @SerializedName("hospitalName")
    private String hospitalName;
    @SerializedName("doctorName")
    private String doctorName;
    @SerializedName("time")
    private long time;
    @SerializedName("physicalCondition")
    private String physicalCondition;
    @SerializedName("prescriptionMedicine")
    private String prescriptionMedicine;
    @SerializedName("treatmentSchedule")
    private String treatmentSchedule;
    @SerializedName("todoSomething")
    private String todoSomething;

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouseholdRegister() {
        return householdRegister;
    }

    public void setHouseholdRegister(String householdRegister) {
        this.householdRegister = householdRegister;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public long getTime() {
        return time;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public String getPrescriptionMedicine() {
        return prescriptionMedicine;
    }

    public void setPrescriptionMedicine(String prescriptionMedicine) {
        this.prescriptionMedicine = prescriptionMedicine;
    }

    public String getTreatmentSchedule() {
        return treatmentSchedule;
    }

    public void setTreatmentSchedule(String treatmentSchedule) {
        this.treatmentSchedule = treatmentSchedule;
    }

    public String getTodoSomething() {
        return todoSomething;
    }

    public void setTodoSomething(String todoSomething) {
        this.todoSomething = todoSomething;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        return ((CaseBean) o).recordNumber - recordNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.recordNumber);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(this.gender);
        dest.writeString(this.householdRegister);
        dest.writeString(this.hospitalName);
        dest.writeString(this.doctorName);
        dest.writeLong(this.time);
        dest.writeString(this.physicalCondition);
        dest.writeString(this.prescriptionMedicine);
        dest.writeString(this.treatmentSchedule);
        dest.writeString(this.todoSomething);
    }

    public CaseBean() {
    }

    protected CaseBean(Parcel in) {
        this.recordNumber = in.readInt();
        this.name = in.readString();
        this.age = in.readInt();
        this.gender = in.readString();
        this.householdRegister = in.readString();
        this.hospitalName = in.readString();
        this.doctorName = in.readString();
        this.time = in.readLong();
        this.physicalCondition = in.readString();
        this.prescriptionMedicine = in.readString();
        this.treatmentSchedule = in.readString();
        this.todoSomething = in.readString();
    }

    public static final Creator<CaseBean> CREATOR = new Creator<CaseBean>() {
        @Override
        public CaseBean createFromParcel(Parcel source) {
            return new CaseBean(source);
        }

        @Override
        public CaseBean[] newArray(int size) {
            return new CaseBean[size];
        }
    };

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
}
