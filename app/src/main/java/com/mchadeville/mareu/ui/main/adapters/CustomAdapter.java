package com.mchadeville.mareu.ui.main.adapters;

import static com.mchadeville.mareu.util.Utils.calendarToString;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ui.main.ItemClickListener;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;
import com.mchadeville.mareu.util.Utils;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Context ctx;
    private final List<MeetingsViewStateItem> listMeetings;
    private int position;
    private ItemClickListener clickListener;

    public CustomAdapter(Context ctx, List<MeetingsViewStateItem> dataSet, ItemClickListener clickListener) {
        this.ctx = ctx;
        listMeetings = dataSet;
        this.clickListener = clickListener;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvIcon;
        private final TextView tvTitle;
        private final TextView tvDateTime;
        private final TextView tvParticipants;
        private final ImageView ivDelete;

        public ViewHolder(View view) {
            super(view);

            tvIcon = (TextView) view.findViewById(R.id.item_meeting_tv_icon);
            tvTitle = (TextView) view.findViewById(R.id.item_meeting_tv_title);
            tvDateTime = (TextView) view.findViewById(R.id.item_meeting_tv_date_time);
            tvParticipants = (TextView) view.findViewById(R.id.item_meeting_tv_participants);
            ivDelete = (ImageView) view.findViewById(R.id.item_btn_delete);
            ivDelete.setOnClickListener(view1 -> {
                Log.i("Adapter", "onClick: ");
                clickListener.onClick(view, getAdapterPosition());
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_meet, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        MeetingsViewStateItem meet = listMeetings.get(position);
        viewHolder.tvTitle.setText(meet.getTopic());

        viewHolder.tvParticipants.setText(Utils.listToString(meet.getParticipants()));
        viewHolder.tvDateTime.setText(ctx.getString(R.string.date_time, calendarToString(meet.getDate()), meet.getTime()));
        viewHolder.tvIcon.setText(meet.getRoom().getName().substring(meet.getRoom().getName().length() - 1));

        switch (meet.getRoom()) {
            case SALLE_A:
                viewHolder.tvIcon.setBackground(AppCompatResources.getDrawable(ctx, R.drawable.icon_room_a));
                break;
            case SALLE_B:
                viewHolder.tvIcon.setBackground(AppCompatResources.getDrawable(ctx, R.drawable.icon_room_b));
                break;
            case SALLE_C:
                viewHolder.tvIcon.setBackground(AppCompatResources.getDrawable(ctx, R.drawable.icon_room_c));
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewRecycled(ViewHolder viewHolder) {
        viewHolder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(viewHolder);
    }

    @Override
    public int getItemCount() {
        return listMeetings.size();
    }
}

