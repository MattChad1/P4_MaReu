package com.mchadeville.mareu.ui.main.filter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mchadeville.mareu.R;
import com.mchadeville.mareu.ViewModelFactory;
import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;

import java.util.HashMap;
import java.util.Map;


public class SideSheetFilterFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    String TAG = "SideSheetFilter";
    SideSheetFilterViewModel viewModel;


    Map<Integer, Room> filtersRoomsCheckboxes = new HashMap<Integer, Room>() {
        {
            put(R.id.filter_salle_A, Room.SALLE_A);
            put(R.id.filter_salle_B, Room.SALLE_B);
            put(R.id.filter_salle_C, Room.SALLE_C);
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


    public SideSheetFilterFragment() {
    }

    public static SideSheetFilterFragment newInstance() {
        SideSheetFilterFragment fragment = new SideSheetFilterFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(SideSheetFilterViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_side_sheet_filter, container, false);

        /* Pour régler le problème sur tablette du SideSheet non visible en entier */
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                View bottomSheetInternal = d.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
                BottomSheetBehavior.from(bottomSheetInternal).setPeekHeight(0);
            }
        });


        for (Map.Entry<Integer, Room> pair : filtersRoomsCheckboxes.entrySet()) {
            ((CheckBox) v.findViewById(pair.getKey())).setText(pair.getValue().getName());
        }

        for (Integer id : filtersRoomsCheckboxes.keySet()) {
            v.findViewById(id).setOnClickListener(this);
        }
        for (Integer id : filtersDatesRadioButtons.keySet()) {
            v.findViewById(id).setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet));
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setPeekHeight(0);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View view1, int i) {
                    if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED || behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                        if (!isStateSaved())
                            dismissAllowingStateLoss();
                    }
                }

                @Override
                public void onSlide(@NonNull View view1, float v) {
                }
            });


});
    }

    @Override
    public void onClick(View v) {
        if (v instanceof CheckBox && ((CheckBox) v).isChecked()) viewModel.addFilter(filtersRoomsCheckboxes.get(v.getId()));
        else if (v instanceof CheckBox && !((CheckBox) v).isChecked()) viewModel.deleteFilter(filtersRoomsCheckboxes.get(v.getId()));

        if (v instanceof RadioButton && ((RadioButton) v).isChecked()) viewModel.updateFilterDate(filtersDatesRadioButtons.get(v.getId()));
    }
}