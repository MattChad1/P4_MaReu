package com.mchadeville.mareu.ui.addMeeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.databinding.ActivityAddMeetingBinding;

import java.util.Calendar;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TextInputLayout editTopic = binding.editSujet;
        TextInputLayout editParticipants = binding.editParticipants;
        TextInputLayout editSalle = binding.editSalle;
        TextInputLayout editHeureDebut = binding.editHeureDebut;


        editHeureDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                TimePickerDialog picker = new TimePickerDialog(AddMeetingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                editHeureDebut.getEditText().setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });


        AddMeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);


    }
}