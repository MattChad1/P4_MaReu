package com.mchadeville.mareu.ui.main.adapters;


import static com.mchadeville.mareu.util.Utils.calendarToString;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ui.main.MainActivity;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;
import com.mchadeville.mareu.util.Utils;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Context ctx;
    private final List<MeetingsViewStateItem> listMeetings;
    private int position;
    OnCardClickListner onCardClickListner;


    public CustomAdapter(Context ctx, List<MeetingsViewStateItem> dataSet) {
        this.ctx = ctx;
        listMeetings = dataSet;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnCreateContextMenuListener*/ {
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
            //view.setOnCreateContextMenuListener(this);
        }

//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            //menuInfo is null
//            menu.add(Menu.NONE, R.id.menu_update,
//                    Menu.NONE, R.string.menu_update);
//            menu.add(Menu.NONE, R.id.menu_delete,
//                    Menu.NONE, R.string.menu_delete);
//        }

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
        viewHolder.tvTitle.setText(meet.getTopic());

        viewHolder.tvParticipants.setText(Utils.listToString(meet.getParticipants()));
        viewHolder.tvDateTime.setText(ctx.getString(R.string.date_time, calendarToString(meet.getDate()), meet.getTime()));
        String roomLetter = meet.getRoom().getName().substring(meet.getRoom().getName().length() - 1);
        viewHolder.tvIcon.setText(roomLetter);

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCardClickListner.OnCardClicked(v, position);
            }
        });

//        viewHolder.itemView.setTag(meet.getTitle());

        switch (roomLetter) {
            case "A":
                viewHolder.tvIcon.setBackgroundResource(R.color.blue);
                break;
            case "B":
                viewHolder.tvIcon.setBackgroundResource(R.color.red);
                break;
            default:
                viewHolder.tvIcon.setBackgroundResource(R.color.purple);
                break;


        }


//        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                setPosition(viewHolder.getAdapterPosition());
//                return false;
//            }
//        });

//        viewHolder.ivDelete.setOnClickListener( x-> {
//            clickListener.onItemClicked(getPosition());
//
//
//        });
//            Log.i("Adapter", "click 1 ");
//            setPosition(viewHolder.getAdapterPosition());
//
//        });

    }

    @Override
    public void onViewRecycled(ViewHolder viewHolder) {
        viewHolder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(viewHolder);
    }

    public int getPosition() {
        return position;
    }

//    public String getTopic() {
//        return listMeetings.get(position).getTitle();
//    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return listMeetings.size();
    }

    public interface OnCardClickListner {
        void OnCardClicked(View view, int position);
    }

    public void setOnCardClickListner(OnCardClickListner onCardClickListner) {
        this.onCardClickListner = onCardClickListner;
    }

}
