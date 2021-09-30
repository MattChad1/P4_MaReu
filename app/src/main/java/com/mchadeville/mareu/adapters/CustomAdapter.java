package com.mchadeville.mareu.adapters;


import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ui.main.MeetingsViewStateItem;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final Context ctx;
    private final List<MeetingsViewStateItem> listMeetings;
    private int position;

    public CustomAdapter(Context ctx, List<MeetingsViewStateItem> dataSet) {
        this.ctx = ctx;
        listMeetings = dataSet;
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final TextView tvIcon;
        private final TextView tvTitle;
        private final TextView tvDetails;


        public ViewHolder(View view) {
            super(view);

            tvIcon = (TextView) view.findViewById(R.id.item_meeting_tv_icon);
            tvTitle = (TextView) view.findViewById(R.id.item_meeting_tv_title);
            tvDetails = (TextView) view.findViewById(R.id.item_meeting_tv_details);
            view.setOnCreateContextMenuListener(this);


//            view.setOnClickListener(v-> {
//                        PopupMenu popupMenu = new PopupMenu(this, );
//                        View menu = LayoutInflater.from(view.getContext())
//                                .inflate(R.menu.context_menu_liste_meetings, view, false);
//                        view.layout(new LayoutInflater(R.menu.context_menu_liste_meetings))
//                    }
//                val popupMenu = PopupMenu(context, holder.binding.layoutItemqa)
//                popupMenu.inflate(R.menu.context_menu_myworkoutsactivity)
//                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
//                    override fun onMenuItemClick(item: MenuItem?): Boolean {
//
//
//                    }
//
//
//            );
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //menuInfo is null
            menu.add(Menu.NONE, R.id.menu_update,
                    Menu.NONE, R.string.menu_update);
            menu.add(Menu.NONE, R.id.menu_delete,
                    Menu.NONE, R.string.menu_delete);
        }

    }

//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView tvIcon;
//        private final TextView tvTitle;
//        private final TextView tvDetails;
//
//
//        public ViewHolder(View view) {
//            super(view);
//
//            tvIcon = (TextView) view.findViewById(R.id.item_meeting_tv_icon);
//            tvTitle = (TextView) view.findViewById(R.id.item_meeting_tv_title);
//            tvDetails = (TextView) view.findViewById(R.id.item_meeting_tv_details);
//            view.setOnCreateContextMenuListener(this);
//
//
//        }
//
//    }




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
        String roomLetter = meet.getRoom().substring(meet.getRoom().length() - 1);
        viewHolder.tvIcon.setText(roomLetter);

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

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(viewHolder.getAdapterPosition());
                return false;
            }
        });

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
}

