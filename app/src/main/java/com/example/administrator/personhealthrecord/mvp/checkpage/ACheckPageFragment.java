package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class ACheckPageFragment extends MvpFragment<ACheckPagePresenter> {

    public abstract void updateImages(List<String> urls);

    public abstract void updateCheckItems(List<CheckBean> checkBeanList);
}
