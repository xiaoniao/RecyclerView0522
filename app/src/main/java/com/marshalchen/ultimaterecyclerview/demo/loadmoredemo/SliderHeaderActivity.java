package com.marshalchen.ultimaterecyclerview.demo.loadmoredemo;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.hkm.slider.SliderLayout;
import com.hkm.slider.SliderTypes.BaseSliderView;
import com.hkm.slider.SliderTypes.TextSliderView;
import com.hkm.slider.TransformerL;
import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.demo.rvComponents.SectionZeroAdapter;

import java.util.ArrayList;

/**
 * 广告ViewPager
 * Created by hesk on 18/3/16.
 */
public class SliderHeaderActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener {

    String[] urls = {
            "http://cs407831.userapi.com/v407831207/1906/oxoP6URjFtA.jpg",
            "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
            "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
            "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
            "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
            "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
            "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
            "http://cs407831.userapi.com/v407831207/191e/QEQE83Ok0lQ.jpg"
    };

    private SectionZeroAdapter simpleRecyclerViewAdapter = null;

    @Override
    protected void onLoadmore() {
        SampleDataboxset.insertMoreWhole(simpleRecyclerViewAdapter, 2);
    }

    @Override
    protected void onFireRefresh() {
        ultimateRecyclerView.setRefreshing(false);
        linearLayoutManager.scrollToPosition(0);
        simpleRecyclerViewAdapter.removeAll();
        ultimateRecyclerView.disableLoadmore();
        ultimateRecyclerView.showEmptyView();
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
    protected void doURV(UltimateRecyclerView urv) {
        ultimateRecyclerView.setHasFixedSize(false);
        simpleRecyclerViewAdapter = new SectionZeroAdapter(new ArrayList<String>());
        configLinearLayoutManager(ultimateRecyclerView);
        enableParallaxHeader();
        enableEmptyViewPolicy();
        enableLoadMore();
        enableRefresh();
        ultimateRecyclerView.setRecylerViewBackgroundColor(Color.parseColor("#ffff66ff"));
        ultimateRecyclerView.setItemViewCacheSize(simpleRecyclerViewAdapter.getAdditionalItems());
        ultimateRecyclerView.setNormalHeader(initSlider(ultimateRecyclerView));
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
    }


    /**
     * 初始化广告
     */
    private View initSlider(UltimateRecyclerView listview) {
        final View view = LayoutInflater.from(getApplication()).inflate(R.layout.list_item_header, null, false);
        final ViewTreeObserver vto = listview.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //Log.d("vto", "globallayout");
                final SliderLayout sl = (SliderLayout) view.findViewById(R.id.header_slider);
                try {
                    sl.setOffscreenPageLimit(1);
                    sl.setSliderTransformDuration(500, new LinearOutSlowInInterpolator());
                    //sl.setPresetTransformer(TransformerL.Default);
                    sl.setPresetTransformer(TransformerL.CubeIn);
                    sl.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    sl.getPagerIndicator().setDefaultIndicatorColor(R.color.accent, R.color.primaryDark);
                    sl.getPagerIndicator().setVisibility(View.GONE);
                    setup_double_faces(sl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }


    protected void setup_double_faces(final SliderLayout mslide) throws Exception {
        ArrayList<BaseSliderView> ie = new ArrayList<>();
        // mslide.setCustomAnimation(new DescriptionAnimation(250, new DecelerateInterpolator()));
        for (int i = 0; i < urls.length; i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(urls[i])
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            ie.add(textSliderView);
        }
        mslide.loadSliderList(ie);
    }

    @Override
    public void onSliderClick(BaseSliderView coreSlider) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }
}
