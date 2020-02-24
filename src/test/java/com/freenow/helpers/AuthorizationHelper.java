package com.freenow.helpers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freenow.datatransferobject.OAuthResponseDTO;

public class AuthorizationHelper
{
    public  static OAuthResponseDTO getOauthData(MockMvc mvc) throws Exception, IOException, JsonParseException, JsonMappingException, UnsupportedEncodingException
    {
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
        return
            objectMapper
                .readValue(
                    result
                        .getResponse()
                        .getContentAsString(),
                    OAuthResponseDTO.class);
    }
}
