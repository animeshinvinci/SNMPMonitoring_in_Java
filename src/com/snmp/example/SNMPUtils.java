package com.snmp.example;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPUtils {

	
	public static String  get(SNMPdataConfig snmpdataConfig,OID oid){
		String result = "";
		TransportMapping transport = null;
		Snmp snmp = null;
		try {
			
			transport = new DefaultUdpTransportMapping();
		    transport.listen();

		    // Creating target Address with Community String , SNMP Version , No of Retry & timeout
		    CommunityTarget comtarget = new CommunityTarget();
		    comtarget.setCommunity(new OctetString(snmpdataConfig.getCommunityString()));
		    comtarget.setVersion(snmpdataConfig.getSnmpversion());
		    comtarget.setAddress(new UdpAddress(snmpdataConfig.getIpaddress() + "/" + snmpdataConfig.getPort()));
		    comtarget.setRetries(snmpdataConfig.getNo_Of_retries());
		    comtarget.setTimeout(snmpdataConfig.getSnmp_timeout());

	        // Creating PDU  with binding OID variables
		     PDU pdu = new PDU(); 
	         pdu.setType(PDU.GET);
	         pdu.add(new VariableBinding(oid)); 
	         pdu.setNonRepeaters(0);


	         snmp = new Snmp(transport);
	         snmp.listen();

	         // send  PDU & Community target
	         ResponseEvent responseEvent = snmp.send(pdu, comtarget); 
	         
	         // extract the response PDU (could be null if timed out) 
	         if (responseEvent != null)
	         {
	           PDU responsePDU = responseEvent.getResponse();
	           if (responsePDU != null)
	           {
	             int errorStatus = responsePDU.getErrorStatus();
	             int errorIndex = responsePDU.getErrorIndex();
	             String errorStatusText = responsePDU.getErrorStatusText();
	             //responsePDU.getRequestID()
	             if (errorStatus == PDU.noError)
	             {
	            	 Vector<? extends VariableBinding> vect = responsePDU.getVariableBindings();
	            	 if(vect.size()>0){
	            		 VariableBinding vb = (VariableBinding)vect.get(0);
	            		 result = vb.getVariable().toString();
	            	 }
	             }
	             else
	             {
	               System.out.println("Error: Request Failed");
	               System.out.println("Error Status = " + errorStatus);
	               System.out.println("Error Index = " + errorIndex);
	               System.out.println("Error Status Text = " + errorStatusText);
	             }
	           }
	           else
	           {
	             System.out.println("Error: Response PDU is null");
	           }
	         }
	         else
	         {
	           System.out.println("Error: Agent Timeout... ");
	         }
	         
		}			
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				snmp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
		}
		


public static VariableBinding  getNext(SNMPdataConfig snmpdataConfig,OID oid){
	
	VariableBinding varBind = null;
	TransportMapping transport = null;
	Snmp snmp = null;
	try {
		
		transport = new DefaultUdpTransportMapping();
	    transport.listen();

	    // Creating target Address with Community String , SNMP Version , No of Retry & timeout
	    CommunityTarget comtarget = new CommunityTarget();
	    comtarget.setCommunity(new OctetString(snmpdataConfig.getCommunityString()));
	    comtarget.setVersion(snmpdataConfig.getSnmpversion());
	    comtarget.setAddress(new UdpAddress(snmpdataConfig.getIpaddress() + "/" + snmpdataConfig.getPort()));
	    comtarget.setRetries(snmpdataConfig.getNo_Of_retries());
	    comtarget.setTimeout(snmpdataConfig.getSnmp_timeout());

        // Creating PDU  with binding OID variables
	     PDU pdu = new PDU(); 
         pdu.setType(PDU.GETNEXT);
         pdu.add(new VariableBinding(oid)); 
         pdu.setNonRepeaters(0);


         snmp = new Snmp(transport);
         snmp.listen();

         // send  PDU & Community target
         ResponseEvent responseEvent = snmp.send(pdu, comtarget); 
         
         // extract the response PDU (could be null if timed out) 
         if (responseEvent != null)
         {
           PDU responsePDU = responseEvent.getResponse();

           if (responsePDU != null)
           {
             int errorStatus = responsePDU.getErrorStatus();
             int errorIndex = responsePDU.getErrorIndex();
             String errorStatusText = responsePDU.getErrorStatusText();
             //responsePDU.getRequestID()
             if (errorStatus == PDU.noError)
             {
            	 Vector<? extends VariableBinding> vect = responsePDU.getVariableBindings();
            	 if(vect.size()>0){
            		 varBind = (VariableBinding)vect.get(0);
            	 }
             }
             else
             {
               System.out.println("Error: Request Failed");
               System.out.println("Error Status = " + errorStatus);
               System.out.println("Error Index = " + errorIndex);
               System.out.println("Error Status Text = " + errorStatusText);
             }
           }
           else
           {
             System.out.println("Error: Response PDU is null");
           }
         }
         else
         {
           System.out.println("Error: Agent Timeout... ");
         }
         
	}			
	catch (IOException e) {
		e.printStackTrace();
	}
	finally{
		try {
			snmp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return varBind;
	}
	
}
