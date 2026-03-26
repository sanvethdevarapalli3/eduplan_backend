package com.eduplan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eduplan.dto.StudyPlanRequest;
import com.eduplan.model.StudyPlan;
import com.eduplan.model.StudySession;
import com.eduplan.model.Topic;
import com.eduplan.model.User;
import com.eduplan.repository.StudyPlanRepository;
import com.eduplan.repository.StudySessionRepository;
import com.eduplan.repository.TopicRepository;
import com.eduplan.repository.UserRepository;

@Service
public class StudyPlanService {

    private final StudyPlanRepository planRepository;
    private final StudySessionRepository sessionRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public StudyPlanService(
            StudyPlanRepository planRepository,
            StudySessionRepository sessionRepository,
            TopicRepository topicRepository,
            UserRepository userRepository){

        this.planRepository = planRepository;
        this.sessionRepository = sessionRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public StudyPlan generatePlan(StudyPlanRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        StudyPlan plan = new StudyPlan();
        plan.setUser(user);

        plan = planRepository.save(plan);

        List<Topic> topics = topicRepository.findAll();

        topics.sort((t1, t2) ->
                (t2.getDifficultyLevel() - t2.getProficiencyLevel()) -
                (t1.getDifficultyLevel() - t1.getProficiencyLevel())
        );

        LocalDate startDate = LocalDate.parse(request.getStartDate());

        int topicIndex = 0;

        for(int i=0;i<request.getDays();i++){

            if(topicIndex >= topics.size()){
                topicIndex = 0;
            }

            Topic topic = topics.get(topicIndex);

            StudySession session = new StudySession();
            session.setTopic(topic);
            session.setStudyPlan(plan);
            session.setStudyDate(startDate.plusDays(i));
            session.setAllocatedHours(request.getHoursPerDay());
            session.setCompleted(false);

            sessionRepository.save(session);

            topicIndex++;
        }

        return plan;
    }

    public StudyPlan getLatestPlanByUser(Long userId) {
        return planRepository.findTopByUserIdOrderByIdDesc(userId)
                .orElseThrow(() -> new RuntimeException("No plan found for user"));
    }
}