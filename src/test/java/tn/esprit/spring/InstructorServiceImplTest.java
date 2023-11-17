package tn.esprit.spring;


import org.junit.Before;
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

    @Before()
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

    @Test
    public void testAddInstructorWithInvalidData() {
        Instructor instructorToSave = new Instructor();
        instructorToSave.setFirstName(""); // Missing name
        when(instructorRepository.save(instructorToSave)).thenReturn(null); // Simulating save failure
        Instructor savedInstructor = instructorService.addInstructor(instructorToSave);
        assertEquals(null, savedInstructor); // Verify null return
    }

    @Test
    public void testUpdateInstructorWithMissingAttributes() {
        Instructor instructorToUpdate = new Instructor();
        instructorToUpdate.setNumInstructor(1L); // Provide ID
        when(instructorRepository.save(instructorToUpdate)).thenReturn(null); // Simulating save failure
        Instructor updatedInstructor = instructorService.updateInstructor(instructorToUpdate);
        assertEquals(null, updatedInstructor); // Verify null return
    }

    @Test
    public void testRetrieveInstructorWithNonExistentID() {
        long numInstructor = -1L; // Invalid ID
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.empty());
        Instructor retrievedInstructor = instructorService.retrieveInstructor(numInstructor);
        assertEquals(null, retrievedInstructor); // Verify null return
    }

    @Test
    public void testAddInstructorAndAssignToNonExistentCourse() {
        Long numCourse = -1L; // Invalid course ID
        Instructor instructor = new Instructor();
        Course course = new Course();
        when(courseRepository.findById(numCourse)).thenReturn(Optional.empty()); // Simulating course absence
        when(instructorRepository.save(instructor)).thenReturn(instructor);
        Instructor updatedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);
        assertEquals(instructor, updatedInstructor); // Instructor saved, but course assignment failed
        assertEquals(0, updatedInstructor.getCourses().size()); // Verify no course assigned
    }

    @Test
    public void testRetrieveAllInstructorsWithEmptyDataSet() {
        when(instructorRepository.findAll()).thenReturn(Collections.emptyList());
        List<Instructor> retrievedInstructors = instructorService.retrieveAllInstructors();
        assertEquals(Collections.emptyList(), retrievedInstructors); // Verify empty list returned
    }

    @Test
    public void testUpdateInstructorWithModifiedCourseAssociations() {
        Instructor instructorToUpdate = new Instructor();
        instructorToUpdate.setNumInstructor(1L); // Provide ID
        Course course1 = new Course();
        course1.setNumCourse(1L); // Existing course
        instructorToUpdate.getCourses().add(course1);
        Course course2 = new Course();
        course2.setNumCourse(2L); // New course
        instructorToUpdate.getCourses().add(course2);
        when(instructorRepository.save(instructorToUpdate)).thenReturn(instructorToUpdate);
        Instructor updatedInstructor = instructorService.updateInstructor(instructorToUpdate);
        assertEquals(instructorToUpdate, updatedInstructor); // Verify updated instructor
        assertEquals(2, updatedInstructor.getCourses().size()); // Verify both courses assigned
    }

}
