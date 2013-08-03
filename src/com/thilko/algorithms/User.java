package com.thilko.algorithms;

import org.joda.time.*;

import java.util.Date;

public class User {

    private DateTime activationTime;

    public boolean isActivated() {
        return DateTime.now().isAfter(activationTime);
    }

    void setActivationTime(DateTime activationTime){
        this.activationTime = activationTime;
    }
}
