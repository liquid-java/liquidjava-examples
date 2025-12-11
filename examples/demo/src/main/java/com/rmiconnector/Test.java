package com.rmiconnector;

import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;

class Test{
    void test1() throws Exception{
        RMIConnector c = new RMIConnector(new JMXServiceURL("service:jmx:protocol:sap"), null);
        c.addConnectionNotificationListener(null, null, c);
        c.getConnectionId(); //error
    }

    void test2() throws Exception{
        RMIConnector c = new RMIConnector(new JMXServiceURL("service:jmx:protocol:sap"), null);
        c.addConnectionNotificationListener(null, null, c);
        c.connect();
        c.getConnectionId();
        c.close();
        c.connect(); //error
    }

    void test3() throws Exception{
        RMIConnector c = new RMIConnector(new JMXServiceURL("service:jmx:protocol:sap"), null);
        c.addConnectionNotificationListener(null, null, c);
        c.connect();
        c.getConnectionId();
        c.close();
        c.getMBeanServerConnection(); //error
    }

    void test4() throws Exception{
        RMIConnector c = new RMIConnector(new JMXServiceURL("service:jmx:protocol:sap"), null);
        c.addConnectionNotificationListener(null, null, c);
        c.connect();
        c.getConnectionId();
        c.close();
        c.close();
    }
}

