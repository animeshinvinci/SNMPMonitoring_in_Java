package com.snmp.example;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class DNSResolve {
	
	public static void main(String[] args) throws UnknownHostException {
		InetAddress inetAddress = InetAddress.getByName("www.google.com");
		
		System.out.println(inetAddress.getHostAddress());
		
	}

}
