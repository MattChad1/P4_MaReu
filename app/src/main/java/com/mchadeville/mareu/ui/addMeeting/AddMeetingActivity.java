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
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Arrays;
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
        Button btnSave = binding.btnSave;


        /* Ajout de l'email d'un participant */
        btnAddParticipant.setOnClickListener(v -> {
            viewModel.addParticipantToTextView(textFromTextInputLayout(editParticipants));
            editParticipants.getEditText().setText(null);
            Toast.makeText(this, "Participant ajouté", Toast.LENGTH_SHORT).show();
        });

        btnDeleteParticipant.setOnClickListener(v -> viewModel.deleteLastParticipantToTextView());

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

        viewModel.getErreurMailLD().observe(this, editParticipants::setError);


        /*Ajouts des emails déjà enregistrés dans le AutocompleteTextView*/
        List<String> allEmails = new ArrayList<>();
        viewModel.getLiveDataAllEmails().observe(this, allEmails::addAll);
        Log.i(TAG, "Test emails: " + allEmails.toString());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, allEmails);
        editParticipantsChild.setAdapter(adapter2);




        /* Pop-up pour les salles  */
        editRoomChild.setOnClickListener(v -> {
            Dialog popupWindow = new Dialog(this);
            List<String> rooms = Room.getAllNames();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, rooms);
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
            TimePickerDialog picker = new TimePickerDialog(this, R.style.AppTheme_Dialog, (tp, sHour, sMinute) -> editStartTime.getEditText().setText(getString(R.string.time_for_timePicker, sHour, sMinute)), hour, minutes, true);
            picker.show();
        });


        /*DatePickerDialog */
        editDateChild.setOnClickListener(v -> {

            DatePickerDialog.OnDateSetListener dpd = (view, year, monthOfYear, dayOfMonth) -> editDateChild.setText(getString(R.string.date_for_datePicker, dayOfMonth, monthOfYear + 1, year));

            Calendar currentDate = Calendar.getInstance();
            DatePickerDialog d = new DatePickerDialog(this, R.style.AppTheme_Dialog, dpd, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
            d.show();
        });

        /* Messages d'erreur */
        viewModel.getViewStateLiveData().observe(this, viewState -> {
            editTopic.setError(viewState.getTopicError());
            editParticipants.setError(viewState.getParticipantsError());
            editRoom.setError(viewState.getRoomError());
            editDate.setError(viewState.getDateError());
            editStartTime.setError(viewState.getTimeError());

            if (viewState.getValidGeneral()) finish();
        });

        btnSave.setOnClickListener(v -> {
            List<String> participantsInput = new ArrayList();
            if (!tvListeEmails.getText().toString().equals("")) participantsInput = Arrays.asList(tvListeEmails.getText().toString().split("\n"));
            viewModel.addMeetingLiveData(textFromTextInputLayout(editTopic), textFromTextInputLayout(editRoom), participantsInput, textFromTextInputLayout(editDate), textFromTextInputLayout(editStartTime));
        });
    }
}