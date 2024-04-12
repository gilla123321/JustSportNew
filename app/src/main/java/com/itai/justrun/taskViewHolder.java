// ViewHolder code for RecyclerView
package com.itai.justrun;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

public class taskViewHolder
        extends RecyclerView.ViewHolder {
    TextView taskName;
    TextView taskDate;
    TextView taskDuration;
    View view;

    taskViewHolder(View itemView)
    {
        super(itemView);
        taskName
                = (TextView)itemView
                .findViewById(R.id.taskName);
        taskDate
                = (TextView)itemView
                .findViewById(R.id.taskDate);
        taskDuration
                = (TextView)itemView
                .findViewById(R.id.taskDuration);

        view = itemView;
    }
}
