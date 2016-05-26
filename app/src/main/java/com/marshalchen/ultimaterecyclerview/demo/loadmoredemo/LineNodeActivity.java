package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.TimeLineModel;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.TimeLineAdapter;
import com.marshalchen.ultimaterecyclerview.quickAdapter.EasyRegularAdapter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 时间轴
 * Created by zJJ on 4/27/2016.
 */
public class LineNodeActivity extends BaseActivity {

    @Override
    protected void onLoadmore() {

    }

    @Override
    protected void onFireRefresh() {

    }

    /**
     * additional patch for the additional item
     */
    public static void insertMoreWhole(EasyRegularAdapter sd, int howmany) {
        ArrayList<TimeLineModel> items = new ArrayList<>();
        addAmount(howmany, items);
        int at = sd.getAdapterItemCount();
        sd.insert(items);
        sd.notifyDataSetChanged();
    }

    protected static void addAmount(int howMany, ArrayList<TimeLineModel> list) {
        for (int i = 0; i < howMany; i++) {
            UUID uuid = UUID.randomUUID();
            TimeLineModel time = new TimeLineModel();
            time.setAge(uuid.variant());
            time.setName(uuid.toString());
            list.add(time);
        }
    }

    @Override
    protected void addButtonTrigger() {
        insertMoreWhole(simpleRecyclerViewAdapter, 3);
    }

    @Override
    protected void removeButtonTrigger() {

    }

    private TimeLineAdapter simpleRecyclerViewAdapter = null;

    @Override
    protected void doURV(UltimateRecyclerView urv) {

        ArrayList<TimeLineModel> list = new ArrayList<>();
        TimeLineModel time = new TimeLineModel();
        time.setAge(139);
        time.setName("England");
        list.add(time);
        TimeLineModel time2 = new TimeLineModel();
        time2.setAge(359);
        time2.setName("Japan");
        list.add(time2);
        TimeLineModel time3 = new TimeLineModel();
        time3.setAge(339);
        time3.setName("HK");
        list.add(time3);
        addAmount(29, list);

        ultimateRecyclerView.setHasFixedSize(true);
        ultimateRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //currently we only support linearlayout option but we have tested anything for the grid layout yet
        simpleRecyclerViewAdapter = new TimeLineAdapter(list);
        enableEmptyViewPolicy();
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ff6f36cf"));
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
        Log.d("uuuu", "doURV");
    }
}
