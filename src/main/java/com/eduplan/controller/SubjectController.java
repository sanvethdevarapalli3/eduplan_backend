package com.eduplan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.eduplan.model.Subject;
import com.eduplan.service.SubjectService;

@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "http://localhost:5173")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/{userId}")
    public Subject addSubject(@PathVariable Long userId,
                              @RequestBody Subject subject) {

        return subjectService.addSubject(userId, subject);
    }
    
    @GetMapping
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }
}