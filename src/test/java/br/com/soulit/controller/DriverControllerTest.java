package br.com.soulit.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.soulit.controller.DriverController;
import br.com.soulit.service.driver.DriverService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverControllerTest extends BaseIntegrationTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    @Qualifier("driverController")
    DriverController controller;

    @Autowired
    DriverService service;

    @Before
    public void setUp() throws Exception
    {
        super.setUp(mvc);
    }


    @Test
    public void test01_findDriverByIdTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/drivers/1")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }


    @Test
    public void test02_findNonExistingDriverByIdTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/drivers/999")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test03_findDriversByStatusTest() throws Exception
    {
        mvc
            .perform(
                get("/v1/drivers?onlineStatus=ONLINE")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
        //.andExpect(jsonPath("$[0].category", is("LUXURY")));
    }


    @Test
    public void test04_createDriverTest() throws Exception
    {
        mvc
            .perform(
                post("/v1/drivers")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"coordinate\": {\n" +
                            "    \"latitude\": 90,\n" +
                            "    \"longitude\": 180\n" +
                            "  },\n" +
                            "  \"id\": 0,\n" +
                            "  \"password\": \"string\",\n" +
                            "  \"username\": \"string\"\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }


    @Test
    public void test05_createDriverWithSameNameTest() throws Exception
    {
        mvc
            .perform(
                post("/v1/drivers")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\n"
                            +
                            "  \"coordinate\": {\n" +
                            "    \"latitude\": 90,\n" +
                            "    \"longitude\": 180\n" +
                            "  },\n" +
                            "  \"id\": 0,\n" +
                            "  \"password\": \"string\",\n" +
                            "  \"username\": \"string\"\n" +
                            "}"))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }


    @Test
    public void test06_deleteDriverTest() throws Exception
    {
        mvc
            .perform(
                delete("/v1/drivers/6")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.OK.value()));

    }


    @Test
    public void test07_deleteNonExistingDriverTest() throws Exception
    {
        mvc
            .perform(
                delete("/v1/drivers/999")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }


    @Test
    public void test08_updateDriverTest() throws Exception
    {
        mvc
            .perform(
                put("/v1/drivers/5?longitude=30&latitude=30")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.OK.value()));

    }


    @Test
    public void test09_updateNonExistingDriverTest() throws Exception
    {
        mvc
            .perform(
                put("/v1/drivers/999?longitude=30&latitude=30")
                    .header("Authorization", "Bearer " + authResponse.getAccessToken())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
