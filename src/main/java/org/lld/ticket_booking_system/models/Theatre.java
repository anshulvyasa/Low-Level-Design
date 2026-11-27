package org.lld.ticket_booking_system.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Theatre {
    private int id;
    private String name;
    private List<Screen> screens;

    public Theatre(int id,String name){
        this.id=id;
        this.name=name;
        this.screens=new ArrayList<>();
    }

    public  void addScreen(Screen screen){
        screens.add(screen);
    }
}
