package com.thilko.kata;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class OrganizerTest {

    @Test
    public void create_appointment(){
        Organizer organizer = new Organizer();
        organizer.add(new Appointment());

        assertThat(organizer.appointments().size(), is(1));
    }


    @Test
    public void conflicting_appointment_is_not_added(){
        Organizer organizer = new Organizer();
        organizer.add(new Appointment(LocalDateTime.of(2016,4,5,16,30), Duration.ofMinutes(30)));

        boolean isAdded = organizer.add(new Appointment(LocalDateTime.of(2016,4,5,16,40), Duration.ofMinutes(5)));

        assertThat(isAdded, is(false));
    }

    @Test
    public void conflicting_with_a_middle_appointment_is_not_added(){
        Organizer organizer = new Organizer();
        organizer.add(new Appointment(LocalDateTime.of(2016,4,5,16,30), Duration.ofMinutes(30)));
        organizer.add(new Appointment(LocalDateTime.of(2016,4,5,17,01), Duration.ofMinutes(10)));
        organizer.add(new Appointment(LocalDateTime.of(2016,4,5,17,30), Duration.ofMinutes(20)));

        boolean isAdded = organizer.add(new Appointment(LocalDateTime.of(2016,4,5,17,12), Duration.ofMinutes(5)));

        assertThat(organizer.appointments().size(), is(4));
        assertThat(isAdded, is(true));
    }
}
