package com.example.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewTitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_content);
        initView();
    }

    private void initView() {
        String new_title = getIntent().getStringExtra("new_title");
        String new_content = getIntent().getStringExtra("new_Content");
        FragmentContent mFragmentContent = (FragmentContent) getSupportFragmentManager().findFragmentById(R.id.new_content_fragment);
        mFragmentContent.refresh(new_title, new_content);
    }

    public void actionStartActivity(Context context,String new_Title, String new_Context) {
        Intent intent = new Intent(context, NewTitleActivity.class);
        intent.putExtra("new_title", new_Title);
        intent.putExtra("new_Content", new_Context);
        context.startActivity(intent);
    }
}
