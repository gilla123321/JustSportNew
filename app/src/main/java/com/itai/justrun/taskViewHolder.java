// ViewHolder code for RecyclerView
package com.itai.justrun;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

public class taskViewHolder
        extends RecyclerView.ViewHolder {
    TextView description;

    TextView startTime;
    View view;

    taskViewHolder(View itemView)
    {
        super(itemView);
        description = itemView.findViewById(R.id.description);
        startTime = itemView.findViewById(R.id.txtStartTime);
        view = itemView;
    }
}
