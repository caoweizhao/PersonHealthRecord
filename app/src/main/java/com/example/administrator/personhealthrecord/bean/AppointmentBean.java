package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andy on 2017/8/1.
 */

public class AppointmentBean extends AbstractReserveBean implements Parcelable {
    //    {"rroId":3,"hospitalName":"成都军区联勤部机关医院","doctorName":"华迪十一","doctorSkill":"
// 擅长神经系统各种常见疾病、疑难疾病的诊断和外科治疗，尤其对于垂体腺瘤、颅咽管瘤及脑血管病有着丰富的治疗
// 经验","startTime":1500944400000,"endTime":1500948000000,"phoneNumber":"","orderStatus":"正在预约"}
    public int rroId;
    public String doctorName;
    public String doctorSkill;
    public String hospitalName;
    public long startTime;
    public long endTime;
    public String phoneNumber;
    public String orderStatus;

    public int getRroId() {
        return rroId;
    }

    public void setRroId(int rroId) {
        this.rroId = rroId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSkill() {
        return doctorSkill;
    }

    public void setDoctorSkill(String doctorSkill) {
        this.doctorSkill = doctorSkill;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String getImageId() {
        return rroId + "";
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public String getHosPitalNameTotal() {
        return hospitalName;
    }

    @Override
    public String getName() {
        return doctorName;
    }

    @Override
    public boolean equals(Object obj) {
        return rroId == ((AppointmentBean) obj).rroId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rroId);
        dest.writeString(this.doctorName);
        dest.writeString(this.doctorSkill);
        dest.writeString(this.hospitalName);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.orderStatus);
    }

    public AppointmentBean() {
    }

    protected AppointmentBean(Parcel in) {
        this.rroId = in.readInt();
        this.doctorName = in.readString();
        this.doctorSkill = in.readString();
        this.hospitalName = in.readString();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.phoneNumber = in.readString();
        this.orderStatus = in.readString();
    }

    public static final Parcelable.Creator<AppointmentBean> CREATOR = new Parcelable.Creator<AppointmentBean>() {
        @Override
        public AppointmentBean createFromParcel(Parcel source) {
            return new AppointmentBean(source);
        }

        @Override
        public AppointmentBean[] newArray(int size) {
            return new AppointmentBean[size];
        }
    };
}
