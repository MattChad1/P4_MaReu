package com.mchadeville.mareu.ui.addMeeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.databinding.ActivityAddMeetingBinding;
import com.mchadeville.mareu.ui.main.MainActivity;

import java.util.Calendar;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextInputLayout editTopic = binding.editTopic;
        TextInputLayout editParticipants = binding.editParticipants;
        TextInputLayout editRoom = binding.editRoom;
        TextInputLayout editStartTime = binding.editStartTime;
        Button save = binding.btnSave;


        // TODO : ne marche pas
        editStartTime.setOnClickListener(new View.OnClickListener() {
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
                                editStartTime.getEditText().setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        AddMeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        save.setOnClickListener(v ->{
            viewModel.addMeetingLiveData(textFromTextInputLayout(editTopic), textFromTextInputLayout(editRoom), textFromTextInputLayout(editParticipants), textFromTextInputLayout(editStartTime));
            viewModel.getValidGeneral().observe(this, res -> {
                Log.i("res ", res.toString());
                if (!res) { // Test si au moins une erreur dans le formulaire
                    viewModel.getValidTopic().observe(this, vTopic -> { if (!vTopic) editTopic.setError(getString(R.string.error_empty_topic));else editTopic.setError(null);});
                    viewModel.getValidPlace().observe(this, vPlace -> { if (!vPlace) editRoom.setError(getString(R.string.error_editRoom_empty));else editRoom.setError(null);});
                    viewModel.getValidParticipants().observe(this, vParticipants -> { if (!vParticipants) editParticipants.setError(getString(R.string.error_editParticipants_empty));else editParticipants.setError(null);});
                    viewModel.getValidTime().observe(this, vTime -> { if (!vTime) editStartTime.setError(getString(R.string.error_editTime_empty));else editStartTime.setError(null);});
                }
                else {
                    startActivity(new Intent(this, MainActivity.class));
                }
            });
        });

    }

    public String textFromTextInputLayout (TextInputLayout til) {
        return Objects.requireNonNull(til.getEditText()).getText().toString();
    }
}