package com.freenow.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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

    @Before
    public void setUp() throws Exception
    {
        super.setUp(mvc);
    }


    @Test
    public void test00_driverSelectsCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/4/car/select/5")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                get("/v1/drivers/4")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
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
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.CONFLICT.value()));

    }


    @Test
    public void test02_nonExistingDriverSelectsCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/999/car/select/2")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test03_driverSelectsNonExistingCar() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/4/car/select/999")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test04_driverRemovesCar() throws Exception
    {
        mvc
            .perform(
                delete("/v2/drivers/4/car/remove")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                get("/v1/drivers/4")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
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
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test06_searchDriverByCarCategory() throws Exception
    {
        disassociateCarsFromDrivers();
        associateDriversToCars();

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"category\": \"REGULAR\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].car.category", is("REGULAR")));



    }


    @Test
    public void test07_searchDriverByCarId() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"id\": \"1\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].car.id", is(1)));


    }


    @Test
    public void test08_searchDriverByCarLicensePlate() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"licensePlate\": \"ABC1234\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].car.licensePlate", is("ABC1234")));


    }


    @Test
    public void test08_searchDriverByCarSeatCount() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"seatCount\": \"4\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$[0].car.seatCount", is(4)));


    }


    @Test
    public void test09_searchDriverByCarConvertible() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"convertible\": \"true\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].car.convertible", is(true)));


    }


    @Test
    public void test10_searchDriverByCarColor() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"color\": \"SILVER\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].car.color", is("SILVER")));


    }


    @Test
    public void test11_searchDriverByCarRating() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"rating\": \"5\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$[0].car.rating", is(5)));


    }


    @Test
    public void test12_searchDriverByCarRating() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"rating\": \"10\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(0)));;


    }


    @Test
    public void test13_searchDriverByCarManufacturer() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"manufacturer\": \"MERCEDEZ\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].car.manufacturer", is("MERCEDEZ")));


    }


    @Test
    public void test14_searchDriverByCarEngineType() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"engineType\": \"HYBRID\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].car.engineType", is("HYBRID")));


    }


    @Test
    public void test14_searchDriverByCarModelYear() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"modelYear\": \"2020\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].car.modelYear", is(2020)));


    }


    @Test
    public void test15_searchDriverByDriverId() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"driverFilter\": {\n" +
                            "    \"id\": \"1\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(1)));


    }


    @Test
    public void test16_searchDriverByDriverName() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"driverFilter\": {\n" +
                            "    \"username\": \"driver\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(8)));


    }


    @Test
    public void test17_searchDriverByDriverOnlineStatus() throws Exception
    {

        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"driverFilter\": {\n" +
                            "    \"onlineStatus\": \"ONLINE\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(4)));


    }


    @Test
    public void test18_searchDriverByCarAndDriverID() throws Exception
    {


        mvc
            .perform(
                post("/v2/drivers/search")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"carFilter\": {\n" +
                            "    \"id\": \"1\"\n" +
                            "  },\n" +
                            "  \"driverFilter\": {\n" +
                            "    \"id\": \"1\"\n" +
                            "  }\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].car.id", is(1)));

        disassociateCarsFromDrivers();

    }

    private void disassociateCarsFromDrivers() throws Exception
    {
        mvc
            .perform(
                delete("/v2/drivers/1/car/remove")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                delete("/v2/drivers/2/car/remove")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                delete("/v2/drivers/3/car/remove")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                delete("/v2/drivers/4/car/remove")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                delete("/v2/drivers/5/car/remove")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    private void associateDriversToCars() throws Exception
    {
        mvc
            .perform(
                patch("/v2/drivers/1/car/select/1")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                patch("/v2/drivers/2/car/select/2")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                patch("/v2/drivers/3/car/select/3")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                patch("/v2/drivers/4/car/select/4")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        mvc
            .perform(
                patch("/v2/drivers/5/car/select/5")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
