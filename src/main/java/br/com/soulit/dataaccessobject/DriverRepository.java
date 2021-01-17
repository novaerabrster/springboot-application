package br.com.soulit.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.soulit.dataaccessobject.custom.DriverSearchRepositoryCustom;
import br.com.soulit.domainobject.DriverFullDO;
import br.com.soulit.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverFullDO, Long>, DriverSearchRepositoryCustom
{

    List<DriverFullDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
