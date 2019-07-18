package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingPotRepository;
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

//    @Autowired
//    private ParkingPotRepository mockParkingPotRepository;

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
        mockMvc.perform(delete("/parking-lots/33"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_3_parking_lots_when_request_pagesize_is_3() throws Exception {
        mockMvc.perform(get("/parking-lots?page=0&&pageSize=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void should_return_parking_lots_information_when_request_parking_lot_id() throws Exception {
        mockMvc.perform(get("/parking-lots/33"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(33));
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