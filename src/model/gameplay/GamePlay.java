package model.gameplay;

import cards.play.StatusTrackCardOutput;
import cards.play.EnemyTrackCardOutput;
import cards.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * @author ricardo
 */

public class GamePlay implements Serializable{   
    private int dayStatus, turn, actions, wallStrength, morale, supplies;
    private int raidedSupplies, tunnelPosition, sabotageBonus, moraleAction, towerAttack, ramAttack, ladderAttack;
    private int allAttacks, raidAction, coupureBonus, ladderStrength, ramStrength, towerStrength; 
    private int closeCombatStrength, ladderPosition, ramPosition, towerPosition, trebuchetNumber, circleAttacks;
    private boolean none, ladder, ram, tower, boilingWatterAttack, towerDown, badWeather, buyFlag, errorflag, oneFreeMoveOnlyErrorflag;
    private List<EventCard> cartas;  
    private EventCard currentCard;
    private EnemyTrackCardOutput enemyCardOutput;
    private StatusTrackCardOutput statusCardOutput;
          
    public GamePlay(){   
        dayStatus = 1; 
        wallStrength = morale = supplies = towerStrength = closeCombatStrength = 4;
        ladderPosition = ramPosition = towerPosition = 4;
        ladderStrength = 2;
        ramStrength = trebuchetNumber = 3;  
        cartas = new ArrayList(); 
        enemyCardOutput = new EnemyTrackCardOutput(trebuchetNumber, ladderPosition, ramPosition, towerPosition);
        statusCardOutput = new StatusTrackCardOutput(tunnelPosition, wallStrength, morale, supplies, raidedSupplies);
        gameSetup();
    }
    
    public void setCardActions(){
        actions = cartas.get(turn).getActionsNumber(dayStatus);    
    }
    
    private void startCardList(){
        cartas.add(new EventCardOne());
        cartas.add(new EventCardTwo());
        cartas.add(new EventCardThree());
        cartas.add(new EventCardFour());
        cartas.add(new EventCardFive());
        cartas.add(new EventCardSix());
        cartas.add(new EventCardSeven());
    } 

    
    public boolean isOneFreeMoveOnlyErrorflag() {
        return oneFreeMoveOnlyErrorflag;
    }

    public void setOneFreeMoveOnlyErrorflag(boolean oneActionOlnlyErrorflag) {
        this.oneFreeMoveOnlyErrorflag = oneActionOlnlyErrorflag;
    }   

    public boolean isErrorflag() {
        return errorflag;
    }

    public void setErrorflag(boolean errorflag) {
        this.errorflag = errorflag;
    }     
    
    public int getDayStatus(){
        return dayStatus;
    }
    
    public void setDayStatus(int dayStatus){
        this.dayStatus = dayStatus;
    }
    
    public int getDado(){
        return (int)(Math.random()*6)+1;
    }
    
    public void setCurrentCard(){
        currentCard = getSingleCardFromList();
    }
    
    private EventCard getSingleCardFromList(){
        return cartas.get(turn);
    }
          
    private void createCardList(){
        startCardList();
        randomizeCardList();        
    }
           
    private void gameSetup(){  
        createCardList();
        setCurrentCard();          
    }
    
    public void buildPlayCardsOutput(){
        statusCardOutput = new StatusTrackCardOutput(tunnelPosition, wallStrength, morale, supplies, raidedSupplies);
        enemyCardOutput = new EnemyTrackCardOutput(trebuchetNumber, ladderPosition, ramPosition, towerPosition);   
    }
    
    public void gameUpdate(){      
        setCurrentCard();        
        setCardActions();
        enemyCardAdvancementCheck();
        enemyCardAdvancement();     
    }    
  
    public StatusTrackCardOutput getStatusCardOutput(){
        return statusCardOutput;
    }
    
    public EnemyTrackCardOutput getEnemyTrackCardOutput(){
        return enemyCardOutput;
    }
    
    public void randomizeCardList(){
        Collections.shuffle(cartas);
    }
    
