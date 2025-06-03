package com.expense.mvc.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseModel {
    private final List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public ExpenseSummary getSummary() {
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double average = total / 30.0;
        List<Expense> top3 = expenses.stream()
                .sorted(Comparator.comparingDouble(Expense::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
        return new ExpenseSummary(total, average, top3);
    }
} 