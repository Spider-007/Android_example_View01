package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentTitle extends Fragment {

    View view;
    private boolean isDoublePage;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_Context);
        initRv();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isDoublePage = true;
        } else {
            isDoublePage = false;
        }
    }

    private void initRv() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new RvAdapter(getViews()));
    }

    private List<News> getViews() {

        List<News> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.title = "倒计时" + i + "s";
            news.content = randomString(("今日热点是" + i));
            mList.add(news);
        }
        return mList;

    }

    private String randomString(String randomTodo) {
        Random random = new Random();
        int randomRegion = random.nextInt(20) + 1;
        StringBuffer mStringBuffer = new StringBuffer();
        //需要循环内容数据
        for (int i = 0; i < randomRegion; i++) {
            mStringBuffer.append(randomTodo);
        }
        return mStringBuffer.toString();
    }

    class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyRvViewHolder> {

        private List<News> mList;

        public RvAdapter(List<News> mList) {
            this.mList = mList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);

            return new MyRvViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyRvViewHolder holder, final int position) {
            News news = mList.get(position);
            holder.mTv.setText(news.title);
            //对单页 还是 双页进行判断
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mList.get(position);
                    if (isDoublePage) {
                        FragmentContent mFragmentContent = (FragmentContent) getFragmentManager().findFragmentById(R.id.new_content_fragment);
                        mFragmentContent.refresh(news.title, news.content);
                    } else {
                        new NewTitleActivity().actionStartActivity(getActivity(), news.title, news.content);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyRvViewHolder extends RecyclerView.ViewHolder {
            TextView mTv;

            public MyRvViewHolder(@NonNull View itemView) {
                super(itemView);
                mTv = itemView.findViewById(R.id.context_TV);
            }
        }
    }

}