    public EventCard getCurrentCard(){
        return currentCard;
    }
    
    public void enemyCardAdvancementCheck(){
        if(currentCard instanceof EventCardOne)
            enemyActionCardOne();
        else if (currentCard instanceof EventCardTwo)
            enemyActionCardTwo();       
        else if (currentCard instanceof EventCardThree)
            enemyActionCardThree();
        else if (currentCard instanceof EventCardFour)
            enemyActionCardFour();
        else if (currentCard instanceof EventCardFive)
            enemyActionCardFive();
        else if (currentCard instanceof EventCardSix)
            enemyActionCardSix();
        else if (currentCard instanceof EventCardSeven)
            enemyActionCardSeven();
    } 
     
    public void enemyCardAdvancement(){
        if(none == true)
            return;
        if(ladder == true)
            ladderPosition--;
        if(ram == true)
            ramPosition--;
        if(tower == true)
            towerPosition--;
    }
                
    public void dayEventCheckCardTwo(int dado){
        switch(dayStatus){
            case 1:{
                if(morale > 0) morale--;
                if(supplies > 0) supplies--;
            }break;
            case 2:{
                sabotageBonus++;
                moraleAction++;
            }break; 
            case 3: trebuchetAttack(dado); 
            break;
        }
    }
          
    public void dayEventResetCardTwo(){
        switch(dayStatus){
            case 2:{ 
                sabotageBonus--;
                moraleAction--;
            }break;
        }
    }
    
    public void dayEventCheckCardThree(){
        switch(dayStatus){
            case 1: {
                if(supplies > 0)
                    supplies--;
            }break;
            case 2: badWeather = true;      
            case 3: circleAttacks += 2; 
        }
    }
         
    public void dayEventResetCardThree(){
        switch(dayStatus){
            case 2: badWeather = false;  
            case 3: circleAttacks -= 2;               
        }
    }
    
    public void dayEventCheckCardFour(){
        switch(dayStatus){
            case 1: {
                if(morale > 0)
                    morale--;
            }break;
            case 2: ramAttack++;
            break;     
            case 3: towerAttack++;
            break;
        }
    }
         
    public void dayEventResetCardFour(){
        switch(dayStatus){
            case 2: ramAttack--;
            break;
            case 3: towerAttack--; 
            break;
        }
    }
    
    public void dayEventCheckCardFive(){
        switch(dayStatus){
            case 1: {
                allAttacks++;
            }break;
            case 2: towerDown = true;
            break;     
            case 3: {
                if(trebuchetNumber < 3)
                    trebuchetNumber++;
                coupureBonus++;
            }break;
        }
    }
         
    public void dayEventResetCardFive(){
        switch(dayStatus){
            case 1: {
                allAttacks--;
            }break;
            case 2: towerDown = false;
            break;
            case 3: coupureBonus--; 
            break;
        }
    }
    
        public void dayEventCheckCardSix(){
        switch(dayStatus){
            case 1: {
                raidAction++;
                sabotageBonus++;
            }break;
            case 2: 
                raidAction++;
                sabotageBonus++;
                coupureBonus++;
            break;     
            case 3:{
                if(ladderPosition == 1)
                    ladderAttack++;
                if(ramPosition == 1)
                    ramAttack++;
                if(towerPosition == 1)
                    towerAttack++;
            }break;
        }
    }
         
    public void dayEventResetCardSix(){
        switch(dayStatus){
            case 1:{ 
                raidAction--;
                sabotageBonus--;               
            }break;
            case 2:{
                raidAction--;
                sabotageBonus--;
                coupureBonus--;
            }break;
            case 3:{ 
                ladderAttack--;
                ramAttack--;
                towerAttack--;
            }break;
        }
    }
    
    public void dayEventCheckCardSeven(){
        switch(dayStatus){
            case 1: {
                ramAttack--;
            }break;
            case 2: 
                towerAttack--;
            break;     
            case 3:{
                    ladderAttack++;
                    ramAttack++;
                    moraleAction++;
            }break;
        }
    }
         
