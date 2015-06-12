package com.discounty.discounty;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.discounty.R;
import com.discounty.Utils;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private ClickListener clickListener;

    public RecyclerAdapter(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int i) {
        data.remove(i);
        notifyItemRemoved(i);
    }

    public String get(int i) {
        Information inf = data.get(i);
        return inf.title + "\n" + inf.desc;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        Information current = data.get(i);
        GradientDrawable icon_color = (GradientDrawable) viewHolder.icon.getBackground();
        icon_color.setColor(Color.parseColor(Utils.getColorOfTheListItem(current.letter)));
        viewHolder.title.setText(current.title);
        viewHolder.icon.setText(Character.toString(current.letter));
        if (!current.desc.isEmpty())
            viewHolder.desc.setText(current.desc);
        else viewHolder.desc.setVisibility(View.GONE);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView icon;
        TextView desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_title);
            icon = (TextView) itemView.findViewById(R.id.text_with_icon);
            desc = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), "Item: " + getPosition(), Toast.LENGTH_SHORT).show();
//            delete(getPosition());


            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
//                delete(getPosition());
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }
}
