package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.marshalchen.ultimaterecyclerview.CustomUltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.SectionZeroAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.demo.ui.header.RentalsSunHeaderView;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;


/**
 * iOS风格下拉刷新
 */
public class PullToRefreshActivity extends AppCompatActivity {

    private Toolbar toolbar;
    protected CustomUltimateRecyclerView ultimateRecyclerView;
    private SectionZeroAdapter simpleRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RentalsSunHeaderView rentalsSunHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_refresh_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ultimateRecyclerView = (CustomUltimateRecyclerView) findViewById(R.id.custom_ultimate_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        ultimateRecyclerView.setLayoutManager(linearLayoutManager);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        simpleRecyclerViewAdapter = new SectionZeroAdapter(list);
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);

        ultimateRecyclerView.setCustomSwipeToRefresh();
//        refreshingMaterial();
//        refreshingString();
        refreshingRental();
//        refreshingStringArray();
    }

    void refreshingString() {
        storeHouseHeader = new StoreHouseHeader(this);
        storeHouseHeader.initWithString("XCode Big Air");
        ultimateRecyclerView.mPtrFrameLayout.removePtrUIHandler(materialHeader);
        ultimateRecyclerView.mPtrFrameLayout.setHeaderView(storeHouseHeader);
        ultimateRecyclerView.mPtrFrameLayout.addPtrUIHandler(storeHouseHeader);
        ultimateRecyclerView.mPtrFrameLayout.autoRefresh(false);
        ultimateRecyclerView.mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                boolean canbePullDown = PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
                return canbePullDown;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onFireRefresh();
                    }
                }, 1800);
            }
        });
    }


    private void onFireRefresh() {
        //下拉刷新
        simpleRecyclerViewAdapter.insertLast("Refresh things");
        linearLayoutManager.scrollToPosition(0);
        ultimateRecyclerView.mPtrFrameLayout.refreshComplete();
        changeHeaderHandler.sendEmptyMessageDelayed(0, 500);
    }


    void refreshingRental() {
        rentalsSunHeaderView = new RentalsSunHeaderView(this);
        rentalsSunHeaderView.setUp(ultimateRecyclerView.mPtrFrameLayout);

        ultimateRecyclerView.mPtrFrameLayout.removePtrUIHandler(materialHeader);
        ultimateRecyclerView.mPtrFrameLayout.removePtrUIHandler(storeHouseHeader);
        ultimateRecyclerView.mPtrFrameLayout.setHeaderView(rentalsSunHeaderView);
        ultimateRecyclerView.mPtrFrameLayout.addPtrUIHandler(rentalsSunHeaderView);
        ultimateRecyclerView.mPtrFrameLayout.autoRefresh(false);
        ultimateRecyclerView.mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                boolean canbePullDown = PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
                return canbePullDown;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simpleRecyclerViewAdapter.insertOne("Refresh things");
                        //   ultimateRecyclerView.scrollBy(0, -50);
                        linearLayoutManager.scrollToPosition(0);
                        ultimateRecyclerView.mPtrFrameLayout.refreshComplete();
                        changeHeaderHandler.sendEmptyMessageDelayed(3, 500);
                    }
                }, 1800);
            }
        });

    }

    void refreshingMaterial() {
        materialHeader = new MaterialHeader(this);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        materialHeader.setColorSchemeColors(colors);
        materialHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        materialHeader.setPadding(0, 15, 0, 10);
        materialHeader.setPtrFrameLayout(ultimateRecyclerView.mPtrFrameLayout);
        ultimateRecyclerView.mPtrFrameLayout.autoRefresh(false);
        ultimateRecyclerView.mPtrFrameLayout.removePtrUIHandler(storeHouseHeader);
        ultimateRecyclerView.mPtrFrameLayout.setHeaderView(materialHeader);
        ultimateRecyclerView.mPtrFrameLayout.addPtrUIHandler(materialHeader);

        ultimateRecyclerView.mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simpleRecyclerViewAdapter.insertLast("Refresh things");
                        //   ultimateRecyclerView.scrollBy(0, -50);
                        linearLayoutManager.scrollToPosition(0);
                        ultimateRecyclerView.mPtrFrameLayout.refreshComplete();
                        //   changeHeaderHandler.sendEmptyMessageDelayed(2, 500);
                    }
                }, 1800);
            }
        });
    }

    Handler changeHeaderHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    refreshingStringArray();
                    break;
                case 1:
                    //  refreshingMaterial();
                    refreshingString();
                    break;
                case 2:
                    // refreshingString();
                    break;
                case 3:
                    refreshingString();
                    break;
                case 4:
                    break;
            }
        }
    };
    private int mLoadTime = 0;
    StoreHouseHeader storeHouseHeader;
    MaterialHeader materialHeader;
    //  RentalsSunHeaderView rentalsSunHeaderView;

    private void refreshingStringArray() {
        storeHouseHeader = new StoreHouseHeader(this);
        storeHouseHeader.setPadding(0, 15, 0, 0);

        // using string array from resource xml file
        storeHouseHeader.initWithStringArray(R.array.storehouse);
        ultimateRecyclerView.mPtrFrameLayout.setDurationToCloseHeader(1500);
        ultimateRecyclerView.mPtrFrameLayout.removePtrUIHandler(materialHeader);
        ultimateRecyclerView.mPtrFrameLayout.setHeaderView(storeHouseHeader);
        ultimateRecyclerView.mPtrFrameLayout.addPtrUIHandler(storeHouseHeader);
        ultimateRecyclerView.mPtrFrameLayout.autoRefresh(false);

        // change header after loaded
        ultimateRecyclerView.mPtrFrameLayout.addPtrUIHandler(new PtrUIHandler() {


            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                if (mLoadTime % 2 == 0) {
                    storeHouseHeader.setScale(1);
                    storeHouseHeader.initWithStringArray(R.array.storehouse);
                } else {
                    storeHouseHeader.setScale(0.5f);
                    storeHouseHeader.initWithStringArray(R.array.akta);
                }
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        ultimateRecyclerView.mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // frame.refreshComplete();
                        simpleRecyclerViewAdapter.insertLast("Refresh things");
                        //   ultimateRecyclerView.scrollBy(0, -50);
                        linearLayoutManager.scrollToPosition(0);
                        ultimateRecyclerView.mPtrFrameLayout.refreshComplete();
                        if (mLoadTime % 2 == 0)
                            changeHeaderHandler.sendEmptyMessageDelayed(1, 500);
                    }
                }, 2000);
            }
        });
    }
}
