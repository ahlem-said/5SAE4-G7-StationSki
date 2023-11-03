package tn.esprit.spring;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceImplTest {
    @InjectMocks
    private InstructorServicesImpl instructorService;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddInstructor() {
        Instructor instructorToSave = new Instructor(); // create an instructor
        when(instructorRepository.save(instructorToSave)).thenReturn(instructorToSave);

        Instructor savedInstructor = instructorService.addInstructor(instructorToSave);

        assertEquals(instructorToSave, savedInstructor);
    }

    @Test
    public void testRetrieveAllInstructors() {
        List<Instructor> instructors = Collections.singletonList(new Instructor()); // create a list of instructors
        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> retrievedInstructors = instructorService.retrieveAllInstructors();

        assertEquals(instructors, retrievedInstructors);
    }

    @Test
    public void testUpdateInstructor() {
        Instructor instructorToUpdate = new Instructor(); // create an instructor
        when(instructorRepository.save(instructorToUpdate)).thenReturn(instructorToUpdate);

        Instructor updatedInstructor = instructorService.updateInstructor(instructorToUpdate);

        assertEquals(instructorToUpdate, updatedInstructor);
    }

    @Test
    public void testRetrieveInstructor() {
        long numInstructor = 1L;
        Instructor instructor = new Instructor(); // create an instructor
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorService.retrieveInstructor(numInstructor);

        assertEquals(instructor, retrievedInstructor);
    }

    @Test
    public void testAddInstructorAndAssignToCourse() {
        Long numCourse = 1L;
        Instructor instructor = new Instructor(); // create an instructor
        Course course = new Course(); // create a course
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor updatedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);

        assertEquals(instructor, updatedInstructor);
        assertEquals(1, updatedInstructor.getCourses().size());
        //verify(courseRepository).findById(numCourse);
    }
}
