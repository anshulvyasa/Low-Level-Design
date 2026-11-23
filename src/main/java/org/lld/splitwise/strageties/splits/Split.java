package org.lld.splitwise.strageties.splits;

import org.lld.splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface Split {
    Map<User,Double> calculateShare(double amount, List<User> participants, Map<String, Object> splitDetails);
}
