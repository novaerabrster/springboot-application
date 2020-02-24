package com.freenow.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.freenow.FreeNowServerApplicantTestApplication;
import com.freenow.datatransferobject.OAuthResponseDTO;
import com.freenow.helpers.AuthorizationHelper;

@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = FreeNowServerApplicantTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application.properties")
public abstract class BaseIntegrationTest
{
    protected OAuthResponseDTO authResponse;

    public void setUp(MockMvc mvc) throws Exception
    {
        if (this.authResponse != null)
        {
            return;
        }
        this.authResponse = AuthorizationHelper.getOauthData(mvc);

    }
}

