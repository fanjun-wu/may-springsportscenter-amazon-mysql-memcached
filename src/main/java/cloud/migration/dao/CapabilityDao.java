package cloud.migration.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cloud.migration.model.Capability;

public interface CapabilityDao {

	
	public void add(Capability capability);
	public void edit(Capability capability);
	public void delete(int capabilityId);
	public Capability getCapability(int capabilityId);
	public List getAllCapability();
}
