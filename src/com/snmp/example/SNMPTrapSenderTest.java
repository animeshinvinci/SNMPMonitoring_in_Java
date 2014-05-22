package com.snmp.example;

import org.apache.log4j.Logger;

public class SNMPTrapSenderTest
{
    protected final Logger trapLog = Logger.getLogger(getClass());

    public static void main(String[] args)
    {
        SNMPTrapSenderTest snmpTest = new SNMPTrapSenderTest();
        snmpTest.sendSnmpTraps();
    }

    public void sendSnmpTraps()
    {
        try
        {
            // your code here for example: which tries to access a server
        }
        catch (Exception e)
        {
            trapLog.info("Server Not found");
        }
    }
}
