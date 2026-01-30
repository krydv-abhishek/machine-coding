package org.abhishek.splitwise.model;

import java.util.List;

public class PercentageExpense extends Expense {

    private List<Integer> dividedPercentageList;

    public PercentageExpense(int amount, User paidBy, List<User> splitAcrossUserList, SplitType expenseType) {
        super(amount, paidBy, splitAcrossUserList, expenseType);
    }

    public List<Integer> getDividedPercentageList() {
        return dividedPercentageList;
    }

    public void setDividedPercentageList(List<Integer> dividedPercentageList) {
        if (dividedPercentageList.size() != this.getNoOfUsersSplitAcross()) {
            throw new RuntimeException("Add percentage for all users");
        }
        int totalAmount = dividedPercentageList.stream().mapToInt(Integer::intValue).sum();

        if (totalAmount != this.getAmount()) {
            throw new RuntimeException("All percentages doesn't add up to 100");
        }
        this.dividedPercentageList = dividedPercentageList;
    }

}
