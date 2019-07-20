package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingPotRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingPotRepository parkingPotRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Transactional
    @Test
    public void should_return_new_parking_lot_when_request_add_a_parking_lot() throws Exception {

        mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"name\":\"1 lot\",\n" +
                        "    \"capacity\":10,\n" +
                        "    \"position\":\"beijing\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("1 lot"));
    }

    @Transactional
    @Test
    public void should_return_code_status_is_ok_when_delete_parking_lot_by_id() throws Exception {
        ParkingLot parkingLot = parkingPotRepository.findAll().get(0);
        mockMvc.perform(delete("/parking-lots/"+parkingLot.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_3_parking_lots_when_request_pagesize_is_3() throws Exception {
        int expectValue = parkingPotRepository.findAll().size()>3?3:parkingPotRepository.findAll().size();
        mockMvc.perform(get("/parking-lots?page=0&&pageSize=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectValue));
    }

    @Test
    public void should_return_parking_lots_information_when_request_parking_lot_id() throws Exception {
        ParkingLot parkingLot = parkingPotRepository.findAll().get(0);
        mockMvc.perform(get("/parking-lots/"+parkingLot.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(parkingLot.getId()));
    }

    @Transactional
    @Test
    public void should_return_parking_lot_when_update_parking_lot() throws Exception {
        mockMvc.perform(put("/parking-lots/33").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"name\": \"good parking lot 3\",\n" +
                        "    \"capacity\": 30,\n" +
                        "    \"position\": \"beijing\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity").value(30));
    }
}