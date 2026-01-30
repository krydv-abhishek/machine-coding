package org.abhishek.splitwise.model;

import java.util.List;

public class ExactExpense extends Expense {

    private List<Integer> dividedAmountList;

    public ExactExpense(int amount, User paidBy, List<User> splitAcrossUserList, SplitType expenseType) {
        super(amount, paidBy, splitAcrossUserList, expenseType);
    }

    public List<Integer> getDividedAmountList() {
        return dividedAmountList;
    }

    public void setDividedAmountList(List<Integer> dividedAmountList) {
        if (dividedAmountList.size() != this.getNoOfUsersSplitAcross()) {
            throw new RuntimeException("Add amount for all users");
        }
        int totalAmount = dividedAmountList.stream().mapToInt(Integer::intValue).sum();

        if (totalAmount != this.getAmount()) {
            throw new RuntimeException("All exact amounts doesn't add up to total amount");
        }
        this.dividedAmountList = dividedAmountList;
    }
}
