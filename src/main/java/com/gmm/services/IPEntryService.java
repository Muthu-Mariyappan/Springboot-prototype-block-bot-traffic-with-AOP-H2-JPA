package com.gmm.services;

import com.gmm.entities.IPEntry;

public interface IPEntryService {
	public IPEntry findByIP(String ip);

	public void insert(IPEntry entry);
}
