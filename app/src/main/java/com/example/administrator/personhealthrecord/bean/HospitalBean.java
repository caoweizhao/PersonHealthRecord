package com.example.administrator.personhealthrecord.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017-7-20.
 */

public class HospitalBean extends DataSupport{
    private String name;
    private String englishName;
    private String category;
    private String level;
    private String address;
    private String summary;
    private String scale;
    private String history;
    private String departmentStatus;
    private String medicalTeam;
    private String medicalEquipment;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(String departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public String getMedicalTeam() {
        return medicalTeam;
    }

    public void setMedicalTeam(String medicalTeam) {
        this.medicalTeam = medicalTeam;
    }

    public String getMedicalEquipment() {
        return medicalEquipment;
    }

    public void setMedicalEquipment(String medicalEquipment) {
        this.medicalEquipment = medicalEquipment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        return getName().equals(((HospitalBean)obj).getName());
    }
}
