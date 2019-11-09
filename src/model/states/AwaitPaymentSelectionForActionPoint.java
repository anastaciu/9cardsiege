
package model.states;

import model.gameplay.GamePlay;

/**
 * @author ricar
 */

public class AwaitPaymentSelectionForActionPoint extends StateAdapter{
    
    public AwaitPaymentSelectionForActionPoint(GamePlay gameplay) {
        super(gameplay);
    }
    
    @Override
    public IStates buyActionPointMorale() {
        if(getGamePlay().getMorale() > 0){
        getGamePlay().setActions(getGamePlay().getActions() + 1);
        getGamePlay().setMorale(getGamePlay().getMorale() - 1);
        getGamePlay().setBuyFlag(true);
        }
        return new AwaitOptionSelection(getGamePlay());
    }

    @Override
    public IStates buyActionPointSupply() {
        if(getGamePlay().getSupplies() > 0){
        getGamePlay().setActions(getGamePlay().getActions() + 1);
        getGamePlay().setSupplies(getGamePlay().getSupplies() - 1);
        getGamePlay().setBuyFlag(true);
        }
        return new AwaitOptionSelection(getGamePlay());
    }
}
