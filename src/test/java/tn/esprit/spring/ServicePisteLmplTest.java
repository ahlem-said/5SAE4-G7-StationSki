package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServicePisteLmplTest {
    @InjectMocks
    private PisteServicesImpl pisteServices;

    @Mock
    private IPisteRepository pisteRepository;


    @Test
    public void testRetrieveAllPistes() {
        // Définir le comportement du mock pisteRepository
        List<Piste> pisteList = new ArrayList<>();
        pisteList.add(new Piste(/* Données de test */));
        when(pisteRepository.findAll()).thenReturn(pisteList);

        // Appeler la méthode du service
        List<Piste> result = pisteServices.retrieveAllPistes();

        // Effectuer des assertions pour vérifier le comportement
        assertEquals(1, result.size());
        // ... autres assertions
    }
    @Test
    public void testAddPiste() {
        // Définir le comportement du mock pisteRepository pour la méthode save
        when(pisteRepository.save(any(Piste.class))).thenReturn(new Piste(/* Données de test */));

        // Appeler la méthode du service
        Piste piste = new Piste(/* Données de test */);
        Piste result = pisteServices.addPiste(piste);

        // Effectuer des assertions pour vérifier le comportement
        assertNotNull(result);
        // ... autres assertions
    }
    @Test
    @Order(1)
    public void testRetrievesAllPistes() {
        List<Piste> listPistes = pisteServices.retrieveAllPistes();
        Assertions.assertEquals(0, listPistes.size());
    }

}
