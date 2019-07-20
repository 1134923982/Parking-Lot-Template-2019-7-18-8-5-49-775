package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLotOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotOrderService mockParkingLotOrderService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_return_new_parking_lot_order_when_request_add_a_parking_lot() throws Exception {
        Mockito.when(
                mockParkingLotOrderService.parkCar(Mockito.any())
        ).thenReturn(new ParkingLotOrder(1,null,"'A12345",null,null,true));

        mockMvc.perform(post("/parking-lot-orders").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "\t\"carId\":\"A12346\",\n" +
                        "\t\"parkingLot\":{\n" +
                        "\t    \"id\": 1,\n" +
                        "\t    \"name\": \"good parking lot 1\",\n" +
                        "\t    \"capacity\": 20,\n" +
                        "\t    \"position\": \"beijing\"\n" +
                        "\t}\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"parkingLot\":null,\"carId\":\"'A12345\",\"createTime\":null,\"endTime\":null,\"orderStatus\":true}"));
    }

    @Test
    public void should_return_parking_lot_order_with_end_time_and_status_is_false_when_request_fetch_car() throws Exception {
        Mockito.when(
                mockParkingLotOrderService.fetchCar(Mockito.anyLong())
        ).thenReturn(new ParkingLotOrder(1,null,"'A12345",null,null,false));

        mockMvc.perform(put("/parking-lot-orders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderStatus").value(false));
    }

}