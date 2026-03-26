package com.eduplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduplan.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
