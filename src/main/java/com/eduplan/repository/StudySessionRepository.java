package com.eduplan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eduplan.model.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession, Long>{

    List<StudySession> findByStudyPlanId(Long planId);

}