package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.AbstractResulUitl;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017-7-19.
 */

public class TestModel extends ACheckPageModel {
    public TestModel(ACheckPagePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getImageRes(Observer<AbstractResulUitl<ImageBean>> observer) {
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
    public void getCheckItems() {
        List<CheckBean> list=new ArrayList<>();
         int i;
                 for(i=0;i<5;i++)
                 {
                     CheckBean bean=new CheckBean();
                     bean.setTitle("项目"+i);
                     bean.setSummary("该体检简单描述"+i);
                     bean.setImageUrl("http://pic.sc.chinaz.com/files/pic/pic9/201208/xpic6813.jpg");
                     bean.setReverations(i);
                     list.add(bean);
                 }
                 mPresenter.onDataReady(list);
    }
}
