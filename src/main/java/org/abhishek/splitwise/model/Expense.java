package org.abhishek.splitwise.model;

import java.util.List;

public class Expense {

    private int amount;
    private User paidBy;
    private int noOfUsersSplitAcross;
    private List<User> splitAcrossUserList;
    private SplitType expenseType;

    public Expense(int amount, User paidBy, List<User> splitAcrossUserList, SplitType expenseType) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitAcrossUserList = splitAcrossUserList;
        this.expenseType = expenseType;
    }

    public int getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getSplitAcrossUserList() {
        return splitAcrossUserList;
    }

    public SplitType getExpenseType() {
        return expenseType;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public void setSplitAcrossUserList(List<User> splitAcrossUserList) {
        this.splitAcrossUserList = splitAcrossUserList;
    }

    public void setExpenseType(SplitType expenseType) {
        this.expenseType = expenseType;
    }

    public int getNoOfUsersSplitAcross() {
        return noOfUsersSplitAcross;
    }

    public void setNoOfUsersSplitAcross(int noOfUsersSplitAcross) {
        if(noOfUsersSplitAcross<0) {
            throw new RuntimeException("Provide at least one user to split across");
        }
        this.noOfUsersSplitAcross = noOfUsersSplitAcross;
    }
}
