package com.mchadeville.mareu.ui.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.FilterRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SideSheetFilter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SideSheetFilter extends BottomSheetDialogFragment implements View.OnClickListener {

    String TAG = "SideSheetFilter";

    Map<Integer, FilterRoom> filtersRoomsCheckboxes = new HashMap<Integer, FilterRoom>() {
        {
            put(R.id.filter_salle_A, FilterRoom.SALLE_A);
            put(R.id.filter_salle_B, FilterRoom.SALLE_B);
            put(R.id.filter_salle_C, FilterRoom.SALLE_C);
        }
    };

    Map<Integer, FilterDate> filtersDatesRadioButtons = new HashMap<Integer, FilterDate>() {
        {
            put(R.id.filter_date1, FilterDate.DATE_TODAY);
            put(R.id.filter_date2, FilterDate.DATE_7DAYS);
            put(R.id.filter_date3, FilterDate.DATE_30DAYS);
            put(R.id.filter_date4, FilterDate.DATE_ALL);
        }
    };

    private List<FilterRoom> filterRoomSelected = new ArrayList<>();
    FilterDate filterDateSelected = FilterDate.DATE_ALL;


    public interface onFormListener {
        void transfertChecks(List<FilterRoom> filterRoomSelected);
    }

    onFormListener formListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            formListener = (onFormListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    public SideSheetFilter() {
        // Required empty public constructor
    }

    public static SideSheetFilter newInstance() {
        SideSheetFilter fragment = new SideSheetFilter();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterRoomSelected.addAll(Arrays.asList(FilterRoom.values()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_side_sheet_filter, container, false);

        for (Integer id : filtersRoomsCheckboxes.keySet()) {
            v.findViewById(id).setOnClickListener(this);
        }
        for (Integer id : filtersDatesRadioButtons.keySet()) {
            v.findViewById(id).setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof CheckBox && ((CheckBox) v).isChecked())
            filterRoomSelected.add(filtersRoomsCheckboxes.get(v.getId()));
        else if (filterRoomSelected.contains(filtersRoomsCheckboxes.get(v.getId())))
            filterRoomSelected.remove(filtersRoomsCheckboxes.get(v.getId()));

        if (v instanceof RadioButton && ((RadioButton) v).isChecked())
            filterDateSelected = filtersDatesRadioButtons.get(v.getId());

        Log.i(TAG, "onClick: filtersRoomSelected " + filterRoomSelected.toString());
        Log.i(TAG, "onClick: filtersDateSelected " + filterDateSelected.toString());

        formListener.transfertChecks(filterRoomSelected);
    }
}