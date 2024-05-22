package com.itai.justrun;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MainRecyclerAdapter
        extends RecyclerView.Adapter<taskViewHolder> {
    List<Task> list = Collections.emptyList();
    Context context;
    ClickListener listener;



    public MainRecyclerAdapter(List<Task> list,
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
        Task task = list.get(index);
        viewHolder.description.setText(task.getDescription());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.e("ttttt","line 74");
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

