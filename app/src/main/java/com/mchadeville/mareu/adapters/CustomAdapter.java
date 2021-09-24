package com.mchadeville.mareu.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.model.Meeting;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

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

//        public TextView getTextView() {
//            return tvTitle;
//        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(List<MeetingsViewStateItem> dataSet) {

        listMeetings = dataSet;

    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_meet, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        MeetingsViewStateItem meet = listMeetings.get(position);

        viewHolder.tvTitle.setText(meet.getTitle());
        viewHolder.tvDetails.setText(meet.getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listMeetings.size();
    }
}

