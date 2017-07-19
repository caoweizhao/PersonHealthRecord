package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.mvp.homepage.api.HomePageService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HomePageModel extends AHomePageModel {

    public HomePageModel(AHomePagePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getImageRes() {
        HomePageService imageService = RetrofitUtil.getRetrofit().create(HomePageService.class);
        imageService.getImagesUrl()
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getExperts() {

    }
}
