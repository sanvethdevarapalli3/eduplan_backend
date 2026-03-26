package com.eduplan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduplan.model.StudyPlan;

@Repository
public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {

    Optional<StudyPlan> findTopByUserIdOrderByIdDesc(Long userId);

}