package org.electrogrid.paf_group147.facility_handling.model;

public class facilityModel {

	private int serviceID;
	private String serviceName;
	private String serviceType;
	private Double unitCost;
	private int maxUnit;
	private Double addCost;
	
	public facilityModel() {
		
	}
	
	public facilityModel(int serviceID, String serviceName, String serviceType, Double unitCost, int maxUnit,
			Double addCost) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.unitCost = unitCost;
		this.maxUnit = maxUnit;
		this.addCost = addCost;
	}

	public int getServiceID() {
		return serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public int getMaxUnit() {
		return maxUnit;
	}

	public Double getAddCost() {
		return addCost;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public void setMaxUnit(int maxUnit) {
		this.maxUnit = maxUnit;
	}

	public void setAddCost(Double addCost) {
		this.addCost = addCost;
	}
	
}
