package org.lld.splitwise.observers;

import org.lld.splitwise.models.Expense;

public interface SplitObserver {
    void onExpenseAdded(Expense expense);
    void onExpenseUpdated(Expense expense);
}
