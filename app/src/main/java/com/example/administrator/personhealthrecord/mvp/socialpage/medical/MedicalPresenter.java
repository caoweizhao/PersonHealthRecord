package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import com.example.administrator.personhealthrecord.bean.MedicineBean;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class MedicalPresenter extends AMedicalPresenter {

    @Override
    public AMedicalModel createModel() {
        // TODO: 2017-7-19
        //return new CheckPageModel(this);
        return new TestModel(this);
    }

    @Override
    public void onMedicineInfosReady(List<MedicineBean> medicineInfos) {
        mView.dismissLoading();
        mView.updateMedicines(medicineInfos);
    }

    @Override
    public void onRequestData() {
        mView.showLoading();
        mModel.getMedicineInfos();
    }


}
