package com.mchadeville.mareu.data.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;
import com.mchadeville.mareu.data.model.Filter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class FilterRepositoryTest {

    private FilterRepository filterRepository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setUp() {
    filterRepository = new FilterRepository();
    }

    @Test
    public void addFilterRoom() {
        Room roomTest = Room.SALLE_A;

        filterRepository.addFilterRoom(roomTest);
        int numFiltres = filterRepository.getFilterLiveData().getValue().getFiltersRooms().size();
        assertEquals(3, numFiltres);
    }

    @Test
    public void deleteFilterRoom() {
        Room roomTest = Room.SALLE_A;
        filterRepository.deleteFilterRoom(roomTest);
        int numFiltres = filterRepository.getFilterLiveData().getValue().getFiltersRooms().size();
        assertEquals(2, numFiltres);
    }

    @Test
    public void updateFilterDate() {
        FilterDate filterTest = FilterDate.DATE_7DAYS;
        filterRepository.updateFilterDate(filterTest);
        assertEquals(filterTest, filterRepository.getFilterLiveData().getValue().getFilterDate() );
    }
}