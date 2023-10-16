package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevadorTest {

    private Elevador elevador;

    @BeforeEach
    public void setUp() {
        elevador = new Elevador();
    }

    @Test
    public void testCreatePerson() {
        Person p = new Person(1);
        elevador.addPerson(p);
        Assertions.assertEquals(1, elevador.getPeople().size());
        Assertions.assertEquals(p, elevador.getPeople().get(0));
    }

    @Test
    public void testElevatorOperation() {
        // Crea personas en distintos pisos
        Person p1 = new Person(1);
        Person p2 = new Person(2);
        //Person p3 = new Person(3);

        // Agrerga personas al elevador
        elevador.addPerson(p2);
        //elevador.addPerson(p3);
        elevador.addPerson(p1);


        // Elevador debería ir al piso 2 primero para recoger a p2
        elevador.operate();
        Assertions.assertEquals(2, elevador.getClass());

        // Insertar p2 en el elevador y p1 y p3 no
        Assertions.assertEquals(1, elevador.getPeople().size());
        Assertions.assertEquals(p2, elevador.getPeople().get(0));

        // Elevador debería ir al piso 3 para recoger a p3
        elevador.operate();
        Assertions.assertEquals(3, elevador.getClass());

        // Inserta p2 y p3 en el elevador y p1 no
        Assertions.assertEquals(2, elevador.getPeople().size());
        Assertions.assertTrue(elevador.getPeople().contains(p2));
        //Assertions.assertTrue(elevador.getPeople().contains(p3));

        // Elevador debería ir al piso 1 para dejar a p2 y p3
        elevador.operate();
        Assertions.assertEquals(1, elevador.getClass());

        // Elevador debería ir al piso 1 para dejar a p2 y p3
        Assertions.assertEquals(0, elevador.getPeople().size());
    }
}

