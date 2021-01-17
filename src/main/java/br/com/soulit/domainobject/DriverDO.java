package br.com.soulit.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.soulit.domainvalue.GeoCoordinate;
import br.com.soulit.domainvalue.OnlineStatus;

@MappedSuperclass
public class DriverDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Embedded
    private GeoCoordinate coordinate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus = OnlineStatus.ONLINE;

    protected DriverDO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


    protected DriverDO(
        Long id, ZonedDateTime dateCreated, String username, GeoCoordinate coordinate,
        ZonedDateTime dateCoordinateUpdated, OnlineStatus onlineStatus)
    {
        super();
        this.id = id;
        this.dateCreated = dateCreated;
        this.username = username;
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = dateCoordinateUpdated;
        this.onlineStatus = onlineStatus;
    }


    protected DriverDO()
    {
        super();
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    public void setOnlineStatus(OnlineStatus onlineStatus)
    {
        this.onlineStatus = onlineStatus;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    public void setCoordinate(GeoCoordinate coordinate)
    {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }


    protected void clear()
    {
        this.id = null;
        this.dateCreated = null;
        this.username = null;
        this.password = null;
        this.deleted = null;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = null;
    }


    public void setUsername(String username)
    {
        this.username = username;
    }

}
