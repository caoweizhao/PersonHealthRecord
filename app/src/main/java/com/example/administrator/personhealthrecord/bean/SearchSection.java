package com.example.administrator.personhealthrecord.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2017-8-7.
 */

public class SearchSection extends SectionEntity<SearchBean> {

    public SearchSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SearchSection(SearchBean bean) {
        super(bean);
    }
}