    public void dayEventResetCardSeven(){
        switch(dayStatus){
            case 1: {
                ramAttack++;
            }break;
            case 2: 
                towerAttack++;
            break;     
            case 3:{
                    ladderAttack--;
                    ramAttack--;
                    moraleAction--;
            }break;
        }        
    }
        
    public void dayEvents(int dado){
        if(currentCard instanceof EventCardOne)
            trebuchetAttack(dado);
        else if (currentCard instanceof EventCardTwo)
            dayEventCheckCardTwo(dado);       
        else if (currentCard instanceof EventCardThree)
            dayEventCheckCardThree();
        else if (currentCard instanceof EventCardFour)
            dayEventCheckCardFour();
        else if (currentCard instanceof EventCardFive)
            dayEventCheckCardFive();
        else if (currentCard instanceof EventCardSix)
            dayEventCheckCardSix();
        else if (currentCard instanceof EventCardSeven)
            dayEventCheckCardSeven();
    }
    
    public void dayEventsReset(){
        if (currentCard instanceof EventCardTwo)
            dayEventResetCardTwo();       
        else if (currentCard instanceof EventCardThree)
            dayEventResetCardThree();
        else if (currentCard instanceof EventCardFour)
            dayEventResetCardFour();
        else if (currentCard instanceof EventCardFive)
            dayEventResetCardFive();
        else if (currentCard instanceof EventCardSix)
            dayEventResetCardSix();
        else if (currentCard instanceof EventCardSeven)
            dayEventResetCardSeven();
    }
        
    public void enemyActionCardOne(){
        none = true;
    }                            
    
    public void enemyActionCardTwo(){
        none = false;
        switch(dayStatus){
            case 1: {tower = true; ladder = ram = false;}break;
            case 2: {
                if(ladderPosition == ramPosition && ladderPosition == towerPosition){
                    ladder = ram = tower = true;                            
                } 
                else if (ladderPosition > ramPosition && ladderPosition > towerPosition){
                    ladder = true;
                    ladder = tower = false;
                }                 
                else if (ramPosition > ladderPosition && ramPosition > towerPosition){
                    ram = true;
                    ladder = tower = false;
                }                                 
                else if (towerPosition > ladderPosition && towerPosition > ramPosition){
                    tower = true;
                    ladder = ram = false;
                } 
                else if(ladderPosition == ramPosition && ladderPosition > towerPosition){
                    ladder = ram = true; 
                    tower = false;
                }         
                else if(ramPosition == towerPosition && ramPosition > ladderPosition){
                    tower = ram = true;
                    ladder = false;                     
                }                               
                else if(ladderPosition == towerPosition && ladderPosition > ramPosition){
                    ladder = tower = true;
                    ram = false;                                               
                } 
            }break;
            case 3: {none = true; ladder = ram = tower = false;}break;
        }        
    }
    
    public void enemyActionCardThree(){
        switch(dayStatus){
            case 1: {ladder = true; none = ram = tower = false;}break;   
            case 2: {none = true; ladder = ram = tower = false;}break;  
            case 3: {ladder = ram = true; none = tower = false;}break;   
        }
    }
    
    public void enemyActionCardFour(){
        none = false;
        switch(dayStatus){
            case 1: {ladder = tower = true; ram = false;}break;                
            case 2: {ladder = ram = true; tower = false;}break; 
            case 3: {tower = true; ladder = ram = false;}break;
        }
    }
    
    public void enemyActionCardFive(){
        none = false;
        switch(dayStatus){
            case 1: {ram = true; ladder = tower = false;}break;                
            case 2: {ladder = ram = true; tower = false;}break; 
            case 3: {ladder = true; ram = tower = false;}break;
        }
    }
    
