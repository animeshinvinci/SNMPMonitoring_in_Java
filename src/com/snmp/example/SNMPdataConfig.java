package com.snmp.example;

import org.snmp4j.mp.SnmpConstants;

public class SNMPdataConfig {
	private String ipaddress;
	private String communityString;
	private int port = 161;
	private int snmpversion = SnmpConstants.version2c;
	private int no_Of_retries = 3;
	private int snmp_timeout = 1000;

	
	public SNMPdataConfig() {
		super();
	}
	
	public int getSnmpversion() {
		return snmpversion;
	}

	public void setSnmpversion(int snmpversion) {
		this.snmpversion = snmpversion;
	}

	public String getIpaddress() {
		return ipaddress;
	}
	
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
	
	public int getNo_Of_retries() {
		return no_Of_retries;
	}
	
	public void setNo_Of_retries(int no_Of_retries) {
		this.no_Of_retries = no_Of_retries;
	}
	
	public String getCommunityString() {
		return communityString;
	}
	
	public void setCommunityString(String communityString) {
		this.communityString = communityString;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getSnmp_timeout() {
		return snmp_timeout;
	}

	public void setSnmp_timeout(int snmp_timeout) {
		this.snmp_timeout = snmp_timeout;
	}


}
