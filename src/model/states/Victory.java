package model.states;

import model.gameplay.GamePlay;

/**
 *
 * @author ricardo
 */
public class Victory extends StateAdapter{
    
    public Victory(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public IStates victory(){        
        return new AwaitBeginning(new GamePlay());
    }
    
}
