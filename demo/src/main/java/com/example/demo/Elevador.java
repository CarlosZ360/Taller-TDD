package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Elevador {
    private int currentFloor;
    private int direction;
    private List<Person> people;

    public List<Person> getPeople() {
        return people;
    }

    public Elevador() {
        currentFloor = 1;
        direction = 1;
        people = new ArrayList<>();
    }

    public void operate() {
        while (true) {
            move();
            openDoor();
            closeDoor();
        }
    }

    public void addPerson(Person p) {
        people.add(p);
        p.pressButton();
    }

    private void move() {
        currentFloor += direction;
        System.out.println("Elevador en piso " + currentFloor);
        checkForPeople();
        checkDirection();
    }

    private void checkForPeople() {
        List<Person> arrivedPeople = new ArrayList<>();
        for (Person p : people) {
            if (p.getDestination() == currentFloor) {
                arrivedPeople.add(p);
            }
        }
        if (!arrivedPeople.isEmpty()) {
            System.out.println("Personas en el elevador: " + people.size());
            System.out.println("Personas que han llegado a su destino: " + arrivedPeople.size());
            people.removeAll(arrivedPeople);
        }
    }

    private void checkDirection() {
        if (currentFloor == 1) {
            direction = 1;
        } else if (currentFloor == 3) {
            direction = -1;
        } else {
            boolean hasUp = false;
            boolean hasDown = false;
            for (Person p : people) {
                if (p.getDestination() > currentFloor) {
                    hasUp = true;
                } else {
                    hasDown = true;
                }
            }
            if (hasUp && !hasDown) {
                direction = 1;
            } else if (hasDown && !hasUp) {
                direction = -1;
            }
        }
    }

    private void openDoor() {
        System.out.println("Puerta abierta en piso " + currentFloor);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void closeDoor() {
        System.out.println("Puerta cerrada en piso " + currentFloor);
    }
}

class Person {
    private int currentFloor;
    private int destination;

    public Person(int currentFloor) {
        this.currentFloor = currentFloor;
        Random random = new Random();
        do {
            destination = random.nextInt(3) + 1;
        } while (destination == currentFloor);
    }

    public void pressButton() {
        System.out.println("Persona en piso " + currentFloor + " ha presionado el botón para ir al piso " + destination);
    }

    public int getDestination() {
        return destination;
    }
}
