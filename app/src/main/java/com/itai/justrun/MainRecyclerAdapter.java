package com.itai.justrun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MainRecyclerAdapter
        extends RecyclerView.Adapter<taskViewHolder> {
    List<taskData> list = Collections.emptyList();
    Context context;
    ClickListener listener;
    public void updateData(List<taskData> newData) {
        list.clear();
        list.addAll(newData);
        notifyDataSetChanged();
    }



    public MainRecyclerAdapter(List<taskData> list,
                               Context context, ClickListener listener)
    {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    public taskViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.task_item,
                        parent, false);

        taskViewHolder viewHolder
                = new taskViewHolder(photoView);
        return viewHolder;
    }


    public void
    onBindViewHolder(final taskViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.taskName
                .setText(list.get(position).name);
        viewHolder.taskDate
                .setText(list.get(position).date);
        viewHolder.taskDuration
                .setText(list.get(position).duration);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listener.click(index);
            }
        });
    }


    public int getItemCount()
    {
        return list.size();
    }

    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

