package org.lld.splitwise.strageties.splits.concrete;

import org.lld.splitwise.models.User;
import org.lld.splitwise.strageties.splits.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit implements Split {
    @Override
    public Map<User, Double> calculateShare(double amount, List<User> participants, Map<String, Object> splitDetails) {
        double amountPerUser=amount/participants.size();

        Map<User,Double> shares=new HashMap<>();
        for(User user:participants){
            shares.put(user,amountPerUser);
        }

        return  shares;
    }
}
