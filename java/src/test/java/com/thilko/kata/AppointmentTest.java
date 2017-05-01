package com.thilko.kata;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class AppointmentTest {

    @Test
    public void isConflicting_conflicting_returnsTrue(){
        Appointment a1 = new Appointment(LocalDateTime.of(2016, 4, 5, 16, 30), Duration.ofMinutes(30));
        Appointment a2 = new Appointment(LocalDateTime.of(2016, 4, 5, 16, 45), Duration.ofMinutes(10));

        Assert.assertThat(a1.isConflicting(a2), Is.is(true));
    }

    @Test
    public void isConflicting_a1Before_returnsFalse(){
        Appointment a1 = new Appointment(LocalDateTime.of(2016, 4, 5, 16, 30), Duration.ofMinutes(30));
        Appointment a2 = new Appointment(LocalDateTime.of(2016, 4, 5, 16, 20), Duration.ofMinutes(5));

        Assert.assertThat(a1.isConflicting(a2), Is.is(false));
        Assert.assertThat(a2.isConflicting(a1), Is.is(false));
    }
    
    @Test
    public void isConflicting_a2ina1_returnsTrue(){
        Appointment a1 = new Appointment(LocalDateTime.of(2016, 4, 5, 16, 30), Duration.ofMinutes(30));
        Appointment a2 = new Appointment(LocalDateTime.of(2016, 4, 5, 16, 20), Duration.ofMinutes(20));

        Assert.assertThat(a1.isConflicting(a2), Is.is(true));
        Assert.assertThat(a2.isConflicting(a1), Is.is(true));
    }
}
