package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @BeforeEach
    void setUp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("19:30 24/04/2023", formatter);

        d1 = new Doctor("Pepe", "Gomez", 36, "pepe@email.com");
        p1 = new Patient("Manuel", "Castro", 25, "manuel@email.com");
        r1 = new Room("Psychology");
        a1 = new Appointment(p1, d1, r1, startsAt, finishesAt);
        a2 = new Appointment(p1, d1, r1, startsAt, finishesAt);
        a3 = new Appointment(p1, d1, r1, startsAt, finishesAt);
    }

    @Test
    void testDoctorConstructor(){
        Assertions.assertNotNull(d1);
        assertThat(d1.getFirstName()).isEqualTo("Pepe");
        assertThat(d1.getLastName()).isEqualTo("Gomez");
        assertThat(d1.getAge()).isEqualTo(36);
        assertThat(d1.getEmail()).isEqualTo("pepe@email.com");
    }

    @Test
    void testEmptyDoctorConstructor(){
        d1 = new Doctor();
        d1.setId(1);

        Assertions.assertNotNull(d1);
        Assertions.assertNull(d1.getFirstName());
        Assertions.assertNull(d1.getLastName());
        assertThat(d1.getAge()).isEqualTo(0);
        Assertions.assertNull(d1.getEmail());
        assertThat(d1.getId()).isEqualTo(1);
    }

    @Test
    void testPatientConstructor(){
        Assertions.assertNotNull(p1);
        assertThat(p1.getFirstName()).isEqualTo("Manuel");
        assertThat(p1.getLastName()).isEqualTo("Castro");
        assertThat(p1.getAge()).isEqualTo(25);
        assertThat(p1.getEmail()).isEqualTo("manuel@email.com");
    }

    @Test
    void testEmptyPatientConstructor(){
        p1 = new Patient();
        p1.setId(2);

        Assertions.assertNotNull(p1);
        Assertions.assertNull(p1.getFirstName());
        Assertions.assertNull(p1.getLastName());
        assertThat(p1.getAge()).isEqualTo(0);
        Assertions.assertNull(p1.getEmail());
        assertThat(p1.getId()).isEqualTo(2);
    }

    @Test
    void testRoomConstructor() {
        Assertions.assertNotNull(r1);
        assertThat(r1.getRoomName()).isEqualTo("Psychology");
    }

    @Test
    void testEmptyRoomConstructor() {
        r1 = new Room();

        Assertions.assertNull(r1.getRoomName());
    }

    @Test
    void testAppointmentConstructor(){
        Assertions.assertNotNull(a1);
        a1.setId(3);

        assertThat(a1.getPatient()).isEqualTo(p1);
        assertThat(a1.getDoctor()).isEqualTo(d1);
        assertThat(a1.getRoom()).isEqualTo(r1);
        assertThat(a1.getStartsAt()).isEqualTo(LocalDateTime.parse("19:30 24/04/2023", DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
        assertThat(a1.getFinishesAt()).isEqualTo(LocalDateTime.parse("19:30 24/04/2023", DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
        assertThat(a1.getId()).isEqualTo(3);
    }

    @Test
    void testAppointmentNotOverlapsDifferentRoom() {

        a2.setRoom(new Room("Rehabilitation"));
        assertThat(a3.getRoom()).isEqualTo(r1);

        assertThat(a2.overlaps(a3)).isFalse();

    }

    @Test
    void appointmentNotOverlapsSameRoomDifferentTime(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        assertThat(a2.getStartsAt()).isEqualTo((LocalDateTime.parse("19:30 24/04/2023", formatter)));
        a2.setFinishesAt(LocalDateTime.parse("20:00 24/04/2023", formatter));

        a3.setStartsAt(LocalDateTime.parse("20:00 24/04/2023", formatter));
        a3.setFinishesAt(LocalDateTime.parse("21:00 24/04/2023", formatter));

        assertThat(a2.overlaps(a3)).isFalse();

    }

    @Test
    void appointmentOverlapsSameRoomSameStartHour(){
        assertThat(a2.getRoom()).isEqualTo(a3.getRoom());
        assertThat(a2.getStartsAt()).isEqualTo(a3.getStartsAt());
        assertThat(a2.getFinishesAt()).isEqualTo(a3.getFinishesAt());

        assertThat(a2.overlaps(a3)).isTrue();
    }


    @Test
    void firstAppointmentOverlapsSecondByRoomAndHour(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        a2.setStartsAt(LocalDateTime.parse("19:00 24/04/2023", formatter));
        a2.setFinishesAt(LocalDateTime.parse("20:00 24/04/2023", formatter));

        a3.setStartsAt(LocalDateTime.parse("19:15 24/04/2023", formatter));
        a3.setFinishesAt(LocalDateTime.parse("20:15 24/04/2023", formatter));

        assertThat(a2.overlaps(a3)).isTrue();
    }

    @Test
    void secondAppointmentOverlapsfirstByRoomAndHour(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        a2.setStartsAt(LocalDateTime.parse("19:15 24/04/2023", formatter));
        a2.setFinishesAt(LocalDateTime.parse("20:15 24/04/2023", formatter));

        a3.setStartsAt(LocalDateTime.parse("19:00 24/04/2023", formatter));
        a3.setFinishesAt(LocalDateTime.parse("20:00 24/04/2023", formatter));

        assertThat(a2.overlaps(a3)).isTrue();
    }

}
