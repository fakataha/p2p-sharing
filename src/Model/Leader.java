/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import com.google.common.primitives.Longs;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for running a modified Chang-Roberts algorithm for leader election in a
 * peer to peer network.  
 * @author Adam
 */
public class Leader implements Runnable {
    
    //class D network group to join
    private final String GROUP = "224.0.0.2";
    //variable to hold the network group
    private InetAddress group;
    //port to rx/tx data packets regarding election on 
    private final int DEST_PORT = 33001;
    //when election started flag set to true
    private boolean participant = false;
    //unique numerical identifier, used to determine leader.  greatest UID = leader
    private long ownID;
    private MulticastSocket serverSocket;
    //reference to the peer who is leader.  can be set to localhost.
    private InetAddress leader;
    //reference to local client to pass leader data when a new leader is found.
    private PeerNode node;
    
    private boolean run = true;
    
    public Leader(PeerNode node) {
        this.ownID = Runtime.getRuntime().freeMemory();
        this.node = node;
        try {
            serverSocket = new MulticastSocket(DEST_PORT);
            group = InetAddress.getByName(GROUP);
            serverSocket.joinGroup(group);
        } catch (IOException ex) {
            Logger.getLogger(PeerDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Listens for messages from group requesting an election.  The packets being
     * received will contain the amount of free memory in the sender.  The local 
     * client will compare against its own memory at startup and determine if it
     * should rebroadcast using <code> startElection</code>
     * claiming it has more free memory or accept the new leader
     * because they have more available resources.  
     * 
     * This may result in a flood of broadcasts temporarily as the leader is 
     * sorted in theory.  E.g. if this is the lowest id peer, then all other 
     * peers might respond and end up with a Big O(n2) transmissions.  
     * 
     * Each peer with greater id will respond, until only one peer responds and 
     * other peers set it as the leader.
     */
    public void receiveMessage()   {
       try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(packet);
            buffer = packet.getData();
            long id = Longs.fromByteArray(buffer);
            if (id < ownID ) {
                startElection();
            }   else    {
                node.setLeader(packet.getAddress());
            }   
            
        } catch (IOException ex) {
            Logger.getLogger(PeerNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Called when this thread receives an id less than its own local id value. 
     * transmits its id to other peers in group.
     */
    public void startElection() {
        participant = true;
        byte[] msg = Longs.toByteArray(ownID);
        sendPacket(msg);
    }

    /**
     * send broadcast messages
     * @param msg 
     */
    private void sendPacket(byte[] buffer)   {
        try {
            group = InetAddress.getByName(GROUP);
            DatagramPacket dPacket = new DatagramPacket(buffer, buffer.length, group, DEST_PORT);
            DatagramSocket dSocket = new DatagramSocket();
            dSocket.send(dPacket);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Leader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Leader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Leader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    public void run() {
        if (this.leader == null) {
            startElection();
        }
        while (run) {
            receiveMessage();
        }
    }
    
    /**
     * stops thread actions
     */
    public void stop()  {
        try {
            serverSocket.leaveGroup(group);
            run = false;
        } catch (IOException ex) {
            Logger.getLogger(Leader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}