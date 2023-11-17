    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import org.springframework.boot.test.context.SpringBootTest;
    import tn.esprit.spring.GestionStationSkiApplication;
    import tn.esprit.spring.entities.*;
    import tn.esprit.spring.repositories.*;
    import tn.esprit.spring.services.SkierServicesImpl;
    import static org.junit.jupiter.api.Assertions.assertEquals;

    import java.time.LocalDate;
    import java.util.HashSet;
    import java.util.Optional;
    import java.util.Set;

    import static org.mockito.Mockito.mock;
    import static org.mockito.Mockito.when;
    @SpringBootTest(classes = GestionStationSkiApplication.class)
    public class SkierServicesImplTest {

        @InjectMocks
        private SkierServicesImpl skierServices;

        @Mock
        private ISkierRepository skierRepository;

        @Mock
        private ISubscriptionRepository subscriptionRepository;
        @Mock
        private IPisteRepository pisteRepository;
        @Mock
        private ICourseRepository courseRepository;
        @Mock
        private IRegistrationRepository registrationRepository;



        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);

            //skierRepository=mock(ISkierRepository.class);




        }


        @Test
        public void testRetrieveSkier() {
            Long numSkier = 1L;
            Skier skier = new Skier();
            skier.setNumSkier(numSkier);

            // Define the expected behavior of the mock skierRepository
            when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));

            // Call the method to be tested
            Skier retrievedSkier = skierServices.retrieveSkier(numSkier);

            // Verify the result
            assertEquals(numSkier, retrievedSkier.getNumSkier());
            // Add more assertions as needed
        }

        @Test
        public void testAddSkier() {
            // Create a sample Skier object
            Skier skier = new Skier();
            skier.setNumSkier(1L);
            skier.setFirstName("John");
            skier.setLastName("Doe");
            skier.setDateOfBirth(LocalDate.of(1990, 5, 15));
            skier.setCity("Example City");

            // Mock the behavior of SubscriptionRepository to return a valid Subscription object
         
            

            // Call the method to be tested
            Skier addedSkier = skierServices.addSkier(skier);

            // Verify the result
            assertEquals(1L, addedSkier.getNumSkier());
            assertEquals("John", addedSkier.getFirstName());
            assertEquals("Doe", addedSkier.getLastName());
            // Add more assertions as needed for other properties and behavior
        }

        @Test
        public void testAssignSkierToSubscription() {
            //skierRepository=mock(ISkierRepository.class);
            Long numSkier = 1L;
            Long numSubscription = 2L;

            Skier skier = new Skier();
            skier.setNumSkier(1L);

            Subscription subscription = new Subscription();
            subscription.setNumSub(2L);

            when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));
            when(subscriptionRepository.findById(numSubscription)).thenReturn(Optional.of(subscription));

            Skier assignedSkier = skierServices.assignSkierToSubscription(numSkier, numSubscription);

            // Verify the result
//            assertEquals(numSkier, assignedSkier.getNumSkier());
//            assertEquals(numSubscription, assignedSkier.getSubscription().getNumSub());
        }

        @Test
        public void testAddSkierAndAssignToCourse() {
            Long numCourse = 3L;

            Skier skier = new Skier();
            skier.setNumSkier(1L);
            skier.setFirstName("John");
            skier.setLastName("Doe");

            Course course = new Course();
            course.setNumCourse(numCourse);

            Set<Registration> registrations = new HashSet<>();
            Registration registration = new Registration();
            registration.setSkier(skier);
            registration.setCourse(course);
            registrations.add(registration);
            skier.setRegistrations(registrations);

            when(skierRepository.save(skier)).thenReturn(skier);
            when(courseRepository.getById(numCourse)).thenReturn(course);

            Skier addedAndAssignedSkier = skierServices.addSkierAndAssignToCourse(skier, numCourse);

            // Verify the result
            assertEquals(skier, addedAndAssignedSkier);
            assertEquals(course, addedAndAssignedSkier.getRegistrations().iterator().next().getCourse());
        }

        @Test
        public void testRemoveSkier() {
            Long numSkier = 1L;

            // Call the method to be tested
            skierServices.removeSkier(numSkier);

            // Verify that the method is called without exceptions (you can't directly assert the effect)
        }


    }


    // Ajoutez d'autres tests pour les autres méthodes de SkierServicesImpl

