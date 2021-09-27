package com.mchadeville.mareu.ui.addMeeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.databinding.ActivityAddMeetingBinding;
import com.mchadeville.mareu.ui.main.MainActivity;

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
        Button save = binding.btnSave;


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

        save.setOnClickListener(v-> {
            viewModel.addMeetingLiveData(editTopic.getEditText().getText().toString(), editSalle.getEditText().getText().toString(), editParticipants.getEditText().getText().toString(), editHeureDebut.getEditText().getText().toString());
            startActivity(new Intent(this, MainActivity.class));
        });



    }
}