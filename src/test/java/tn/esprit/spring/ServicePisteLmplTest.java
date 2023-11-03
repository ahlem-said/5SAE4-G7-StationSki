package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controllers.PisteRestController;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class ServicePisteLmplTest {
    @InjectMocks
    private PisteServicesImpl pisteServices;

    @Mock
    private IPisteRepository pisteRepository;


    @Test
     void testRetrievePistesById() {
        Piste piste = new Piste(34L,"hello", Color.BLUE,11, 23);

        List<Piste> pisteList = new ArrayList<Piste>(){
           {
           add(new Piste(64L,"hello", Color.BLACK,177, 203));
           add(new Piste(68L,"asmaaa", Color.RED,101, 26));

           }
        };

        Mockito.when(pisteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(piste));
       Piste piste1 = pisteServices.retrievePiste(64L);
       Assertions.assertNotNull(piste1);
    }

   @Test
   void testAjoutPiste() {

      Piste pisteAAjouter = new Piste(34L, "hello", Color.BLUE, 11, 23);


      Mockito.when(pisteRepository.save(Mockito.any(Piste.class))).thenReturn(pisteAAjouter);

      Piste pisteAjoutee = pisteServices.addPiste(pisteAAjouter);

      Mockito.verify(pisteRepository).save(pisteAAjouter);


      Assertions.assertNotNull(pisteAjoutee);



   }

   void  testAllPistes(){
      Piste piste = new Piste(34L,"hello", Color.BLUE,11, 23);

      List<Piste> pisteList = new ArrayList<Piste>(){
         {
            add(new Piste(64L,"hello", Color.BLACK,177, 203));
            add(new Piste(68L,"asmaaa", Color.RED,101, 26));

         }
      };
      Mockito.when(pisteRepository.findAll()).thenReturn(pisteList);

      List<Piste> retrievedAllPistes = pisteServices.retrieveAllPistes();
     Assertions.assertEquals(pisteList.size(), retrievedAllPistes.size());


   }

   @Test
   void testRemovePiste () {

      Long numPiste = 1L;


      Mockito.doNothing().when(pisteRepository).deleteById(1L);


      pisteServices.removePiste(numPiste);


      Mockito.verify(pisteRepository).deleteById(1L);
   }



}
