package com.gevernova.onlinecoursemanagement;

import java.util.ArrayList;
import java.util.Arrays;

// STEP 5 - MAIN METHOD TO GIVE DATA AND GET THE PERCENTAGE AND ELIGIBILITY RESULTS

public class Main {
    public static void main(String[] args) {

        // add students details
        Student studentOne = new Student("80", "Disha Yadav");
        Module moduleOne = new Module("Week 1", 50);
        Module moduleTwo = new Module("Week 2", 80);

        // AS PER QUESTION - List<CourseProgress>>
        Course course = new Course("JAVA", new ArrayList<>
                (Arrays.asList(moduleOne, moduleTwo)));

        // check progress
        CourseProgress progress = new CourseProgress(course);
        progress.moduleAddition(moduleOne,50);
        progress.moduleAddition(moduleTwo,80);

        // display the details
        System.out.println("Student name is - "+studentOne.getName());
        System.out.println("Student id is - "+studentOne.getId());
        System.out.println("Student progress is - "+progress.getProgressPercent());
        System.out.println("Eligible to get a Certificate? -  "+progress.isEligible());

        // single responsibility principle
        // open closed principle
        // liskov substitution principle
    }
}