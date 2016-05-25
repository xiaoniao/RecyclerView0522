package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.graphics.Color;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.sectionZeroAdapter;
import com.marshalchen.ultimaterecyclerview.ui.swipe.SwipeableRecyclerViewTouchListener;
import com.marshalchen.ultimaterecyclerview.ui.swipe.defaultRegularSwipe;

import java.util.ArrayList;

/**
 * Created by hesk on 7/1/2015.
 */
public class DebugLoadMoreActivity extends BasicFunctions {

    private sectionZeroAdapter simpleRecyclerViewAdapter = null;

    @Override
    protected void enableEmptyViewPolicy() {
        ultimateRecyclerView.setEmptyView(R.layout.empty_view, UltimateRecyclerView.EMPTY_SHOW_LOADMORE_ONLY);
    }

    @Override
    protected void onLoadmore() {
        SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 2);
    }

    @Override
    protected void enableSwipe() {
        super.enableSwipe();
        ultimateRecyclerView.addOnItemTouchListener(new SwipeableRecyclerViewTouchListener(ultimateRecyclerView.mRecyclerView, new defaultRegularSwipe<>(simpleRecyclerViewAdapter)));
    }

    @Override
    protected void addButtonTrigger() {
        simpleRecyclerViewAdapter.insertFirst("rand added item");
        ultimateRecyclerView.reenableLoadmore();
    }

    @Override
    protected void removeButtonTrigger() {
        simpleRecyclerViewAdapter.removeLast();
    }

    @Override
    protected void onFireRefresh() {
        ultimateRecyclerView.setRefreshing(false);
        linearLayoutManager.scrollToPosition(0);
        simpleRecyclerViewAdapter.removeAll();
        ultimateRecyclerView.disableLoadmore();
    }

    @Override
    protected void doURV(UltimateRecyclerView ultimateRecyclerView) {
        ultimateRecyclerView.setHasFixedSize(false);
        simpleRecyclerViewAdapter = new sectionZeroAdapter(new ArrayList<String>());
        configLinearLayoutManager(ultimateRecyclerView);
        enableParallaxHeader();
        enableEmptyViewPolicy();
        enableLoadMore();
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ffff66ff"));
        enableRefresh();
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
    }

    private void toggleSelection(int position) {
        simpleRecyclerViewAdapter.toggleSelection(position);
        actionMode.setTitle("Selected " + "1");
    }
}
