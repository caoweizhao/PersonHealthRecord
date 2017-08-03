package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017-7-31.
 */

public class ImmuneBean extends DataSupport implements AbstractItem, Parcelable {


    /**
     * id : 1
     * name : 乙肝疫苗
     * summary : 乙肝疫苗是用于预防乙肝的特殊药物。疫苗接种后，可刺激免疫系统产生保护性抗体，这种抗体存在于人的体液之中，乙肝病毒一旦出现，抗体会立即作用，将其清除，阻止感染，并不会伤害肝脏，从而使人体具有了预防乙肝的免疫力，达到预防乙肝感染的目的。 接种乙肝疫苗是预防乙肝病毒感染的最有效方法。
     * function : 中国大多数乙肝病毒携带者来源于新生儿及儿童期的感染。由此可见，新生儿的预防尤为重要，所有新生儿都应当接种乙肝疫苗。这是因为新生儿对乙肝病毒最没有免疫力，而且免疫功能尚不健全，一旦受染，很难清除病毒而成为乙肝病毒携带者。其次，学龄前儿童也应进行接种。第三是HBsAg阳性者的配偶及其他从事有感染乙肝危险职业的人，如密切接触血液的人员、医护人员、血液透析患者等。第四是意外暴露于乙肝病毒的人，如意外地被HBsAg阳性血液污染的针头刺伤，或被HBsAg阳性血液溅于眼结膜或口腔黏膜，输入HBsAg阳性的血液等，均应接种乙肝疫苗。 乙型肝炎疫苗全程接种共3 针，按照0、1、6 个月程序，即接种第1 针疫苗后，间隔1 及6 个月注射第2 及第3 针疫苗。新生儿接种乙型肝炎疫苗越早越好，要求在出生后24 h 内接种。新生儿的接种部位为大腿前部外侧肌肉内，儿童和成人为上臂三角肌中部肌肉内注射。单用乙型肝炎疫苗阻断母婴传播的保护率为87.8%。 （1）如果新生儿的父母均没有乙肝，该新生儿在出生后应尽快（24小时内）给予基因工程乙肝疫苗1支肌肉注射，注射部位新生儿为大腿前部外侧肌肉1个月后，再打1支，6个月后再打1支，一共3针，此方案称为0、1、6方案；儿童和成人打疫苗前需先进行化验，如果乙肝三系统检查均为阴性，转氨酶正常，可以按0、1、6方案进行乙肝疫苗接种（成人一般剂量加倍）。免疫成功率为90%以上，免疫成功的标志是乙肝表面抗体转为阳性，保护时间一般至少可持续12年，接种者可定期复查乙肝三系统，只要表面抗体依然存在，证明免疫能力依旧。 （2）对于母亲一方为单纯表面抗原阳性的新生儿，单用乙肝疫苗就可取得比较满意的效果，乙肝疫苗的使用方法依然是0、1、6方案，有报导认为第一针可打2支（10微克/l毫升）效果更好。 （3）对于母亲一方为乙肝病毒表面抗原和e抗原双阳性的新生儿最好是联合应用高效价的乙肝免疫球蛋白和乙肝疫苗。具体方法是新生儿采用注射2次高效价乙肝免疫球蛋白（出生后立即及出生后1个月各注射1支，每支200国际单位）及3次乙肝疫苗（每次10微克，生后2、3、5月各注射1次）；也有采取出生后立即注射1支高效价乙肝免疫球蛋白，及3次乙肝疫苗（每次15微克，生后立即及1月、6月各注射1次），2个方案保护的成功率都在90%以上。 （4）对于意外暴露于乙肝病毒的人，在意外接触 HBV 感染者的血液和体液后，可按照以下方法处理：a.血清学检测 应立即检测HBsAg、抗-HBs、ALT 等，并在3 和6 个月内复查。b.主动和被动免疫 如已接种过乙型肝炎疫苗，且已知抗-HBs ≥10 mIU/ml 者，可不进行特殊处理。如未接种过乙型肝炎疫苗，或虽接种过乙型肝炎疫苗，但抗-HBs <10 mIU/ml 或抗-HBs 水平不详，应立即注射HBIG 200~400 IU，并同时在不同部位接种一针乙型肝炎疫苗 （20μg），于1 和6 个月后分别接种第2 和第3 针乙型肝炎疫苗 （各20μg）。 （5）青少年是HBV的易感者，也可接种乙肝疫苗，也要按“0、1、6免疫方案”进行接种，即先注射第一针，一个月后注射第二针，6个月后注射第三针。成年人、老年人、孕妇都可以接种乙肝疫苗。只不过年龄越大，接种的成功率相对小一些。其实老年人接种乙肝疫苗的实际意义并不大，一是成功率较低，二是感染HBv的机率不大，或老年人可能早已感染HBv。但是，如果老年人确实是一位HBv易感者，家里和周围又有HBv感染者，和他们密切接触，接种乙肝疫苗还是有益的。成年人和老年人接种乙肝疫苗的剂量可增加一些，抗原剂量越大，免疫原性也越强，刺激免疫系统，更易于产生抗体。 乙肝疫苗的接种需视具体情况并遵医嘱进行，接种疫苗后一般反应轻微，少部分人可能出现低热、接种部位红肿、压痛等症状，一般均在1～2天内消失，乙肝疫苗使用的安全性有可靠的保证。从免疫效果看，大量研究表明，受种者的抗体阳转率在90%以上，接种疫苗后三年，保护率在80%以上；而且，只要疫苗合用方法适宜，疫苗HbsAg含量足够并稳定，疫苗免疫阻断母婴传播的效果也十分理想。
     * reason : 首先，新生儿接种乙肝疫苗是因为乙肝疫苗可以很好的预防乙肝病毒的感染，新生儿出生后成功接种乙肝疫苗就能够确保将来不会感染乙肝，因此，新生儿需要接种乙肝疫苗。 其次，乙肝若不有效的治疗就会向着肝硬化、肝癌的方向发展，因此说，有效的预防乙肝就是成功的预防肝硬化，预防肝癌的发生，是预防肝硬化肝癌的第一针。乙肝疫苗较便宜，每支三十几块钱，民众都能接受。 乙肝疫苗能预防乙肝的原理简单的说就是：乙肝疫苗其实就是制备乙肝病毒表面的某些有效蛋白，这些蛋白接种人体后，免疫细胞会产生“特异性武器”（抗体）来对抗乙肝病毒，而接种者本身不会被感染。当人体再次接触乙肝病毒的时候，这种早已存在于体内的“特异性武器”就会立即“开火”，清除病毒，抵御感染。
     * groupPeople : 处于HBV感染高度危险状态的易感者（未感染过HBV的人）应接种乙肝疫苗。主要包括： ①全部新生儿及幼儿园未接种过乙肝疫苗的孩子； ②传染科、口腔科、血液室、透析室和经常接触血液的工作人员； ③新加入某一群体的人员，如新入伍的战士、新入学的大学生； ④从事食品服务行业者及保育工作人员； ⑤发育障碍者，收容所中的患者和工作人员； ⑥血液透析患者； ⑦使用血液制品者； ⑧器官移植前的患者；需长期应用免疫抑制剂者； ⑨乙肝病毒携带者的家庭接触者； ⑩注射毒品成瘾者；长期教养机构中的犯人。
     * attention : 注射乙肝疫苗有哪些注意事项，在上臂三角肌肌内注射，每次一支。注射部位可能有红肿，疼痛，发热等反应。注意，使用时充分摇匀，有摇不散块状物等不得使用。 接种禁忌 乙肝疫苗可以和流脑疫苗、脊髓灰质疫苗、乙脑疫苗、麻疹疫苗同时接种。 患急性传染病或其他慢性疾病者不能接种乙肝疫苗患有皮炎、化脓性皮肤病、严重湿疹的小儿不宜乙肝疫苗接种，等待病愈后方可进行乙肝疫苗接种；患有严重心、肝、肾疾病和活动型结核病的小儿不宜乙肝疫苗接种；神经系统包括脑、发育不正常，有脑炎后遗症、癫痫病的小儿不宜乙肝疫苗接种；有腋下或淋巴结肿大的小儿不宜乙肝疫苗接种，应查明病因治愈后再乙肝疫苗接种；有哮喘、荨麻疹等过敏体质的小儿不宜乙肝疫苗接种；有血清病、支气管哮喘、过敏性荨麻疹及对青霉素、磺胺等一些药物过敏者，不宜乙肝疫苗接种。 发热、体温超过37.5℃应暂缓乙肝疫苗接种感冒、轻度低热等一般性疾病视情况可暂缓乙肝疫苗接种； 免疫缺陷或正接受免疫抑制药物治疗的，不宜乙肝疫苗接种。低体重、早产、剖腹产等非正常出生的新生儿。严重营养不良、严重佝偻病、先天性免疫缺陷的小儿不宜接种。 对乙肝疫苗成分过敏不宜接种乙肝疫苗；空腹饥饿时不宜预防接种。 妊娠期妇女不宜接种乙肝疫苗。 意外接触病毒者应该注意 （1）对未接种过疫苗的接触者，先注射乙肝免疫球蛋白（24小时内），同时在不同部位接种乙肝疫苗。 （2）如果接触者已接种过疫苗，但未经全程免疫，应在注射乙肝免疫球蛋白后按乙肝疫苗免疫程序补上全程免疫。 （3）接种过疫苗，并已产生乙肝表面抗体的接触者，应根据其抗体水平而定。如果乙肝表面抗体水平足够可不必处理；水平不够应加强注射1针疫苗；如果初次免疫无应答者应尽早注射乙肝免疫球蛋白和乙肝疫苗各1针。 抗体产生 有些人抗体产生较晚，被称为应答迟缓。对此可加注1～2针，第二针后有50%～70%可产生抗体左右，第三针后90%左右产生抗体；可采用0、1、2、12个月的免疫程序；在接种乙肝疫苗同时，合用小剂量的白细胞介素-2；卡介苗或牛痘苗能增加对乙肝疫苗的免疫应答，可配合使用。 而有的人注射后没有抗体，需要加大剂量注射，但是仍有部分人由于遗传的原因，在多次大剂量注射后仍是无法产生抗体，这类人群要避免和传染源密切接触，以防发生感染。 乙肝疫苗接种后产生的抗体水平随时间逐渐下降。一般接种疫苗，注射3针后1个月97%的人都可测到表面抗体；第2年仍保持在这一水平；第3年降到74%左右，抗体滴度也下降。是否需要再次接种疫苗，主要是要在测定乙肝表面抗体的滴度后，决定何时再打乙肝疫苗。乙肝表面抗体滴度小于或者等于10国际单位/毫升者，应在半年内接种。抗体滴度大于10国际单位/毫升可在6年内复种。中国的很多医学者建议免疫后3年内加强1次为好。 接种后不产生抗体 （1）更换疫苗。对接种血源性乙肝疫苗无应答者，可改用基因工程重组乙肝疫苗接种，使之成为低应答者；对接种无前S蛋白乙肝疫苗后无应答者，可改用有前S蛋白的乙肝疫苗。 （2）增加接种次数。有些人抗体产生较晚，称为应答迟缓。对此可加注1～2针，或者重新接种疫苗，并且适当增加剂量。可采用0、1、2、12个月的免疫程序。 （3）在接种乙肝疫苗同时，合用小剂量的白细胞介素-2。 （4）卡介苗或牛痘苗能增加对乙肝疫苗的免疫应答，可配合使用。 （5）改变接种途径。有人对肌肉注射疫苗后无应答者可改为皮内注射，每两周一次，直到迟发型变态反应呈阳性为止。
     * imageUrl : plan_1.png
     * time : 1501550157000
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("summary")
    private String summary;
    @SerializedName("function")
    private String function;
    @SerializedName("reason")
    private String reason;
    @SerializedName("groupPeople")
    private String groupPeople;
    @SerializedName("attention")
    private String attention;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("time")
    private long time;

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return (int) (((ImmuneBean) o).time - time);
    }

    // TODO: 2017-8-1
    @Override
    public boolean equals(Object o) {
        return ((ImmuneBean) o).id == id;
    }


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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getGroupPeople() {
        return groupPeople;
    }

    public void setGroupPeople(String groupPeople) {
        this.groupPeople = groupPeople;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.summary);
        dest.writeString(this.function);
        dest.writeString(this.reason);
        dest.writeString(this.groupPeople);
        dest.writeString(this.attention);
        dest.writeString(this.imageUrl);
        dest.writeLong(this.time);
    }

    public ImmuneBean() {
    }

    protected ImmuneBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.summary = in.readString();
        this.function = in.readString();
        this.reason = in.readString();
        this.groupPeople = in.readString();
        this.attention = in.readString();
        this.imageUrl = in.readString();
        this.time = in.readLong();
    }

    public static final Creator<ImmuneBean> CREATOR = new Creator<ImmuneBean>() {
        @Override
        public ImmuneBean createFromParcel(Parcel source) {
            return new ImmuneBean(source);
        }

        @Override
        public ImmuneBean[] newArray(int size) {
            return new ImmuneBean[size];
        }
    };
}
