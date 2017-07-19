package com.example.administrator.personhealthrecord.mvp.testFragment;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;
import com.example.administrator.personhealthrecord.mvp.testFragment.apidemo.NewService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017-7-19.
 */

public class BlankModel extends BaseModel<BlankPresenter> {

    Retrofit mRetrofit;
    private final NewService mNewService;

    public BlankModel(BlankPresenter presenter) {
        super(presenter);
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.191.1:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mNewService = mRetrofit.create(NewService.class);
    }

    public void doGet() {
        mNewService.getNews()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<NewsBean>, ObservableSource<NewsBean>>() {
                    @Override
                    public ObservableSource<NewsBean> apply(List<NewsBean> been) throws Exception {
                        return Observable.fromIterable(been);
                    }
                })
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean value) {
                        Log.d("BlankModel", "\n");
                        Log.d("BlankModel", "title:" + value.getTitle());
                        Log.d("BlankModel", "content:" + value.getContent());
                        Log.d("BlankModel", "time:" + value.getTime());
                        Log.d("BlankModel", "url:" + value.getImageUrl());
                        Log.d("BlankModel", "summary:" + value.getSummary());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("BlankModel", "error:" + e);
                    }

                    @Override
                    public void onComplete() {
                        mPresenter.done();
                    }
                });
       /* Call<List<NewsBean>> call = mNewService.getNews();
        call.enqueue(new Callback<List<NewsBean>>() {
            @Override
            public void onResponse(Call<List<NewsBean>> call, Response<List<NewsBean>> response) {
                Log.d("BlankModel", "result:" + response.);
            }

            @Override
            public void onFailure(Call<List<NewsBean>> call, Throwable t) {
                Log.d("BlankModel","failure");
            }
        });*/
    }
}
