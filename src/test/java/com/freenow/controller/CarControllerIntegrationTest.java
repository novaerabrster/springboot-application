package com.freenow.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.freenow.service.car.CarService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarControllerIntegrationTest extends BaseIntegrationTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    CarController controller;

    @Autowired
    CarService carService;


    @Test
    public void test01_testControllerInjected()
    {
        assertThat(controller).isNotNull();
    }


    @Test
    public void test02_findCarsByCategoryTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/cars?category=LUXURY")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].category", is("LUXURY")));
    }


    @Test
    public void test03_findCarByIdTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/cars/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }


    @Test
    public void test04_findNonExistingCarByIdTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/cars/999")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void test05_createCarTest() throws Exception
    {
        mvc
            .perform(
                post("/v1/cars/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"category\": \"REGULAR\",\n" +
                            "  \"color\": \"BLACK\",\n" +
                            "  \"convertible\": true,\n" +
                            "  \"engineType\": \"ELECTRIC\",\n" +
                            "  \"id\": 0,\n" +
                            "  \"licensePlate\": \"string\",\n" +
                            "  \"manufacturer\": \"MERCEDEZ\",\n" +
                            "  \"modelYear\": 0,\n" +
                            "  \"rating\": 0,\n" +
                            "  \"seatCount\": 0\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }


    @Test
    public void test06_createDuplicateCarTest() throws Exception
    {
        mvc
            .perform(
                post("/v1/cars/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"category\": \"REGULAR\",\n" +
                            "  \"color\": \"BLACK\",\n" +
                            "  \"convertible\": true,\n" +
                            "  \"engineType\": \"ELECTRIC\",\n" +
                            "  \"id\": 0,\n" +
                            "  \"licensePlate\": \"string\",\n" +
                            "  \"manufacturer\": \"MERCEDEZ\",\n" +
                            "  \"modelYear\": 0,\n" +
                            "  \"rating\": 0,\n" +
                            "  \"seatCount\": 0\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }


    @Test
    public void test07_deleteCarTest() throws Exception
    {
        mvc
            .perform(
                delete("/v1/cars/6")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.OK.value()));
        mvc
            .perform(
                get("/v1/cars?category=REGULAR")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].category", is("REGULAR")));
    }


    @Test
    public void test08_deleteNonExistingCarTest() throws Exception
    {
        mvc
            .perform(
                delete("/v1/cars/999")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test09_updateCarTest() throws Exception
    {
        mvc
            .perform(
                patch("/v1/cars/1?category=REGULAR")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.OK.value()));
        mvc
            .perform(
                get("/v1/cars?category=REGULAR")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].category", is("REGULAR")));

        mvc
            .perform(
                get("/v1/cars/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.category", is("REGULAR")));
    }


    @Test
    public void test_10updateNonExistingCarTest() throws Exception
    {
        mvc
            .perform(
                patch("/v1/cars/999?category=REGULAR")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
