package com.mchadeville.mareu.ui.addMeeting;

import static com.mchadeville.mareu.util.Utils.textFromTextInputLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
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
        TextInputLayout editParticipants = binding.editParticipants;
        AutoCompleteTextView editParticipantsChild = binding.editParticipantsChild;
        Button btnAddParticipant = binding.btnAddParticipant;
        TextView tvListeEmails = binding.listeEmails;
        Button btnDeleteParticipant = binding.btnDeleteParticipant;
        TextInputLayout editRoom = binding.editRoom;
        TextInputLayout editStartTime = binding.editStartTime;
        TextInputLayout editDate = binding.editDate;
        TextInputEditText editStartTimeChild = binding.editStartTimeChild;
        TextInputEditText editRoomChild = binding.editRoomChild;
        Button save = binding.btnSave;


        /* Ajout de l'email d'un participant */
        btnAddParticipant.setOnClickListener(v -> {
            viewModel.addParticipantToTextView(textFromTextInputLayout(editParticipants));
            editParticipants.getEditText().setText(null);
        });

        btnDeleteParticipant.setOnClickListener(v -> {
            viewModel.deleteLastParticipantToTextView();
        });

        viewModel.getLiveDataListeEmails().observe(this, listeEmails -> {
            Log.i(TAG, "onCreate: modif viewModel.getLiveDataListeEmails");
            tvListeEmails.setText(Utils.listToStringRevert(listeEmails));
            if (btnDeleteParticipant.getVisibility()== View.GONE && listeEmails.size()>0) btnDeleteParticipant.setVisibility(View.VISIBLE);
            else if (btnDeleteParticipant.getVisibility()== View.VISIBLE && listeEmails.size()==0) btnDeleteParticipant.setVisibility(View.GONE);
        });



        /*Ajouts des emails déjà enregistrés sur le AutocompleteTextView
         */
        List<String> allEmails = new ArrayList<>();
        viewModel.getLiveDataAllEmails().observe(this, allEmails::addAll);
        Log.i(TAG, "Test emails: " + allEmails.toString());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, allEmails);
        editParticipantsChild.setAdapter(adapter2);


        /* Pop-up pour les salles  */
        editRoomChild.setOnClickListener(v -> {
            Dialog popupWindow = new Dialog(this);
            Resources res = getResources();
            String[] rooms = res.getStringArray(R.array.rooms_array);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, rooms);
            ListView listView = new ListView(this);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                editRoom.getEditText().setText(rooms[position]);
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
            TimePickerDialog picker = new TimePickerDialog(this, R.style.AppTheme_Dialog , new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                    editStartTime.getEditText().setText(sHour + ":" + sMinute);
                }
            }, hour, minutes, true);
            picker.show();
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
                }
                else {
                    onBackPressed();
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);

                }
            });
        });
    }
}