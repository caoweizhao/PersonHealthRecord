package com.example.administrator.personhealthrecord.mvp.checkpage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class TestModel extends ACheckPageModel {
    public TestModel(ACheckPagePresenter presenter) {
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
    public void getCheckItems() {

    }
}
