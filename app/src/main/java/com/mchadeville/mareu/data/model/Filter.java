package com.mchadeville.mareu.data.model;

import com.mchadeville.mareu.data.FilterDate;
import com.mchadeville.mareu.data.Room;

import java.util.List;
import java.util.Objects;

public class Filter {
    List<Room> filtersRooms;
    FilterDate filterDate;

    public Filter(List<Room> filtersRooms, FilterDate filterDate) {
        this.filtersRooms = Objects.requireNonNull(filtersRooms, "filterRooms can not be null");
        this.filterDate = Objects.requireNonNull(filterDate, "filterDate can not be null");
    }

    public List<Room> getFiltersRooms() {
        return filtersRooms;
    }

    public FilterDate getFilterDate() {
        return filterDate;
    }
}
