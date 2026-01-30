package org.abhishek.splitwise;

import org.abhishek.splitwise.model.Balance;
import org.abhishek.splitwise.model.ExactExpense;
import org.abhishek.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class ExactSplit implements Split<ExactExpense> {

    @Override
    public List<Balance> splitBalances(ExactExpense expense) {

        List<Integer> dividedAmount = expense.getDividedAmountList();
        List<Balance> balances = new ArrayList<>();
        int i =0;
        for (User user : expense.getSplitAcrossUserList()) {
            balances.add(new Balance(user, dividedAmount.get(i++)));
        }
        return balances;
    }
}
