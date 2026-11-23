package org.lld.splitwise.factory;

import org.lld.splitwise.strageties.splits.Split;
import org.lld.splitwise.strageties.splits.concrete.EqualSplit;
import org.lld.splitwise.strageties.splits.concrete.PercentageSplit;

public class SplitFactory {
    public static Split createSplit(String splitType){
        switch (splitType.toUpperCase()){
            case "EQUAL":
                return new EqualSplit();
            case "PERCENTAGE":
                return  new PercentageSplit();
            default:
                throw  new IllegalArgumentException("Unknown split type: " + splitType);
        }
    }
}
