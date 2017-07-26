package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import com.example.administrator.personhealthrecord.bean.MedicineBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.medical.api.MedicalService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017-7-19.
 */

public class MedicalModel extends AMedicalModel {

    private MedicalService mMedicalService;

    public MedicalModel(AMedicalPresenter presenter) {
        super(presenter);
        mMedicalService = RetrofitUtil.getRetrofit().create(MedicalService.class);
    }

    @Override
    public void getMedicineInfos() {
        mMedicalService.getMedicineInfos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MedicineBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MedicineBean> value) {
                        mPresenter.onMedicineInfosReady(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
