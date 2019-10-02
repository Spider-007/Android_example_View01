package com.example.android_example_view01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentLayoutE extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView();
    }

    public int layoutId() {
        return R.layout.activity_framelayout;
    }

    public void initView() {
        mButton = findViewById(R.id.btn_Fragment);
        mButton.setOnClickListener(this);
        repleaseFragment(new FragmentLeft());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Fragment:
                repleaseFragment(new FragmentRight());
                break;
            default:
                break;
        }
    }

    private void repleaseFragment(Fragment mFragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.mFrameLayout, mFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
