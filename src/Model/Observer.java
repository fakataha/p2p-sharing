/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.net.InetAddress;
import java.util.Map;

/**
 *
 * @author Adam
 */
public interface Observer {
    
    public void update(Map<String, PeerNode> peers);
}
