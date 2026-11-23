package org.lld.splitwise.core;

import org.lld.splitwise.models.Expense;
import org.lld.splitwise.models.Transaction;
import org.lld.splitwise.models.User;
import org.lld.splitwise.models.UserPair;
import org.lld.splitwise.observers.SplitObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceSheet implements SplitObserver {
    private static final double SETTLEMENT_TOLERANCE = 0.001;
    private Map<UserPair, Double> balances = new HashMap<>();

    @Override
    public void onExpenseAdded(Expense expense) {
        updateBalances(expense);
    }

    @Override
    public void onExpenseUpdated(Expense expense) {
        updateBalances(expense);
    }

    private void updateBalances(Expense expense) {
        User payer = expense.getPayer();
        Map<User, Double> shares = expense.getShares();

        for (User participant : shares.keySet()) {
            double amount = shares.get(participant);

            if (!payer.equals(participant)) {
                UserPair userPair = new UserPair(participant, payer);

                Double currentBalance = balances.getOrDefault(userPair, 0.0);
                balances.put(userPair, currentBalance + amount);
            }
        }
    }

    public double getTotalBalance(User user) {
        double totalBalance = 0.0;

        for (UserPair userPair : balances.keySet()) {
            double amount = balances.get(userPair);

            if (userPair.getUser1() == user) {
                totalBalance -= amount;
            }
            if (userPair.getUser2() == user) {
                totalBalance += amount;
            }
        }

        return totalBalance;
    }

    public List<Transaction> getSimplifiedTransactions() {
        Map<User, Double> netBalances = new HashMap<>();

        for (UserPair userPair : balances.keySet()) {
            double amount = balances.get(userPair);

            User debitor = userPair.getUser1();
            User creditor = userPair.getUser2();

            netBalances.put(debitor, netBalances.getOrDefault(debitor, 0.0) - amount);
            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
        }

        List<User> debitors = new ArrayList<>();
        List<User> creditors = new ArrayList<>();

        for (User user : netBalances.keySet()) {
            double value = netBalances.get(user);

            if (value < 0) {
                debitors.add(user);
            } else if (value > 0) {
                creditors.add(user);
            }
        }

        List<Transaction> transactions = new ArrayList<>();
        int debtorIndex = 0;
        int creditorindex = 0;

        while (debtorIndex < debitors.size() && creditorindex < creditors.size()) {
            User debitor = debitors.get(debtorIndex);
            User creditor = creditors.get(creditorindex);

            double debitorBalance = netBalances.get(debitor);
            double creditorBalance = netBalances.get(creditor);

            double transferAmount = Math.min(Math.abs(debitorBalance), creditorBalance);

            transactions.add(new Transaction(debitor, creditor, transferAmount));

            netBalances.put(debitor, debitorBalance + transferAmount);
            netBalances.put(creditor, creditorBalance - transferAmount);

            if (Math.abs(netBalances.get(debitor)) < 0.001) {
                debtorIndex++;
            }
            if (Math.abs(netBalances.get(creditor)) < 0.001) {
                creditorindex++;
            }
        }
        return transactions;
    }

    public int getSubOptimalMinimumSettlements() {
        List<Double> unsettledBalances = getUnsettledBalances();
        return subOptimalDfs(0, unsettledBalances, unsettledBalances.size());
    }

    private int subOptimalDfs(int currentUserIndex, List<Double> creditList, int n) {
        while (currentUserIndex < n && creditList.get(currentUserIndex) == 0) {
            currentUserIndex++;
        }
        if (currentUserIndex == n) {
            return 0;
        }

        int cost = Integer.MAX_VALUE;
        for (int nextIndex = currentUserIndex + 1; nextIndex < n; nextIndex++) {
            if (creditList.get(currentUserIndex) * creditList.get(nextIndex) < 0) {
                creditList.set(nextIndex, creditList.get(nextIndex) + creditList.get(currentUserIndex));
                cost = Math.min(cost, 1 + subOptimalDfs(currentUserIndex + 1, creditList, n));
                creditList.set(nextIndex, creditList.get(nextIndex) - creditList.get(currentUserIndex));
            }
        }
        return cost;
    }

    private List<Double> getUnsettledBalances() {
        Map<User, Double> netBalances = new HashMap<>();

        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
            UserPair userPair = entry.getKey();
            double amount = entry.getValue();

            User user1 = userPair.getUser1();
            User user2 = userPair.getUser2();

            netBalances.put(user1, netBalances.getOrDefault(user1, 0.0) - amount);
            netBalances.put(user2, netBalances.getOrDefault(user2, 0.0) + amount);
        }

        List<Double> unsettledBalances = new ArrayList<>();
        for (double balance : netBalances.values()) {
            if (Math.abs(balance) > SETTLEMENT_TOLERANCE) {
                unsettledBalances.add(balance);
            }
        }
        return unsettledBalances;
    }

    public int getMinimumSettlementsDp() {
        List<Double> unsettledBalances = getUnsettledBalances();
        int userCount = unsettledBalances.size();
        if (userCount >= Integer.SIZE - 1) {
            throw new IllegalArgumentException("Array-based settlement DP supports at most 30 unsettled users");
        }

        int subsetCount = 1 << userCount;
        double[] subsetBalances = new double[subsetCount];
        int[] maxGroups = new int[subsetCount];

        for (int mask = 1; mask < subsetCount; mask++) {
            int firstUserBit = Integer.lowestOneBit(mask);
            int firstUser = Integer.numberOfTrailingZeros(firstUserBit);
            subsetBalances[mask] = subsetBalances[mask ^ firstUserBit] + unsettledBalances.get(firstUser);

            for (int user = 0; user < userCount; user++) {
                int userBit = 1 << user;
                if ((mask & userBit) != 0) {
                    int previousMask = mask ^ userBit;
                    maxGroups[mask] = Math.max(maxGroups[mask], maxGroups[previousMask]);
                }
            }

            if (Math.abs(subsetBalances[mask]) <= SETTLEMENT_TOLERANCE) {
                maxGroups[mask]++;
            }
        }

        return userCount - maxGroups[subsetCount - 1];
    }
}
