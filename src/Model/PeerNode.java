/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to pass local socket information to peers.  Goal is to allow a peer 
 * to store discovered peers in a data collection.
 * @author mcnabba
 */
public class PeerNode implements PeerListener {
       
    private final int PORT = 33000;
    private static Map<InetAddress, PeerNode> peers;
    private boolean run = true;
    private InetAddress address;
    private Thread commsThread;
    private PeerComms pComms;
    private PeerDiscovery pDisc;
    private List<Observer> observers;
    private InetAddress leader = null;
    
    public PeerNode()   {
        peers = new HashMap<>();
        pDisc = new PeerDiscovery(this);
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(PeerNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        pComms = new PeerComms();
        commsThread = new Thread(pComms);
        observers = new ArrayList<>();
    }

    /**
     * constructor to store incoming Peer connections.  
     * @param address
     */
    public PeerNode(InetAddress address)   {
        this.address = address;
    }

    public void stop()  {
        pComms.stopRun();
        pDisc.stopRun();
        try {
            commsThread.join();
            pDisc.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(PeerNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Map<InetAddress, PeerNode> getPeers()    {
        return peers;
    }
    
    public void addPeerNode(PeerNode n) {
        if (!peers.containsKey(n.address))  {
            peers.put(n.address, n);
            System.out.println("Peer added to Map");
            notifyListeners();
        }
    }
    
    @Override
    public String toString()    {
        return address.getHostAddress();
    }
    
    /**
     * starts listening for peers, broadcasting to peers, and starts a leader
     * election.  Upon a local PeerNode starting, it should discover the leader
     * in the P2P network, which it might usurp.
     */
    public void start() {
        commsThread.start();
        pDisc.start();
        if ( leader == null )   {
            Leader leaderSelection = new Leader(this);
            Thread leaderThread = new Thread(leaderSelection);
            leaderThread.start();
        }
    }
    
    public void setLeader(InetAddress leader)   {
        this.leader = leader;
    }
    
    @Override
    public void register(Observer obs) {
        if (!observers.contains(obs))   {
            observers.add(obs);
        }
    }

    @Override
    public void unregister(Observer obs) {
        if (observers.contains(obs))    {
            observers.remove(obs);
        }
    }

    @Override
    public void notifyListeners() {
        for (Observer obs : observers ) {
            obs.update(peers);
        }
    }
}
