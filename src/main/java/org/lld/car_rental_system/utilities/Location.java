package org.lld.car_rental_system.utilities;

import lombok.Getter;

@Getter
public class Location {
    private  String address;
    private   String city;
    private  String state;
    private  String zipCode;

    public Location(String address,String city,String state,String zipCode){
        this.address=address;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
    }
}
