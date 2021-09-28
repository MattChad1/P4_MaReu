package com.mchadeville.mareu.ui.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.adapters.CustomAdapter;
import com.mchadeville.mareu.databinding.ActivityMainBinding;
import com.mchadeville.mareu.ui.addMeeting.AddMeetingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView rv;
    private List<MeetingsViewStateItem> datas = new ArrayList();
    private String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FloatingActionButton fab = binding.fabMain;
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddMeetingActivity.class)));


        rv = binding.listeMeetings;
        MainViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        CustomAdapter adapter = new CustomAdapter(this, datas);

        rv.setAdapter(adapter);

        viewModel.getMeetingViewStateItemsLiveData().observe(this, meetingsViewStateItems -> {
            datas.clear();
            datas.addAll(meetingsViewStateItems);
            adapter.notifyDataSetChanged();
            Log.i(TAG, "onCreate: view model");
        });

        ActionMenuItemView btnFilter = binding.appBar.findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener (v -> {
            Log.i("btnFilter", "ok1");
            datas.clear();
            viewModel.getMeetingsFiltered().observe(this, meetingsViewStateItems -> {
                Log.i("btnFilter", "ok2");
                        datas.addAll(meetingsViewStateItems);
                        adapter.notifyDataSetChanged();
                    }
            );
        });

    }
}