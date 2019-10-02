package com.example.android_example_view01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private Button mButton;
    private Button mToButton;
    private TitleLayout mTitleLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        mProgressBar
                = findViewById(R.id.activity_progress);
        mButton =
                findViewById(R.id.click);
        mToButton =
                findViewById(R.id.Toclick);
        mTitleLayout =
                findViewById(R.id.widget_title);
        mButton.setOnClickListener(this);
        mToButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click:
                int mProgress = mProgressBar.getProgress();
                if (mProgress == 100) {
                    mProgress -= 10;
                } else {
                    mProgress += 10;
                }
                mProgressBar.setProgress(mProgress);
                break;
            case R.id.Toclick:
                AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("AlertDialog").setMessage("Are You app is Running?");
                mAlertDialog.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                    }
                }).show();
                break;
            default:
                break;
        }
    }
}
