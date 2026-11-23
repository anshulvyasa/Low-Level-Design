package org.lld.splitwise.models;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UserPair {
    private User user1;
    private User user2;

    public UserPair(User user1,User user2){
        this.user1=user1;
        this.user2=user2;
    }

    @Override
    public int hashCode(){
        return Objects.hash(user1,user2);
    }

    @Override
    public  boolean equals(Object obj){
        if(this==obj) return  true;
        if(!(obj instanceof  UserPair)) return  false;

        UserPair userPair=(UserPair) obj;
        return  this.user1.equals(userPair.getUser1())&&this.user2.equals(userPair.getUser2());
    }
}
