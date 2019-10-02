package com.example.android_example_view01;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        Log.e("YJH", "onCreate->"+getClass().getSimpleName());
    }

    public abstract int layoutId();

    public abstract void initView();


    public abstract void initData();

}
