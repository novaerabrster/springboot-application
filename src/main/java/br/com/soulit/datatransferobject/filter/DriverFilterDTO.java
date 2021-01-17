package br.com.soulit.datatransferobject.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.soulit.domainvalue.OnlineStatus;

public class DriverFilterDTO
{
    @JsonProperty(required = false)
    private Long id;
    @JsonProperty(required = false)
    private String username;
    @JsonProperty(required = false)
    private OnlineStatus onlineStatus;

    protected DriverFilterDTO(Long id, String username, OnlineStatus onlineStatus)
    {
        super();
        this.id = id;
        this.username = username;
        this.onlineStatus = onlineStatus;
    }

    public static class DriverFilterDTOBuilder
    {
        private Long id;
        private String username;
        private OnlineStatus onlineStatus;

        public DriverFilterDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverFilterDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverFilterDTOBuilder setOnlineStatus(OnlineStatus onlineStatus)
        {
            this.onlineStatus = onlineStatus;
            return this;
        }


        public DriverFilterDTO build()
        {
            return new DriverFilterDTO(id, username, onlineStatus);
        }

    }

    public DriverFilterDTOBuilder newBuilder()
    {
        return new DriverFilterDTOBuilder();
    }


    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }
}
