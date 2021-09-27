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

        save.setOnClickListener(v ->{

            Log.i("save.setOnClickListener", textFromTextInputLayout(editTopic) +"/" + textFromTextInputLayout(editSalle) +"/" + textFromTextInputLayout(editParticipants) +"/" + textFromTextInputLayout(editHeureDebut));
            viewModel.addMeetingLiveData(textFromTextInputLayout(editTopic), textFromTextInputLayout(editSalle), textFromTextInputLayout(editParticipants), textFromTextInputLayout(editHeureDebut));
            viewModel.getValidGeneral().observe(this, res -> {
                Log.i("res ", res.toString());
                if (!res) { // Test si au moins une erreur dans le formulaire
                    viewModel.getValidTopic().observe(this, vTopic -> { if (!vTopic) editTopic.setError(getString(R.string.error_empty_topic));else editTopic.setError(null);});
                    viewModel.getValidPlace().observe(this, vPlace -> { if (!vPlace) editSalle.setError("Vous devez préciser le lieu de la réunion");else editSalle.setError(null);});
                    viewModel.getValidParticipants().observe(this, vParticipants -> { if (!vParticipants) editParticipants.setError("Vous devez préciser qui participe");else editParticipants.setError(null);});
                    viewModel.getValidTime().observe(this, vTime -> { if (!vTime) editHeureDebut.setError("Vous devez préciser l'heure de la réunion");else editHeureDebut.setError(null);});
                    Log.i("save.setOnClickListener", "error");
                }
                else {
                    startActivity(new Intent(this, MainActivity.class));
                    Log.i("save.setOnClickListener", "ok");
                }
            });
        });

    }

    public String textFromTextInputLayout (TextInputLayout til) {
        return Objects.requireNonNull(til.getEditText()).getText().toString();
    }
}