package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class TestModel extends AHomePageModel {
    public TestModel(AHomePagePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getImageRes() {
        List images = new ArrayList();
        images.add("http://img06.tooopen.com/images/20160921/tooopen_sy_179583447187.jpg");
        images.add("http://pics.sc.chinaz.com/files/pic/pic9/201508/apic14052.jpg");
        images.add("http://img02.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg");
        images.add("http://pic.sc.chinaz.com/files/pic/pic9/201208/xpic6813.jpg");
        List titles = new ArrayList();
        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title3");
        titles.add("Title4");
        mPresenter.onImagesReady(images);
    }

    @Override
    public void getExperts(@Contract.ExpertType int type) {
        List images = new ArrayList();
        images.add("http://img06.tooopen.com/images/20160921/tooopen_sy_179583447187.jpg");
        images.add("http://pics.sc.chinaz.com/files/pic/pic9/201508/apic14052.jpg");
        images.add("http://img02.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg");
        images.add("http://pic.sc.chinaz.com/files/pic/pic9/201208/xpic6813.jpg");
        List titles = new ArrayList();
        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title3");
        titles.add("Title4");
        mPresenter.onExpertsReady(images);
    }

    @Override
    public void getHospitals() {
        List<HospitalBean> hospitalBeens = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HospitalBean hospitalBean = new HospitalBean();
            hospitalBean.setName("中山大学第三附属医院（天河院区）");
            hospitalBean.setAddress("广州市天河区天河路600号");
            hospitalBean.setClazz("一级甲等");
            hospitalBeens.add(hospitalBean);
        }
        mPresenter.onHospitalReady(hospitalBeens);
    }
}
