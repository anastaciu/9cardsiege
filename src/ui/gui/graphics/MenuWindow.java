package ui.gui.graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import model.ObservableGame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.Observable;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import model.Game;
import model.LoadSave;
import model.states.AwaitBeginning;
import model.states.AwaitEnemySelectionForCloseCombat;
import model.states.AwaitForTunnelActionSelection;
import model.states.AwaitOptionSelection;
import model.states.AwaitPaymentSelectionForActionPoint;
import model.states.AwaitTopCard;
import model.states.AwaitTrackSelectionForArcherAttack;
import model.states.AwaitTrackSelectionForBoilingWaterAttack;
import model.states.GameOver;

/**
 *
 * @author ricardo
 */

public class MenuWindow extends JFrame implements Observer{    
    ObservableGame game;
    int dado;
    private static final String ERRO1 = "\n\n    The action is\n    not possible...";
    private static final String DADO = "     You rolled: ";
    private ImageIcon img, enemyToken, playerToken, trebuchetToken, tunnelToken, raidToken;
    private JLabel cctoken, ladder1, ladder2, ladder3, ladder4, ram1, ram2, ram3, ram4, tower1, tower2,tower3, tower4, trebuchet1, trebuchet2, trebuchet3; 
    private JMenu contentMenuBar, menuHelp, menuBarGame;
    private JPanel firstMenuLayered, enenySelectionForCloseCombat, enenySelectionPanelForBolingWater, tokensPanel;
    private JLabel mainBackLabel, enemyCardLabel, statusCardLabel, eventCard;
    private JButton loseMoraleButton, loseSuppliesButton, freeMoveForwardButton, freeMoveBackButton, automaticForwardButton, automaticBackButton;
    private JButton mainExit, mainLoadGame, mainStart, drawCard, ramButtonForCloseCombat, towerButtonForCloseCombat, ladderButtonForCloseCombat;
    private JButton ladderButtonForArcherAttack, ramButtonForArcherAttack, towerButtonForArcherAttack, ladderButtonForBoilingWater, ramButtonForBoilingWater, towerButtonForBoilingWater;
    private JButton archerAttackButton, boilingWaterButton, sabotageButton, rallyTroopsButton, closeCombatButton, tunnelMoveButton, supplyRaidButton, buyPointButton, coupureButton;
    private JPanel mainMenuBackgroundPanel, startMenuButtonsPanel, nextCardButtonPanel, awaitOptionPanel,enemySelectionForArcherAttack;
    private JMenuBar mainMenuBar;   
    private JMenuItem menuAbout, menuDetailedHelp, menuExit, menuLoad, menuNew, menuQuickHelp, menuSave;
    private JPanel moralePanel1, moralePanel2, moralePanel3, moralePanel4, player0Panel, ramPanel1, ramPanel2, ramPanel3, ramPanel4;
    private JPanel gamePanel, playCardsPanel, rightPanel, GameInfoPanel, suppliesPanel1, suppliesPanel2, suppliesPanel3, suppliesPanel4, suppyRaidPanel1, suppyRaidPanel2;
    private JTextArea gameInfo;
    private JLabel player0, wall1, wall2, wall3, wall4, morale1, morale2, morale3, morale4, supplies1, supplies2, supplies3, supplies4, troopsCastle, troopsTunnel1, troopsTunnel2, troopsEnemyLines, raidedSupplies1, raidedSupplies2;
    private JPanel buyPointPanel, movementSelectionPanel, towerPanel1, towerPanel2, towerPanel3, towerPanel4, trebuchetPanel1, trebuchetPanel2, trebuchetPanel3;;
    private JPanel tunelPanel0, tunelPanel1, tunelPanel2, tunelPanel3, wallPanel1, wallPanel2, wallPanel3, wallPanel4, ccPanel, ladderPanel1, ladderPanel2, ladderPanel3, ladderPanel4;
    
    public MenuWindow(ObservableGame obs){
        super("Nine Card Siege");
        game = obs;
        addObserver();
        visible();
        initWindowAndMenu();
        initStartLayeredPanel();         
        update();   
    }
    
    private void update(){
        update(game, this);               
    }
    
    private void visible(){
        this.setVisible(true);
    }
      
    private void addObserver(){
    game.addObserver(this);
    }
    
    private void initWindowAndMenu() {     
        mainMenuBar = new JMenuBar();
        menuBarGame = new JMenu();
        menuNew = new JMenuItem();
        menuLoad = new JMenuItem();
        menuSave = new JMenuItem();
        menuExit = new JMenuItem();
        contentMenuBar = new JMenu();
        menuHelp = new JMenu();
        menuQuickHelp = new JMenuItem();
        menuDetailedHelp = new JMenuItem();
        menuAbout = new JMenuItem();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//      setResizable(false);
        setSize(new Dimension(1024, 768));
        menuBarGame.setText("Game");
        menuBarGame.setMnemonic(KeyEvent.VK_G);
        menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuNew.setText("New");
        menuNew.addActionListener(new MenuWindow.StartNewGameListener());
        menuBarGame.add(menuNew);
        menuLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuLoad.setText("Load");
        menuLoad.addActionListener(new MenuWindow.LoadObjListener());
        menuBarGame.add(menuLoad);
        menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuSave.setText("Save");
        menuSave.addActionListener(new MenuWindow.SaveObjMenuBarListener());
        menuBarGame.add(menuSave);
        menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menuExit.setText("Exit");
        menuExit.addActionListener(new MenuWindow.MainExitListener());       
        menuBarGame.add(menuExit);
        mainMenuBar.add(menuBarGame);
        contentMenuBar.setText("Contents");
        contentMenuBar.setMnemonic(KeyEvent.VK_C);
        menuHelp.setText("Help");
        menuQuickHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuQuickHelp.setText("Quick Help");
        menuHelp.add(menuQuickHelp);
        menuQuickHelp.addActionListener(new MenuWindow.HelpListener());     
        menuDetailedHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuDetailedHelp.setText("Detailed Help");
        menuDetailedHelp.addActionListener(new MenuWindow.DetailedHelpListener());
        menuHelp.add(menuDetailedHelp);
        contentMenuBar.add(menuHelp);
        menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuAbout.setText("About");
        contentMenuBar.add(menuAbout);
        menuAbout.addActionListener(new MenuWindow.ContentsListener());        
        mainMenuBar.add(contentMenuBar);
        setJMenuBar(mainMenuBar);
    }
    
