package br.com.soulit.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import br.com.soulit.ServerApplication;
import br.com.soulit.datatransferobject.OAuthResponseDTO;
import br.com.soulit.helpers.AuthorizationHelper;

@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = ServerApplication.class)
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

