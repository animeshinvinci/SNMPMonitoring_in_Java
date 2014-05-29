package com.snmp.example;



public class Interface {
	private final String  ifIndex = "1.3.6.1.2.1.2.2.1.1";
	private final String  name = "1.3.6.1.2.1.2.2.1.2";
	private final String  physicalAddr = "1.3.6.1.2.1.2.2.1.6";
	private final String  adminstatus = "1.3.6.1.2.1.2.2.1.7";
	
	
	
	
	public String getIfIndex() {
		return ifIndex;
	}
	public String getName() {
		return name;
	}
	public String getPhysicalAddr() {
		return physicalAddr;
	}
	public String getAdminstatus() {
		return adminstatus;
	}
	
	
}
