package com.marshalchen.ultimaterecyclerview.demo.swiplistdemo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.R;

import java.util.List;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {

    private List<String> data;
    private Context context;

    public PackageAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.package_row, viewGroup, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final String item = getItem(i);
        viewHolder.tvTitle.setText(data.get(i));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        Button bAction1;
        Button bAction2;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.example_row_iv_image);
            tvTitle = (TextView) itemView.findViewById(R.id.example_row_tv_title);
            bAction1 = (Button) itemView.findViewById(R.id.example_row_b_action_1);
            bAction2 = (Button) itemView.findViewById(R.id.example_row_b_action_2);
        }
    }
}
