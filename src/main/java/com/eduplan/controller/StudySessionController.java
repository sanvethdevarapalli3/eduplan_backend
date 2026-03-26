package com.eduplan.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.eduplan.model.StudySession;
import com.eduplan.service.StudySessionService;

@RestController
@RequestMapping("/sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class StudySessionController {

    private final StudySessionService sessionService;

    public StudySessionController(StudySessionService sessionService){
        this.sessionService = sessionService;
    }

    @PostMapping
    public StudySession createSession(@RequestBody StudySession session){
        return sessionService.createSession(session);
    }

    @GetMapping("/plan/{planId}")
    public List<StudySession> getSessionsByPlan(@PathVariable Long planId){
        return sessionService.getSessionsByPlan(planId);
    }

    @PutMapping("/complete/{sessionId}")
    public StudySession markCompleted(@PathVariable Long sessionId){
        return sessionService.markCompleted(sessionId);
    }
}