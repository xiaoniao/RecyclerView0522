package com.marshalchen.ultimaterecyclerview.demo.expandemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

/**
 * Created by hesk on 16/7/15.
 */
public class TestExpandableRV extends AppCompatActivity {

    // text path
    private static String[] sampledatagroup1 = {
            "peter",
            "http://google",
            "billy",
            "http://google",
            "lisa",
            "http://google",
            "visa",
            "http://google",
            "biso",
            "http://google"
    };

    private static String[] sampledatagroup2 = {
            "mother",
            "http://google",
            "father",
            "http://google",
            "son",
            "http://google",
            "holy spirit",
            "http://google",
            "god the son",
            "http://google"
    };

    private static String[] sampledatagroup3 = {
            "SONY",
            "http://google",
            "LG",
            "http://google",
            "SAMSUNG",
            "http://google",
            "XIAOMI",
            "http://google",
            "HTC",
            "http://google"
    };


    private UltimateRecyclerView ultimateRecyclerView;
    private ExpCustomAdapter simpleRecyclerViewAdapter = null;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ultimateRecyclerView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);
        ultimateRecyclerView.setHasFixedSize(false);

        simpleRecyclerViewAdapter = new ExpCustomAdapter(this);
        simpleRecyclerViewAdapter.addAll(ExpCustomAdapter.getPreCodeMenu(sampledatagroup1, sampledatagroup2, sampledatagroup3), 0); //添加数据并刷新界面

        linearLayoutManager = new LinearLayoutManager(this);
        ultimateRecyclerView.setLayoutManager(linearLayoutManager);
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ffffff"));
        addExpandableFeatures();
    }

    private void addExpandableFeatures() {
        ultimateRecyclerView.getItemAnimator().setAddDuration(100);
        ultimateRecyclerView.getItemAnimator().setRemoveDuration(100);
        ultimateRecyclerView.getItemAnimator().setMoveDuration(200);
        ultimateRecyclerView.getItemAnimator().setChangeDuration(100);
    }

}
