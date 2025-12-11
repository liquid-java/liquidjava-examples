package com.rmiconnector;

import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIServer;
import javax.security.auth.Subject;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

@ExternalRefinementsFor("javax.management.remote.rmi.RMIConnector")
@StateSet({"start", "connected", "closed"})
public interface RMIConnectorRefinements {

    // ####### Constructors ######## //
    @StateRefinement(to="start(this)")
    public void RMIConnector(JMXServiceURL url, Map<String,?> environment);

    @StateRefinement(to="start(this)")
    public void RMIConnector(RMIServer rmiServer, Map<String,?> environment);


    // ####### Changing states ######## //
    @StateRefinement(from="start(this)",to="connected(this)")
    public void connect();

    @StateRefinement(from="start(this)",to="connected(this)")
    @StateRefinement(from="connected(this)")
    public void connect(Map<String,?> environment);

    @StateRefinement(to="closed(this)")
    public void close();

    
    // ####### Getters ######## //
    @StateRefinement(from="connected(this)")
    public String getConnectionId();

    @StateRefinement(from="connected(this)")
    public MBeanServerConnection getMBeanServerConnection();

    @StateRefinement(from="connected(this)")
    public MBeanServerConnection getMBeanServerConnection(Subject delegationSubject);
    
    // ####### Others ######## //
    // public String toString()
    // public JMXServiceURL getAddress()
    // public void addConnectionNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback)
    // public void removeConnectionNotificationListener(NotificationListener listener)
    // public void removeConnectionNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback)

}
