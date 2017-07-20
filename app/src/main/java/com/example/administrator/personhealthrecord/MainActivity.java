package com.example.administrator.personhealthrecord;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.personhealthrecord.mvp.healthynews.HealthyNewsFragement;
import com.example.administrator.personhealthrecord.mvp.testFragment.BlankFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        BlankFragment blankFragment = BlankFragment.newInstance("", "");
        HealthyNewsFragement fragement=new HealthyNewsFragement();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragement)
                    .commit();
        }

        /*SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        systemBarTintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        //systemBarTintManager.setNavigationBarTintEnabled(true);
        systemBarTintManager.setNavigationBarTintColor(R.color.colorPrimary);*/
    }


}
