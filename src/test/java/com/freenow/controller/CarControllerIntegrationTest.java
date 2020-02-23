package com.freenow.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.freenow.FreeNowServerApplicantTestApplication;
import com.freenow.service.car.CarService;


@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = FreeNowServerApplicantTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application.properties")

public class CarControllerIntegrationTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    CarController controller;

    @Autowired
    CarService carService;

    //    @TestConfiguration
    //    static class CarControllerIntegrationTestContextConfiguration
    //    {
    //        @Autowired
    //        private CarRepository repository;
    //
    //        @Bean
    //        public CarService carService()
    //        {
    //            return new DefaultCarService(repository);
    //        }
    //
    //    }

    @Test
    public void testControllerInjected() {
        assertThat(controller).isNotNull();
    }


    @Test
    public void findCarsByCategoryTest() throws Exception
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
    public void findCarByIdTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/cars/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void createCarTest() throws Exception
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
            .andExpect(status().is(201));
    }

}
