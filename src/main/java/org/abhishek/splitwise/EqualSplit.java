package org.abhishek.splitwise;

import org.abhishek.splitwise.model.Balance;
import org.abhishek.splitwise.model.Expense;
import org.abhishek.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplit implements Split<Expense> {

    @Override
    public List<Balance> splitBalances(Expense expense) {
        double amount = expense.getAmount() * 100;
        int noOfSplits = expense.getNoOfUsersSplitAcross();
        double amountPerUser = Math.floor(amount / noOfSplits) / 100.0;

        List<Balance> balances = new ArrayList<>();
        for (User user : expense.getSplitAcrossUserList()) {
            balances.add(new Balance(user, amountPerUser));
        }
        return balances;
    }
}
