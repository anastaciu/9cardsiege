
package cards.play;

import java.io.Serializable;

/**
 *
 * @author ricardo
 */
public class EnemyTrackCardOutput implements Serializable{
    static char LADDER = 'L', RAM = 'R', TOWER = 'T';
    Object [][] enemyTrackCard;
    int trebuchets;
    
    public EnemyTrackCardOutput(int trebuchets, int ladderPosition, int ramPosition, int towerPosition){
        createTracks();
        this.trebuchets = trebuchets;  
        updateEnemyPositionInCard(ladderPosition, ramPosition, towerPosition);
    }
    
    private void createTracks(){
        enemyTrackCard = new Object[5][3];    
        for(int i = 0; i < enemyTrackCard.length; i++)    
            for(int j = 0; j < enemyTrackCard[i].length; j++)
                enemyTrackCard[i][j] = i;
    }
    
    private void updateEnemyPositionInCard(int ladderPosition, int ramPosition, int towerPosition){
        enemyTrackCard[ladderPosition][0] = LADDER;
        enemyTrackCard[ramPosition][1] = RAM;
        enemyTrackCard[towerPosition][2] = TOWER;                
    }
    
        @Override
    public String toString(){
        String s = "Cards updated...\n";
        s += "\n*-*-- ENEMY TRACK CARD --*-*\n\n";
        for (Object[] i : enemyTrackCard) {
            for (Object it : i) {
                s += "   " + "[" + it + "]" + "    ";             
            }
            s += "\n";
        }
        s += "  Ladder     Ram      Tower";
        s+="\n\n";
        s += "Trebuchets: " + trebuchets +"\n";
          
    return s + "\n";
    }
}
    
