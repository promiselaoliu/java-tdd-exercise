package com.odde.tdd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetReport {
    public static long totalBudget(LocalDate start, LocalDate end, List<Budget> budgets)
    {
        List<Budget> budgetList = getTargetBudgets(start, end, budgets);
        long total = 0;
        for (Budget budget: budgetList){
            int month = budget.getMonth().getMonthValue();
            if (month == start.getMonthValue() && month == end.getMonthValue()) {
                total += (end.getDayOfMonth() - start.getDayOfMonth() + 1) * budget.getAmount()/start.lengthOfMonth();
            }else if (month == start.getMonthValue()){
                total += (start.lengthOfMonth() - start.getDayOfMonth() + 1)* budget.getAmount()/start.lengthOfMonth() ;
            }else if (month == end.getMonthValue()){
                total += (end.getDayOfMonth()) * budget.getAmount()/end.lengthOfMonth() ;
            }else {
                total += budget.getAmount();
            }
        }
        return total;
    }
    private static List<Budget> getTargetBudgets(LocalDate start, LocalDate end, List<Budget> budgets) {
        List<Budget> budgetList = new ArrayList<>();
        for (Budget budget: budgets){
            if (budget.getMonth().getYear() == start.getYear()
                    && budget.getMonth().getMonthValue() >= start.getMonthValue()
                    && budget.getMonth().getMonthValue() <= end.getMonthValue()){
                budgetList.add(budget);
            }
        }
        return budgetList;
    }
}
