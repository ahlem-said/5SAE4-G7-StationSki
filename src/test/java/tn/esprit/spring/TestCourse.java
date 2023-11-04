package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

 class TestCourse {

        @Mock
        ICourseRepository iCourseRepository;
        @InjectMocks
        CourseServicesImpl courseServices;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void testRetrieveCourse() {
            Course course = new Course(1L, 2, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 4, new HashSet<>());
            List<Course> listCourses = new ArrayList<Course>() {
                {
                    add(new Course(2L, 3, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 4, new HashSet<>()));
                    add(new Course(3L, 4, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 4, new HashSet<>()));
                }
            };
            Mockito.when(iCourseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));

            Course course1 = courseServices.retrieveCourse(1L);
            assertNotNull(course1);
            System.out.println("course");


        }

        @Test
        void testAddCourse() {
            // Créez un objet Course fictif pour le test
            Course course = new Course(1L, 2, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 4, new HashSet<>());

            // Définissez le comportement du mock pour la méthode save
            Mockito.when(iCourseRepository.save(Mockito.any(Course.class))).thenReturn(course);

            // Appelez la méthode à tester
            Course addedCourse = courseServices.addCourse(course);

            // Vérifiez si la méthode save a été appelée avec le bon objet Course
            Mockito.verify(iCourseRepository).save(course);

            // Vérifiez si la méthode a renvoyé le bon objet Course
            assertNotNull(addedCourse);
            System.out.println("course ADDED");

        }

        @Test
        void testRetrieveAllCourses() {
            // Créez des exemples de cours simulés (ou utilisez des données réelles si nécessaire)
            List<Course> expectedCourses = new ArrayList<>();
            expectedCourses.add(new Course(1L, 2, TypeCourse.INDIVIDUAL, Support.SKI, 50.0f, 4, new HashSet<>()));
            expectedCourses.add(new Course(2L, 3, TypeCourse.COLLECTIVE_CHILDREN, Support.SNOWBOARD, 60.0f, 3, new HashSet<>()));

            // Configurez le comportement simulé pour le repository
            Mockito.when(iCourseRepository.findAll()).thenReturn(expectedCourses);

            // Appelez la méthode à tester
            List<Course> retrievedCourses = courseServices.retrieveAllCourses();

            // Assurez-vous que la liste de cours renvoyée correspond à ce que vous attendez
            assertEquals(expectedCourses.size(), retrievedCourses.size());
            System.out.println("courses");
        }

        @Test
        void testUpdateCourse() {
            // Créez un exemple de cours que vous souhaitez mettre à jour
            Course courseToUpdate = new Course(1L, 2, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 50.0f, 4, new HashSet<>());

            // Configurez le comportement simulé pour la méthode `save` du repository
            Mockito.when(iCourseRepository.save(courseToUpdate)).thenReturn(courseToUpdate);

            // Appelez la méthode à tester
            Course updatedCourse = courseServices.updateCourse(courseToUpdate);

            // Assurez-vous que le cours a bien été mis à jour
            assertNotNull(updatedCourse);
            System.out.println("course updated");
        }

        @Test
        void testRemoveCourse() {
            // Numéro du cours à supprimer
            Long numCourse = 1L;

            // Configurez le mock pour la méthode deleteById
            Mockito.doNothing().when(iCourseRepository).deleteById(1L);

            // Appelez la méthode à tester
            courseServices.removeCourse(numCourse);

            // Assurez-vous que le cours a bien été supprimé
            verify(iCourseRepository).deleteById(1L);
            System.out.println("course deleted");
        }
    }

