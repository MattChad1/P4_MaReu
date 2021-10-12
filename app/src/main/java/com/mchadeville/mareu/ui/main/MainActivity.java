package com.mchadeville.mareu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.databinding.ActivityMainBinding;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingActivity;
import com.mchadeville.mareu.ui.main.adapters.CustomAdapter;
import com.mchadeville.mareu.ui.main.filter.SideSheetFilterFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

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

        FloatingActionButton fab = binding.fabMain;
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddMeetingActivity.class)));

        ActionMenuItemView btnFilter = binding.appBar.findViewById(R.id.btn_filter);
        SideSheetFilterFragment ssf = new SideSheetFilterFragment();
        btnFilter.setOnClickListener(v -> {
            ssf.show(getSupportFragmentManager(), "");
        });

        rv = binding.listeMeetings;
        adapter = new CustomAdapter(this, datas, this);
        rv.setAdapter(adapter);
        adapter.setClickListener(this);

        viewModel.getMeetingsViewStateItemMediatorLD().observe(this, meetingsViewStateItems -> {
            datas.clear();
            datas.addAll(meetingsViewStateItems);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onClick(View view, int position) {
        Log.i(TAG, "onClick: position" + position);
        viewModel.deleteMeetingLiveData(datas.get(position).getId());
    }
}