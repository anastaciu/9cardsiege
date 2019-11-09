
package ui.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import model.Game;
import model.gameplay.GamePlay;
import model.states.AwaitBeginning;
import model.states.AwaitEnemySelectionForCloseCombat;
import model.states.AwaitForTunnelActionSelection;
import model.states.AwaitOptionSelection;
import model.states.AwaitPaymentSelectionForActionPoint;
import model.states.AwaitTopCard;
import model.states.AwaitTrackSelectionForArcherAttack;
import model.states.AwaitTrackSelectionForBoilingWaterAttack;
import model.states.GameOver;
import model.states.Victory;

/**
 * @author ricardo
 */

public class TextUI{
    
    private static final String ERRO = "\nAction not available at this time...\n";
    private static final String ERRO1 = "\nAction only available in the next turn...\n";
    private Game game = new Game();  
    
    private void getUserInputWhileAwaitingBeginning(){              
        int op;
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("\n*********NINE CARD SIEGE*********\n");
        System.out.print("\n1 - Start Game" + "\n2 - Load Game\n0 - Exit\n");
        System.out.print("-> ");
        while (!in.hasNextInt()){
            in.next();
        }
        op = in.nextInt();
        if (op == 0){
            game.finish();
        } 
        else if (op == 1) {                                        
            System.out.println("\nGame Track Cards...");  
            game.start();
            game.getGamePlay().buildPlayCardsOutput();
            System.out.print(game.getGamePlay().getEnemyTrackCardOutput().toString());
            System.out.print(game.getGamePlay().getStatusCardOutput().toString());
        } 
        else if (op == 2) {            
            	try {            
            FileInputStream fis = new FileInputStream("savegame.ser");
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                game = (Game)ois.readObject();           
            }
            } catch (FileNotFoundException e) {
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Não foi possível carregar o jogo");
            }   
            game.loadGame();       
        }
    }    
    
    private void getUserInputWhileAwaitTopCard(){
        int op;
        System.out.println("\n1 - Draw Top Card");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
         while (!in.hasNextInt()){
            in.next();
        }
        op = in.nextInt();

        if (op == 1){
            System.out.println(game.getGamePlay().getCurrentCard().toString());
            game.drawTopCard();
        }
    }

    private void getUserInputForActionSelection(){  
        int dado = game.getGamePlay().getDado();            
        int op;       
        //System.out.println(game.getGamePlay().isOneFreeMoveOnlyErrorflag());
        if(game.getGamePlay().isErrorflag())          
            System.out.println(ERRO);                 
        else{           
            System.out.println(game.getGamePlay().toString());
            game.getGamePlay().buildPlayCardsOutput();
            System.out.print(game.getGamePlay().getEnemyTrackCardOutput().toString());
            System.out.print(game.getGamePlay().getStatusCardOutput().toString());
            game.checkGameOver();
        } 
        game.drawNextCard(dado);
        if(game.getGamePlay().isOneFreeMoveOnlyErrorflag() || game.getGamePlay().isBuyFlag() || game.getGamePlay().isBoilingWatterAttack())
            System.out.println(ERRO1);
        if(game.getGamePlay().getActions() > 0){
            System.out.println("\nSelect Action:");
            System.out.print("\n1 - Archer Attack\n2 - Boiling Water Attack\n3 - Close Combat\n4 - Coupure\n5 - Rally Troops\n6 - Tunnel Movement\n7 - Supply Raid\n8 - Sabotage\n9 - Buy Action Point\n\n10 - Save Game\n11 - Main Menu\n");
            System.out.print("-> ");
            Scanner in = new Scanner(System.in);        
            while (!in.hasNextInt()){
                in.next();
            }         
            op = in.nextInt();          
            switch(op){           
                case 1: 
                    game.archerAttack();            
                    break;
                case 2: 
                    game.boilingWaterAttack();
                    break;
                case 3: 
                    game.closeCombat();
                    break;            
                case 4: 
                    dado = game.getGamePlay().getDado();
                    System.out.println("You rolled " + dado);
                    game.coupure(dado);
                    
                    break;                 
                case 5: 
                    dado = game.getGamePlay().getDado();
                    System.out.println("You rolled " + dado);
                    game.rallyTroops(dado);
                    break;

                case 6: game.tunnelMove();               
                    break;
                case 7: 
                    dado = game.getGamePlay().getDado();
                    System.out.println("You rolled " + dado);
                    game.supplyRaid(dado);             
                    break;
                case 8: 
                    dado = game.getGamePlay().getDado();
                    System.out.println("You rolled " + dado);
                    game.sabotage(dado);
                    break;
                case 9: 
                    game.buyActionPoint();
                    break;
                case 10: 
                    try {
                        FileOutputStream fos = new FileOutputStream("savegame.ser");
                        ObjectOutputStream oos;
                        oos = new ObjectOutputStream(fos);
                        oos.writeObject(game);          
                        oos.close();
                    } catch (FileNotFoundException e) {
                    } catch (IOException e) {
                        System.err.println("Não foi possível salvar o jogo");       
                }
            break;    
                case 11: 
                    game.mainMenu();break;    
        } 
    } 
        else
            System.out.println("\nNo actions remaining in this turn....\n");
    } 

    private void getUserInputBoilingWatterTrackSelection(){
        int op, dado;
        if(game.getGamePlay().isErrorflag()) {           
            System.out.println(ERRO);
            game.getGamePlay().setErrorflag(false);
        }
        System.out.println("\nSelect Track For Boiling Water Attack:");
        System.out.println("\n1 - Ladder\n2 - Ram\n3 - Tower");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
         while (!in.hasNextInt()){
            in.next();
        }
        op = in.nextInt();
        dado = game.getGamePlay().getDado(); 
        if(op > 0 && op < 4){
            System.out.println("You rolled " + dado);
            game.executeBoilingWaterAttack(op, dado);         
        }
    }

    private void getUserInputForArcherAttack(){
        int op, dado; 
        System.out.println("\nSelect Track For Archer Attack:");
        System.out.println("\n1 - Ladder\n2 - Ram\n3 - Tower");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
         while (!in.hasNextInt()){
            in.next();
        }    
        op = in.nextInt();
        dado = game.getGamePlay().getDado();       
        if(op > 0 && op < 4) { 
            System.out.println("You rolled " + dado);
            game.executeArcherAttack(op, dado);
        }
    }

    private void getUserInputForTunnelMovement(){
        int op; 
        System.out.println("\nSelect Tunnel Movement");
        System.out.println("\n1 - Free Movement Forward\n2 - Free Movement Back\n3 - Automatic Movement Forward\n4 - Automatic Movement Back");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()){
            in.next();
        }
        op = in.nextInt();
        switch(op){ 
            case 1: game.automaticTunnelMoveForward();
                break;
            case 2: game.automaticTunnelMoveBack();
                break;
            case 3: game.tunnelMoveForwardWithActionPoint();
                break;
            case 4: game.tunnelMoveBackWithActionPoint();
                break; 
        }
    }
    
    private void getUserInputForBuyingActionPoint(){
        int op; 
        System.out.println("\nSelect Buy Option");
        System.out.println("\n1 - BuyAction Point With Morale\n2 - Buy Action Point With Supply\n");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()){
            in.next();
        }
        op = in.nextInt();
        switch(op){ 
            case 1: game.buyActionPointMorale();
                break;
            case 2: game.buyActionPointSupply();
                break; 
        }
    }

    private void getUserInputForCloseCombat(){
        int op, dado; 
        System.out.println("\nSelect Enemy to Attack in Close Combat");
        System.out.println("\n1 - Ladder\n2 - Ram\n3 - Tower");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()){
            in.next();
        }
        op = in.nextInt();
        dado = game.getGamePlay().getDado();   
        if(op > 0 && op < 4){
            System.out.println("You rolled " + dado);
            game.executeCloseCombat(op, dado);      
        }
    }
     
    private void getVictoryCredits(){
        System.out.println("THE ENENY IS RETREATING, THE SIEGE IS OVER...");
        System.out.println("YOU SAVED THE CITY...");
        System.out.println("YOU'RE THE MAN... OR WOMAN... OR WHATEVER...\n\n");
        System.out.println("Author: Ricardo Silva\n"); 
        game.victory();
    }
          
    public void runGame(){ 
        while(!(game.getState() instanceof GameOver)){                    
            if (game.getState() instanceof AwaitBeginning){
                getUserInputWhileAwaitingBeginning();
            }
            else if (game.getState() instanceof AwaitTopCard){
                getUserInputWhileAwaitTopCard();
            }
            else if (game.getState() instanceof AwaitOptionSelection){
                getUserInputForActionSelection();
            }
            else if (game.getState() instanceof AwaitTrackSelectionForBoilingWaterAttack){
                getUserInputBoilingWatterTrackSelection();
            }
            else if (game.getState() instanceof AwaitTrackSelectionForArcherAttack){
                getUserInputForArcherAttack();
            }           
            else if (game.getState() instanceof AwaitForTunnelActionSelection){
                getUserInputForTunnelMovement();     
            } 
            else if (game.getState() instanceof AwaitPaymentSelectionForActionPoint){                                
                getUserInputForBuyingActionPoint();
            }
            else if (game.getState() instanceof AwaitEnemySelectionForCloseCombat){                                
                getUserInputForCloseCombat();    
            }   
            else if (game.getState() instanceof Victory){                                
                getVictoryCredits();    
            }             
        }
        System.out.println("************** GAME OVER ***************");
    } 
}