    public void enemyActionCardSix(){
        none = false;
        switch(dayStatus){
            case 1: {
                if(ladderPosition == ramPosition && ladderPosition == towerPosition){
                    ladder = ram = tower = true;                            
                } 
                else if (ladderPosition > ramPosition && ladderPosition > towerPosition){
                    ladder = true;
                    ladder = tower = false;
                }                 
                else if (ramPosition > ladderPosition && ramPosition > towerPosition){
                    ram = true;
                    ladder = tower = false;
                }                                 
                else if (towerPosition > ladderPosition && towerPosition > ramPosition){
                    tower = true;
                    ladder = ram = false;
                } 
                else if(ladderPosition == ramPosition && ladderPosition > towerPosition){
                    ladder = ram = true; 
                    tower = false;
                }         
                else if(ramPosition == towerPosition && ramPosition > ladderPosition){
                    tower = ram = true;
                    ladder = false;                     
                }                               
                else if(ladderPosition == towerPosition && ladderPosition > ramPosition){
                    ladder = tower = true;
                    ram = false;                                               
                } 
            }break;
            case 2: {ladder = true; ram = tower = false;}break;
            case 3: {ladder = false; ram = tower = true;}break;
        }        
    }
    
    public void enemyActionCardSeven(){
        none = false;
        switch(dayStatus){
            case 1: {ram = true; ladder = tower = false;}break;                
            case 2: {tower = true; ladder = ram = false;}break; 
            case 3: {ladder = ram = tower = true;}break;
        }
    }
    
    public void trebuchetAttack(int dado){
        if(trebuchetNumber == 3)
            wallStrength -= 2;
        if(trebuchetNumber == 2)
            wallStrength -= 1;
        if(trebuchetNumber == 1){
            int die = dado;
            if(die >= 4)
                wallStrength -= 1;
        }  
    }
    
    public void checkLadderPositionForStrength(){
        if(ladderPosition == 0)
            ladderStrength = 4;
    }
        
    public void checkRamPositionForStrength(){
        if(ramPosition == 0)
            ramStrength = 4;    
    }

    public void setLadderPosition(int position){
        ladderPosition = position;     
    }
    
    public void setRamPosition(int position){
        ramPosition = position;
        
    }
    
    public void setTowerPosition(int position){
        towerPosition = position;       
    }    
    
    public void setTrebuchets(int position){
        trebuchetNumber = position;    
    }      
    
    public int getTrebuchets(){
        return trebuchetNumber;
    }
    
    public int getLadderPosition(){
        return ladderPosition;
    }
    
    public int getRamPosition(){
        return ramPosition;
    }
    
    public int getTowerPosition(){
        return towerPosition;
    }
             
    public void coupureAction(int dado){
        if(dado > 4 && wallStrength < 4)
            wallStrength++;
    }
    
    public void rallyTroops(int dado){
            if(dado > 4 && morale < 4)
            morale++;           
    }
                 
    public boolean enemyLineCheck(){
        return tunnelPosition == 3;
    }
    
    public void actionUsed(){
        actions--;        
    }
    
    public void tunnelMovementForward(){        
        tunnelPosition++;    
    }
    
    public void tunnelMovementBackward(){        
        tunnelPosition--;    
    }
    
    public void returnToCastleEndOfDay(){
        tunnelPosition = 0;
    }
    
    public void unitCapturedEndOfDay(){
        morale--;
    }
    
    public void raidSupplies(int dado){
        if(dado > 2 && dado < 6) 
            raidedSupplies += 1;
        switch(dado){
            case 6:
                raidedSupplies += 2;
            case 1:
                raidedSupplies -= 1;
        }
        if(raidedSupplies > 2)
            raidedSupplies = 2;
        if(raidedSupplies < 0)
            raidedSupplies = 0;
    }
    
    public void sabotageAction(int dado){
        if(dado > 4)
            trebuchetNumber--;
        if(trebuchetNumber < 0)
            trebuchetNumber = 0;
    }
        
    public boolean checkEnemyPositionForImmediateGameOver(){
        return (ladderPosition == 0 && ramPosition == 0 && towerPosition == 0);          
    }
    
