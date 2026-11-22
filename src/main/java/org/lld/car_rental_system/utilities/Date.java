package org.lld.car_rental_system.utilities;

import lombok.Getter;

@Getter
public class Date {
    private final long time;
    public Date(long time){
        this.time=time;
    }
}
