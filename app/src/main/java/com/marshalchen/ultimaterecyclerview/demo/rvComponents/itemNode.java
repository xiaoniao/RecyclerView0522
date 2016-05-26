package com.marshalchen.ultimaterecyclerview.demo.rvComponents;

import android.view.View;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.ui.timelineview.TimelineView;

/**
 * 时间线 ViewHolder
 * Created by zJJ on 4/27/2016.
 */
public class ItemNode extends UltimateRecyclerviewViewHolder {

    public static final int layout = R.layout.item_node;
    public TimelineView mTimelineView;
    public TextView name;

    /**
     * the view
     *
     * @param itemView the view context
     */
    public ItemNode(View itemView) {
        super(itemView);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        name = (TextView) itemView.findViewById(R.id.tx_name);
    }

    /**
     * 初始化自定义TimelineView
     *
     * this is the initialization of the node
     *
     * @param viewTypeLine the type of node to redraw
     */
    public void init(int viewTypeLine) {
        mTimelineView.initLine(viewTypeLine);
    }
}