    public boolean checkStatusCardForImmediateLoss(){
        return ((wallStrength == 0 && morale == 0 )|| (morale == 0 && supplies == 0) || (supplies == 0 && wallStrength == 0));       
    }
    
    public void ressuplyAction(){
        if (tunnelPosition == 1 && raidedSupplies > 0){
            supplies += raidedSupplies;
            raidedSupplies = 0;
        }
        if(supplies > 4)
            supplies = 4;
    }
    
        public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
           
    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getWallStrength() {
        return wallStrength;
    }

    public void setWallStrength(int wallStrength) {
        this.wallStrength = wallStrength;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public int getSupplies() {
        return supplies;
    }

    public void setSupplies(int supplies) {
        this.supplies = supplies;
    }

    public int getRaidedSupplies() {
        return raidedSupplies;
    }

    public void setRaidedSupplies(int raidedSupplies) {
        this.raidedSupplies = raidedSupplies;
    }

    public int getTunnelPosition() {
        return tunnelPosition;
    }

    public void setTunnelPosition(int tunnelPosition) {
        this.tunnelPosition = tunnelPosition;
    }

    public int getSabotageBonus() {
        return sabotageBonus;
    }

    public int getMoraleAction() {
        return moraleAction;
    }

    public void setMoraleAction(int moraleAction) {
        this.moraleAction = moraleAction;
    }

    public int getTowerAttack() {
        return towerAttack;
    }

    public void setTowerAttack(int towerAttack) {
        this.towerAttack = towerAttack;
    }

    public int getRamAttack() {
        return ramAttack;
    }

    public void setRamAttack(int ramAttack) {
        this.ramAttack = ramAttack;
    }

    public int getLadderAttack() {
        return ladderAttack;
    }

    public void setLadderAttack(int ladderAttack) {
        this.ladderAttack = ladderAttack;
    }

    public int getAllAttacks() {
        return allAttacks;
    }

    public void setAllAttacks(int allAttacks) {
        this.allAttacks = allAttacks;
    }

    public int getRaidAction() {
        return raidAction;
    }

    public void setRaidAction(int raidAction) {
        this.raidAction = raidAction;
    }

    public int getCoupureBonus() {
        return coupureBonus;
    }

    public int getLadderStrength() {
        return ladderStrength;
    }

    public void setLadderStrength(int ladderStrength) {
        this.ladderStrength = ladderStrength;
    }

    public int getRamStrength() {
        return ramStrength;
    }

    public void setRamStrength(int ramStrength) {
        this.ramStrength = ramStrength;
    }

    public int getTowerStrength() {
        return towerStrength;
    }

    public void setTowerStrength(int towerStrength) {
        this.towerStrength = towerStrength;
    }

    public int getCloseCombatStrength() {
        return closeCombatStrength;
    }

    public void setCloseCombatStrength(int closeCombatStrength) {
        this.closeCombatStrength = closeCombatStrength;
    }

    public int getTrebuchetNumber() {
        return trebuchetNumber;
    }

    public void setTrebuchetNumber(int trebuchetNumber) {
        this.trebuchetNumber = trebuchetNumber;
    }

    public boolean isBoilingWatterAttack(){
        
        return boilingWatterAttack;
    }

    public void setBoilingWatterAttack(boolean boilingWatterAttack) {
        this.boilingWatterAttack = boilingWatterAttack;
    }

    public boolean isTowerDown() {
        return towerDown;
    }

    public boolean isBadWeather() {
        return badWeather;
    }
    
    public int getCircleAttacks() {
        return circleAttacks;
    }           

    public boolean isBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(boolean buyFlag) {
        this.buyFlag = buyFlag;
    }
    
    public List<EventCard> getCartas() {
        return cartas;
    }      

    public void setCartas(List<EventCard> cartas) {
        this.cartas = cartas;
    }    
    
    @Override
    public String toString(){
        String s = "              DAY " + dayStatus + "\n             TURN " + (turn + 1)  + "\n          " + actions + " ACTIONS" ;
        return s;
    }

}


