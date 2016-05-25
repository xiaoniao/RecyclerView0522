package com.marshalchen.ultimaterecyclerview.demo.rvComponents;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.SwipeableUltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeLayout;

import java.util.List;

/**
 * 滑动删除适配器
 */
public class SwipeAdapter extends SwipeableUltimateViewAdapter<String> {

    public SwipeAdapter(List<String> mData) {
        super(mData);
    }

    @Override
    protected int getNormalLayoutResId() {
        return SVHolder.layout;
    }

    @Override
    protected void withBindHolder(UltimateRecyclerviewViewHolder holder, String data, int position) {
        super.withBindHolder(holder, data, position);
        ((SVHolder) holder).textView.setText(data);
    }

    @Override
    protected UltimateRecyclerviewViewHolder newViewHolder(final View view) {

        final SVHolder viewHolder = new SVHolder(view, true);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLogs.d("click");
            }
        });

        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(view.getContext(), "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(viewHolder.getPosition());
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.getPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public SVHolder newFooterHolder(View view) {
        return new SVHolder(view, false);
    }

    @Override
    public SVHolder newHeaderHolder(View view) {
        return new SVHolder(view, false);
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    protected void removeNotifyExternal(int pos) {
        closeItem(pos);
    }

    /**
     * 滑动删除ViewHolder
     */
    public static class SVHolder extends UltimateRecyclerviewViewHolder {
        // TODO  在 ViewHolder 中设置 对应的 ayout id 这样好一点嘛？
        public static final int layout = R.layout.item_swipeable;
        public TextView textView;
        public Button deleteButton;
        public SwipeLayout swipeLayout;

        public SVHolder(View itemView, boolean bind) {
            super(itemView);
            if (bind) {
                textView = (TextView) itemView.findViewById(R.id.position);
                deleteButton = (Button) itemView.findViewById(R.id.delete);
                swipeLayout = (SwipeLayout) itemView.findViewById(R.id.recyclerview_swipe);
                swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);
                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            }
        }
    }
}
