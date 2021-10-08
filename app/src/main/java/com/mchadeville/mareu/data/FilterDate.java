package com.mchadeville.mareu.data;

public enum FilterDate {
    DATE_TODAY (0),
    DATE_7DAYS (7),
    DATE_30DAYS (30),
    DATE_ALL (100000);

    private final long maxDays;
    FilterDate(long maxDays) {this.maxDays = maxDays;}

    public long getMaxDays() {
        return maxDays;
    }
}
