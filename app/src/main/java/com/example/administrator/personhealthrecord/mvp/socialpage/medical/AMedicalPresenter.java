package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import com.example.administrator.personhealthrecord.bean.MedicineBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AMedicalPresenter extends BasePresenter<AMedicalFragment, AMedicalModel> {

    /**
     * 药品推荐数据请求完毕
     *
     */
    public abstract void onMedicineInfosReady(List<MedicineBean> medicineInfos);

    /**
     * 请求所有数据
     */
    public abstract void onRequestData();
}
