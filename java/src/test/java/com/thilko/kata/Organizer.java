package com.thilko.kata;

import java.util.ArrayList;
import java.util.List;

public class Organizer {


    private List<Appointment> app = new ArrayList<>();

    public List<Appointment> appointments() {
        return app;
    }

    public boolean add(Appointment appointment) {
        boolean isConflicting = app.stream().anyMatch((meeting) -> meeting.isConflicting(appointment));

        return !isConflicting && app.add(appointment);
    }
}