    private void placeTokens(){
        cctoken.setVisible(game.getGameData().getLadderPosition()== 0 || game.getGameData().getRamPosition()== 0 || game.getGameData().getTowerPosition()== 0);    
        ladder1.setVisible(game.getGameData().getLadderPosition()== 1);    
        ram1.setVisible(game.getGameData().getRamPosition()== 1);
        tower1.setVisible(game.getGameData().getTowerPosition()== 1);
        ladder2.setVisible(game.getGameData().getLadderPosition()== 2);    
        ram2.setVisible(game.getGameData().getRamPosition()== 2);
        tower2.setVisible(game.getGameData().getTowerPosition()== 2);
        ladder3.setVisible(game.getGameData().getLadderPosition()== 3);    
        ram3.setVisible(game.getGameData().getRamPosition()== 3);
        tower3.setVisible(game.getGameData().getTowerPosition()== 3);
        ladder4.setVisible(game.getGameData().getLadderPosition()== 4);    
        ram4.setVisible(game.getGameData().getRamPosition()== 4);
        tower4.setVisible(game.getGameData().getTowerPosition()== 4);
        trebuchet1.setVisible(game.getGameData().getTrebuchetNumber() == 1); 
        trebuchet2.setVisible(game.getGameData().getTrebuchetNumber() == 2); 
        trebuchet3.setVisible(game.getGameData().getTrebuchetNumber() == 3);
        player0.setVisible(game.getGameData().getWallStrength() == 0 || game.getGameData().getSupplies() == 0 || game.getGameData().getMorale() == 0);
        wall1.setVisible(game.getGameData().getWallStrength() == 1);
        wall2.setVisible(game.getGameData().getWallStrength() == 2);
        wall3.setVisible(game.getGameData().getWallStrength() == 3);
        wall4.setVisible(game.getGameData().getWallStrength() == 4);
        morale1.setVisible(game.getGameData().getMorale() == 1);
        morale2.setVisible(game.getGameData().getMorale() == 2);
        morale3.setVisible(game.getGameData().getMorale() == 3); 
        morale4.setVisible(game.getGameData().getMorale() == 4); 
        supplies1.setVisible(game.getGameData().getSupplies() == 1);
        supplies2.setVisible(game.getGameData().getSupplies() == 2);
        supplies3.setVisible(game.getGameData().getSupplies() == 3);
        supplies4.setVisible(game.getGameData().getSupplies() == 4);
        troopsCastle.setVisible(game.getGameData().getTunnelPosition() == 0);
        troopsTunnel1.setVisible(game.getGameData().getTunnelPosition() == 1);
        troopsTunnel2.setVisible(game.getGameData().getTunnelPosition() == 2);
        troopsEnemyLines.setVisible(game.getGameData().getTunnelPosition() == 3);
        raidedSupplies1.setVisible(game.getGameData().getRaidedSupplies() == 1);
        raidedSupplies2.setVisible(game.getGameData().getRaidedSupplies() == 2);  
    }

    
    private void initStartLayeredPanel(){   
        tokensPanel = new JPanel();
        ccPanel = new JPanel();
        ramPanel1 = new JPanel();
        ramPanel2 = new JPanel();
        ramPanel3 = new JPanel();
        ramPanel4 = new JPanel();
        ladderPanel1 = new JPanel();
        ladderPanel2 = new JPanel();
        towerPanel1 = new JPanel();      
        towerPanel2 = new JPanel();
        towerPanel3 = new JPanel();
        ladderPanel3 = new JPanel();
        ladderPanel4 = new JPanel();
        towerPanel4 = new JPanel();
        trebuchetPanel1 = new JPanel();
        trebuchetPanel2 = new JPanel();
        trebuchetPanel3 = new JPanel();
        wallPanel4 = new JPanel();
        moralePanel4 = new JPanel();
        suppliesPanel4 = new JPanel();
        moralePanel3 = new JPanel();
        wallPanel3 = new JPanel();
        suppliesPanel3 = new JPanel();
        wallPanel2 = new JPanel();
        moralePanel2 = new JPanel();
        suppliesPanel2 = new JPanel();
        wallPanel1 = new JPanel();
        moralePanel1 = new JPanel();
        suppliesPanel1 = new JPanel();
        player0Panel = new JPanel();
        tunelPanel0 = new JPanel();
        tunelPanel1 = new JPanel();
        tunelPanel2 = new JPanel();
        tunelPanel3 = new JPanel();
        suppyRaidPanel2 = new JPanel();
        suppyRaidPanel1 = new JPanel();
        mainMenuBar = new JMenuBar();
        menuBarGame = new JMenu();
        menuNew = new JMenuItem();
        menuLoad = new JMenuItem();
        menuSave = new JMenuItem();
        menuExit = new JMenuItem();
        contentMenuBar = new JMenu();
        menuHelp = new JMenu();
        menuQuickHelp = new JMenuItem();
        menuDetailedHelp = new JMenuItem();
        menuAbout = new JMenuItem();
        firstMenuLayered = new JPanel();
        ccPanel = new JPanel();        
        startMenuButtonsPanel = new JPanel();
        mainStart = new JButton();
        mainLoadGame = new JButton();
        mainExit = new JButton();
        mainMenuBackgroundPanel = new JPanel();
        mainBackLabel = new JLabel();
        nextCardButtonPanel = new JPanel();
        drawCard = new JButton();       
        archerAttackButton = new JButton(); 
        boilingWaterButton = new JButton();
        sabotageButton = new JButton(); 
        rallyTroopsButton = new JButton();
        closeCombatButton = new JButton();
        tunnelMoveButton = new JButton();
        supplyRaidButton = new JButton();
        buyPointButton= new JButton();
        coupureButton= new JButton();
        awaitOptionPanel = new JPanel();
        ladderButtonForArcherAttack = new JButton();
        ramButtonForArcherAttack = new JButton();
        towerButtonForArcherAttack = new JButton();
        enemySelectionForArcherAttack = new JPanel();
        enenySelectionForCloseCombat = new JPanel();
        enenySelectionPanelForBolingWater = new JPanel();
        ladderButtonForCloseCombat = new JButton();
        ramButtonForCloseCombat = new JButton();
        towerButtonForCloseCombat = new JButton();
        ladderButtonForBoilingWater = new JButton();
        ramButtonForBoilingWater = new JButton();
        towerButtonForBoilingWater = new JButton();
        buyPointPanel = new JPanel();
        loseMoraleButton = new JButton();
        loseSuppliesButton = new JButton();
        movementSelectionPanel = new JPanel();
        freeMoveForwardButton = new JButton();
        freeMoveBackButton = new JButton();
        automaticForwardButton = new JButton();
        automaticBackButton = new JButton();
        gamePanel = new JPanel();
        playCardsPanel = new JPanel();
        enemyCardLabel = new JLabel();
        statusCardLabel = new JLabel();
        rightPanel = new JPanel();
        eventCard = new JLabel();
        GameInfoPanel = new JPanel();
        gameInfo = new JTextArea();
        ladder3 = new JLabel();
        ladder4 = new JLabel();
        ram4 = new JLabel();
        ladder1 = new JLabel();
        cctoken = new JLabel();
        ram1 = new JLabel();
        tower4 = new JLabel();
        tower3 = new JLabel();
        tower2 = new JLabel();
        ladder2 = new JLabel();
        ram2 = new JLabel();
        tower1 = new JLabel();
        ram3 = new JLabel();
        trebuchet1 = new JLabel();
        trebuchet2 = new JLabel();
        trebuchet3 = new JLabel();
        wall4 = new JLabel();
        morale4 = new JLabel();
        supplies4 = new JLabel();
        wall3 = new JLabel();
        morale3 = new JLabel();
        supplies3 = new JLabel();
        wall2 = new JLabel();
        morale2 = new JLabel();
        supplies2 = new JLabel();
        troopsCastle = new JLabel();
        raidedSupplies1 = new JLabel();
        troopsTunnel1 = new JLabel();
        troopsTunnel2 = new JLabel();
        troopsEnemyLines = new JLabel();
        raidedSupplies2 = new JLabel();
        wall1 = new JLabel();
        morale1 = new JLabel();
        supplies1 = new JLabel();
        player0 = new JLabel();      
        mainStart.setFont(new Font("Vivaldi", Font.BOLD, 36));
        mainStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainStart.setText("Start New Game");
        mainStart.setAutoscrolls(true);
        mainStart.setHorizontalTextPosition(SwingConstants.CENTER);
        mainStart.addActionListener(new MenuWindow.NewGameListener());
        mainLoadGame.setFont(mainStart.getFont());
        mainLoadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainLoadGame.setText("Load Game");
        mainLoadGame.addActionListener(new MenuWindow.LoadObjListener());
        mainExit.setFont(mainStart.getFont());
        mainExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainExit.setText("Exit"); 
        mainExit.addActionListener(new MenuWindow.MainExitListener());       
        startMenuButtonsPanel.setOpaque(false);
        tokensPanel.setOpaque(false);
        GroupLayout startMenuButtonsPanelLayout = new GroupLayout(startMenuButtonsPanel);
        startMenuButtonsPanel.setLayout(startMenuButtonsPanelLayout);
        startMenuButtonsPanelLayout.setHorizontalGroup(startMenuButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainLoadGame, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainStart, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
            .addComponent(mainExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        startMenuButtonsPanelLayout.setVerticalGroup(
            startMenuButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(startMenuButtonsPanelLayout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addComponent(mainStart, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainLoadGame, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainExit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        gamePanel.setPreferredSize(new Dimension(1057, 772));
        playCardsPanel.setName("playCardsPanel"); 
        playCardsPanel.setOpaque(false);
        enemyCardLabel.setIcon(new ImageIcon(getClass().getResource("/ui/gui/Imagens/enemy.png"))); 
        enemyCardLabel.setName("enemyCardLabel"); 
        statusCardLabel.setIcon(new ImageIcon(getClass().getResource("/ui/gui/Imagens/status.png"))); 
        statusCardLabel.setName("statusCardLabel");         
        GroupLayout playCardsPanelLayout = new GroupLayout(playCardsPanel);
        playCardsPanel.setLayout(playCardsPanelLayout);
        playCardsPanelLayout.setHorizontalGroup(
            playCardsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playCardsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(playCardsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(enemyCardLabel, GroupLayout.Alignment.TRAILING)
                    .addComponent(statusCardLabel, GroupLayout.Alignment.TRAILING)))
        );
        playCardsPanelLayout.setVerticalGroup(
            playCardsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, playCardsPanelLayout.createSequentialGroup()
                .addComponent(enemyCardLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusCardLabel, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
        );                 
        rightPanel.setOpaque(false);   
        GameInfoPanel.setOpaque(false);
        gameInfo.setBackground(new Color(153, 153, 153));
        gameInfo.setColumns(3);
        gameInfo.setFont(new Font("Algerian", 1, 24));
        gameInfo.setName("gameInfo");
        gameInfo.setEditable(false);
        gameInfo.setBackground(Color.YELLOW);
        GroupLayout GameInfoPanelLayout = new GroupLayout(GameInfoPanel);
        GameInfoPanel.setLayout(GameInfoPanelLayout);
        GameInfoPanelLayout.setHorizontalGroup(
            GameInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(gameInfo, GroupLayout.Alignment.TRAILING)
        );
        GameInfoPanelLayout.setVerticalGroup(
            GameInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, GameInfoPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(gameInfo, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );        
        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(eventCard))
            .addComponent(GameInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(eventCard)
                .addGap(37, 37, 37)
                .addComponent(GameInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );        
        GroupLayout gamePanelLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                .addComponent(playCardsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(playCardsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );        
        mainMenuBackgroundPanel.setName("mainMenuBackgroundPanel");
        mainMenuBackgroundPanel.setVerifyInputWhenFocusTarget(false);
        img = new ImageIcon(getClass().getResource("/ui/gui/Imagens/symbol.png"));        
        mainBackLabel.setIcon(img);
        mainBackLabel.setMaximumSize(null);
        mainBackLabel.setMinimumSize(new Dimension(0, 0));
        mainBackLabel.setOpaque(true);
        mainBackLabel.setPreferredSize(null);
        mainBackLabel.setRequestFocusEnabled(false);
        mainBackLabel.setVerifyInputWhenFocusTarget(false);       
        GroupLayout mainMenuBackgroundPanelLayout = new GroupLayout(mainMenuBackgroundPanel);
        mainMenuBackgroundPanel.setLayout(mainMenuBackgroundPanelLayout);
        mainMenuBackgroundPanelLayout.setHorizontalGroup(
            mainMenuBackgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainBackLabel, GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
        );
        mainMenuBackgroundPanelLayout.setVerticalGroup(
            mainMenuBackgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, mainMenuBackgroundPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainBackLabel, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE))
        );        
        nextCardButtonPanel.setOpaque(false);
        drawCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        drawCard.setFont(mainStart.getFont());        
        drawCard.setText("Draw Card");
        drawCard.setName("drawCard");         
        drawCard.addActionListener(new MenuWindow.DrawTopCard());        
        GroupLayout awaitCardPanelLayout = new GroupLayout(nextCardButtonPanel);
        nextCardButtonPanel.setLayout(awaitCardPanelLayout);
        awaitCardPanelLayout.setHorizontalGroup(
            awaitCardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(awaitCardPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(drawCard, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        awaitCardPanelLayout.setVerticalGroup(
            awaitCardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, awaitCardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drawCard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        ); 
        awaitOptionPanel.setOpaque(false);
        archerAttackButton.setFont(new Font("Vivaldi", 1, 24));  
        archerAttackButton.setText("Archer Attack");
        archerAttackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        archerAttackButton.addActionListener(new MenuWindow.ArcherAttackListener());
        boilingWaterButton.setFont(archerAttackButton.getFont());
        boilingWaterButton.setText("Boiling Water Attack");
        boilingWaterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boilingWaterButton.addActionListener(new MenuWindow.BoilingWaterAttackListener());
        closeCombatButton.setFont(archerAttackButton.getFont());
        closeCombatButton.setText("Close Combat");
        closeCombatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeCombatButton.addActionListener(new MenuWindow.CloseCombatListener());
        coupureButton.setFont(archerAttackButton.getFont());
        coupureButton.setText("Coupure");
        coupureButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        coupureButton.addActionListener(new MenuWindow.CoupureListener());
        rallyTroopsButton.setFont(archerAttackButton.getFont());
        rallyTroopsButton.setText("Rally Troops");
        rallyTroopsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rallyTroopsButton.addActionListener(new MenuWindow.RallyTroopsListener());
        tunnelMoveButton.setFont(archerAttackButton.getFont());
        tunnelMoveButton.setText("Tunnel Movement");
        tunnelMoveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tunnelMoveButton.addActionListener(new MenuWindow.TunnelMoveListener());
        supplyRaidButton.setFont(archerAttackButton.getFont());
        supplyRaidButton.setText("Supply Raid");
        supplyRaidButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        supplyRaidButton.addActionListener(new MenuWindow.SupplyRaidListener());
        sabotageButton.setFont(archerAttackButton.getFont());
        sabotageButton.setText("Sabotage");
        sabotageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sabotageButton.addActionListener(new MenuWindow.SabotageListener());
        buyPointButton.setFont(archerAttackButton.getFont());
        buyPointButton.setText("Buy Action Point");
        buyPointButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buyPointButton.addActionListener(new MenuWindow.BuyPointListener());        
        ladderButtonForArcherAttack.setFont(archerAttackButton.getFont());
        ladderButtonForArcherAttack.setText("Ladder");
        ladderButtonForArcherAttack.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        ladderButtonForArcherAttack.addActionListener(new MenuWindow.LadderListenerForArcherAttack());
        ramButtonForArcherAttack.setFont(archerAttackButton.getFont());
        ramButtonForArcherAttack.setText("Ram");
        ramButtonForArcherAttack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ramButtonForArcherAttack.addActionListener(new MenuWindow.RamListenerForArcherAttack());
        towerButtonForArcherAttack.setFont(archerAttackButton.getFont());
        towerButtonForArcherAttack.setText("Tower");
        towerButtonForArcherAttack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        towerButtonForArcherAttack.addActionListener(new MenuWindow.TowerListenerForArcherAttack());        
        ladderButtonForBoilingWater.setFont(archerAttackButton.getFont());
        ladderButtonForBoilingWater.setText("Ladder");
        ladderButtonForBoilingWater.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ladderButtonForBoilingWater.addActionListener(new MenuWindow.LadderListenerForBoilingWaterAttack());
        ramButtonForBoilingWater.setFont(archerAttackButton.getFont());
        ramButtonForBoilingWater.setText("Ram");
        ramButtonForBoilingWater.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ramButtonForBoilingWater.addActionListener(new MenuWindow.RamListenerForBoilingWaterAttack());
        towerButtonForBoilingWater.setFont(archerAttackButton.getFont());
        towerButtonForBoilingWater.setText("Tower");
        towerButtonForBoilingWater.setCursor(new Cursor(Cursor.HAND_CURSOR));
        towerButtonForBoilingWater.addActionListener(new MenuWindow.TowerListenerForBoilingWaterAttack());
        GroupLayout enenySelectionPanelForBolingWaterLayout = new GroupLayout(enenySelectionPanelForBolingWater);
        enenySelectionPanelForBolingWater.setLayout(enenySelectionPanelForBolingWaterLayout);
        enenySelectionPanelForBolingWaterLayout.setHorizontalGroup(
            enenySelectionPanelForBolingWaterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(enenySelectionPanelForBolingWaterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enenySelectionPanelForBolingWaterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ladderButtonForBoilingWater, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ramButtonForBoilingWater, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(towerButtonForBoilingWater, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        enenySelectionPanelForBolingWaterLayout.setVerticalGroup(
            enenySelectionPanelForBolingWaterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(enenySelectionPanelForBolingWaterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ladderButtonForBoilingWater)
                .addGap(18, 18, 18)
                .addComponent(ramButtonForBoilingWater)
                .addGap(18, 18, 18)
                .addComponent(towerButtonForBoilingWater)
                .addContainerGap(7, Short.MAX_VALUE))
        );        
        ladderButtonForCloseCombat.setFont(archerAttackButton.getFont());
        ladderButtonForCloseCombat.setText("Ladder");
        ladderButtonForCloseCombat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ladderButtonForCloseCombat.setName("ladderButtonForCloseCombat"); 
        ladderButtonForCloseCombat.addActionListener(new MenuWindow.LadderListenerForCloseCombatAttack());
        ramButtonForCloseCombat.setFont(archerAttackButton.getFont());
        ramButtonForCloseCombat.setText("Ram");
        ramButtonForCloseCombat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ramButtonForCloseCombat.setName("ramButtonForCloseCombat"); 
        ramButtonForCloseCombat.addActionListener(new MenuWindow.RamListenerForCloseCombatAttack());
        towerButtonForCloseCombat.setFont(archerAttackButton.getFont());
        towerButtonForCloseCombat.setText("Tower");
        towerButtonForCloseCombat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        towerButtonForCloseCombat.setName("towerButtonForCloseCombat"); 
        towerButtonForCloseCombat.addActionListener(new MenuWindow.TowerListenerForCloseCombatAttack());
        GroupLayout enenySelectionForCloseCombatLayout = new GroupLayout(enenySelectionForCloseCombat);
        enenySelectionForCloseCombat.setLayout(enenySelectionForCloseCombatLayout);
        enenySelectionForCloseCombatLayout.setHorizontalGroup(
            enenySelectionForCloseCombatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(enenySelectionForCloseCombatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enenySelectionForCloseCombatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ladderButtonForCloseCombat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ramButtonForCloseCombat, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(towerButtonForCloseCombat, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        enenySelectionForCloseCombatLayout.setVerticalGroup(
            enenySelectionForCloseCombatLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(enenySelectionForCloseCombatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ladderButtonForCloseCombat)
                .addGap(18, 18, 18)
                .addComponent(ramButtonForCloseCombat)
                .addGap(18, 18, 18)
                .addComponent(towerButtonForCloseCombat)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        GroupLayout awaitOptionPanelLayout = new GroupLayout(awaitOptionPanel);
        awaitOptionPanel.setLayout(awaitOptionPanelLayout);
        awaitOptionPanelLayout.setHorizontalGroup(
            awaitOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(archerAttackButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(boilingWaterButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(closeCombatButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(coupureButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(rallyTroopsButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(tunnelMoveButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(supplyRaidButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(sabotageButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
            .addComponent(buyPointButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
        awaitOptionPanelLayout.setVerticalGroup(
            awaitOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, awaitOptionPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(archerAttackButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boilingWaterButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(closeCombatButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(coupureButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rallyTroopsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tunnelMoveButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supplyRaidButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sabotageButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buyPointButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );        
        GroupLayout enenySelectionPanelLayout = new GroupLayout(enemySelectionForArcherAttack);
       enemySelectionForArcherAttack.setLayout(enenySelectionPanelLayout);
        enenySelectionPanelLayout.setHorizontalGroup(
            enenySelectionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(enenySelectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enenySelectionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(ladderButtonForArcherAttack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ramButtonForArcherAttack, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addComponent(towerButtonForArcherAttack, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        enenySelectionPanelLayout.setVerticalGroup(
            enenySelectionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(enenySelectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ladderButtonForArcherAttack)
                .addGap(18, 18, 18)
                .addComponent(ramButtonForArcherAttack)
                .addGap(18, 18, 18)
                .addComponent(towerButtonForArcherAttack)
                .addContainerGap(7, Short.MAX_VALUE))
        );        
        buyPointPanel.setOpaque(false);
        loseMoraleButton.setFont(archerAttackButton.getFont());
        loseMoraleButton.setText("Lose Morale");
        loseMoraleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loseMoraleButton.addActionListener(new MenuWindow.LoseMoraleListener());
        loseSuppliesButton.setFont(archerAttackButton.getFont());
        loseSuppliesButton.setText("Lose Supplies");
        loseSuppliesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loseSuppliesButton.setName("loseSuppliesButton"); 
        loseSuppliesButton.addActionListener(new MenuWindow.LoseSuppliesListener());
        GroupLayout buyPointPanelLayout = new GroupLayout(buyPointPanel);
        buyPointPanel.setLayout(buyPointPanelLayout);
        buyPointPanelLayout.setHorizontalGroup(
            buyPointPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(loseMoraleButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loseSuppliesButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );
        buyPointPanelLayout.setVerticalGroup(
            buyPointPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(buyPointPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loseMoraleButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loseSuppliesButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        movementSelectionPanel.setOpaque(false);
        freeMoveForwardButton.setFont(archerAttackButton.getFont());
        freeMoveForwardButton.setText("Free Move Forward");
        freeMoveForwardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        freeMoveForwardButton.addActionListener(new MenuWindow.FreeMoveForwardListener());
        freeMoveBackButton.setFont(archerAttackButton.getFont());
        freeMoveBackButton.setText("Free Move Back");
        freeMoveBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        freeMoveBackButton.addActionListener(new MenuWindow.FreeMoveBackListener());
        automaticForwardButton.setFont(archerAttackButton.getFont());
        automaticForwardButton.setText("Automatic Forward");
        automaticForwardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        automaticForwardButton.addActionListener(new MenuWindow.AutomaticForwardListener());
        automaticBackButton.setFont(archerAttackButton.getFont());
        automaticBackButton.setText("Automatic Back");
        automaticBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        automaticBackButton.addActionListener(new MenuWindow.AutomaticBackListener());
        GroupLayout movementSelectionPanelLayout = new GroupLayout(movementSelectionPanel);
        movementSelectionPanel.setLayout(movementSelectionPanelLayout);
        movementSelectionPanelLayout.setHorizontalGroup(
            movementSelectionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(freeMoveForwardButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(freeMoveBackButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(automaticForwardButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(automaticBackButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        movementSelectionPanelLayout.setVerticalGroup(
            movementSelectionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(movementSelectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(freeMoveForwardButton)
                .addGap(18, 18, 18)
                .addComponent(freeMoveBackButton)
                .addGap(18, 18, 18)
                .addComponent(automaticForwardButton)
                .addGap(18, 18, 18)
                .addComponent(automaticBackButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );                
        try{
        enemyToken = new ImageIcon(getClass().getResource("/ui/gui/Imagens/tokens/enemy.png"));
        trebuchetToken = new ImageIcon(getClass().getResource("/ui/gui/Imagens/tokens/trebuchet.png"));
        playerToken = new ImageIcon(getClass().getResource("/ui/gui/Imagens/tokens/player.png"));
        tunnelToken = new ImageIcon(getClass().getResource("/ui/gui/Imagens/tokens/troops.png"));
        raidToken = new ImageIcon(getClass().getResource("/ui/gui/Imagens/tokens/supplies.png"));
        }catch(Exception x){
        }        
        ladder3.setIcon(enemyToken); 
        ladder4.setIcon(enemyToken); 
        ram4.setIcon(enemyToken); 
        ladder1.setIcon(enemyToken); 
        cctoken.setIcon(enemyToken); 
        ram1.setIcon(enemyToken); 
        tower4.setIcon(enemyToken);    
        tower3.setIcon(enemyToken);  
        tower2.setIcon(enemyToken);
        ladder2.setIcon(enemyToken); 
        ram2.setIcon(enemyToken);    
        tower1.setIcon(enemyToken);    
        ram3.setIcon(enemyToken);   
        trebuchet1.setIcon(trebuchetToken); 
        trebuchet2.setIcon(trebuchetToken); 
        trebuchet3.setIcon(trebuchetToken); 
        wall4.setIcon(playerToken); 
        morale4.setIcon(playerToken); 
        supplies4.setIcon(playerToken);  
        wall3.setIcon(playerToken); 
        morale3.setIcon(playerToken); 
        supplies3.setIcon(playerToken);  
        wall2.setIcon(playerToken); 
        morale2.setIcon(playerToken); 
        supplies2.setIcon(playerToken);  
        troopsCastle.setIcon(tunnelToken); 
        raidedSupplies1.setIcon(raidToken); 
        troopsTunnel1.setIcon(tunnelToken); 
        troopsTunnel2.setIcon(tunnelToken); 
        troopsEnemyLines.setIcon(tunnelToken); 
        raidedSupplies2.setIcon(raidToken); 
        wall1.setIcon(playerToken); 
        morale1.setIcon(playerToken); 
        supplies1.setIcon(playerToken); 
        player0.setIcon(playerToken); 
        
        
        tokensPanel.setName("tokensPanel"); 
        tokensPanel.setOpaque(false);

        ccPanel.setOpaque(false); 


        GroupLayout ccPanelLayout = new GroupLayout(ccPanel);
        ccPanel.setLayout(ccPanelLayout);
        ccPanelLayout.setHorizontalGroup(
            ccPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ccPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ccPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(cctoken)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ccPanelLayout.setVerticalGroup(
            ccPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ccPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ccPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(cctoken)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel1.setOpaque(false);
        GroupLayout ramPanel1Layout = new GroupLayout(ramPanel1);
        ramPanel1.setLayout(ramPanel1Layout);
        ramPanel1Layout.setHorizontalGroup(
            ramPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ramPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel1Layout.setVerticalGroup(
            ramPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ramPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel2.setOpaque(false);
        GroupLayout ramPanel2Layout = new GroupLayout(ramPanel2);
        ramPanel2.setLayout(ramPanel2Layout);
        ramPanel2Layout.setHorizontalGroup(
            ramPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ramPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel2Layout.setVerticalGroup(
            ramPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ramPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel3.setOpaque(false);
        GroupLayout ramPanel3Layout = new GroupLayout(ramPanel3);
        ramPanel3.setLayout(ramPanel3Layout);
        ramPanel3Layout.setHorizontalGroup(
            ramPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ramPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel3Layout.setVerticalGroup(
            ramPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ramPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel4.setOpaque(false); 
        GroupLayout ramPanel4Layout = new GroupLayout(ramPanel4);
        ramPanel4.setLayout(ramPanel4Layout);
        ramPanel4Layout.setHorizontalGroup(
            ramPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ramPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ramPanel4Layout.setVerticalGroup(
            ramPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ramPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ramPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ram4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        ); 
        ladderPanel1.setOpaque(false);
        GroupLayout ladderPanel1Layout = new GroupLayout(ladderPanel1);
        ladderPanel1.setLayout(ladderPanel1Layout);
        ladderPanel1Layout.setHorizontalGroup(
            ladderPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ladderPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel1Layout.setVerticalGroup(
            ladderPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ladderPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel2.setOpaque(false);
        GroupLayout ladderPanel2Layout = new GroupLayout(ladderPanel2);
        ladderPanel2.setLayout(ladderPanel2Layout);
        ladderPanel2Layout.setHorizontalGroup(
            ladderPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ladderPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel2Layout.setVerticalGroup(
            ladderPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ladderPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel1.setOpaque(false);
        GroupLayout towerPanel1Layout = new GroupLayout(towerPanel1);
        towerPanel1.setLayout(towerPanel1Layout);
        towerPanel1Layout.setHorizontalGroup(
            towerPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(towerPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel1Layout.setVerticalGroup(
            towerPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(towerPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel2.setOpaque(false);
        GroupLayout towerPanel2Layout = new GroupLayout(towerPanel2);
        towerPanel2.setLayout(towerPanel2Layout);
        towerPanel2Layout.setHorizontalGroup(
            towerPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(towerPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel2Layout.setVerticalGroup(
            towerPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(towerPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel3.setOpaque(false);
        GroupLayout towerPanel3Layout = new GroupLayout(towerPanel3);
        towerPanel3.setLayout(towerPanel3Layout);
        towerPanel3Layout.setHorizontalGroup(
            towerPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(towerPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel3Layout.setVerticalGroup(
            towerPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(towerPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel3.setOpaque(false);
        GroupLayout ladderPanel3Layout = new GroupLayout(ladderPanel3);
        ladderPanel3.setLayout(ladderPanel3Layout);
        ladderPanel3Layout.setHorizontalGroup(
            ladderPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ladderPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel3Layout.setVerticalGroup(
            ladderPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ladderPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel4.setOpaque(false);
        GroupLayout ladderPanel4Layout = new GroupLayout(ladderPanel4);
        ladderPanel4.setLayout(ladderPanel4Layout);
        ladderPanel4Layout.setHorizontalGroup(
            ladderPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(ladderPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ladderPanel4Layout.setVerticalGroup(
            ladderPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(ladderPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ladderPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ladder4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel4.setOpaque(false);
        GroupLayout towerPanel4Layout = new GroupLayout(towerPanel4);
        towerPanel4.setLayout(towerPanel4Layout);
        towerPanel4Layout.setHorizontalGroup(
            towerPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(towerPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        towerPanel4Layout.setVerticalGroup(
            towerPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(towerPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(towerPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tower4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        trebuchetPanel1.setOpaque(false);
        GroupLayout trebuchetPanel1Layout = new GroupLayout(trebuchetPanel1);
        trebuchetPanel1.setLayout(trebuchetPanel1Layout);
        trebuchetPanel1Layout.setHorizontalGroup(
            trebuchetPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(trebuchetPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(trebuchetPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(trebuchet1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        trebuchetPanel1Layout.setVerticalGroup(
            trebuchetPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(trebuchetPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(trebuchetPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(trebuchet1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        trebuchetPanel2.setOpaque(false);
        GroupLayout trebuchetPanel2Layout = new GroupLayout(trebuchetPanel2);
        trebuchetPanel2.setLayout(trebuchetPanel2Layout);
        trebuchetPanel2Layout.setHorizontalGroup(
            trebuchetPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(trebuchetPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(trebuchetPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(trebuchet2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        trebuchetPanel2Layout.setVerticalGroup(
            trebuchetPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(trebuchetPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(trebuchetPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(trebuchet2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        trebuchetPanel3.setOpaque(false);
        GroupLayout trebuchetPanel3Layout = new GroupLayout(trebuchetPanel3);
        trebuchetPanel3.setLayout(trebuchetPanel3Layout);
        trebuchetPanel3Layout.setHorizontalGroup(
            trebuchetPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(trebuchetPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(trebuchetPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(trebuchet3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        trebuchetPanel3Layout.setVerticalGroup(
            trebuchetPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(trebuchetPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(trebuchetPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(trebuchet3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel4.setOpaque(false);
        GroupLayout wallPanel4Layout = new GroupLayout(wallPanel4);
        wallPanel4.setLayout(wallPanel4Layout);
        wallPanel4Layout.setHorizontalGroup(
            wallPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(wallPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel4Layout.setVerticalGroup(
            wallPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(wallPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel4.setOpaque(false);
        GroupLayout moralePanel4Layout = new GroupLayout(moralePanel4);
        moralePanel4.setLayout(moralePanel4Layout);
        moralePanel4Layout.setHorizontalGroup(
            moralePanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(moralePanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel4Layout.setVerticalGroup(
            moralePanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(moralePanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel4.setOpaque(false);
        GroupLayout suppliesPanel4Layout = new GroupLayout(suppliesPanel4);
        suppliesPanel4.setLayout(suppliesPanel4Layout);
        suppliesPanel4Layout.setHorizontalGroup(
            suppliesPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(suppliesPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel4Layout.setVerticalGroup(
            suppliesPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(suppliesPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel3.setOpaque(false);
        GroupLayout moralePanel3Layout = new GroupLayout(moralePanel3);
        moralePanel3.setLayout(moralePanel3Layout);
        moralePanel3Layout.setHorizontalGroup(
            moralePanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(moralePanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel3Layout.setVerticalGroup(
            moralePanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(moralePanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel3.setOpaque(false);
        GroupLayout wallPanel3Layout = new GroupLayout(wallPanel3);
        wallPanel3.setLayout(wallPanel3Layout);
        wallPanel3Layout.setHorizontalGroup(
            wallPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(wallPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel3Layout.setVerticalGroup(
            wallPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(wallPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        suppliesPanel3.setOpaque(false);
        GroupLayout suppliesPanel3Layout = new GroupLayout(suppliesPanel3);
        suppliesPanel3.setLayout(suppliesPanel3Layout);
        suppliesPanel3Layout.setHorizontalGroup(
            suppliesPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(suppliesPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel3Layout.setVerticalGroup(
            suppliesPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(suppliesPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel2.setOpaque(false);
        GroupLayout wallPanel2Layout = new GroupLayout(wallPanel2);
        wallPanel2.setLayout(wallPanel2Layout);
        wallPanel2Layout.setHorizontalGroup(
            wallPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(wallPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel2Layout.setVerticalGroup(
            wallPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(wallPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel2.setOpaque(false);
        GroupLayout moralePanel2Layout = new GroupLayout(moralePanel2);
        moralePanel2.setLayout(moralePanel2Layout);
        moralePanel2Layout.setHorizontalGroup(
            moralePanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(moralePanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel2Layout.setVerticalGroup(
            moralePanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(moralePanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel2.setOpaque(false);
        GroupLayout suppliesPanel2Layout = new GroupLayout(suppliesPanel2);
        suppliesPanel2.setLayout(suppliesPanel2Layout);
        suppliesPanel2Layout.setHorizontalGroup(
            suppliesPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(suppliesPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel2Layout.setVerticalGroup(
            suppliesPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(suppliesPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel1.setOpaque(false);
        GroupLayout wallPanel1Layout = new GroupLayout(wallPanel1);
        wallPanel1.setLayout(wallPanel1Layout);
        wallPanel1Layout.setHorizontalGroup(
            wallPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(wallPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        wallPanel1Layout.setVerticalGroup(
            wallPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(wallPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(wallPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(wall1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel1.setOpaque(false);
        GroupLayout moralePanel1Layout = new GroupLayout(moralePanel1);
        moralePanel1.setLayout(moralePanel1Layout);
        moralePanel1Layout.setHorizontalGroup(
            moralePanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(moralePanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        moralePanel1Layout.setVerticalGroup(
            moralePanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(moralePanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(moralePanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(morale1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel1.setOpaque(false);
        GroupLayout suppliesPanel1Layout = new GroupLayout(suppliesPanel1);
        suppliesPanel1.setLayout(suppliesPanel1Layout);
        suppliesPanel1Layout.setHorizontalGroup(
            suppliesPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(suppliesPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        suppliesPanel1Layout.setVerticalGroup(
            suppliesPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(suppliesPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppliesPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(supplies1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        player0Panel.setOpaque(false);
        GroupLayout player0PanelLayout = new GroupLayout(player0Panel);
        player0Panel.setLayout(player0PanelLayout);
        player0PanelLayout.setHorizontalGroup(
            player0PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(player0PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(player0PanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(player0)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        player0PanelLayout.setVerticalGroup(
            player0PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
            .addGroup(player0PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(player0PanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(player0)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        tunelPanel0.setOpaque(false);
        GroupLayout tunelPanel0Layout = new GroupLayout(tunelPanel0);
        tunelPanel0.setLayout(tunelPanel0Layout);
        tunelPanel0Layout.setHorizontalGroup(
            tunelPanel0Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel0Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel0Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsCastle)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel0Layout.setVerticalGroup(
            tunelPanel0Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel0Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel0Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsCastle)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel1.setOpaque(false);
        GroupLayout tunelPanel1Layout = new GroupLayout(tunelPanel1);
        tunelPanel1.setLayout(tunelPanel1Layout);
        tunelPanel1Layout.setHorizontalGroup(
            tunelPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel1Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsTunnel1)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel1Layout.setVerticalGroup(
            tunelPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel1Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsTunnel1)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel2.setOpaque(false);
        GroupLayout tunelPanel2Layout = new GroupLayout(tunelPanel2);
        tunelPanel2.setLayout(tunelPanel2Layout);
        tunelPanel2Layout.setHorizontalGroup(
            tunelPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel2Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsTunnel2)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel2Layout.setVerticalGroup(
            tunelPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel2Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsTunnel2)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel3.setOpaque(false);
        GroupLayout tunelPanel3Layout = new GroupLayout(tunelPanel3);
        tunelPanel3.setLayout(tunelPanel3Layout);
        tunelPanel3Layout.setHorizontalGroup(
            tunelPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel3Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsEnemyLines)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        tunelPanel3Layout.setVerticalGroup(
            tunelPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(tunelPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tunelPanel3Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(troopsEnemyLines)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        suppyRaidPanel2.setOpaque(false);
        GroupLayout suppyRaidPanel2Layout = new GroupLayout(suppyRaidPanel2);
        suppyRaidPanel2.setLayout(suppyRaidPanel2Layout);
        suppyRaidPanel2Layout.setHorizontalGroup(
            suppyRaidPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(suppyRaidPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppyRaidPanel2Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(raidedSupplies2)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        suppyRaidPanel2Layout.setVerticalGroup(
            suppyRaidPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(suppyRaidPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppyRaidPanel2Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(raidedSupplies2)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        suppyRaidPanel1.setOpaque(false);
        GroupLayout suppyRaidPanel1Layout = new GroupLayout(suppyRaidPanel1);
        suppyRaidPanel1.setLayout(suppyRaidPanel1Layout);
        suppyRaidPanel1Layout.setHorizontalGroup(
            suppyRaidPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(suppyRaidPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppyRaidPanel1Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(raidedSupplies1)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        suppyRaidPanel1Layout.setVerticalGroup(
            suppyRaidPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(suppyRaidPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(suppyRaidPanel1Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(raidedSupplies1)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );

 GroupLayout tokensPanelLayout = new GroupLayout(tokensPanel);
        tokensPanel.setLayout(tokensPanelLayout);
        tokensPanelLayout.setHorizontalGroup(
            tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tokensPanelLayout.createSequentialGroup()
                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(tokensPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(tunelPanel0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tunelPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tunelPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tunelPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(suppyRaidPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(tokensPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ccPanel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(tokensPanelLayout.createSequentialGroup()
                                        .addComponent(trebuchetPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(trebuchetPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                        .addComponent(ladderPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ramPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                        .addComponent(ladderPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ramPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                        .addComponent(ladderPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ramPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                        .addComponent(ladderPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(ramPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(tokensPanelLayout.createSequentialGroup()
                                    .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(wallPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wallPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wallPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wallPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(39, 39, 39)
                                    .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(moralePanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(moralePanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(moralePanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(moralePanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(player0Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(tokensPanelLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(towerPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(towerPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(towerPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(towerPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trebuchetPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(suppliesPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(suppliesPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(suppliesPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(suppliesPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(tokensPanelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(suppyRaidPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        tokensPanelLayout.setVerticalGroup(
            tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tokensPanelLayout.createSequentialGroup()
                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(suppliesPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(tokensPanelLayout.createSequentialGroup()
                        .addComponent(ccPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ramPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ladderPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(towerPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ramPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ladderPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(towerPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ramPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(towerPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ladderPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ramPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ladderPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(towerPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(tokensPanelLayout.createSequentialGroup()
                                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(trebuchetPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trebuchetPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                                .addComponent(trebuchetPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(wallPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(moralePanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(moralePanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(wallPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliesPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(wallPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(moralePanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliesPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(wallPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(moralePanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliesPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, tokensPanelLayout.createSequentialGroup()
                        .addComponent(suppyRaidPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suppyRaidPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(tokensPanelLayout.createSequentialGroup()
                        .addComponent(player0Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tokensPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(tunelPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tunelPanel0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tunelPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tunelPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        
       
        firstMenuLayered.add(awaitOptionPanel);
        firstMenuLayered.add(nextCardButtonPanel);               
        firstMenuLayered.add(startMenuButtonsPanel);
        firstMenuLayered.add(enemySelectionForArcherAttack);
        firstMenuLayered.add(enenySelectionPanelForBolingWater);
        firstMenuLayered.add(buyPointPanel);
        firstMenuLayered.add(movementSelectionPanel);        
        firstMenuLayered.add(enenySelectionForCloseCombat);   
        firstMenuLayered.add(tokensPanel);
        firstMenuLayered.add(gamePanel);
        firstMenuLayered.add(mainMenuBackgroundPanel);
        
               

        GroupLayout firstMenuLayeredLayout = new GroupLayout(firstMenuLayered);
        firstMenuLayered.setLayout(firstMenuLayeredLayout);       
        firstMenuLayeredLayout.setHorizontalGroup(
            firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1064, Short.MAX_VALUE)
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(179, 179, 179)
                    .addComponent(startMenuButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainMenuBackgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap(339, Short.MAX_VALUE)
                    .addComponent(nextCardButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(339, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(awaitOptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(enemySelectionForArcherAttack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap(437, Short.MAX_VALUE)
                    .addComponent(enenySelectionPanelForBolingWater, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(417, 417, 417)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap(447, Short.MAX_VALUE)
                    .addComponent(enenySelectionForCloseCombat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(407, 407, 407)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(buyPointPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(movementSelectionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))         
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                .addContainerGap(779, Short.MAX_VALUE)
                .addComponent(tokensPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))    
                
        );    

        firstMenuLayeredLayout.setVerticalGroup(
            firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 766, Short.MAX_VALUE)
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(100, 100, 100)
                    .addComponent(startMenuButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(101, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainMenuBackgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap(330, Short.MAX_VALUE)
                    .addComponent(nextCardButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(370, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(awaitOptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(enemySelectionForArcherAttack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap(298, Short.MAX_VALUE)
                    .addComponent(enenySelectionPanelForBolingWater, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(278, 278, 278)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, firstMenuLayeredLayout.createSequentialGroup()
                    .addContainerGap(308, Short.MAX_VALUE)
                    .addComponent(enenySelectionForCloseCombat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(268, 268, 268)))
                .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(buyPointPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(movementSelectionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))      
            .addGroup(firstMenuLayeredLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(firstMenuLayeredLayout.createSequentialGroup()
                    .addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 766, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))  
            .addComponent(tokensPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)    
        );
        
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(firstMenuLayered, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(firstMenuLayered, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        
    }
       
    private void updateTextArea(){  
        if(game.getGameData().isBuyFlag() || game.getGameData().isErrorflag() || game.getGameData().isBoilingWatterAttack() ||  game.getGameData().isOneFreeMoveOnlyErrorflag()){
            gameInfo.setBackground(Color.RED);
            gameInfo.append(ERRO1);
        } 
        else
            resetTextAreaColor();
    }
    
    private void showMessage(int dado){
            JOptionPane.showMessageDialog(MenuWindow.this,
            DADO + dado, "Dado", JOptionPane.INFORMATION_MESSAGE);      
    }
       
    private void resetTextAreaColor(){
        gameInfo.setBackground(Color.YELLOW);
    }
    
    private void placeEventCardImage(){
        try{               
        eventCard.setIcon(new ImageIcon(getClass().getResource(game.getGameData().getCurrentCard().getCardLocation())));
        }catch(NullPointerException x){        
        }
    }
    
    class FreeMoveForwardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.automaticTunnelMoveForward();
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class FreeMoveBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.automaticTunnelMoveBack();
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class AutomaticForwardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.tunnelMoveForwardWithActionPoint();
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class AutomaticBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.tunnelMoveBackWithActionPoint();
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class LoseSuppliesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.buyActionPointSupply();
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class LoseMoraleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           game.buyActionPointMorale();
           game.drawNextCard(game.getGameData().getDado());
        }
    }

    class LadderListenerForArcherAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);            
            game.executeArcherAttack(1, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class RamListenerForArcherAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();   
            showMessage(dado);            
            game.executeArcherAttack(2, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class TowerListenerForArcherAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.executeArcherAttack(3, dado); 
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class LadderListenerForBoilingWaterAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);            
            game.executeBoilingWaterAttack(1, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class RamListenerForBoilingWaterAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.executeBoilingWaterAttack(2, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class TowerListenerForBoilingWaterAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);            
            game.executeBoilingWaterAttack(3, dado);
            game.drawNextCard(4);
        }
    }

class LadderListenerForCloseCombatAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado(); 
            showMessage(dado);
            game.executeCloseCombat(1, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class RamListenerForCloseCombatAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            gameInfo.append(DADO + dado); 
            showMessage(dado);
            game.executeCloseCombat(2, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

class TowerListenerForCloseCombatAttack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.executeCloseCombat(3, dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }
    
    class DrawTopCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.drawTopCard(); 
        }
    }

    class BuyPointListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.buyActionPoint();
        }
    }

    class SabotageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.sabotage(dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class SupplyRaidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.supplyRaid(dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class TunnelMoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.tunnelMove();
        }
    }

    class RallyTroopsListener implements ActionListener {
      @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.rallyTroops(dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }

    class CoupureListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dado = game.getGameData().getDado();
            showMessage(dado);
            game.coupure(dado);
            game.drawNextCard(game.getGameData().getDado());
        }
    }
   
    class CloseCombatListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.closeCombat();
        }
    }

    class BoilingWaterAttackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.boilingWaterAttack();
        }
    }

    class ArcherAttackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.archerAttack();            
        }
    }

    class HelpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File("./help/9CS_PlayerAid_20171217.pdf"));
                } catch (IOException ex) {
                }
            }
        }
    }                                             

    class SaveObjMenuBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser("./gui_saved");
            if (jfc.showSaveDialog(MenuWindow.this) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                try{
                    LoadSave.saveGame(file, game.getGame());
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(MenuWindow.this, "Operation failed: \r\n\r\n" + e);
                }
            } else {
                System.out.println("Operation canceled...");
            }
        }
    }                                       

    class DetailedHelpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File("./help/9CS_Rules_20171217.pdf"));
                } catch (IOException ex) {
                }
            }
        }
    }                                                 
                                     
    class ContentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MenuWindow.this,
                    "Nine Card Siege\nRicardo Silva\n21120616",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    class NewGameListener implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {      
            game.start();   
        }                                         
    }
    
        class StartNewGameListener implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {              
            game.mainMenu();   
        }                                         
    }
    
    class LoadObjListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./gui_saved");
            if (fc.showOpenDialog(MenuWindow.this) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();             
                try{
                    game.setGame((Game)LoadSave.loadGame(file));
                }catch(IOException | ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(MenuWindow.this, "Operation failed: \r\n\r\n" + e);
                }         
            } 
        }       
    }  
    
    class MainExitListener implements ActionListener{       
        @Override
        public void actionPerformed(ActionEvent e) {                                         
        System.exit(0);
        }                  
    }  
                                              
    @Override
    public void update(Observable o, Object arg){ 
        mainMenuBar.setVisible(!(game.getState() instanceof AwaitBeginning));
        startMenuButtonsPanel.setVisible(game.getState() instanceof AwaitBeginning);
        mainBackLabel.setVisible(game.getState() instanceof AwaitBeginning || game.getState() instanceof AwaitTopCard); 
        nextCardButtonPanel.setVisible(game.getState() instanceof AwaitTopCard);
        awaitOptionPanel.setVisible(game.getState() instanceof AwaitOptionSelection);  
        enemySelectionForArcherAttack.setVisible(game.getState() instanceof AwaitTrackSelectionForArcherAttack);
        enenySelectionPanelForBolingWater.setVisible(game.getState() instanceof AwaitTrackSelectionForBoilingWaterAttack);
        enenySelectionForCloseCombat.setVisible(game.getState() instanceof AwaitEnemySelectionForCloseCombat);
        movementSelectionPanel.setVisible(game.getState() instanceof AwaitForTunnelActionSelection);
        buyPointPanel.setVisible(game.getState() instanceof AwaitPaymentSelectionForActionPoint);
        gamePanel.setVisible(game.getState() instanceof AwaitOptionSelection || game.getState() instanceof AwaitPaymentSelectionForActionPoint ||
        game.getState() instanceof AwaitForTunnelActionSelection || game.getState() instanceof AwaitEnemySelectionForCloseCombat ||
        game.getState() instanceof AwaitTrackSelectionForBoilingWaterAttack || game.getState() instanceof AwaitTrackSelectionForArcherAttack);
        tokensPanel.setVisible(game.getState() instanceof AwaitOptionSelection || game.getState() instanceof AwaitPaymentSelectionForActionPoint ||
        game.getState() instanceof AwaitForTunnelActionSelection || game.getState() instanceof AwaitEnemySelectionForCloseCombat ||
        game.getState() instanceof AwaitTrackSelectionForBoilingWaterAttack || game.getState() instanceof AwaitTrackSelectionForArcherAttack);
        gameInfo.setText(game.getGameData().toString()); 
        placeEventCardImage(); 
        placeTokens();
        updateTextArea();
    }                   
}
