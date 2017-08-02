package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-8-1.
 */

public class PHRBean implements Parcelable {

    /**
     * username : saber
     * height : null
     * weight : null
     * bodyMassIndex : null
     * bloodType : null
     * heartRate : null
     * bloodPressure : null
     * smokingVolume : null
     * drinkingType : null
     * alcoholVolume : null
     * drinkingFrequency : null
     * physicalExercise : null
     * medicineAllergy : null
     */

    /**
     * 用户名
     */
    @SerializedName("username")
    private String username;
    /**
     * 身高
     */
    @SerializedName("height")
    private String height;
    /**
     * 体重
     */
    @SerializedName("weight")
    private String weight;

    /**
     * 体重指数 kg/m2
     */
    @SerializedName("bodyMassIndex")
    private String bodyMassIndex;
    /**
     * 血型
     */
    @SerializedName("bloodType")
    private String bloodType;
    /**
     * 心率
     */
    @SerializedName("heartRate")
    private String heartRate;

    @SerializedName("bloodPressureUp")
    private String bloodPressureUp;
    @SerializedName("bloodPressureDown")
    private String bloodPressureDown;
    /**
     * 吸烟量
     */
    @SerializedName("smokingVolume")
    private String smokingVolume;

    /**
     * 饮酒类型
     */
    @SerializedName("drinkingType")
    private String drinkingType;

    /**
     * 饮酒量
     */
    @SerializedName("alcoholVolume")
    private String alcoholVolume;
    /**
     * 饮酒频率
     */
    @SerializedName("drinkingFrequency")
    private String drinkingFrequency;
    /**
     * 体育锻炼情况
     */
    @SerializedName("physicalExercise")
    private String physicalExercise;
    /**
     * 药物过敏情况
     */
    @SerializedName("medicineAllergy")
    private String medicineAllergy;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(String bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSmokingVolume() {
        return smokingVolume;
    }

    public void setSmokingVolume(String smokingVolume) {
        this.smokingVolume = smokingVolume;
    }

    public String getDrinkingType() {
        return drinkingType;
    }

    public void setDrinkingType(String drinkingType) {
        this.drinkingType = drinkingType;
    }

    public String getAlcoholVolume() {
        return alcoholVolume;
    }

    public void setAlcoholVolume(String alcoholVolume) {
        this.alcoholVolume = alcoholVolume;
    }

    public String getDrinkingFrequency() {
        return drinkingFrequency;
    }

    public void setDrinkingFrequency(String drinkingFrequency) {
        this.drinkingFrequency = drinkingFrequency;
    }

    public String getPhysicalExercise() {
        return physicalExercise;
    }

    public void setPhysicalExercise(String physicalExercise) {
        this.physicalExercise = physicalExercise;
    }

    public String getMedicineAllergy() {
        return medicineAllergy;
    }

    public void setMedicineAllergy(String medicineAllergy) {
        this.medicineAllergy = medicineAllergy;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.height);
        dest.writeString(this.weight);
        dest.writeString(this.bodyMassIndex);
        dest.writeString(this.bloodType);
        dest.writeString(this.heartRate);
        dest.writeString(this.bloodPressureUp);
        dest.writeString(this.bloodPressureDown);
        dest.writeString(this.smokingVolume);
        dest.writeString(this.drinkingType);
        dest.writeString(this.alcoholVolume);
        dest.writeString(this.drinkingFrequency);
        dest.writeString(this.physicalExercise);
        dest.writeString(this.medicineAllergy);
    }

    public PHRBean() {
    }

    public String getBloodPressureUp() {
        return bloodPressureUp;
    }

    public void setBloodPressureUp(String bloodPressureUp) {
        this.bloodPressureUp = bloodPressureUp;
    }

    public String getBloodPressureDown() {
        return bloodPressureDown;
    }

    public void setBloodPressureDown(String bloodPressureDown) {
        this.bloodPressureDown = bloodPressureDown;
    }

    protected PHRBean(Parcel in) {
        this.username = in.readString();
        this.height = in.readString();
        this.weight = in.readString();
        this.bodyMassIndex = in.readString();
        this.bloodType = in.readString();
        this.heartRate = in.readString();
        this.bloodPressureUp = in.readString();
        this.bloodPressureDown = in.readString();
        this.smokingVolume = in.readString();
        this.drinkingType = in.readString();
        this.alcoholVolume = in.readString();
        this.drinkingFrequency = in.readString();
        this.physicalExercise = in.readString();
        this.medicineAllergy = in.readString();
    }

    public static final Creator<PHRBean> CREATOR = new Creator<PHRBean>() {
        @Override
        public PHRBean createFromParcel(Parcel source) {
            return new PHRBean(source);
        }

        @Override
        public PHRBean[] newArray(int size) {
            return new PHRBean[size];
        }
    };
}
