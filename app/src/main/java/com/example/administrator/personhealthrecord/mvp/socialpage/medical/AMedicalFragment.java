package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import com.example.administrator.personhealthrecord.bean.MedicineInfo;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AMedicalFragment extends MvpFragment<AMedicalPresenter> {

    public abstract void updateMedicines(List<MedicineInfo> medicineInfoList);

}
