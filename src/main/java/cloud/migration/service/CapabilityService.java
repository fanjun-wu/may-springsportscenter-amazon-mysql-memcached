package cloud.migration.service;

import java.util.List;

import cloud.migration.model.Capability;


public interface CapabilityService {

	public void saveCapability(Capability capability);
	public void editCapability(Capability capability);
	public void deleteCapability(int capabilityId);
	public Capability getCapability(int capabilityId);
	public List getCapabilities();
}
