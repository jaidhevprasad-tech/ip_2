package com.example.demo.dto;

import com.example.demo.model.FoodLog;
import java.time.LocalDate;
import java.util.List;

public class DailySummaryDTO {
    private LocalDate date;
    private int totalCalories;
    private int breakfastCalories;
    private int lunchCalories;
    private int dinnerCalories;
    private int snackCalories;
    private int targetCalories;
    private int remainingCalories;
    private List<FoodLog> logs;

    public DailySummaryDTO() {
    }

    public DailySummaryDTO(LocalDate date, int totalCalories, int breakfastCalories, int lunchCalories,
                           int dinnerCalories, int snackCalories, int targetCalories,
                           int remainingCalories, List<FoodLog> logs) {
        this.date = date;
        this.totalCalories = totalCalories;
        this.breakfastCalories = breakfastCalories;
        this.lunchCalories = lunchCalories;
        this.dinnerCalories = dinnerCalories;
        this.snackCalories = snackCalories;
        this.targetCalories = targetCalories;
        this.remainingCalories = remainingCalories;
        this.logs = logs;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public int getBreakfastCalories() {
        return breakfastCalories;
    }

    public void setBreakfastCalories(int breakfastCalories) {
        this.breakfastCalories = breakfastCalories;
    }

    public int getLunchCalories() {
        return lunchCalories;
    }

    public void setLunchCalories(int lunchCalories) {
        this.lunchCalories = lunchCalories;
    }

    public int getDinnerCalories() {
        return dinnerCalories;
    }

    public void setDinnerCalories(int dinnerCalories) {
        this.dinnerCalories = dinnerCalories;
    }

    public int getSnackCalories() {
        return snackCalories;
    }

    public void setSnackCalories(int snackCalories) {
        this.snackCalories = snackCalories;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }

    public int getRemainingCalories() {
        return remainingCalories;
    }

    public void setRemainingCalories(int remainingCalories) {
        this.remainingCalories = remainingCalories;
    }

    public List<FoodLog> getLogs() {
        return logs;
    }

    public void setLogs(List<FoodLog> logs) {
        this.logs = logs;
    }
}
