package com.mchadeville.mareu.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FloatingActionButton fab = binding.fabMain;
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddMeetingActivity.class)));


        rv = binding.listeReu;
        MainViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        CustomAdapter adapter = new CustomAdapter(datas);

        rv.setAdapter(adapter);

        viewModel.getMeetingViewStateItemsLiveData().observe(this, meetingsViewStateItems -> {
            datas.addAll(meetingsViewStateItems);
            adapter.notifyDataSetChanged();
            Log.i(TAG, "onCreate: view model");
        });

    }
}