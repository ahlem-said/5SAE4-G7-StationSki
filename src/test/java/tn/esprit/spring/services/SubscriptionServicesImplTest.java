package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyString;
@SpringBootTest
class SubscriptionServicesImplTest {


    @InjectMocks

    private SubscriptionServicesImpl subscriptionService;
    @Mock
    private ISubscriptionRepository subscriptionRepository;
    @Mock
    private Logger log;
    @Mock
    private ISkierRepository skierRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
        //skierRepository=mock(ISkierRepository.class
    @Test
    void testAddSubscription() {
        // Given
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL); // Set the subscription type
        subscription.setStartDate(LocalDate.now()); // Set a valid start date


        when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);
        Subscription result = subscriptionService.addSubscription(subscription);

        // Then
        assertEquals(TypeSubscription.ANNUAL, result.getTypeSub());
        assertNotNull(result.getStartDate()); // Ensure start date is not null
        // Add more assertions based on your business logic
    }

    @Test
    void testUpdateSubscription() {
        // Given
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.SEMESTRIEL); // Set the subscription type
        subscription.setStartDate(LocalDate.now()); // Set a valid start date

        when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);

        // When
        Subscription result = subscriptionService.updateSubscription(subscription);

        // Then
        assertEquals(TypeSubscription.SEMESTRIEL, result.getTypeSub());
        assertNotNull(result.getStartDate());
        // Add more assertions based on your business logic
    }

    @Test
    void testRetrieveSubscriptionById() {
        // Given
        Long subscriptionId = 1L;
        Subscription subscription = new Subscription();
        subscription.setNumSub(subscriptionId);
        subscription.setTypeSub(TypeSubscription.MONTHLY); // Set the subscription type
        subscription.setStartDate(LocalDate.now()); // Set a valid start date

        when(subscriptionRepository.findById(subscriptionId)).thenReturn(Optional.of(subscription));

        // When
        Subscription result = subscriptionService.retrieveSubscriptionById(subscriptionId);

        // Then
        assertEquals(subscription, result);
        assertNotNull(result.getStartDate());
        // Add more assertions based on your business logic
    }

    @Test
    void testGetSubscriptionByType() {
        // Given
        TypeSubscription type = TypeSubscription.ANNUAL;
        Set<Subscription> subscriptions = new HashSet<>();
        Subscription subscription = new Subscription();
        subscription.setTypeSub(type);
        subscription.setStartDate(LocalDate.now()); // Set a valid start date
        subscriptions.add(subscription);

        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(subscriptions);

        // When
        Set<Subscription> result = subscriptionService.getSubscriptionByType(type);

        // Then
        assertEquals(subscriptions, result);
        assertNotNull(result.iterator().next().getStartDate());
        // Add more assertions based on your business logic
    }


//    @Test
//    void testUpdateSubscription() {
//        // Given
//        Subscription subscription = new Subscription();
//        subscription.setTypeSub(TypeSubscription.SEMESTRIEL);
//
//        // When
//        when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(subscription);
//        Subscription result = subscriptionService.updateSubscription(subscription);
//
//        // Then
//        assertEquals(TypeSubscription.SEMESTRIEL, result.getTypeSub());
//        // Add more assertions based on your business logic
//    }
//
//    @Test
//    void testRetrieveSubscriptionById() {
//        // Given
//        Long subscriptionId = 1L;
//        Subscription subscription = new Subscription();
//        subscription.setNumSub(subscriptionId);
//
//        // When
//        when(subscriptionRepository.findById(subscriptionId)).thenReturn(Optional.of(subscription));
//        Subscription result = subscriptionService.retrieveSubscriptionById(subscriptionId);
//
//        // Then
//        assertEquals(subscription, result);
//        // Add more assertions based on your business logic
//    }
//
//    @Test
//    void testGetSubscriptionByType() {
//        // Given
//        TypeSubscription type = TypeSubscription.MONTHLY;
//        Set<Subscription> subscriptions = new HashSet<>();
//        Subscription subscription = new Subscription();
//        subscription.setTypeSub(type);
//        subscriptions.add(subscription);
//
//        // When
//        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(subscriptions);
//        Set<Subscription> result = subscriptionService.getSubscriptionByType(type);
//
//        // Then
//        assertEquals(subscriptions, result);
//        // Add more assertions based on your business logic
//    }
//
//    @Test
//    void testRetrieveSubscriptionsByDates() {
//        // Given
//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = LocalDate.now().plusMonths(1);
//        List<Subscription> subscriptions = Arrays.asList(new Subscription(), new Subscription());
//
//        // When
//        when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(subscriptions);
//        List<Subscription> result = subscriptionService.retrieveSubscriptionsByDates(startDate, endDate);
//
//        // Then
//        assertEquals(subscriptions, result);
//        // Add more assertions based on your business logic
//    }
//
//    @Test
//    void testRetrieveSubscriptions() {
//        // Given
//        Subscription subscription = new Subscription();
//        subscription.setNumSub(1L);
//        Skier skier = new Skier();
//        skier.setFirstName("John");
//        skier.setLastName("Doe");
//
//        // When
//        when(subscriptionRepository.findDistinctOrderByEndDateAsc()).thenReturn(Collections.singletonList(subscription));
//        when(skierRepository.findBySubscription(subscription)).thenReturn(skier);
//
//        // Then
//        subscriptionService.retrieveSubscriptions();
//        // You can use verify to check if the log.info() method was called with the expected parameters
//        verify(skierRepository, times(1)).findBySubscription(subscription);
//    }




    }
