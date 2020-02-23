package com.freenow.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

import com.freenow.FreeNowServerApplicantTestApplication;

@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = FreeNowServerApplicantTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application.properties")
public class BaseIntegrationTest
{

}
