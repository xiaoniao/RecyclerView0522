package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.graphics.Color;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.SectionZeroAdapter;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.ui.swipe.SwipeableRecyclerViewTouchListener;
import com.marshalchen.ultimaterecyclerview.ui.swipe.defaultRegularSwipe;

import java.util.ArrayList;

/**
 * 自动加载
 * Created by hesk on 7/1/2015.
 */
public class LoadMoreActivity extends BaseActivity {

    private SectionZeroAdapter simpleRecyclerViewAdapter = null;

    // 初始化 RecyclerView
    @Override
    protected void doURV(UltimateRecyclerView ultimateRecyclerView) {
        ultimateRecyclerView.setHasFixedSize(false);
        simpleRecyclerViewAdapter = new SectionZeroAdapter(new ArrayList<String>());
        configLinearLayoutManager(ultimateRecyclerView);
        enableEmptyViewPolicy();
        enableLoadMore();
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ffff66ff"));
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
    }

    // 设置空布局
    @Override
    protected void enableEmptyViewPolicy() {
        // TODO 没数据时空布局老是显示不出来，这是一个问题
        ultimateRecyclerView.setEmptyView(R.layout.empty_view, UltimateRecyclerView.EMPTY_KEEP_HEADER);
    }

    // 下拉刷新
    @Override
    protected void onFireRefresh() {

    }

    // 加载更多
    @Override
    protected void onLoadmore() {
        SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 2);
    }

    // 从头部增加数据
    @Override
    protected void addButtonTrigger() {
        simpleRecyclerViewAdapter.insertFirst("rand added item");
        ultimateRecyclerView.reenableLoadmore();
    }

    // 从底部删除数据
    @Override
    protected void removeButtonTrigger() {
        simpleRecyclerViewAdapter.removeLast();
    }

   
}
