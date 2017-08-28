package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017-7-22.
 */

public class MedicineBean extends DataSupport implements AbstractItem,SearchBean {

    /**
     * code : H10970410
     * name : 氯雷他定片
     * function : 用...
     * useTaboo : 对本品过敏者禁用。
     * category : 本品为耳鼻喉科及皮肤科用药类非处方药药品。
     * component : 本品每粒含氯雷他定。
     * method : ...
     * character : 本品为白色片。
     * attention : ...
     * expiryDate : 48个月
     * standard : 10mg*6片/盒
     * imageUrl : medicine_4
     */

    /**
     * 编号
     */
    @SerializedName("code")
    private String code;
    /**
     * 药名
     */
    @SerializedName("name")
    private String name;
    /**
     * 功效
     */
    @SerializedName("function")
    private String function;
    /**
     *
     */
    @SerializedName("useTaboo")
    private String useTaboo;
    /**
     * 分类
     */
    @SerializedName("category")
    private String category;
    /**
     * 组份
     */
    @SerializedName("component")
    private String component;
    /**
     * 使用方法
     */
    @SerializedName("method")
    private String method;
    /**
     * 药品特点
     */
    @SerializedName("character")
    private String character;
    /**
     * 注意事项
     */
    @SerializedName("attention")
    private String attention;
    /**
     * 有效日期
     */
    @SerializedName("expiryDate")
    private String expiryDate;
    /**
     * 规格
     */
    @SerializedName("standard")
    private String standard;
    /**
     * 图片URL
     */
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getUseTaboo() {
        return useTaboo;
    }

    public void setUseTaboo(String useTaboo) {
        this.useTaboo = useTaboo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public String getSummary() {
        return getFunction();
    }

    @Override
    public int getType() {
        return TYPE_MEDICINE;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getDate() {
        return "";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return getCode().compareTo(((MedicineBean) o).getCode());
    }

    @Override
    public boolean equals(Object obj) {
        return getCode().equals(((MedicineBean) obj).getCode());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeString(this.function);
        dest.writeString(this.useTaboo);
        dest.writeString(this.category);
        dest.writeString(this.component);
        dest.writeString(this.method);
        dest.writeString(this.character);
        dest.writeString(this.attention);
        dest.writeString(this.expiryDate);
        dest.writeString(this.standard);
        dest.writeString(this.imageUrl);
    }

    public MedicineBean() {
    }

    protected MedicineBean(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.function = in.readString();
        this.useTaboo = in.readString();
        this.category = in.readString();
        this.component = in.readString();
        this.method = in.readString();
        this.character = in.readString();
        this.attention = in.readString();
        this.expiryDate = in.readString();
        this.standard = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Creator<MedicineBean> CREATOR = new Creator<MedicineBean>() {
        @Override
        public MedicineBean createFromParcel(Parcel source) {
            return new MedicineBean(source);
        }

        @Override
        public MedicineBean[] newArray(int size) {
            return new MedicineBean[size];
        }
    };
}
