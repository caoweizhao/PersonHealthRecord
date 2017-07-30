package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andy on 2017/7/27.
 */

public class PackageBean implements Parcelable{
//    {
//        "timestamp": 1501124419332,
//            "status": "success",
//            "message": "列表返回成功",
//            "collection": [
//        {
//            "id": 1,
//                "name": "入职体检套餐",
//                "summary": "一般身体，内科，外科，眼科，耳鼻喉，抽血",
//                "packageDetail": "一般情况：身高、体重、体重指数、血压、心率。 内 科：心脏、肺部、腹部、肝、脾、神经系统。 外 科：四肢脊柱、皮肤、甲状腺、浅表淋巴结。 眼 科：视力、色觉、裂隙灯检查、眼睑、结膜等。 耳 鼻 喉：耳镜检查外耳道、鼓膜等、前鼻镜检查鼻中膈、嗅觉、听力测定。 　　彩 超：肝、胆、脾、胰、双肾。 　　心 电 图：12导同步心电图检查。 　　数字化摄片： 胸部正位片。 　　尿 常 规：尿比重、隐血、细胞、蛋白、尿糖等。 抽 血：谷丙转氨酶、谷草转氨酶、肾功、血糖、血常规 　　主 检：体检结果汇总及建议、健康档案。",
//                "packagePrice": 236,
//                "allocatedQuantity": 0,
//                "imageUrl": "package_3.png"
//        }
//        ]
//    }

    private int id;
    private String name;
    private String summary;
    private String packageDetail;
    private double packagePrice;
    private int allocatedQuantity;
    private String imageUrl;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPackageDetail() {
        return packageDetail;
    }

    public void setPackageDetail(String packageDetail) {
        this.packageDetail = packageDetail;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public int getAllocatedQuantity() {
        return allocatedQuantity;
    }

    public void setAllocatedQuantity(int allocatedQuantity) {
        this.allocatedQuantity = allocatedQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(summary);
        dest.writeString(packageDetail);
        dest.writeString(imageUrl);
        dest.writeInt(allocatedQuantity);
        dest.writeDouble(packagePrice);
    }
    public static final Parcelable.Creator<PackageBean> CREATOR= new Creator<PackageBean>() {
        @Override
        public PackageBean createFromParcel(Parcel source) {
            return new PackageBean(source);
        }

        @Override
        public PackageBean[] newArray(int size) {
            return new PackageBean[size];
        }
    };
    private PackageBean(Parcel in)
    {
        id=in.readInt();
        name=in.readString();
        summary=in.readString();
        packageDetail=in.readString();
        imageUrl=in.readString();
        allocatedQuantity=in.readInt();
        packagePrice=in.readDouble();
    }
}
