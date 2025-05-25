package com.gevernova.onlinecoursemanagement;

// STEP 4 - CALCULATION OF THE PERCENTAGE USING STREAMS AND JAVA8

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class CourseProgress {
    private  Course course;
    private  Map<Module, Integer> moduleScores = new HashMap<>();
    private Grading gradingStandard = new PercentageRequired();

    public CourseProgress(Course course) {
        this.course = course;
    }

    public void moduleAddition(Module module, int score) {
        try{
            if(module.getTitle() == null || module.getPassingMarks()<0){
                throw new InputMismatchException("Kindly enter valid title and marks greater than 0");
            }
            moduleScores.put(module, score);
        } catch (Exception e) {
            System.out.println("An exception has occured that says " + e.getMessage());
        }
    }

    // AS PER QUESTION - Java 8: Stream to calculate progress % and eligibility

    public double getProgressPercent() {

        return course.getModules().stream()
                .mapToDouble(m -> moduleScores.getOrDefault(m, 0) *
                        (m.getPassingMarks() / 100.0))
                .sum();
    }

    public boolean isEligible() {
        return gradingStandard.isEligible(getProgressPercent());
    }

    public Course getCourse() {
        return course;
    }
}