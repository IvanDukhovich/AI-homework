package com.expense.mvc.controller;

import com.expense.mvc.model.Expense;
import com.expense.mvc.model.ExpenseModel;
import com.expense.mvc.model.ExpenseSummary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseMvcController {
    private final ExpenseModel model = new ExpenseModel();

    @GetMapping("/")
    public String showPage(Model viewModel) {
        viewModel.addAttribute("expenses", model.getExpenses());
        viewModel.addAttribute("expense", new Expense());
        viewModel.addAttribute("summary", model.getSummary());
        return "expense";
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expense expense, Model viewModel) {
        model.addExpense(expense);
        viewModel.addAttribute("expenses", model.getExpenses());
        viewModel.addAttribute("expense", new Expense());
        viewModel.addAttribute("summary", model.getSummary());
        return "expense";
    }

    @PostMapping("/delete/{index}")
    public String deleteExpense(@PathVariable int index, Model viewModel) {
        model.removeExpense(index);
        viewModel.addAttribute("expenses", model.getExpenses());
        viewModel.addAttribute("expense", new Expense());
        viewModel.addAttribute("summary", model.getSummary());
        return "expense";
    }
} 