package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import com.example.administrator.personhealthrecord.bean.MedicineBean;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AMedicalFragment extends MvpFragment<AMedicalPresenter> {

    public abstract void updateMedicines(List<MedicineBean> medicineInfoList);

}
