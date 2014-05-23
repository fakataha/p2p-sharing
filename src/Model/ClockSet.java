/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author joel
 */
public class ClockSet extends Thread {
    
    private volatile boolean run = true;
    private long out;
    private PeerNode node;
    
    public ClockSet(PeerNode n) {
        out = 0;
        node = n;
    }
    
    public double getOut() {
        return out;
    }
    
    public void setOut(long o) {
        this.out = o;
    }
    
    @Override
    public void run() {
        checkTime();
    }

    private void checkTime() {
        while (run) {
            try {
                Thread.sleep(10000);
                if (node.isLeader()) {
                    Iterator it = node.getPeers().values().iterator();
                    while(it.hasNext()) {
                        System.out.println("Checking time.");
                        String ip = it.next().toString();
                        long difference = getTimeFromServer(ip);
                        setAdjustedTime(difference/2, ip);
                    }
                } else {
                    System.out.println("Guess i'm not leader :(");
                }
                
            } catch (Exception e) {
                System.out.println("Trouble gettting time difference");
                e.printStackTrace();
            }
            
        }
        
    }
    
    public long getTimeFromServer(String fcip) throws RemoteException, NotBoundException {
        RMIFileClient fc = new RMIFileClient(fcip);
        Date d = new Date();
        long localTime = d.getTime();
        long remoteTime = fc.getServerTime();
        return localTime - remoteTime;
    }
    
    public void requestStop() {
        run = false;
        System.out.println("Clock Set stop = " + run);
    }
    private void setAdjustedTime(long difference, String fcip) throws RemoteException, NotBoundException {
        RMIFileClient fc = new RMIFileClient(fcip);
        fc.setServerDifference(difference);
    }
    
}
