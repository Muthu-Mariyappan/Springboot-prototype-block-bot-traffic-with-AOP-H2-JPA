package com.gmm.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmm.entities.IPEntry;
import com.gmm.repositories.IPEntryRepository;

@Transactional // Spring dynamically creates a proxy that implements the same interface(s) as
				// the class that gets annotated
@Service("ipEntryService") // to detected by @Autowired
public class IPEntryServiceImpl implements IPEntryService {

	@Autowired
	IPEntryRepository ipEntryRepository;

	@Override
	public IPEntry findByIP(String ip) {
		return this.ipEntryRepository.getEntryByIP(ip);
	}

	@Override
	public void insert(IPEntry entry) {
		this.ipEntryRepository.save(entry);
	}

}
