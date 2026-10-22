package com.example.demo.service;

import com.example.demo.dto.DailySummaryDTO;
import com.example.demo.model.FoodLog;
import com.example.demo.repository.FoodLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FoodLogService {

    private final FoodLogRepository repository;

    public FoodLogService(FoodLogRepository repository) {
        this.repository = repository;
    }

    public DailySummaryDTO getDailySummary(LocalDate date, int targetCalories) {
        if (date == null) {
            date = LocalDate.now();
        }
        if (targetCalories <= 0) {
            targetCalories = 2000;
        }

        List<FoodLog> logs = repository.findByLogDateOrderByCreatedAtDesc(date);

        int total = 0;
        int breakfast = 0;
        int lunch = 0;
        int dinner = 0;
        int snack = 0;

        for (FoodLog log : logs) {
            int cal = log.getCalories() != null ? log.getCalories() : 0;
            total += cal;

            String meal = log.getMealType() != null ? log.getMealType().toUpperCase() : "SNACK";
            switch (meal) {
                case "BREAKFAST" -> breakfast += cal;
                case "LUNCH" -> lunch += cal;
                case "DINNER" -> dinner += cal;
                default -> snack += cal;
            }
        }

        int remaining = targetCalories - total;

        return new DailySummaryDTO(
                date,
                total,
                breakfast,
                lunch,
                dinner,
                snack,
                targetCalories,
                remaining,
                logs
        );
    }

    public FoodLog logFood(FoodLog foodLog) {
        if (foodLog.getLogDate() == null) {
            foodLog.setLogDate(LocalDate.now());
        }
        if (foodLog.getCalories() == null || foodLog.getCalories() < 0) {
            foodLog.setCalories(0);
        }
        if (foodLog.getMealType() == null || foodLog.getMealType().trim().isEmpty()) {
            foodLog.setMealType("SNACK");
        } else {
            foodLog.setMealType(foodLog.getMealType().trim().toUpperCase());
        }
        return repository.save(foodLog);
    }

    public Optional<FoodLog> updateFoodLog(Long id, FoodLog updated) {
        return repository.findById(id).map(existing -> {
            if (updated.getFoodName() != null) {
                existing.setFoodName(updated.getFoodName());
            }
            if (updated.getCalories() != null) {
                existing.setCalories(updated.getCalories());
            }
            if (updated.getMealType() != null) {
                existing.setMealType(updated.getMealType().toUpperCase());
            }
            if (updated.getServingSize() != null) {
                existing.setServingSize(updated.getServingSize());
            }
            if (updated.getLogDate() != null) {
                existing.setLogDate(updated.getLogDate());
            }
            return repository.save(existing);
        });
    }

    public boolean deleteFoodLog(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Map<String, Object>> getPastDaysTrend(int days, int targetCalories) {
        if (days <= 0) days = 7;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days - 1);

        List<FoodLog> logs = repository.findByLogDateBetweenOrderByLogDateAsc(startDate, endDate);
        Map<LocalDate, Integer> dayTotals = new HashMap<>();

        for (FoodLog log : logs) {
            dayTotals.merge(log.getLogDate(), log.getCalories(), Integer::sum);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
            Map<String, Object> item = new HashMap<>();
            item.put("date", d.toString());
            item.put("calories", dayTotals.getOrDefault(d, 0));
            item.put("target", targetCalories);
            result.add(item);
        }
        return result;
    }
}
