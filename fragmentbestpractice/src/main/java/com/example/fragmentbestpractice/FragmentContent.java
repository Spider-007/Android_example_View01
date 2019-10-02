package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentContent extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    public void refresh(String newTitle, String newContent) {
        View view1 = view.findViewById(R.id.visible_Layout);
        view1.setVisibility(View.VISIBLE);
        TextView text_Title = view.findViewById(R.id.text_Title);
        TextView text_Context = view.findViewById(R.id.text_Content);
        text_Title.setText(newTitle);
        text_Context.setText(newContent);
    }

}
