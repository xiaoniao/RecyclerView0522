package com.marshalchen.ultimaterecyclerview.demo.rvComponents;

import android.graphics.Color;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.R;
import com.marshalchen.ultimaterecyclerview.quickAdapter.EasyRegularAdapter;

import java.security.SecureRandom;
import java.util.List;


public class SectionZeroAdapter extends EasyRegularAdapter<String, ItemCommonBinder> {

    public SectionZeroAdapter(List<String> stringList) {
        super(stringList);
    }

    /**
     * the layout id for the normal data
     *
     * @return the ID
     */
    @Override
    protected int getNormalLayoutResId() {
        return ItemCommonBinder.layout;
    }

    @Override
    protected ItemCommonBinder newViewHolder(View view) {
        return new ItemCommonBinder(view, true);
    }

    @Override
    public ItemCommonBinder newFooterHolder(View view) {
        return new ItemCommonBinder(view, false);
    }

    @Override
    public ItemCommonBinder newHeaderHolder(View view) {
        return new ItemCommonBinder(view, false);
    }

    public final void insertOne(String e) {
        insertLast(e);
    }

    public final void removeLastOne() {
        removeLast();
    }

    @Override
    protected void withBindHolder(ItemCommonBinder holder, String data, int position) {
        holder.textViewSample.setText(data + "just the sample data");
        holder.item_view.setBackgroundColor(Color.parseColor("#AAffffff"));
        SecureRandom imgGen = new SecureRandom();
        switch (imgGen.nextInt(3)) {
            case 0:
                holder.imageViewSample.setImageResource(R.drawable.scn1);
                break;
            case 1:
                holder.imageViewSample.setImageResource(R.drawable.jr13);
                break;
            case 2:
                holder.imageViewSample.setImageResource(R.drawable.jr16);
                break;
        }
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        swapPositions(fromPosition, toPosition);
        super.onItemMove(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        if (position > 0)
            removeAt(position);
        super.onItemDismiss(position);
    }
}
