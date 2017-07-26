package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017-7-19.
 */

public class NewsBean extends DataSupport implements Parcelable,AbstractItem,Comparable<NewsBean>{
    private static final String TAG="NewsBean";
    /**
     * id : 1
     * title : 习近平：加快建设开放型经济新体制
     * summary : 央视网消息（新闻联播）：中共中央总书记、国家主席、中央军委主席、中央财经领导小组组长习近平7月17日下午主持召开中央财经领导小组第十六次会议，研究改善投资和市场环境、扩大对外开放问题。习近平发表重要讲话强调，要改善投资和市场环境，加快对外开放步伐，降低市场运行成本，营造稳定公平透明、可预期的营商环境，加快建设开放型经济新体制，推动我国经济持续健康发展。
     * content : 中共中央政治局常委、国务院总理、中央财经领导小组副组长李克强，中共中央政治局常委、国务院副总理、中央财经领导小组成员张高丽出席会议。 　　会议分别听取了国家发展改革委关于对外商投资实行准入前国民待遇加负面清单管理模式、商务部关于推动贸易平衡和中美双边投资有关问题、人民银行关于完善人民币汇率形成机制和扩大金融业对外开放的汇报。领导小组成员进行了讨论。 　　习近平在讲话中指出，我们提出建设开放型经济新体制，一个重要目的就是通过开放促进我们自身加快制度建设、法规建设，改善营商环境和创新环境，降低市场运行成本，提高运行效率，提升国际竞争力。外商投资推动了资源合理配置，促进了市场化改革，对我国经济发展发挥了重要作用。推进供给侧结构性改革，实现经济向更高形态发展，跟上全球科技进步步伐，都要继续利用好外资。要加快放开育幼养老、建筑设计、会计审计、商贸物流、电子商务，以及一般制造业和服务业等竞争性领域对外资准入限制和股比限制。要尽快在全国推行自由贸易试验区试行过的外商投资负面清单。 　　习近平强调，要加快统一内外资法律法规，制定新的外资基础性法律。要清理涉及外资的法律、法规、规章和政策文件，凡是同国家对外开放大方向和大原则不符的法律法规或条款，要限期废止或修订。外资企业准入后按照公司法依法经营，要做到法律上平等、政策上一致，实行国民待遇。北京、上海、广州、深圳等特大城市要率先加大营商环境改革力度。要清理并减少各类检查和罚款，建立涉企收费目录清单制度，严禁越权收费、超标准收费、自设收费项目、重复收费，杜绝中介机构利用政府影响违规收费，行业协会不得强制企业入会或违规收费。 　　习近平指出，产权保护特别是知识产权保护是塑造良好营商环境的重要方面。要完善知识产权保护相关法律法规，提高知识产权审查质量和审查效率。要加快新兴领域和业态知识产权保护制度建设。要加大知识产权侵权违法行为惩治力度，让侵权者付出沉重代价。要调动拥有知识产权的自然人和法人的积极性和主动性，提升产权意识，自觉运用法律武器依法维权。 　　习近平强调，扩大金融业对外开放是我国对外开放的重要方面。要合理安排开放顺序，对有利于保护消费者权益、有利于增强金融有序竞争、有利于防范金融风险的领域要加快推进。要有序推进资本项目开放，稳步推动人民币国际化，继续完善人民币汇率形成机制，保持人民币汇率在合理均衡水平上的基本稳定。 　　习近平指出，扩大金融业对外开放，金融监管能力必须跟得上，在加强监管中不断提高开放水平。要结合我国实际，学习和借鉴国际上成熟的金融监管做法，补齐制度短板，完善资本监管、行为监管、功能监管方式，确保监管能力和对外开放水平相适应。 　　习近平强调，要在稳定出口市场的同时主动扩大进口，促进经常项目收支平衡。要改善贸易自由化便利化条件，切实解决进口环节制度性成本高、检验检疫和通关流程繁琐、企业投诉无门等突出问题。要研究降低有些消费品的关税，鼓励特色优势产品进口。要创造公平竞争的国内市场环境，在关税、进口检验、市场营销等方面创造机会平等的条件，让消费者自主选择，让市场发挥作用。 　　习近平指出，要加强统一领导，做好统筹协调和落实工作。各地区各部门要把思想和行动统一到党中央关于扩大对外开放的决策部署上来，落实扩大开放各项措施。要更好发挥地方在扩大开放方面的积极作用，发挥好自由贸易试验区对外开放先行一步的改革创新作用。 　　中央财经领导小组成员出席，中央和国家有关部门负责同志列席会议。
     * category : 经济
     * time : 1500362595000
     * imageUrl : information_1.png
     */

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("summary")
    private String summary;
    @SerializedName("content")
    private String content;
    @SerializedName("category")
    private String category;
    @SerializedName("time")
    private long time;
    @SerializedName("imageUrl")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public long getTime() {
        return time;
    }

    public void setCategory(String category) {
        this.category = category;
    }
     @Override
    public String getdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    public NewsBean()
    {

    }
    public void setTime(long time) {
        this.time = time;
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
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(imageUrl);
        dest.writeLong(time);
    }

    public static final Parcelable.Creator<NewsBean> CREATOR= new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel source) {
            return new NewsBean(source);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    private NewsBean(Parcel in)
    {
        title=in.readString();
        content=in.readString();
        imageUrl=in.readString();
        time=in.readLong();
    }

    @Override
    public boolean equals(Object obj) {
        Log.d(TAG, "equals: "+((NewsBean)obj).getTime()+"  "+getTime()+"  "+(getTime()==((NewsBean)obj).getTime()));
        return getTime()==((NewsBean)obj).getTime();
    }

    @Override
    public int compareTo(@NonNull NewsBean o) {
        Date date1=new Date(getTime());
        Date date2=new Date(o.getTime());
        if(date1.after(date2))
            return -1;
        else
            return 1;
    }
}
