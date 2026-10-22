package com.example.demo.controller;

import com.example.demo.dto.DailySummaryDTO;
import com.example.demo.model.FoodLog;
import com.example.demo.service.FoodLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = "*")
public class FoodLogController {

    private final FoodLogService foodLogService;

    public FoodLogController(FoodLogService foodLogService) {
        this.foodLogService = foodLogService;
    }

    @GetMapping
    public ResponseEntity<DailySummaryDTO> getDailySummary(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "2000") int target) {
        if (date == null) {
            date = LocalDate.now();
        }
        DailySummaryDTO summary = foodLogService.getDailySummary(date, target);
        return ResponseEntity.ok(summary);
    }

    @PostMapping
    public ResponseEntity<FoodLog> logFood(@RequestBody FoodLog foodLog) {
        FoodLog saved = foodLogService.logFood(foodLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodLog> updateFood(
            @PathVariable Long id,
            @RequestBody FoodLog foodLog) {
        return foodLogService.updateFoodLog(id, foodLog)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        boolean deleted = foodLogService.deleteFoodLog(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/trend")
    public ResponseEntity<List<Map<String, Object>>> getTrend(
            @RequestParam(defaultValue = "7") int days,
            @RequestParam(defaultValue = "2000") int target) {
        return ResponseEntity.ok(foodLogService.getPastDaysTrend(days, target));
    }
}
