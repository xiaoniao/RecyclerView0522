package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.SectionZeroAdapter;
import com.marshalchen.ultimaterecyclerview.ui.emptyview.EmptyViewOnShownListener;

import java.util.ArrayList;

/**
 * Created by hesk on 25/2/16.
 */
public class FinalEmptyViewDisplayActivity extends BaseActivity implements EmptyViewOnShownListener {

    private SectionZeroAdapter simpleRecyclerViewAdapter = null;
    private Handler handler = new Handler();
    private int time = 0;

    @Override
    protected void onLoadmore() {
        Log.d("uuuu", "-----------load more-----------");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 5);
            }
        }, 1000);
    }

    @Override
    protected void onFireRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ultimateRecyclerView.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    protected void enableEmptyViewPolicy() {
        ultimateRecyclerView.setEmptyView(R.layout.empty_view_v2, UltimateRecyclerView.EMPTY_CLEAR_ALL, this);
    }

    @Override
    protected void doURV(UltimateRecyclerView urv) {
        ultimateRecyclerView.setHasFixedSize(false);
        simpleRecyclerViewAdapter = new SectionZeroAdapter(new ArrayList<String>());
        configLinearLayoutManager(ultimateRecyclerView);

        enableEmptyViewPolicy();
        enableLoadMore();
        enableRefresh();
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
        ultimateRecyclerView.showEmptyView();
    }

    @Override
    protected void toggleButtonTrigger() {
        simpleRecyclerViewAdapter.removeAll();
        ultimateRecyclerView.showEmptyView();
        ultimateRecyclerView.disableLoadmore();
    }

    @Override
    public void onEmptyViewShow(View mView) {
        TextView tv = (TextView) mView.findViewById(R.id.exp_section_title);
        if (tv != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Your pressed at \"");
            sb.append(time);
            sb.append("\" and that is not found.");
            tv.setText(sb.toString());
        }
        time++;
    }
}
