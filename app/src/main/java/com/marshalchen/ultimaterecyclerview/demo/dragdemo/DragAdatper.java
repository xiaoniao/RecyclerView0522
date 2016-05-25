package com.marshalchen.ultimaterecyclerview.demo.dragdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.dragsortadapter.DragSortAdapter;

import java.util.List;


public class DragAdatper extends DragSortAdapter<MainViewHolder> {
    private List<Integer> stringList;

    public DragAdatper(RecyclerView recyclerView, List<Integer> data) {
        super(recyclerView);
        this.stringList = data;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_linear, parent, false);
        MainViewHolder holder = new MainViewHolder(this, view);
        view.setOnClickListener(holder);
        view.setOnLongClickListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        Integer itemId = stringList.get(position);
        holder.textViewSample.setText(itemId + "  ");
        // NOTE: check for getDraggingId() match to set an "invisible space" while dragging
        // holder.container.setVisibility(getDraggingId() == itemId ? View.INVISIBLE : View.VISIBLE);
        // holder.container.postInvalidate();
    }

    @Override
    public long getItemId(int position) {
        return stringList.get(position).hashCode();
    }

    protected static int convertToOriginalPosition(int position, int dragInitial, int dragCurrent) {
        if (dragInitial < 0 || dragCurrent < 0) {
            // not dragging
            return position;
        } else {
            if ((dragInitial == dragCurrent) ||
                    ((position < dragInitial) && (position < dragCurrent)) ||
                    (position > dragInitial) && (position > dragCurrent)) {
                return position;
            } else if (dragCurrent < dragInitial) {
                if (position == dragCurrent) {
                    return dragInitial;
                } else {
                    return position - 1;
                }
            } else { // if (dragCurrent > dragInitial)
                if (position == dragCurrent) {
                    return dragInitial;
                } else {
                    return position + 1;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    @Override
    public int getPositionForId(long id) {
        return stringList.indexOf((int) id);
    }

    @Override
    public boolean move(int fromPosition, int toPosition) {
        stringList.add(toPosition, stringList.remove(fromPosition));
        return true;
    }
}
