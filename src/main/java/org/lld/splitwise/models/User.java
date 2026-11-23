package org.lld.splitwise.models;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class User {
    private final String id;
    private final String email;

    @Setter
    private String name;

    public User(String id,String email,String name){
        this.id=id;
        this.email=email;
        this.name=name;
    }

    @Override
    public boolean equals(Object o){
        if(o==this) return  true;
        if(!(o instanceof User)) return  false;
        User user=(User) o;

        return this.id.equals(user.getId());
    }

    @Override
    public  int hashCode(){
        return  Objects.hash(id);
    }
}
