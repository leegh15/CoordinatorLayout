package com.lgh.coordinatorlayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.coordinatorlayout.R;
import com.lgh.coordinatorlayout.adapter.BaseRecyclerAdapter;
import com.lgh.coordinatorlayout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Arrays;
import java.util.Collection;

import butterknife.BindView;

import static android.R.layout.simple_list_item_2;

/**
 * Created by liguohui on 2019/11/29 17:20
 */
public class RecommendFragment extends Fragment {


    RecyclerView mRecyclerView;
    SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<Void> mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        refreshLayout=view.findViewById(R.id.refreshLayout);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<Void>(initData(), simple_list_item_2) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Void model, int position) {
                holder.text(android.R.id.text1, "数据" + position);
                holder.text(android.R.id.text2, "测试数据" + position);
                holder.textColorId(android.R.id.text2, R.color.colorPrimary);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.refresh(initData());
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                if (mAdapter.getItemCount() < 100) {
                    refreshLayout.getLayout().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.loadMore(initData());
                            if (mAdapter.getItemCount() > 60) {
                                Toast.makeText(getContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                            } else {
                                refreshLayout.finishLoadMore();
                            }
                        }
                    }, 2000);
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });
    }

    private Collection<Void> initData() {
        return Arrays.asList(null, null, null, null, null, null, null, null, null, null);
    }


}
