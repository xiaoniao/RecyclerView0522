package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.SectionZeroAdapter;

import java.util.ArrayList;

/**
 * 只加载一次
 * Created by hesk on 25/2/16.
 */
public class FirstPageCancelLoadMore extends BaseActivity {

    private SectionZeroAdapter simpleRecyclerViewAdapter = null;
    private Handler time_count = new Handler();

    @Override
    protected void enableEmptyViewPolicy() {
        ultimateRecyclerView.setEmptyView(R.layout.empty_view, UltimateRecyclerView.EMPTY_KEEP_HEADER_AND_LOARMORE);
    }

    // 加载更多
    @Override
    protected void onLoadmore() {
        Log.d("uuuu", "----------onLoadmore---------");
        time_count.postDelayed(new Runnable() {
            @Override
            public void run() {
                SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 2);
                // 取消加载更多
                ultimateRecyclerView.disableLoadmore();
            }
        }, 1000);
    }

    @Override
    protected void onFireRefresh() {
        ultimateRecyclerView.setRefreshing(false);
    }

    @Override
    protected void doURV(UltimateRecyclerView urv) {
        ultimateRecyclerView.setHasFixedSize(false);
        simpleRecyclerViewAdapter = new SectionZeroAdapter(new ArrayList<String>());
        configLinearLayoutManager(ultimateRecyclerView);

        enableEmptyViewPolicy();
        enableLoadMore();
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ff4fcccf"));
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
    }

    @Override
    protected void addButtonTrigger() {
        SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 3);
        ultimateRecyclerView.reenableLoadmore();
    }

    @Override
    protected void removeButtonTrigger() {
        simpleRecyclerViewAdapter.removeAll();
    }

    @Override
    protected void toggleButtonTrigger() {
        if (!status_progress) {
            isEnableAutoLoadMore = !isEnableAutoLoadMore;
            if (isEnableAutoLoadMore) {
                ultimateRecyclerView.reenableLoadmore();
            }
        }
    }
}
