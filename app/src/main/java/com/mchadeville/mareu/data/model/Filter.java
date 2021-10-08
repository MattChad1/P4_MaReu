package com.mchadeville.mareu.data.model;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;

import java.util.List;

public class Filter {
    List<Room> filtersRooms;
    FilterDate filterDate;

    public Filter(List<Room> filtersRooms, FilterDate filterDate) {
        this.filtersRooms = filtersRooms;
        this.filterDate = filterDate;
    }

    public List<Room> getFiltersRooms() {
        return filtersRooms;
    }

    public FilterDate getFilterDate() {
        return filterDate;
    }


}
