
package model.states;
import model.gameplay.GamePlay;

/**
 * @author ricardo
 */

public class AwaitBeginning extends StateAdapter{
    
    public AwaitBeginning(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public IStates start(){ 
//        getGamePlay().gameSetup();        
        return new AwaitTopCard(getGamePlay());             
    }
    
    @Override
    public IStates finish(){          
        return new GameOver(getGamePlay());              
    }
    
    @Override
    public IStates loadGame(){       
        return new AwaitOptionSelection(getGamePlay());              
    }
       
}
