package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andy on 2017/8/2.
 */

public class ReserveOrderBean extends AbstractReserveBean implements Parcelable {
    //    {"timestamp":1501647273579,"status":"success","message":"列表返回成功",
//            "collection":[{"orderId":5,"packageName":"高血压套餐","packageDetail"
//        :"一般情况：身高、体重、体重指数、血压、心率。 内 科：心脏、肺部、腹部、肝、脾、神经系统。" +
//                " 外 科：四肢脊柱、皮肤、甲状腺、浅表淋巴结。 眼 科：视力、色觉、裂隙灯检查、眼睑、结膜等。" +
//                " 眼底检查 耳 鼻 喉：耳镜检查外耳道、鼓膜等、前鼻镜检查鼻中隔、嗅觉、听力测定。 彩 超：肝、" +
//                "胆、脾、胰、双肾。 子宫附件（女）或前列腺（男） 心超检查 ：心脏结构及心脏功能。 心 电 图：" +
//                "十二导同步（附图纸）。 胸部正位片：心、肺、纵膈。 尿 常 规：尿比重、隐血、细胞、蛋白、尿糖等。" +
//                " 抽 血：肝功全套、肾功、血糖、血常规、血脂四项、CA199、AFP、CEA、 　 甲功三项。 主 检：" +
//                "体检结果汇总及建议、健康档案。","packagePrice":976.0,"hospitalName":"成都军区联" +
//                "勤部机关医院","startTime":1500188400000,"endTime":1500192000000,"name":"ofo","" +
//                "phoneNumber":"111111111111","medicalStatus":"正在预约"}]}

    public int orderId;
    public String packageName;
    public String packageDetail;
    public String packagePrice;
    public String hospitalName;
    public long startTime;
    public long endTime;
    public String name;
    public String phoneNumber;
    public String medicalStatus;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDetail() {
        return packageDetail;
    }

    public void setPackageDetail(String packageDetail) {
        this.packageDetail = packageDetail;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMedicalStatus() {
        return medicalStatus;
    }

    public void setMedicalStatus(String medicalStatus) {
        this.medicalStatus = medicalStatus;
    }

    @Override
    public String getImageId() {
        return getOrderId()+"";
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public String getHospitalNameTotal() {
        return hospitalName;
    }

    @Override
    public String getName() {
        return packageName;
    }

    @Override
    public boolean equals(Object obj) {
        return orderId==((ReserveOrderBean)obj).orderId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderId);
        dest.writeString(this.packageName);
        dest.writeString(this.packageDetail);
        dest.writeString(this.packagePrice);
        dest.writeString(this.hospitalName);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
        dest.writeString(this.name);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.medicalStatus);
    }

    public ReserveOrderBean() {
    }

    protected ReserveOrderBean(Parcel in) {
        this.orderId = in.readInt();
        this.packageName = in.readString();
        this.packageDetail = in.readString();
        this.packagePrice = in.readString();
        this.hospitalName = in.readString();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.name = in.readString();
        this.phoneNumber = in.readString();
        this.medicalStatus = in.readString();
    }

    public static final Parcelable.Creator<ReserveOrderBean> CREATOR = new Parcelable.Creator<ReserveOrderBean>() {
        @Override
        public ReserveOrderBean createFromParcel(Parcel source) {
            return new ReserveOrderBean(source);
        }

        @Override
        public ReserveOrderBean[] newArray(int size) {
            return new ReserveOrderBean[size];
        }
    };
}
