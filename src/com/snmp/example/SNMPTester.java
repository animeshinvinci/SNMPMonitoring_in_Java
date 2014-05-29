package com.snmp.example;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

public class SNMPTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ipaddress = "demo.snmplabs.com";
		String community = "public";
		int snmpversion = SnmpConstants.version2c;
		
		SNMPdataConfig snmpconfigObj = new SNMPdataConfig();
		snmpconfigObj.setIpaddress(ipaddress);
		snmpconfigObj.setCommunityString(community);
		snmpconfigObj.setSnmpversion(snmpversion);
		
		printFirstAnswer(snmpconfigObj);
		
		printSecondAmswer(snmpconfigObj);

		
	}
	
	private static void printFirstAnswer(SNMPdataConfig snmpconfigObj) {
		String SysName = "1.3.6.1.2.1.1.5.0";
		String SysDesc = "1.3.6.1.2.1.1.1.0";
		String sysName_result = SNMPUtils.get(snmpconfigObj, new OID(SysName));
		String sysDesc_result = SNMPUtils.get(snmpconfigObj, new OID(SysDesc));
		System.out.println("System Name  : " + sysName_result);
		System.out.println("System Description  : " + sysDesc_result);
	}
	
	private static void printSecondAmswer(SNMPdataConfig snmpconfigObj) {
		HashMap<String,String> statusMap = new HashMap<String,String>();
		statusMap.put("1", "UP");
		statusMap.put("2", "Down");
		statusMap.put("3", "testing");
		
		String interfaceNumber = "1.3.6.1.2.1.2.2.1.1";
		ArrayList<String> indexList = new ArrayList<String>();
		VariableBinding varBind = SNMPUtils.getNext(snmpconfigObj, new OID(interfaceNumber));
		String result = varBind.getOid().toString();
		int indexAvail = result.indexOf(interfaceNumber);
		while(indexAvail == 0){
			indexList.add(result.substring(interfaceNumber.length()+ 1));
			varBind = SNMPUtils.getNext(snmpconfigObj, varBind.getOid());
			result = varBind.getOid().toString();
			indexAvail = result.indexOf(interfaceNumber);
		}
		Interface  interfaceObj = new Interface();
		ArrayList<HashMap<String,String>> outputListmap = new  ArrayList<HashMap<String,String>>();
		String[] keyList = {"Interface No","Interface Name","Interface MAC","Interface Admin Status"};
		for(String indexValue :indexList){
			HashMap<String,String> interfaceMap = new HashMap<String,String>();
			interfaceMap.put("Interface No",indexValue);
			String  interface_name =interfaceObj.getName() + "." + indexValue;
			String name = SNMPUtils.get(snmpconfigObj, new OID(interface_name));
			interfaceMap.put("Interface Name",name);
			String  interface_mac = interfaceObj.getPhysicalAddr() + "." + indexValue;
			String mac = SNMPUtils.get(snmpconfigObj, new OID(interface_mac));
			interfaceMap.put("Interface MAC",mac);
			String  interface_admin =interfaceObj.getAdminstatus() + "." + indexValue;
			String admin_status = SNMPUtils.get(snmpconfigObj, new OID(interface_admin));
			interfaceMap.put("Interface Admin Status",admin_status);
			outputListmap.add(interfaceMap);
		}
		
		System.out.println("Interface No   Interface Name      Interface MAC          Interface Admin Status");
		for(HashMap< String,String> outMap: outputListmap){
			String sr = String.format("%-20s  %-10s  %s    %10s", outMap.get(keyList[0]), outMap.get(keyList[1]), outMap.get(keyList[2]),statusMap.get(outMap.get(keyList[3])));
			System.out.println(sr);
		}
		
	}

}
