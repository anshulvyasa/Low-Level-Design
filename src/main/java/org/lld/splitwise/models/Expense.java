package org.lld.splitwise.models;

import lombok.Getter;

import java.util.List;
import java.util.Map;


@Getter
public class Expense {
   private final String id;
   private String description;
   private double amount;
   private User payer;
   private List<User> participants;
   Map<User,Double> shares;

   public  Expense(String id,String description,double amount,User payer,List<User> participants,Map<User,Double> shares){
      this.id=id;
      this.description=description;
      this.amount=amount;
      this.payer=payer;
      this.participants=participants;
      this.shares=shares;
   }
}
