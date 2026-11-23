package org.lld.splitwise.core;

import lombok.Getter;
import org.lld.splitwise.models.Expense;
import org.lld.splitwise.observers.SplitObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseController {
    @Getter
    Map<String,Expense> expenses=new HashMap<>();
    List<SplitObserver> observers=new ArrayList<>();

    public void addObserver(SplitObserver observer){
        observers.add(observer);
    }

    public  void removeObserver(SplitObserver observer){
        observers.add(observer);
    }

    public void notifyObserverOnAddExpense(Expense expense){
        for(SplitObserver observer:observers){
            observer.onExpenseAdded(expense);
        }
    }

    public void notifyObserverOnUpdateExpense(Expense expense){
        for (SplitObserver observer:observers){
            observer.onExpenseUpdated(expense);
        }
    }


    public boolean addExpense(Expense expense){
        String expenseId=expense.getId();

        if(!expenses.containsKey(expenseId)){
            expenses.put(expenseId,expense);
            notifyObserverOnAddExpense(expense);
            return  true;
        }
        return false;
    }

    private boolean updateExpenses(Expense expense) {
        String expenseId=expense.getId();

        if(!expenses.containsKey(expenseId)){
            return  false;
        }

        expenses.put(expenseId,expense);
        notifyObserverOnUpdateExpense(expense);
        return  true;
    }
}
