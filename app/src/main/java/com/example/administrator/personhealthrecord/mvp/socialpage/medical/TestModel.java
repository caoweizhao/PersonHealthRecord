package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.MedicineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class TestModel extends AMedicalModel {
    public TestModel(AMedicalPresenter presenter) {
        super(presenter);
    }

    @Override
    public void getMedicineInfos() {
        
        Log.d("TestModel","getMedicineInfos");
        
        List<MedicineBean> medicineInfos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MedicineBean medicineInfo = new MedicineBean();
            medicineInfo.setFunction("本品为耳鼻喉科及皮肤科用药类非处方药药品");
            medicineInfo.setName("氯雷他定片");
            medicineInfo.setImageUrl("http://img07.tooopen.com/images/20170323/tooopen_sy_202923944295.jpg");
            medicineInfos.add(medicineInfo);
        }

        mPresenter.onMedicineInfosReady(medicineInfos);
    }
}
