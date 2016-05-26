package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.graphics.Color;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.SectionZeroAdapter;

import java.util.ArrayList;

/**
 * Created by hesk on 19/2/16.
 */
public class NoHeaderLoadMoreActivity extends BaseActivity {

    private SectionZeroAdapter simpleRecyclerViewAdapter = null;

    @Override
    protected void enableEmptyViewPolicy() {
        ultimateRecyclerView.setEmptyView(R.layout.empty_view, UltimateRecyclerView.EMPTY_KEEP_HEADER_AND_LOARMORE);
    }

    @Override
    protected void onLoadmore() {
        SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 2);
    }

    @Override
    protected void onFireRefresh() {
        ultimateRecyclerView.setRefreshing(false);
        ultimateRecyclerView.scrollVerticallyTo(0);
        simpleRecyclerViewAdapter.removeAll();
        ultimateRecyclerView.disableLoadmore();
        ultimateRecyclerView.showEmptyView();
    }

    @Override
    protected void doURV(UltimateRecyclerView ultimateRecyclerView) {
        ultimateRecyclerView.setHasFixedSize(false);
        ArrayList<String> list = new ArrayList<>();
        list.add("o2fn31");
        list.add("of2n32");
        list.add("of3n36");
        simpleRecyclerViewAdapter = new SectionZeroAdapter(list);
        configLinearLayoutManager(ultimateRecyclerView);
        enableEmptyViewPolicy();
        enableLoadMore();
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ff4fcccf"));
        enableRefresh();
        ultimateRecyclerView.setItemViewCacheSize(simpleRecyclerViewAdapter.getAdditionalItems());
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
    }

    @Override
    protected void addButtonTrigger() {
        simpleRecyclerViewAdapter.insertLast("++ new Item");
        ultimateRecyclerView.reenableLoadmore();
    }

    @Override
    protected void removeButtonTrigger() {
        simpleRecyclerViewAdapter.removeLast();
    }

}
