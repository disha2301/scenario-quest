package com.gevernova;

import com.gevernova.onlinecoursemanagement.Course;
import com.gevernova.onlinecoursemanagement.CourseProgress;
import com.gevernova.onlinecoursemanagement.Module;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StudentProgressTest {

    // positive test case to check for progress percentage
    @Test
    public void testProgress_Positive_Progress(){
        Module moduleOne = new Module("Week 1", 50);
        Module moduleTwo = new Module("Week 2", 80);
        Course course = new Course("JAVA", new ArrayList<>
                (Arrays.asList(moduleOne, moduleTwo)));
        CourseProgress progress = new CourseProgress(course);
        progress.moduleAddition(moduleOne,50);
        progress.moduleAddition(moduleTwo,80);
        assertEquals(89,progress.getProgressPercent());
        assertTrue(progress.isEligible());
    }

    // positive test case to check for checking the eligibility
    @Test
    public void testProgress_Positive_Eligibility(){
        Module moduleOne = new Module("Week 1", 50);
        Module moduleTwo = new Module("Week 2", 80);
        Course course = new Course("JAVA", new ArrayList<>
                (Arrays.asList(moduleOne, moduleTwo)));
        CourseProgress progress = new CourseProgress(course);
        progress.moduleAddition(moduleOne,50);
        progress.moduleAddition(moduleTwo,80);
        assertTrue(progress.isEligible());
    }

    // negative test case check for progress percentage
    @Test
    public void testProgress_Negative_Progress(){
        Module moduleOne = new Module("Week 1", 50);
        Module moduleTwo = new Module("Week 2", 89);
        Course course = new Course("JAVA", new ArrayList<>
                (Arrays.asList(moduleOne, moduleTwo)));
        CourseProgress progress = new CourseProgress(course);
        progress.moduleAddition(moduleOne,50);
        progress.moduleAddition(moduleTwo,80);
        assertNotEquals(9,progress.getProgressPercent());
    }

    // negative test case check for progress percentage
    @Test
    public void testProgress_Negative_Eligibility(){
        Module moduleOne = new Module("Week 1", 10);
        Module moduleTwo = new Module("Week 2", 20);
        Course course = new Course("JAVA", new ArrayList<>
                (Arrays.asList(moduleOne, moduleTwo)));
        CourseProgress progress = new CourseProgress(course);
        progress.moduleAddition(moduleOne,10);
        progress.moduleAddition(moduleTwo,20);
        assertFalse(progress.isEligible());
    }

    @Test
    public void testProgress_EmptyModules() {
        Module moduleOne = new Module("Intro", 50);
        Module moduleTwo = new Module("Advanced", 50);
        Course course = new Course("Spring Boot", Arrays.asList(moduleOne, moduleTwo));
        CourseProgress progress = new CourseProgress(course);

        assertEquals(0.0, progress.getProgressPercent());
        assertFalse(progress.isEligible());
    }

    // test case with zero-weight modules
    @Test
    public void testProgress_ZeroWeightModule() {
        Module moduleOne = new Module("Theory", 0);
        Module moduleTwo = new Module("Practical", 100);
        Course course = new Course("OS", Arrays.asList(moduleOne, moduleTwo));
        CourseProgress progress = new CourseProgress(course);

        progress.moduleAddition(moduleOne, 100);
        progress.moduleAddition(moduleTwo, 50);

        assertEquals(50.0, progress.getProgressPercent());
        assertFalse(progress.isEligible());
    }

}