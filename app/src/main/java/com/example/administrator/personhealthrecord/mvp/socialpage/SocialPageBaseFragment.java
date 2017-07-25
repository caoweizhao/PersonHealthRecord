package com.example.administrator.personhealthrecord.mvp.socialpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-7-25.
 */

public abstract class SocialPagerBaseFragment extends Fragment {

    Unbinder mUnbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected abstract void fetchData();

    protected abstract void fetchDataDone();

    protected abstract void loadMoreData();

    protected abstract void loadMoreDataDone();

}
