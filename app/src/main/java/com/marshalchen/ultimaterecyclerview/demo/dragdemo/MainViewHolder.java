package com.marshalchen.ultimaterecyclerview.demo.dragdemo;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.dragsortadapter.DragSortAdapter;
import com.marshalchen.ultimaterecyclerview.dragsortadapter.NoForegroundShadowBuilder;

/**
 * Created by liuzhuang on 16/5/22.
 */
public class MainViewHolder extends DragSortAdapter.ViewHolder implements
        View.OnClickListener, View.OnLongClickListener {


    TextView textViewSample;
    ImageView imageViewSample;
    ProgressBar progressBarSample;

    public MainViewHolder(DragSortAdapter adapter, View itemView) {
        super(adapter, itemView);
        textViewSample = (TextView) itemView.findViewById(R.id.str_textview_holder);
        imageViewSample = (ImageView) itemView.findViewById(R.id.str_image_holder);
        progressBarSample = (ProgressBar) itemView.findViewById(R.id.str_progress_holder);
        progressBarSample.setVisibility(View.GONE);
    }

    @Override
    public void onClick(@NonNull View v) {
        URLogs.d(textViewSample.getText() + " clicked!");
    }

    @Override
    public boolean onLongClick(@NonNull View v) {
        startDrag();
        return true;
    }

    @Override
    public View.DragShadowBuilder getShadowBuilder(View itemView, Point touchPoint) {
        return new NoForegroundShadowBuilder(itemView, touchPoint);
    }
}
