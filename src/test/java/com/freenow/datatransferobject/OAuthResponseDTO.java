package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthResponseDTO
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
