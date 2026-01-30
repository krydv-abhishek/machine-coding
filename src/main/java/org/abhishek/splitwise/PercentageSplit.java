package org.abhishek.splitwise;

import org.abhishek.splitwise.model.Balance;
import org.abhishek.splitwise.model.PercentageExpense;
import org.abhishek.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplit implements Split<PercentageExpense> {

    @Override
    public List<Balance> splitBalances(PercentageExpense expense) {

        List<Integer> dividedAmount = expense.getDividedPercentageList();
        List<Balance> balances = new ArrayList<>();
        int amount = expense.getAmount();
        int i = 0;
        for (User user : expense.getSplitAcrossUserList()) {
            balances.add(new Balance(user, amount * ((double) dividedAmount.get(i++) / 100)));
        }
        return balances;
    }
}
