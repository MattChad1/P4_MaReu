package com.mchadeville.mareu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.adapters.CustomAdapter;
import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.FilterRoom;
import com.mchadeville.mareu.databinding.ActivityMainBinding;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingActivity;
import com.mchadeville.mareu.ui.main.filter.SideSheetFilter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity /*implements SideSheetFilter.onFormListener*/ {

    private ActivityMainBinding binding;
    private RecyclerView rv;
    private List<MeetingsViewStateItem> datas = new ArrayList();
    private String TAG = "MainActivity";
    private CustomAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        Log.i(TAG, "onCreate: ");

        FloatingActionButton fab = binding.fabMain;
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddMeetingActivity.class)));

        ActionMenuItemView btnFilter = binding.appBar.findViewById(R.id.btn_filter);
        SideSheetFilter ssf = new SideSheetFilter();
        btnFilter.setOnClickListener(v -> {
            ssf.show(getSupportFragmentManager(), "");
        });


        rv = binding.listeMeetings;
        adapter = new CustomAdapter(this, datas);

        rv.setAdapter(adapter);
        registerForContextMenu(rv);


        viewModel.getMeetingsViewStateItemMediatorLD().observe(this, meetingsViewStateItems -> {
            datas.clear();
            datas.addAll(meetingsViewStateItems);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        String title;
        try {
            //position = ((BackupRestoreListAdapter)getAdapter()).getPosition();
            position = adapter.getPosition();
//            title = adapter.getTopic();


        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_update:
                // do your stuff
                break;
            case R.id.menu_delete:
                Log.i(TAG, "onContextItemSelected: position : " + position);
                Log.i(TAG, "onContextItemSelected: datas.get(position).getId() : " + datas.get(position).getId());
                viewModel.deleteMeetingLiveData(datas.get(position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

//    @Override
//    public void transfertChecks(List<FilterRoom> filterRoomSelected) {
//        Log.i(TAG, "transfertChecks: ");
//        viewModel.filterDatas (filterRoomSelected, FilterDate.DATE_30DAYS);
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}