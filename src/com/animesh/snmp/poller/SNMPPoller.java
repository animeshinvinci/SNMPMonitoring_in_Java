package com.animesh.snmp.poller;

import org.apache.log4j.Logger;

import com.animesh.log.logging.Logging;


public class SNMPPoller{
	
	
	private String IPAddress ;
	private String communityStr;
	private String SNMPver;
	private String SNMPPort;
	
	public SNMPPoller(String iPAddress, String communityStr, String sNMPver,
			String sNMPPort) {
		super();
		IPAddress = iPAddress;
		this.communityStr = communityStr;
		SNMPver = sNMPver;
		SNMPPort = sNMPPort;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getCommunityStr() {
		return communityStr;
	}
	public void setCommunityStr(String communityStr) {
		this.communityStr = communityStr;
	}
	public String getSNMPver() {
		return SNMPver;
	}
	public void setSNMPver(String sNMPver) {
		SNMPver = sNMPver;
	}
	public String getSNMPPort() {
		return SNMPPort;
	}
	public void setSNMPPort(String sNMPPort) {
		SNMPPort = sNMPPort;
	}
	
	public void doPoll(){
		
		
	}

}
