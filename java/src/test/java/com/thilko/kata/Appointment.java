package com.thilko.kata;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Appointment {
    private LocalDateTime time;
    private Duration duration;

    public Appointment(LocalDateTime time, Duration duration) {
        this.time = time;
        this.duration = duration;
    }

    public Appointment() {
        time = LocalDateTime.now();
        duration = Duration.ofMinutes(15);
    }

    public boolean isConflicting(Appointment appointment) {
        if (appointment.time.isBefore(time)) {
            return Duration.between(appointment.time, time).minus(appointment.duration).isNegative();
        } else {
            return Duration.between(time, appointment.time).minus(duration).isNegative();
        }

    }
}
