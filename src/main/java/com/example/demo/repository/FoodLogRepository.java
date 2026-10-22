package com.example.demo.repository;

import com.example.demo.model.FoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {
    
    List<FoodLog> findByLogDateOrderByCreatedAtDesc(LocalDate logDate);
    
    List<FoodLog> findByLogDateBetweenOrderByLogDateAsc(LocalDate startDate, LocalDate endDate);
}
