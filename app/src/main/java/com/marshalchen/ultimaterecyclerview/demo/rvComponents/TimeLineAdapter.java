package com.marshalchen.ultimaterecyclerview.demo.rvComponents;

import android.view.View;

import com.marshalchen.ultimaterecyclerview.demo.modules.TimeLineModel;
import com.marshalchen.ultimaterecyclerview.quickAdapter.EasyRegularAdapter;
import com.marshalchen.ultimaterecyclerview.ui.timelineview.TimelineView;

import java.util.List;

/**
 * 时间线适配器
 * TimeLineModel 数据
 * itemNode ViewHolder
 * Created by zJJ on 4/27/2016.
 */
public class TimeLineAdapter extends EasyRegularAdapter<TimeLineModel, ItemNode> {

    public TimeLineAdapter(List<TimeLineModel> feedList) {
        super(feedList);
    }

    // onCreateViewHolder 布局 layout id
    @Override
    protected int getNormalLayoutResId() {
        return ItemNode.layout;
    }

    // onCreateViewHolder 创建 ViewHolder (一个继承 UltimateRecyclerviewViewHolder 的ViewHolder)
    @Override
    protected ItemNode newViewHolder(View view) {
        return new ItemNode(view);
    }

    // onBindViewHolder
    @Override
    protected void withBindHolder(ItemNode holder, TimeLineModel data, int position) {
        holder.name.setText("name：" + data.getName() + " age：" + data.getAge());
        holder.init(TimelineView.getTimeLineViewType(position, getItemCount()));
    }

    // TODO 像它这种API设计 方法名可不可以尽量保持和原生的一致
    // 例如 withBindHolder 可以改成  onBindViewHolderu
    //     newViewHolder  可以改成  onCreateViewHolderu
}
