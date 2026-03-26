package com.eduplan.controller;

import org.springframework.web.bind.annotation.*;
import com.eduplan.service.StudyPlanService;
import com.eduplan.dto.StudyPlanRequest;
import com.eduplan.model.StudyPlan;

@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "http://localhost:5173")
public class StudyPlanController {

    private final StudyPlanService planService;

    public StudyPlanController(StudyPlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/generate")
    public StudyPlan generatePlan(@RequestBody StudyPlanRequest request) {
        return planService.generatePlan(request);
    }

    @GetMapping("/user/{userId}")
    public StudyPlan getLatestPlan(@PathVariable Long userId) {
        return planService.getLatestPlanByUser(userId);
    }
}