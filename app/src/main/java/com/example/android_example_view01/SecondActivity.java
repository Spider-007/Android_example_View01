package com.example.android_example_view01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Button mBtn;
    private EditText mEditText;
    private RecyclerView mRecyclerView;
    private List<Msg> mList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.second_layout);
        initView();
        initData();
    }


    public void initView() {
        mBtn = findViewById(R.id.send);
        mEditText = findViewById(R.id.edit_Text);
        mRecyclerView = findViewById(R.id.rv_Address);
    }

    public void initData() {
        Msg msg = new Msg();
        msg.context = "One";
        msg.type = Msg.RECEIVER;
        mList.add(msg);

        Msg msg2 = new Msg();
        msg2.context = "10月1日 happy";
        msg2.type = Msg.SEND;
        mList.add(msg2);

        Msg msg3 = new Msg();
        msg3.context = "Two happy";
        msg3.type = Msg.RECEIVER;
        mList.add(msg3);

        Msg msg4 = new Msg();
        msg4.context = "error???";
        msg4.type = Msg.SEND;
        mList.add(msg4);

        initRv();
    }

    private void initRv() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new RvAdapter(mList));
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg mMsg = new Msg();
                mMsg.context = mEditText.getText().toString().trim();
                mMsg.type = Msg.SEND;
                mList.add(mMsg);

                RvAdapter rvAdapter1 = new RvAdapter(mList);
                mRecyclerView.setAdapter(rvAdapter1);
                rvAdapter1.notifyItemChanged(mList.size() - 1);
                mRecyclerView.scrollToPosition(mList.size() - 1);
                mEditText.setText("");
            }
        });

    }


    class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {


        private List<Msg> list;

        @NonNull
        @Override
        public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, parent, false);
            return new RvViewHolder(view);
        }

        public RvAdapter(List<Msg> mlist) {
            this.list = mlist;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
            Msg msg = list.get(position);
            if (msg.type == Msg.RECEIVER) {
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftTv.setText(msg.context);
            } else if (msg.type == Msg.SEND) {
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightTv.setText(msg.context);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class RvViewHolder extends RecyclerView.ViewHolder {

            TextView leftTv, rightTv;
            LinearLayout leftLayout, rightLayout;

            public RvViewHolder(@NonNull View itemView) {
                super(itemView);
                leftTv = itemView.findViewById(R.id.left_Tv);
                rightTv = itemView.findViewById(R.id.right_Tv);
                leftLayout = itemView.findViewById(R.id.leftLayout);
                rightLayout = itemView.findViewById(R.id.rightLayout);
            }
        }
    }
}
