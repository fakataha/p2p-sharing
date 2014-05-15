/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcnabba
 */
public class RMIClient implements RMIInterface {
    
    private static final String REGISTRY_URL = "localhost";
    private static final int REGISTRY_PORT = 1099;
    private static Registry extRegistry;
    private static Registry intRegistry;
    
    public RMIClient()  {
        start();
    }
    
    public void start() {
        try {
            RMIInterface stub = (RMIInterface) UnicastRemoteObject.exportObject(this, 0);
            extRegistry = LocateRegistry.createRegistry(REGISTRY_PORT);
            extRegistry.rebind("stub", stub);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        printRegistry();
    }
    
    public void connect()   {
        try {
            intRegistry = LocateRegistry.getRegistry(REGISTRY_URL);
            printRegistry();
        } catch (RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printRegistry() {
        try {
            System.out.println("stubs in registry");
            String[] bindings = Naming.list(REGISTRY_URL);
            for (String str : bindings )    {
                System.out.println(str);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public File getFile() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}