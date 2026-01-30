package org.abhishek.splitwise;

import org.abhishek.splitwise.model.Expense;
import org.abhishek.splitwise.model.Balance;

import java.util.List;

public interface Split<T extends Expense> {

    List<Balance> splitBalances(T expense);

}
