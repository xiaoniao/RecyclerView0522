package com.marshalchen.ultimaterecyclerview.demo.dragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.marshalchen.ultimaterecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖拽
 * 支持 LinearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager
 */
public class DragActivity extends AppCompatActivity {

    private RecyclerView ultimateRecyclerView;
    private DragAdatper simpleRecyclerViewAdapter = null;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        ultimateRecyclerView = (RecyclerView) findViewById(R.id.ultimate_recycler_view);
        ultimateRecyclerView.setHasFixedSize(false);

        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);

        simpleRecyclerViewAdapter = new DragAdatper(ultimateRecyclerView, data);
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        //ultimateRecyclerView.setLayoutManager(linearLayoutManager);
        //ultimateRecyclerView.setLayoutManager(gridLayoutManager);
        ultimateRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        ultimateRecyclerView.setAdapter(simpleRecyclerViewAdapter);
    }

}
