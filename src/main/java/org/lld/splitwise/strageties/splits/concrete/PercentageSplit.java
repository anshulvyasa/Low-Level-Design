package org.lld.splitwise.strageties.splits.concrete;

import org.lld.splitwise.models.User;
import org.lld.splitwise.strageties.splits.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplit implements Split {
    @Override
    public Map<User, Double> calculateShare(double amount, List<User> participants, Map<String, Object> splitDetails) {
        Map<User,Double> splits=(Map<User, Double>) splitDetails.get("percentages");

        Map<User,Double> shares=new HashMap<>();
        for(User user:participants){
            double percentage=splits.getOrDefault(user,0.0);

            shares.put(user,amount*percentage/100);
        }

        return shares;
    }
}
