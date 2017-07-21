package com.example.administrator.personhealthrecord.mvp.homepage;

import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AHomePagePresenter extends BasePresenter<AHomePageFragment, AHomePageModel> {

    /**
     * 图片轮播数据请求完毕
     * @param urls
     */
    public abstract void onImagesReady(List<String> urls);

    /**
     * 专家轮播数据请求完毕
     * @param expertBeanList
     */
    public abstract void onExpertsReady(List<ExpertBean> expertBeanList);

    /**
     * 医院推荐数据请求完毕
     * @param hospitalBeanList
     */
    public abstract void onHospitalReady(List<HospitalBean> hospitalBeanList);

    /**
     * 请求所有数据（图片，医生，医院）
     */
    public abstract void onRequestData();

    /**
     * 医生分类选择，进行相应数据请求
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public abstract void onSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id);
}
