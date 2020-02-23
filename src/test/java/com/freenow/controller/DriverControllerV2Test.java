package com.freenow.controller;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

import com.freenow.service.driver.DriverService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverControllerV2Test extends BaseIntegrationTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    //    @Qualifier("driverControllerV2")
    DriverControllerV2 controller;

    @Autowired
    DriverService service;

    @Test
    public void test00_driverSelectsCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/4/car/select/5")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                get("/v1/drivers/4")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(4)))
            .andExpect(jsonPath("$.car.id", is(5)));
    }


    @Test
    public void test01_driverSelectsCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/5/car/select/5")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.CONFLICT.value()));

    }

    @Test
    public void test02_nonExistingDriverSelectsCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/999/car/select/2")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test03_driverSelectsNonExistingCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/4/car/select/999")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test04_driverRemovesCar() throws Exception
    {
        mvc
            .perform(
                delete("/v2/drivers/4/car/remove")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                get("/v1/drivers/4")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(4)))
            .andExpect(jsonPath("$.car").doesNotExist());
    }

    @Test
    public void test05_nonExistingDriverRemovesCar() throws Exception
    {
        mvc
            .perform(
                delete("/v2/drivers/999/car/remove")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
