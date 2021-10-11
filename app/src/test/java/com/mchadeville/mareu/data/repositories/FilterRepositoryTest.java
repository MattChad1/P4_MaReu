package com.mchadeville.mareu.data.repositories;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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