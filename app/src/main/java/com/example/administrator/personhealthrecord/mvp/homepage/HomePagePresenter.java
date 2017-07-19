package com.example.administrator.personhealthrecord.mvp.homepage;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HomePagePresenter extends AHomePagePresenter {
    @Override
    public AHomePageModel createModel() {
        return new HomePageModel(this);
    }

    @Override
    public void onImagesReady(List<String> urls) {

    }
}
