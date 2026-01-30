package org.abhishek.splitwise.repository;

import org.abhishek.splitwise.model.Expense;
import org.abhishek.splitwise.model.Balance;
import org.abhishek.splitwise.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceRepository {

    private Map<User, Map<User, Double>> localDb;

    public BalanceRepository() {
        Map<User, Expense> localDb = new HashMap<>();
    }


    public List<Balance> getBalanceByUser(User user) {

        List<Balance> balances = new ArrayList<>();
        for (Map.Entry<User, Double> entry : localDb.get(user).entrySet()) {
            if (entry.getValue() != 0) {
                balances.add(new Balance(entry.getKey(), entry.getValue()));
            }
        }
        return balances;
    }

    public void saveExpense(User user, List<Balance> balances) {
        for (Balance balance : balances) {
            localDb.get(user).put(balance.getUser(), balance.getAmount());
            localDb.get(balance.getUser()).put(user, balance.getAmount() * -1);
        }
    }

}
