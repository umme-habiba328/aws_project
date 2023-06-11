package com.academy.finalProject.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.academy.finalProject.service.CourseService;
import com.academy.finalProject.service.TrainingSessionService;

@Aspect
@Configuration
public class AspectController {
	
	@Autowired
	TrainingSessionService trainingSessionService;
	
	@Autowired
	CourseService courseService;

	@Before(value = "execution(* com.academy.finalProject.service.TrainingSessionService.getTrainingSessionForCourse(..))")
	public void updateStatusBeforeGetTrainingSessionForCourse(JoinPoint joinPoint) {
		trainingSessionService.updateTrainingSessionStatusService();
	}
	
	@Before(value = "execution(* com.academy.finalProject.service.TrainingSessionService.getTrainingSessionByDate(..))")
	public void updateStatusBeforeGetTrainingSessionByDate(JoinPoint joinPoint) {
		trainingSessionService.updateTrainingSessionStatusService();
	}
	
	@Before(value = "execution(* com.academy.finalProject.service.TrainingSessionService.getById(..))")
	public void updateStatusBeforeGetTrainingSessionById(JoinPoint joinPoint) {
		trainingSessionService.updateTrainingSessionStatusService();
	}
	
	@Before(value = "execution(* com.academy.finalProject.service.CourseService.getCourseService(..))")
	public void updateStatusBeforeGetCourseService(JoinPoint joinPoint) {
		courseService.updateCourseStatusService();
	}
	
	@Before(value = "execution(* com.academy.finalProject.service.CourseService.getCourseByTitleService(..))")
	public void updateStatusBeforeGetCourseByTitleService(JoinPoint joinPoint) {
		courseService.updateCourseStatusService();
	}
}
