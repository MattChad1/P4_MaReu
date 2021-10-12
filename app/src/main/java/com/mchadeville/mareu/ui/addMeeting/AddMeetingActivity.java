package com.mchadeville.mareu.ui.addMeeting;

import static com.mchadeville.mareu.util.Utils.textFromTextInputLayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.databinding.ActivityAddMeetingBinding;
import com.mchadeville.mareu.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private String TAG = "AddMeetingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AddMeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        TextInputLayout editTopic = binding.editTopic;
        TextInputLayout editRoom = binding.editRoom;
        TextInputEditText editRoomChild = binding.editRoomChild;
        TextInputLayout editParticipants = binding.editParticipants;
        AutoCompleteTextView editParticipantsChild = binding.editParticipantsChild;
        Button btnAddParticipant = binding.btnAddParticipant;
        Button btnDeleteParticipant = binding.btnDeleteParticipant;
        TextView tvTitreListeMails = binding.titreListeEmails;
        TextView tvListeEmails = binding.listeEmails;
        TextInputLayout editStartTime = binding.editStartTime;
        TextInputLayout editDate = binding.editDate;
        TextInputEditText editDateChild = binding.editDateChild;
        TextInputEditText editStartTimeChild = binding.editStartTimeChild;
        Button save = binding.btnSave;


        /* Ajout de l'email d'un participant */
        btnAddParticipant.setOnClickListener(v -> {
            viewModel.addParticipantToTextView(textFromTextInputLayout(editParticipants));
            editParticipants.getEditText().setText(null);
            Toast.makeText(this, "Participant ajouté", Toast.LENGTH_SHORT).show();
        });

        btnDeleteParticipant.setOnClickListener(v -> {
            viewModel.deleteLastParticipantToTextView();
        });

        viewModel.getLiveDataListeEmails().observe(this, listeEmails -> {
            tvListeEmails.setText(Utils.listToStringRevert(listeEmails));
            if (btnDeleteParticipant.getVisibility() == View.GONE && listeEmails.size() > 0) {
                btnDeleteParticipant.setVisibility(View.VISIBLE);
                tvTitreListeMails.setVisibility(View.VISIBLE);
            }
            else if (btnDeleteParticipant.getVisibility() == View.VISIBLE && listeEmails.size() == 0) {
                btnDeleteParticipant.setVisibility(View.GONE);
                tvTitreListeMails.setVisibility(View.GONE);
            }
        });



        /*Ajouts des emails déjà enregistrés dans le AutocompleteTextView*/
        List<String> allEmails = new ArrayList<>();
        viewModel.getLiveDataAllEmails().observe(this, allEmails::addAll);
        Log.i(TAG, "Test emails: " + allEmails.toString());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, allEmails);
        editParticipantsChild.setAdapter(adapter2);


        /* Pop-up pour les salles  */
        editRoomChild.setOnClickListener(v -> {
            Dialog popupWindow = new Dialog(this);
            List<String> rooms = Room.getAllNames();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, rooms);
            ListView listView = new ListView(this);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                editRoom.getEditText().setText(rooms.get(position));
                popupWindow.dismiss();
            });
            listView.setAdapter(adapter);
            popupWindow.setContentView(listView);
            popupWindow.show();
        });


        /*TimePickerDialog */
        editStartTimeChild.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);
            TimePickerDialog picker = new TimePickerDialog(this, R.style.AppTheme_Dialog, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                    editStartTime.getEditText().setText(getString(R.string.time_for_timePicker, sHour, sMinute));
                }
            }, hour, minutes, true);
            picker.show();
        });


        /*DatePickerDialog */
        editDateChild.setOnClickListener(v -> {

            DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                    editDateChild.setText(getString(R.string.date_for_datePicker, dayOfMonth, monthOfYear+1, year));
                }
            };

            Calendar currentDate = Calendar.getInstance();
            DatePickerDialog d = new DatePickerDialog(this, R.style.AppTheme_Dialog, dpd, currentDate.get(Calendar.YEAR) ,currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
            d.show();
        });



        /* Validation du formulaire */
        save.setOnClickListener(v -> {
            viewModel.addMeetingLiveData(textFromTextInputLayout(editTopic), textFromTextInputLayout(editRoom), textFromTextInputLayout(editStartTime), textFromTextInputLayout(editDate));
            viewModel.getValidGeneral().observe(this, res -> {
                Log.i("res ", res.toString());
                if (!res) { // Test si au moins une erreur dans le formulaire
                    viewModel.getValidTopic().observe(this, vTopic -> {
                        if (!vTopic) editTopic.setError(getString(R.string.error_empty_topic));
                        else editTopic.setError(null);
                    });
                    viewModel.getValidPlace().observe(this, vPlace -> {
                        if (!vPlace) editRoom.setError(getString(R.string.error_editRoom_empty));
                        else editRoom.setError(null);
                    });
                    viewModel.getValidParticipants().observe(this, vParticipants -> {
                        if (!vParticipants) editParticipants.setError(getString(R.string.error_editParticipants_empty));
                        else editParticipants.setError(null);
                    });
                    viewModel.getValidTime().observe(this, vTime -> {
                        if (!vTime) editStartTime.setError(getString(R.string.error_editTime_empty));
                        else editStartTime.setError(null);
                    });
                    viewModel.getValidDate().observe(this, vDate -> {
                        if (!vDate) editDate.setError(getString(R.string.error_editDate_empty));
                        else editDate.setError(null);
                    });
                }
                else {
                    finish();
                }
            });
        });
    }

}