package com.mchadeville.mareu.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Context ctx;
    private final List<MeetingsViewStateItem> listMeetings;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvIcon;
        private final TextView tvTitle;
        private final TextView tvDetails;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvIcon = (TextView) view.findViewById(R.id.item_meeting_tv_icon);
            tvTitle = (TextView) view.findViewById(R.id.item_meeting_tv_title);
            tvDetails = (TextView) view.findViewById(R.id.item_meeting_tv_details);
        }

    }

    public CustomAdapter(Context ctx, List<MeetingsViewStateItem> dataSet) {
        this.ctx = ctx;
        listMeetings = dataSet;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_meet, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        MeetingsViewStateItem meet = listMeetings.get(position);
        viewHolder.tvTitle.setText(meet.getTitle());
        viewHolder.tvDetails.setText(meet.getDescription());
        viewHolder.tvIcon.setText(meet.getRoom().substring(0,1));

        switch (meet.getRoom()) {
            case "A" :
                viewHolder.tvIcon.setBackgroundResource(R.color.blue);
                break;
            case "B" :
                viewHolder.tvIcon.setBackgroundResource(R.color.red);
                break;
            default:
                viewHolder.tvIcon.setBackgroundResource(R.color.purple);
                break;


        }

    }

    @Override
    public int getItemCount() {
        return listMeetings.size();
    }
}

