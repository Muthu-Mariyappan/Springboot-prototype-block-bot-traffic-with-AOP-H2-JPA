package com.gmm.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gmm.entities.IPEntry;


@Repository("ipEntryRepository") // sets role - marks this as DAO -- Data access object
public interface IPEntryRepository extends CrudRepository<IPEntry,String>{
	
	@Query(value="select * from IPTABLE where IP = :ip",nativeQuery = true)//use of native query to get value by string value
	public IPEntry getEntryByIP(@Param("ip") String ip);
}
