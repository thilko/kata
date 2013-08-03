package com.thilko.algorithms;

import static org.hamcrest.CoreMatchers.is;
import org.joda.time.DateTime;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void isActivated_activationTimeIsPassed_returnsTrue(){
        user.setActivationTime(DateTime.now().minusHours(1));
        assertThat(user.isActivated(), is(true));
    }

    @Test
    public void isActivated_activationTimeIsAhead_returnsFalse(){
        user.setActivationTime(DateTime.now().plusHours(1));
        assertThat(user.isActivated(), is(false));
    }

}
