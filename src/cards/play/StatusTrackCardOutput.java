
package cards.play;

import java.io.Serializable;

/**
 * @author ricardo
 */

public class StatusTrackCardOutput implements Serializable{
    final char WALL = 'W', MORALE = 'M', SUPPLIES = 'S';
    final Object [][] castleCard = new Object[5][3];
    final char[] tunnel = new char[4];
    int raidedSupplies;
    int tunnelPosition;
    
    public StatusTrackCardOutput(int tunnelPosition, int wallStrength, int morale, int supplies, int raidedSupplies){ 
        createCastle();
        populateTracks(wallStrength, morale, supplies);
        updateRaidedSupplies(raidedSupplies);
        updateTunnelPosition(tunnelPosition);
    }
    
    private void createCastle(){
        for(int i = castleCard.length, it = 0; i > 0; i--, it++)    
            for(int j = 0; j < castleCard[it].length; j++)
                castleCard[it][j] = i-1;        
    }
        
    private void updateTunnelPosition(int tunnelPosition){
        this.tunnelPosition = tunnelPosition;
    }
    
    private void updateRaidedSupplies(int raidedSupplies){
        this.raidedSupplies = raidedSupplies;
    }
      
    private void populateTracks(int wallStrength, int morale, int supplies){
        castleCard[4-wallStrength][0] = WALL;
        castleCard[4-morale][1] = MORALE;
        castleCard[4-supplies][2] = SUPPLIES;                
    }
    
    @Override
    public String toString(){
        int i;
        String s = "\n*-*-- STATUS TRACK CARD --*-*\n\n";
        s += "   Wall     Morale   Supplies\n";
        for (i = 0; i < castleCard.length; i++) {
            for (Object item : castleCard[i]) {
                s += "   " + "[" + item + "]" + "    ";             
            }
            s += "\n";
        }
        s += "\nCastle       Tunnel    Enemy Lines\n";
        for(i = 0; i < 4; i++)
            if(i == tunnelPosition)
                s += "  [" + "T" + "]   ";
            else
                s += "  [" + " ]   ";
        s += "\n\nRaided Supplies: " + raidedSupplies;
        
        return s + "\n";
    }
}
