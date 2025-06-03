package com.expense.mvc.model;

import java.util.List;

public class ExpenseSummary {
    private double total;
    private double averageDaily;
    private List<Expense> top3;

    public ExpenseSummary(double total, double averageDaily, List<Expense> top3) {
        this.total = total;
        this.averageDaily = averageDaily;
        this.top3 = top3;
    }

    public double getTotal() {
        return total;
    }

    public double getAverageDaily() {
        return averageDaily;
    }

    public List<Expense> getTop3() {
        return top3;
    }
} 