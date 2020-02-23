package com.freenow.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.freenow.dataaccessobject.custom.DriverSearchRepositoryCustom;
import com.freenow.domainobject.DriverFullDO;
import com.freenow.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverFullDO, Long>, DriverSearchRepositoryCustom
{

    List<DriverFullDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
