
package com.example.demo;

import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateDoctor() throws Exception{
        Doctor doctor = new Doctor("Zebenzui", "Hernández", 55, "zeben@email.com");

        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }

    @Test
    void shoulGetNoDoctors() throws Exception{

        List<Doctor> doctors = new ArrayList<>();

        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoDoctors() throws Exception{
        List<Doctor> doctors = new ArrayList<>();

        doctors.add(new Doctor("Juan", "Salgado", 26, "juan@email.com"));
        doctors.add(new Doctor("Amelia", "Pérez", 44, "amelia@email.com"));

        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldgetDoctorById() throws Exception{
        Doctor doctor = new Doctor("Pepe", "Martínez", 36, "pepe@email.com");
        doctor.setId(1);

        Optional<Doctor> doctorOptional = Optional.of(doctor);

        assertThat(doctorOptional).isPresent();
        assertThat(doctorOptional.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor.getId())).thenReturn(doctorOptional);
        mockMvc.perform(get("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetAnyDoctorById() throws Exception{

        final long WRONG_ID = 2;

        Doctor doctor = new Doctor("Pepe", "Martínez", 36, "pepe@email.com");
        doctor.setId(1);

        Optional<Doctor> doctorOptional = Optional.of(doctor);

        assertThat(doctorOptional).isPresent();
        assertThat(doctorOptional.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor.getId())).thenReturn(doctorOptional);
        mockMvc.perform(get("/api/doctors/" + WRONG_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteDoctorById() throws Exception{
        Doctor doctor = new Doctor("Samuel", "Hernández", 23, "samuel@email.com");
        doctor.setId(2);

        Optional<Doctor> doctorOptional = Optional.of(doctor);

        assertThat(doctorOptional).isPresent();
        assertThat(doctorOptional.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(2);

        when(doctorRepository.findById(doctor.getId())).thenReturn(doctorOptional);
        mockMvc.perform(delete("/api/doctors/" + doctor.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void shouldNotDeleteAnyDoctorById() throws Exception{
        final long WRONG_ID = 2;

        Doctor doctor = new Doctor("Pepe", "Martínez", 36, "pepe@email.com");
        doctor.setId(1);

        Optional<Doctor> doctorOptional = Optional.of(doctor);

        assertThat(doctorOptional).isPresent();
        assertThat(doctorOptional.get().getId()).isEqualTo(doctor.getId());
        assertThat(doctor.getId()).isEqualTo(1);

        when(doctorRepository.findById(doctor.getId())).thenReturn(doctorOptional);
        mockMvc.perform(delete("/api/doctors/" + WRONG_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllDoctors() throws Exception{
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());
    }

}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreatePatient() throws Exception{
        Patient patient = new Patient("Diego", "Martínez", 22, "diego@email.com");

        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());
    }

    @Test
    void shoulGetNoPatients() throws Exception{

        List<Patient> patients = new ArrayList<>();

        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoPatients() throws Exception{
        List<Patient> patients = new ArrayList<>();

        patients.add(new Patient("Ayoze", "Álvarez", 55, "ayoze@email.com"));
        patients.add( new Patient("Lorena", "Martínez", 47, "lorena@email.com"));

        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldgetPatientById() throws Exception{
        Patient patient = new Patient("Juan Marcos", "Arcos", 15, "juan@email.com");
        patient.setId(1);

        Optional<Patient> patientOptional = Optional.of(patient);

        assertThat(patientOptional).isPresent();
        assertThat(patientOptional.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(1);

        when(patientRepository.findById(patient.getId())).thenReturn(patientOptional);
        mockMvc.perform(get("/api/patients/" + patient.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetAnyPatientById() throws Exception{

        final long WRONG_ID = 2;

        Patient patient = new Patient("Ignacio", "Hernández", 30, "ignacio@email.com");
        patient.setId(1);

        Optional<Patient> patientOptional = Optional.of(patient);

        assertThat(patientOptional).isPresent();
        assertThat(patientOptional.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(1);

        when(patientRepository.findById(patient.getId())).thenReturn(patientOptional);
        mockMvc.perform(get("/api/patients/" + WRONG_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeletePatientById() throws Exception{
        Patient patient = new Patient("Alberto", "Barrios", 26, "alberto@email.com");
        patient.setId(2);

        Optional<Patient> patientOptional = Optional.of(patient);

        assertThat(patientOptional).isPresent();
        assertThat(patientOptional.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(2);

        when(patientRepository.findById(patient.getId())).thenReturn(patientOptional);
        mockMvc.perform(delete("/api/patients/" + patient.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void shouldNotDeleteAnyPatientById() throws Exception{
        final long WRONG_ID = 2;

        Patient patient = new Patient("Jennifer", "Gonzalez", 27, "jennifer@email.com");
        patient.setId(1);

        Optional<Patient> patientOptional = Optional.of(patient);

        assertThat(patientOptional).isPresent();
        assertThat(patientOptional.get().getId()).isEqualTo(patient.getId());
        assertThat(patient.getId()).isEqualTo(1);

        when(patientRepository.findById(patient.getId())).thenReturn(patientOptional);
        mockMvc.perform(delete("/api/patients/" + WRONG_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllPatients() throws Exception{
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());
    }
}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateRoom() throws Exception{
        Room room = new Room("Dermatology");

        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }

    @Test
    void shoulGetNoRoom() throws Exception{

        List<Room> rooms = new ArrayList<>();

        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoRooms() throws Exception{

        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room("Dermatology"));
        rooms.add(new Room("SpeechTherapist"));

        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldgetRoomByName() throws Exception{

        String roomName = "Psychology";
        Room room = new Room(roomName);

        Optional<Room> roomOptional = Optional.of(room);

        assertThat(roomOptional).isPresent();
        assertThat(roomOptional.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo(roomName);

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(roomOptional);
        mockMvc.perform(get("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetAnyRoomByName() throws Exception{

        Room room = new Room();

        Optional<Room> roomOptional = Optional.of(room);

        assertThat(roomOptional).isPresent();
        assertThat(roomOptional.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo(null);

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(roomOptional);
        mockMvc.perform(get("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteRoomByName() throws Exception{

        String roomName = "Psychology";
        Room room = new Room(roomName);

        Optional<Room> roomOptional = Optional.of(room);

        assertThat(roomOptional).isPresent();
        assertThat(roomOptional.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo(roomName);

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(roomOptional);
        mockMvc.perform(delete("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isOk());

    }

    @Test
    void shouldNotDeleteAnyRoomByName() throws Exception{

        Room room = new Room();

        Optional<Room> roomOptional = Optional.of(room);

        assertThat(roomOptional).isPresent();
        assertThat(roomOptional.get().getRoomName()).isEqualTo(room.getRoomName());
        assertThat(room.getRoomName()).isEqualTo(null);

        when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(roomOptional);
        mockMvc.perform(delete("/api/rooms/" + room.getRoomName()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllRooms() throws Exception{
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());
    }

}
