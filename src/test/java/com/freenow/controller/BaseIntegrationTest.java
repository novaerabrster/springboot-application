package com.freenow.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Base64;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freenow.FreeNowServerApplicantTestApplication;

@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = FreeNowServerApplicantTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application.properties")
public class BaseIntegrationTest
{
    protected OAuthResponse authResponse;

    protected static class OAuthResponse
    {
        @JsonProperty(value = "access_token")
        private String accessToken;
        @JsonProperty(value = "token_type")
        private String tokenType;
        @JsonProperty(value = "expires_in")
        private Long expiresIn;
        @JsonProperty
        private String scope;

        public String getAccessToken()
        {
            return accessToken;
        }


        public void setAccessToken(String accessToken)
        {
            this.accessToken = accessToken;
        }


        public String getTokenType()
        {
            return tokenType;
        }


        public void setTokenType(String tokenType)
        {
            this.tokenType = tokenType;
        }


        public Long getExpiresIn()
        {
            return expiresIn;
        }


        public void setExpiresIn(Long expiresIn)
        {
            this.expiresIn = expiresIn;
        }


        public String getScope()
        {
            return scope;
        }


        public void setScope(String scope)
        {
            this.scope = scope;
        }


        @Override
        public String toString()
        {
            return "OAuthResponse [accessToken="
                + accessToken + ", tokenType="
                + tokenType + ", expiresIn=" + expiresIn + ", scope=" + scope + "]";
        }
    }

    public void setUp(MockMvc mvc) throws Exception
    {
        if (this.authResponse != null)
        {
            return;
        }
        RestTemplate restTemplate = new RestTemplate();
        String credentials = "c23e47fa-e28b-4813-8a62-521060f58db9:d54cbb5b-7985-487a-b4e6-1f2ae3997d81";
        String encodedCredentials = new String(Base64.getEncoder().encode(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);

        MvcResult result =
            mvc
                .perform(
                    post("/oauth/token?grant_type=client_credentials")
                        .header("Authorization", "Basic " + encodedCredentials))
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        this.authResponse = objectMapper.readValue(result.getResponse().getContentAsString(), OAuthResponse.class);

    }
}
