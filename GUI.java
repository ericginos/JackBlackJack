import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame {

	// window resolution
	int aW = 1280;
	int aH = 837;
	double bankAmount = 2500;
	double betAmount = 0;
	int maxBet = 500;
	int minBet = 1;
	// int originalBet = 0;
	int pCardCount = 0;
	int dCardCount = 0;
	int s1CardCount = 0;
	int s2CardCount = 0;

	int split = 0;
	double splitAmount = 0;
	int leftDone = 0;
	
	String playStatus = "OFF";
   
	

	
	/*Settings dialog will set the number of decks*/
	int numOfDecks = 1;
	

	Board board = new Board();
	//background music
	BackgroundMusic bgMusic = new BackgroundMusic();
	// player and dealer's hand instantiation
	//Deck blackjackDeck = new Deck();
	
	Deck blackjackDeck = new Deck(numOfDecks);
	
	
	CardHand dealersHand = new CardHand();
	CardHand playersHand = new CardHand();
	CardHand splitHand1 = new CardHand();
	CardHand splitHand2 = new CardHand();

	String playerStatus;
	String dealerStatus;
	String split1Status;
	String split2Status;

	// Load table image
	JLabel picLabelTable = new JLabel();
	

	// colorBackground
	// Color colorBackground = new Color(39,119,20);
	// Color colorButtn = new Color(204,204,0);

	// Buttons
	// Toolkit t = Toolkit.getDefaultToolkit(); //not sure what this is or even if
	// we need it????

	JButton bDeal = new JButton(new ImageIcon("resources/Buttons/button_deal.png"));
	JButton bHit = new JButton(new ImageIcon("resources/Buttons/button_hit.png"));
	JButton bStand = new JButton(new ImageIcon("resources/Buttons/button_stand.png"));
	JButton bDoubleDown = new JButton(new ImageIcon("resources/Buttons/button_double.png"));
	JButton bSplit = new JButton(new ImageIcon("resources/Buttons/button_split.png"));
	JButton bClearBet = new JButton(new ImageIcon("resources/Buttons/button_clear-bet.png"));
	JButton bNewHand = new JButton(new ImageIcon("resources/Buttons/button_play.png"));

	JButton bOne = new JButton(new ImageIcon("resources/chips/OneDollar.png"));
	JButton bFive = new JButton(new ImageIcon("resources/chips/FiveDollar.png"));
	JButton bTwentyFive = new JButton(new ImageIcon("resources/chips/TwentyFiveDollar.png"));
	JButton bOneHun = new JButton(new ImageIcon("resources/chips/OneHunDollar.png"));
	JButton bFiveHun = new JButton(new ImageIcon("resources/chips/FiveHunDollar.png"));
	JLabel labelUpdate = new JLabel();
	
	JLabel labelBackDeck = new JLabel();
	
	JButton bMusic = new JButton("Music");

	JLabel labelPlayer = new JLabel();
	JLabel labelDealer = new JLabel();

	JLabel labelSplit1 = new JLabel();
	JLabel labelSplit2 = new JLabel();
	JLabel split1Update = new JLabel();
	JLabel split2Update = new JLabel();

	JLabel labelPlayerCard1 = new JLabel();
	JLabel labelDealerCard1 = new JLabel();

	JLabel labelPlayerCard2 = new JLabel();
	JLabel labelDealerCard2 = new JLabel();

	// Sharon's code
	private JLabel[] createLabels() {
		JLabel[] card = new JLabel[11];
		for (int i = 0; i < 11; i++) {
			card[i] = new JLabel();
		}
		return card;
	}

	JLabel[] labelPlayerCard = createLabels();
	JLabel[] labelDealerCard = createLabels();
	JLabel[] labelSplitCard1 = createLabels();
	JLabel[] labelSplitCard2 = createLabels();

	// ---------------------------

	JLabel labelBankAmount = new JLabel();
	JLabel labelBetAmount = new JLabel();

	// font
	// Font fontButton = new Font("Calibri", Font.PLAIN, 12); //do we need this????
	
	
	static JMenuBar menubar;
    static JMenu options, submenu;
    static JMenuItem option1, option2, option3, option4, option5, option6;
	
	
	public GUI() {

		ImageIcon imageIcon = new ImageIcon("resources/40%.png");

		int n = JOptionPane.showConfirmDialog(null, "14 Rules of BlackJack: \n \n \n"
				+ "1. The goal of blackjack is to beat the dealer's hand without going over 21.\n"
				+ "2. Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.\n"
				+ "3. Each player starts with two cards, one of the dealer's cards is hidden until the end.\n"
				+ "4. To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n"
				+ "5. If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.\n"
				+ "6. If you are dealt 21 from the start (Ace & 10), you got a blackjack.\n"
				+ "7. Blackjack usually means you win 1.5 the amount of your bet. Depends on the casino.\n"
				+ "8. Dealer will hit until his/her cards total 17 or higher.\n"
				+ "9. Doubling is like a hit, only the bet is doubled and you only get one more card.\n"
				+ "10. Split can be done when you have two of the same card - the pair is split into two hands\n"
				+ "11. Splitting also doubles the bet, because each new hand is worth the original bet\n"
				+ "12. You can only double/split on the first move, or first move of a hand created by a split.\n"
				+ "13. You cannot play on two aces after they are split.\n"
				+ "14. You can only double down on hand totals equaling to 9,10,11\n \n "
				+ "Are you ready to play BlackJack?", "BlackJack1.1", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, imageIcon);

		if (n == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
		if (n == JOptionPane.YES_OPTION) {
			// bBet.setEnabled(false);
			playSound("resources/sounds/Laugh.wav");
			bDoubleDown.setEnabled(false);
			bDeal.setEnabled(true);
			bStand.setEnabled(false);
			bHit.setEnabled(false);
			bSplit.setEnabled(false);
			//background music
			//playBackgroundMusic("START");
			bgMusic.playBackgroundMusic();
			
		}
	    if (n == JOptionPane.CLOSED_OPTION) {
	          System.exit(0);
	      }
		
		// this.setBackground(colorBackground);
		this.setSize(aW, aH);
		this.setTitle("JackBlack 1.2");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(board);
		this.setLayout(null);

		menubar = new JMenuBar();
	      options = new JMenu("Menu");
	      submenu=new JMenu("Settings");

	      option1 = new JMenuItem("Number of Decks");
	      option2 = new JMenuItem("About");
	      option3 = new JMenuItem("Quit");
	      option4 = new JMenuItem("Volume");
	      option5 = new JMenuItem("Starting Amount");
	      option6 = new JMenuItem("Background Music");

	      // add menu items to menu
	      options.add(option1);
	      options.add(option2);
	      options.add(option3);

	      submenu.add(option1);
	      submenu.add(option4);
	      submenu.add(option5);
	      submenu.add(option6);
	      options.add(submenu);

	      MenuNumOfDecks aMenuNumOfDecks = new MenuNumOfDecks();
	      option1.addActionListener(aMenuNumOfDecks);

	      MenuAbout aMenuAbout = new MenuAbout();
	      option2.addActionListener(aMenuAbout);

	      MenuQuit aMenuQuit = new MenuQuit();
	      option3.addActionListener(aMenuQuit);

	      MenuVolume aMenuVolume = new MenuVolume();
	      option4.addActionListener(aMenuVolume);

	      MenuAmount aMenuAmount = new MenuAmount();
	      option5.addActionListener(aMenuAmount);
	      
	      MenuBackGroundMusic aMenuBackgroundMusic = new MenuBackGroundMusic();
	      option6.addActionListener(aMenuBackgroundMusic);

	      // add menu to menu bar
	      menubar.add(options);
	      this.setJMenuBar(menubar);
	      this.setContentPane(board);
	      this.setLayout(null);
	      
		add(labelUpdate);
		labelUpdate.setBounds(250, 280, 800, 100);
		labelUpdate.setForeground(Color.WHITE);
		labelUpdate.setVisible(true);
		labelUpdate.setText("WELCOME TO JACK BLACK'S BLACK JACK!");
		labelUpdate.setFont(new Font("Helvetica Neue", Font.PLAIN, 35));

		add(labelPlayer);
		labelPlayer.setBounds(490, 475, 700, 100);
		labelPlayer.setForeground(Color.WHITE);
		labelPlayer.setText("Player's Hand");
		labelPlayer.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
		labelPlayer.setVisible(false);

		add(labelDealer);
		labelDealer.setBounds(490, 100, 700, 100);
		labelDealer.setForeground(Color.WHITE);
		labelDealer.setText("Dealer's Hand");
		labelDealer.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
		labelDealer.setVisible(false);

		add(labelSplit1);
		labelSplit1.setBounds(400, 440, 700, 100);
		labelSplit1.setForeground(Color.WHITE);
		labelSplit1.setVisible(false);
		labelSplit1.setText("Left Side");
		labelSplit1.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

		add(labelSplit2);
		labelSplit2.setBounds(750, 440, 700, 100);
		labelSplit2.setForeground(Color.WHITE);
		labelSplit2.setVisible(false);
		labelSplit2.setText("Right Side");
		labelSplit2.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

		add(split1Update);
		split1Update.setBounds(300, 280, 800, 100);
		split1Update.setForeground(Color.WHITE);
		split1Update.setVisible(false);
		split1Update.setText("Hi");
		split1Update.setFont(new Font("Helvetica Neue", Font.PLAIN, 35));

		add(split2Update);
		split2Update.setBounds(700, 280, 800, 100);
		split2Update.setForeground(Color.WHITE);
		split2Update.setVisible(false);
		split2Update.setText("HI");
		split2Update.setFont(new Font("Helvetica Neue", Font.PLAIN, 35));
		int adjustButton = 70;

		board.add(bNewHand);
		ActNewHand aNewHand = new ActNewHand();
		bNewHand.addActionListener(aNewHand);
		// bDeal.setBounds(835, 600, 100, 40);
		bNewHand.setBounds(220 + adjustButton, 600, 100, 40);
		bNewHand.setOpaque(false);
		bNewHand.setContentAreaFilled(false);
		bNewHand.setBorderPainted(false);
		if (bankAmount <= 0) {
			labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
			bNewHand.setEnabled(false);
		} else {
			bNewHand.setEnabled(true);
		}

		board.add(bDeal);
		ActDeal aDeal = new ActDeal();
		bDeal.addActionListener(aDeal);
		// bDeal.setBounds(835, 600, 100, 40);
		bDeal.setBounds(340 + adjustButton, 600, 100, 40);
		bDeal.setOpaque(false);
		bDeal.setContentAreaFilled(false);
		bDeal.setBorderPainted(false);
		bDeal.setEnabled(false);

		board.add(bHit);
		ActHit aHit = new ActHit();
		bHit.addActionListener(aHit);
		bHit.setBounds(460 + adjustButton, 600, 100, 40);
		bHit.setOpaque(false);
		bHit.setContentAreaFilled(false);
		bHit.setBorderPainted(false);

		/*
		 * board.add(bBet); ActBet aBet = new ActBet(); bBet.addActionListener(aBet);
		 * bBet.setBounds(595, 600, 100, 40); bBet.setOpaque(false);
		 * bBet.setContentAreaFilled(false); bBet.setBorderPainted(false);
		 */

		board.add(bStand);
		ActStand aStand = new ActStand();
		bStand.addActionListener(aStand);
		// bStand.setBounds(715, 600, 100, 40);
		bStand.setBounds(580 + adjustButton, 600, 100, 40);
		bStand.setOpaque(false);
		bStand.setContentAreaFilled(false);
		bStand.setBorderPainted(false);

		board.add(bDoubleDown);
		ActDoubleDown aDoubleDown = new ActDoubleDown();
		bDoubleDown.addActionListener(aDoubleDown);
		// bDoubleDown.setBounds(355, 600, 100, 40);
		bDoubleDown.setBounds(700 + adjustButton, 600, 100, 40);
		bDoubleDown.setOpaque(false);
		bDoubleDown.setContentAreaFilled(false);
		bDoubleDown.setBorderPainted(false);

		board.add(bSplit);
		ActSplit aSplit = new ActSplit();
		bSplit.addActionListener(aSplit);
		// bDoubleDown.setBounds(355, 600, 100, 40);
		bSplit.setBounds(820 + adjustButton, 600, 100, 40);
		bSplit.setOpaque(false);
		bSplit.setContentAreaFilled(false);
		bSplit.setBorderPainted(false);

		board.add(bClearBet);
		ActClearBet aClearBet = new ActClearBet();
		bClearBet.addActionListener(aClearBet);
		// bDeal.setBounds(835, 600, 100, 40);
		bClearBet.setBounds(930, 710, 100, 40);
		bClearBet.setOpaque(false);
		bClearBet.setContentAreaFilled(false);
		bClearBet.setBorderPainted(false);
		bClearBet.setVisible(false);
		bClearBet.setEnabled(false);

		// MONEY BUTTONS

		board.add(bOne);
		ActOne aOne = new ActOne();
		bOne.addActionListener(aOne);
		bOne.setBounds(340, 650, 100, 103);
		bOne.setOpaque(false);
		bOne.setContentAreaFilled(false);
		bOne.setBorderPainted(false);
		bOne.setEnabled(false);

		board.add(bFive);
		ActFive aFive = new ActFive();
		bFive.addActionListener(aFive);
		bFive.setBounds(460, 660, 100, 102);
		bFive.setOpaque(false);
		bFive.setContentAreaFilled(false);
		bFive.setBorderPainted(false);
		bFive.setEnabled(false);

		board.add(bTwentyFive);
		ActTwentyFive aTwentyFive = new ActTwentyFive();
		bTwentyFive.addActionListener(aTwentyFive);
		bTwentyFive.setBounds(580, 660, 100, 99);
		bTwentyFive.setOpaque(false);
		bTwentyFive.setContentAreaFilled(false);
		bTwentyFive.setBorderPainted(false);
		bTwentyFive.setEnabled(false);

		board.add(bOneHun);
		ActOneHun aOneHun = new ActOneHun();
		bOneHun.addActionListener(aOneHun);
		bOneHun.setBounds(700, 660, 100, 100);
		bOneHun.setOpaque(false);
		bOneHun.setContentAreaFilled(false);
		bOneHun.setBorderPainted(false);
		bOneHun.setEnabled(false);

		board.add(bFiveHun);
		ActFivHun aFivHun = new ActFivHun();
		bFiveHun.addActionListener(aFivHun);
		bFiveHun.setBounds(820, 650, 100, 102);
		bFiveHun.setOpaque(false);
		bFiveHun.setContentAreaFilled(false);
		bFiveHun.setBorderPainted(false);
		bFiveHun.setEnabled(false);
		
		/*board.add(bMusic);
		Music aMusic = new Music();
		bMusic.addActionListener(aMusic);
		bMusic.setBounds(50, 50, 100, 20);
		bMusic.setOpaque(true);
		bMusic.setContentAreaFilled(true);
		bMusic.setBorderPainted(true);
		bMusic.setEnabled(true);*/
		

		/*
		 * labelUpdate.setText(""); System.out.println("Shuffling cards...");
		 * labelUpdate.setText("Shuffling cards..."); blackjackDeck.shuffle();
		 * playShuffle(); sleep(3); playShuffle(); sleep(3); playShuffle(); sleep(3);
		 * labelUpdate.setText("Place Bet"); System.out.println("Place Bet");
		 */
		add(labelBankAmount);
		labelBankAmount.setBounds(930, 620, 300, 100);
		labelBankAmount.setForeground(Color.WHITE);
		labelBankAmount.setVisible(true);
		labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
		labelBankAmount.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));

		add(labelBetAmount);
		labelBetAmount.setBounds(930, 640, 300, 100);
		labelBetAmount.setForeground(Color.WHITE);
		labelBetAmount.setVisible(true);
		labelBetAmount.setText("BET AMOUNT: $" + String.format("%.2f", betAmount));
		labelBetAmount.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		
		add(labelBackDeck);
	      labelBackDeck.setBounds(0, 50,74,98);
	      labelBackDeck.setIcon(new ImageIcon("resources/cards/b.gif"));
	      labelBackDeck.setVisible(false);


		/*
		 * bOne.setEnabled(true); bFive.setEnabled(true);;
		 * bTwentyFive.setEnabled(true);; bOneHun.setEnabled(true);
		 * bFiveHun.setEnabled(true);
		 */
	}


	public static void playBackgroundMusic(String bgStatus) {
		try {
			
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("resources/sounds/Background.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			long clipTimePosition = 0;
			
			if(bgStatus == "START") {

			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
			}
			
			if(bgStatus == "OFF") {
				clipTimePosition = clip.getMicrosecondPosition();
				clip.stop();
			}
			
			if(bgStatus == "ON") {
				clip.setMicrosecondPosition(clipTimePosition);
				clip.start();
			}
			
		} catch (Exception ex) {
			System.out.println("Error with playing Background sound.");
			ex.printStackTrace();
		}

	}
	
	
	
	public static void playShuffle() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("resources/sounds/shufflingcards.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}

	}
	
	public void playSounds(String soundName)
	   {
	      try
	      {
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	         Clip clip = AudioSystem.getClip( );
	         clip.open(audioInputStream);
	         clip.start( );
	      }
	      catch(Exception ex)
	      {
	         System.out.println("Error with playing sound.");
	         ex.printStackTrace( );
	      }
	   }
	

	public static void sleep(int time) {

		try {
			TimeUnit.SECONDS.sleep(time);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class Board extends JPanel {

		public void paintComponent(Graphics g) {
			// g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);

			// Main background screen image
			Toolkit t = Toolkit.getDefaultToolkit();
			Image i = t.getImage("resources/table1280x820.png");
			// Image displayCard=t.getImage("resources/cards/2c.gif");
			// Image displayCardtwo=t.getImage("resources/cards/6s.gif");
			g.drawImage(i, 0, 0, this);
			// g.drawImage(displayCard, 555, 350,100, 150,this);
			// g.drawImage(displayCardtwo, 575, 350, 100, 150,this);

			// Cards Displays Area
			// g.setColor(Color.black);
			// g.drawRect(675, 350, 150, 200);
			// g.drawRect(475, 50, 150, 200);
			// g.drawRect(675, 50, 150, 200);

		}

	}

	public void clearTable() {

		split = 0;
		leftDone = 0;

		labelPlayer.setText("");
		labelDealer.setText("");
		labelSplit1.setText("");
		labelSplit2.setText("");
		split1Update.setText("");
		split2Update.setText("");

		playersHand.clearHand();
		dealersHand.clearHand();
		splitHand1.clearHand();
		splitHand2.clearHand();

		for (int i = 0; i < labelPlayerCard.length; i++) {
			// labelPlayerCard[i].setVisible(false);
			labelPlayerCard[i].setIcon(new ImageIcon(" "));
		}

		for (int i = 0; i < labelDealerCard.length; i++) {
			// labelDealerCard[i].setVisible(false);
			labelDealerCard[i].setIcon(new ImageIcon(" "));
		}

		for (int i = 0; i < labelSplitCard1.length; i++) {
			// labelSplitCard1[i].setVisible(false);
			labelSplitCard1[i].setIcon(new ImageIcon(" "));
		}

		for (int i = 0; i < labelSplitCard2.length; i++) {
			// labelSplitCard2[i].setVisible(false);
			labelSplitCard2[i].setIcon(new ImageIcon(" "));
		}

	}

	public class ActNewHand implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			 labelBackDeck.setVisible(true);

			clearTable();

			betAmount = 0;
			labelBetAmount.setText("BET AMOUNT: $" + betAmount);

			labelUpdate.setBounds(500, 280, 700, 100);
			labelUpdate.setText("CLICK PLAY BUTTON TO START");
			System.out.println("Play button was clicked");

			labelUpdate.setText("");
			System.out.println("Shuffling cards...");
			labelUpdate.setText("SHUFFLING CARDS...");
			
			/*Karl*/
			/*Shuffle deck when new deck shoe has been created */
			if(blackjackDeck.numCardsDealt() == 0)
			{
				blackjackDeck.shuffle();
				playShuffle();			
			}
			
			/*Shuffle deck when number of cards dealt is greater than half of the size of the original shoe size*/
			if(blackjackDeck.numCardsDealt() > (blackjackDeck.numCardsInDeck()/2))
			{
				blackjackDeck.shuffle();
				playShuffle();
			}			
			/*KarlEnd*/

			labelUpdate.setText("PLACE BET");
			System.out.println("Place Bet");
			playSound("resources/sounds/Doyouhaveanymoney.wav");

			bDeal.setEnabled(false);
			bHit.setEnabled(false);
			bStand.setEnabled(false);
			bSplit.setEnabled(false);
			bDoubleDown.setEnabled(false);
			bNewHand.setEnabled(false);

			bOne.setEnabled(true);
			bFive.setEnabled(true);
			;
			bTwentyFive.setEnabled(true);
			;
			bOneHun.setEnabled(true);
			bFiveHun.setEnabled(true);
			
			playSounds("resources/Sounds/ButtonClick2.wav");

		}
	}

	public class ActDeal implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// Testing Split
			//bSplit.setEnabled(true);

			System.out.println("Deal button was clicked.");

			bClearBet.setEnabled(false);
			labelUpdate.setText("Dealing cards...");
			System.out.println("Dealing cards...");

			playersHand.addCard(blackjackDeck.dealCard());
			dealersHand.addCard(blackjackDeck.dealCard());
			playersHand.addCard(blackjackDeck.dealCard());
			dealersHand.addCard(blackjackDeck.dealCard());

			// Sharon's code

			pCardCount = 2;
			dCardCount = 2;
			// card image label
			// player cards
			for (int i = 1; i >= 0; i--) {
				add(labelPlayerCard[i]);
				labelPlayerCard[i].setBounds(550 + ((i) * 20), 325, 80, 200);
				AnimationPlayer phAnimation = new AnimationPlayer(labelPlayerCard[i]);
				phAnimation.start();
				labelPlayerCard[i].setIcon(new ImageIcon("resources/cards/" + playersHand.printCardImage(i)));
			}

			// dealers cards
			add(labelDealerCard[2]);
			add(labelDealerCard[1]);
			labelDealerCard[1].setBounds(550, 125, 80, 200);
			labelDealerCard[2].setBounds(570, 125, 80, 200);
			AnimationDealer d1Animation = new AnimationDealer(labelDealerCard[1]);
			d1Animation.start();
			
			labelDealerCard[1].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(0)));
			labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));
			labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + "b.gif"));
			
			AnimationDealer d2Animation = new AnimationDealer(labelDealerCard[2]);
			d2Animation.start();
			
			
			for (int i = 0; i < labelDealerCard.length; i++) {
				labelPlayerCard[i].setVisible(true);
			}

			for (int i = 0; i < labelDealerCard.length; i++) {
				labelDealerCard[i].setVisible(true);
			}

			System.out.println("Players Hand");
			playersHand.printHand();
			playersHand.printHandImage();
			System.out.println("Total " + playersHand.handValue());
			System.out.println("Status " + playersHand.handStatus());
			System.out.println();
			playerStatus = playersHand.handStatus();
			System.out.println();
			System.out.println("Dealer's Hand");
			dealersHand.printHand();
			dealersHand.printHandImage();
			System.out.println("Total " + dealersHand.handValue());
			System.out.println("Status " + dealersHand.handStatus());
			System.out.println();
			dealerStatus = dealersHand.handStatus();
			System.out.println();
			bDeal.setEnabled(false);

			if (playerStatus == "play") {
				labelUpdate.setText("HIT OR STAND?");
				bHit.setEnabled(true);
				bStand.setEnabled(true);
			}
			if (playerStatus == "doubledown") {
				labelUpdate.setText("HIT, STAND, OR DOUBLE DOWN?");
				bHit.setEnabled(true);
				bStand.setEnabled(true);
				bDoubleDown.setEnabled(true);
			}
			if (playerStatus == "split") {
				labelUpdate.setText("HIT, STAND, OR SPLIT?");
				bHit.setEnabled(true);
				bStand.setEnabled(true);
				bSplit.setEnabled(true);
			}
			if ((playerStatus == "blackjack") && (dealerStatus == "blackjack")) {
				// labelDealerCard1.setIcon(new ImageIcon("resources/cards/" +
				// dealersHand.printCardImage(1)));
				// flips 2nd card
				playSound("resources/sounds/Noway.wav");
				labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));
				labelUpdate.setText("DRAW");
				bankAmount += betAmount;
				betAmount = 0;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
				labelPlayer.setText("PLAYER'S HAND: BLACKJACK " + playersHand.handValue());
				labelDealer.setText("DEALER'S HAND: BLACKJACK " + dealersHand.handValue());
				bDeal.setEnabled(false);
				bHit.setEnabled(false);
				bStand.setEnabled(false);
				bSplit.setEnabled(false);
				bDoubleDown.setEnabled(false);
				if (bankAmount <= 0) {
					labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
					bNewHand.setEnabled(false);
				} else {
					bNewHand.setEnabled(true);
				}
			}
			if ((playerStatus == "blackjack") && (dealerStatus != "blackjack")) {

				// labelDealerCard1.setIcon(new ImageIcon("resources/cards/" +
				// dealersHand.printCardImage(1)));
				// flips 2nd card
				playSound("resources/sounds/Ilostmywallet.wav");
				labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));
				labelUpdate.setText("BLACKJACK YOU WIN! $" + 1.5 * betAmount);
				bankAmount += betAmount + (1.5 * betAmount);
				betAmount = 0;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
				labelPlayer.setText("PLAYER'S HAND:  BLACKJACK " + playersHand.handValue());
				labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
				bDeal.setEnabled(false);
				bHit.setEnabled(false);
				bStand.setEnabled(false);
				bSplit.setEnabled(false);
				bDoubleDown.setEnabled(false);
				if (bankAmount <= 0) {
					labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
					bNewHand.setEnabled(false);
				} else {
					bNewHand.setEnabled(true);
				}
			}

			if ((playerStatus != "blackjack") && (dealerStatus == "blackjack")) {
				// labelDealerCard1.setIcon(new ImageIcon("resources/cards/" +
				// dealersHand.printCardImage(1)));
				// flips 2nd card
				playSound("resources/sounds/Iwinonetonothing.wav");
				labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));
				labelUpdate.setText("DEALER HAS BLACKJACK YOU LOSE! $" + betAmount);
				betAmount = 0;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
				labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
				labelDealer.setText("DEALER'S HAND: BLACKJACK " + dealersHand.handValue());
				bDeal.setEnabled(false);
				bHit.setEnabled(false);
				bStand.setEnabled(false);
				bSplit.setEnabled(false);
				bDoubleDown.setEnabled(false);
				if (bankAmount <= 0) {
					labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
					bNewHand.setEnabled(false);
				} else {
					bNewHand.setEnabled(true);
				}
			}

			if ((playerStatus != "blackjack") && (dealerStatus != "blackjack")) {
				labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
				if (dealersHand.getCardValue(0) == 1) {
					labelDealer.setText("DEALER'S UP CARD: ACE");
				} else {
					labelDealer.setText("DEALER'S UP CARD:" + dealersHand.getCardValue(0));
				}
			}

			labelPlayer.setVisible(true);
			labelDealer.setVisible(true);

			bOne.setEnabled(false);
			bFive.setEnabled(false);
			bTwentyFive.setEnabled(false);
			bOneHun.setEnabled(false);
			bFiveHun.setEnabled(false);
			
			playSounds("resources/Sounds/ButtonClick2.wav");
		}

	}

	public class ActHit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * labelUpdate.setText("YOU HIT"); labelUpdate.setOpaque(false);
			 * labelUpdate.setFont(new Font("Serif", Font.BOLD, 35));
			 * System.out.println("you clicked hit");
			 */
			// sharon's code
			labelUpdate.setText("YOU HIT");
			labelUpdate.setOpaque(false);
			labelUpdate.setFont(new Font("Helvetica Neue", Font.PLAIN, 35));
			System.out.println("you clicked hit");

			bSplit.setEnabled(false);
			bDoubleDown.setEnabled(false);

			if (split == 0) {
				for (int i = 2; i < 11; i++) {
					labelPlayerCard[i].setVisible(true);
				}

				playersHand.addCard(blackjackDeck.dealCard());
				pCardCount += 1;

				for (int i = pCardCount - 1; i >= 0; i--) {
					add(labelPlayerCard[i]);
					labelPlayerCard[i].setBounds(550 + ((i) * 20), 325, 80, 200);
					AnimationHand ph1Animation = new AnimationHand(labelPlayerCard[i]);
					ph1Animation.start();
					labelPlayerCard[i].setIcon(new ImageIcon("resources/cards/" + playersHand.printCardImage(i)));
				}

				// Text stuff
				System.out.println("Players Hand");
				playersHand.printHand();
				playersHand.printHandImage();
				System.out.println("Total " + playersHand.handValue());
				System.out.println("Status " + playersHand.handStatus());
				System.out.println();
				playerStatus = playersHand.handStatus();

				labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
				// labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());

				if (playerStatus == "twentyone") {
					labelUpdate.setText("TWENTY ONE!");
					bHit.setEnabled(false);
				}

				if (playerStatus == "bust") {
					// flips 2nd card
					labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));
					// Added 11/1/2020
					playSound("resources/sounds/Sorry.wav");
					labelUpdate.setText("BUST! YOU LOSE $" + betAmount);
					betAmount = 0;
					bDeal.setEnabled(false);
					bHit.setEnabled(false);
					bStand.setEnabled(false);
					bSplit.setEnabled(false);
					bDoubleDown.setEnabled(false);
					if (bankAmount <= 0) {
						labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
						bNewHand.setEnabled(false);
					} else {
						bNewHand.setEnabled(true);
					}
					labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
					labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
					// Added 11/1/2020
					labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
				}
			}
			if (split == 1) {

				if (leftDone == 0) {
					System.out.println("In left hand.");
					splitHand1.addCard(blackjackDeck.dealCard());
					s1CardCount += 1;

					for (int i = s1CardCount - 1; i >= 0; i--) {
						add(labelSplitCard1[i]);
						labelSplitCard1[i].setBounds(400 + ((i) * 20), 325, 80, 200);
						AnimationHand phAnimation = new AnimationHand(labelSplitCard1[i]);
						phAnimation.start();
						labelSplitCard1[i].setIcon(new ImageIcon("resources/cards/" + splitHand1.printCardImage(i)));
					}
					// Text stuff
					System.out.println("Players First Hand");
					splitHand1.printHand();
					splitHand1.printHandImage();
					System.out.println("Total " + splitHand1.handValue());
					System.out.println("Status " + splitHand1.handStatus());
					split1Status = splitHand1.handStatus();
					System.out.println();

					labelSplit1.setText("Value: " + splitHand1.handValue());

					if (split1Status == "twentyone") {
						labelUpdate.setText("Left Hand = Twentyone! Switching to Right Hand...");
						leftDone = 1;
						return;
					}

					if (split1Status == "bust") {
						labelUpdate.setText("Left Hand = Bust! Switching to Right Hand...");
						labelSplit1.setText("Bust!");
						leftDone = 1;
						return;
					}
				}
				if (leftDone == 1) {
					System.out.println(leftDone);
					System.out.println("In right hand.");

					if (split2Status == "blackjack") {
						bStand.doClick();
					}

					splitHand2.addCard(blackjackDeck.dealCard());
					s2CardCount += 1;

					for (int i = s2CardCount - 1; i >= 0; i--) {
						add(labelSplitCard2[i]);
						labelSplitCard2[i].setBounds(750 + ((i) * 20), 325, 80, 200);
						AnimationHand phAnimation = new AnimationHand(labelSplitCard2[i]);
						phAnimation.start();
						labelSplitCard2[i].setIcon(new ImageIcon("resources/cards/" + splitHand2.printCardImage(i)));
					}

					// Text stuff
					System.out.println("Players Second Hand");
					splitHand2.printHand();
					splitHand2.printHandImage();
					System.out.println("Total " + splitHand2.handValue());
					System.out.println("Status " + splitHand2.handStatus());
					System.out.println();
					split2Status = splitHand2.handStatus();

					labelSplit2.setText("Value: " + splitHand2.handValue());

					if (split2Status == "twentyone") {
						labelUpdate.setText("Right Hand = Twentyone!");
						bHit.setEnabled(false);
						bStand.doClick();
					}

					if (split2Status == "bust") {
						labelUpdate.setText("Right Hand = Bust!");
						labelSplit2.setText("Bust!");
						bHit.setEnabled(false);
						bStand.doClick();
					}
				}
			}
			
			playSounds("resources/Sounds/ButtonClick2.wav");
		}
	}

	/*
	 * public class ActBet implements ActionListener {
	 *
	 * @Override public void actionPerformed(ActionEvent e) {
	 * labelUpdate.setText("Place your bet...");
	 *
	 * System.out.println( "Bet button was clicked"); }
	 *
	 * }
	 */
	public class ActStand implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * labelUpdate.setText("YOU STAND"); System.out.println( "you clicked stand");
			 */
			// Sharon's code

			if (split == 0) {
				labelUpdate.setText("YOU STAND");

				System.out.println("you clicked stand");

				// flips 2nd card
				labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));

				do {
					System.out.println("Dealer's Hand");
					dealersHand.printHand();
					dealersHand.printHandImage();
					System.out.println("Total " + dealersHand.handValue());
					System.out.println("Status " + dealersHand.dealersHandStatus());
					System.out.println();
					dealerStatus = dealersHand.dealersHandStatus();
					if (dealerStatus == "play") {
						dealersHand.addCard(blackjackDeck.dealCard());
						dCardCount += 1;

						// have to redisplay all cards otherwise it doesn't overlap properly
						for (int i = dCardCount - 1; i >= 0; i--) {
							add(labelDealerCard[i]);
							labelDealerCard[i].setBounds(550 + ((i) * 20), 125, 80, 200);
							AnimationHand d2Animation = new AnimationHand(labelDealerCard[i]);
							d2Animation.start();
							labelDealerCard[i]
									.setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(i)));
						}
						dealerStatus = dealersHand.dealersHandStatus();
					}
				} while (dealerStatus == "play");

				if (dealerStatus == "bust") {
					// Bank = Bank + originalBet*2;
					bankAmount = bankAmount + betAmount + betAmount;
					playSound("resources/sounds/Ilostmywallet.wav");
					// System.out.println("You win\nPlayers total: " + playersHand.handValue() +
					// "\nDealers total: " + dealersHand.handValue() + "\nYour Bank: $" + Bank);
					System.out.println("You win\nPlayers total: " + playersHand.handValue() + "\nDealers total: "
							+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
					labelUpdate.setText("YOU WIN! $" + String.format("%.2f", betAmount));
					labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
					labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
				}

				if ((dealerStatus == "stand") || (dealerStatus == "twentyone")) {
					if (dealersHand.handValue() < playersHand.handValue()) {
						playSound("resources/sounds/Ilostmywallet.wav");
						bankAmount = bankAmount + betAmount + betAmount;
						System.out.println("You win\nPlayers total: " + playersHand.handValue() + "\nDealers total: "
								+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
						labelUpdate.setText("YOU WIN! $" + String.format("%.2f", betAmount));
						labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
						labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
						betAmount = 0;
					}
					if (dealersHand.handValue() > playersHand.handValue()) {
						playSound("resources/sounds/Sorry.wav");
						System.out.println("You lose\nPlayers total: " + playersHand.handValue() + "\nDealers total: "
								+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
						labelUpdate.setText("YOU LOST! $" + String.format("%.2f", betAmount));
						labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
						labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
						betAmount = 0;
					}
					if (dealersHand.handValue() == playersHand.handValue()) {
						bankAmount = bankAmount + betAmount;
						playSound("resources/sounds/Noway.wav");
						labelUpdate.setText("DRAW!");
						labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
						labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
						System.out.println("Tie");
						betAmount = 0;
					}
				}

				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", splitAmount));
				bDeal.setEnabled(false);
				bHit.setEnabled(false);
				bStand.setEnabled(false);
				bSplit.setEnabled(false);
				bDoubleDown.setEnabled(false);
				if (bankAmount <= 0) {
					labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
					bNewHand.setEnabled(false);
				} else {
					bNewHand.setEnabled(true);
				}
			}
			if (split == 1) {
				if (leftDone == 0) {
					labelUpdate.setText("Switching to Right Hand...");
					System.out.println("You hit stand. Switching to Right Hand");
					bHit.setEnabled(true);
					bStand.setEnabled(true);
					leftDone = 1;

					// Only second hand is blackjack
					if (split2Status == "blackjack") {
						playSound("resources/sounds/Ilostmywallet.wav");
						labelUpdate.setText("Right: Blackjack!");
						bStand.doClick();
					}
					return;
				}
				if (leftDone == 1) {
					labelUpdate.setText("");
					split1Update.setVisible(true);
					split2Update.setVisible(true);

					System.out.println("you clicked stand in split and done with left");

					// flips 2nd card
					labelDealerCard[2].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(1)));

					do {
						System.out.println("Dealer's Hand");
						dealersHand.printHand();
						dealersHand.printHandImage();
						System.out.println("Total " + dealersHand.handValue());
						System.out.println("Status " + dealersHand.dealersHandStatus());
						System.out.println();
						dealerStatus = dealersHand.dealersHandStatus();
						if (dealerStatus == "play") {
							dealersHand.addCard(blackjackDeck.dealCard());
							dCardCount += 1;

							// have to redisplay all cards otherwise it doesn't overlap properly
							for (int i = dCardCount - 1; i >= 0; i--) {
								add(labelDealerCard[i]);
								labelDealerCard[i].setBounds(550 + ((i) * 20), 125, 80, 200);
								AnimationHand d2Animation = new AnimationHand(labelDealerCard[i]);
								d2Animation.start();
								labelDealerCard[i]
										.setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(i)));
							}
							dealerStatus = dealersHand.dealersHandStatus();
						}
					} while (dealerStatus == "play");
					labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());

					// Split blackjack
					if (split1Status == "blackjack" && dealerStatus != "blackjack") {
						playSound("resources/sounds/Ilostmywallet.wav");
						split1Update.setText("Left: BLACKJACK YOU WIN! $" + 1.5 * betAmount);
						bankAmount += betAmount + (1.5 * betAmount);
					}
					if (split2Status == "blackjack" && dealerStatus != "blackjack") {

						split2Update.setText("Right: BLACKJACK YOU WIN! $" + 1.5 * betAmount);
						bankAmount += betAmount + (1.5 * betAmount);
					}

					if (split1Status == "bust") {
						playSound("resources/sounds/Whistle.wav");
						System.out.println("You lose\nPlayers totals: " + splitHand1.handValue() + "\nDealers total: "
								+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
						split1Update.setText("Left: YOU LOST! $" + betAmount);
					}
					if (split2Status == "bust") {
						System.out.println("You lose\nPlayers totals: " + splitHand2.handValue() + "\nDealers total: "
								+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
						split2Update.setText("Right: YOU LOST! $" + betAmount);
					}

					if (dealerStatus == "bust" && split1Status != "bust") {
						bankAmount = bankAmount + betAmount + betAmount;
						System.out.println("You win\nPlayers totals: " + splitHand1.handValue() + "\nDealers total: "
								+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
						split1Update.setText("Left: YOU WIN! $" + betAmount);
					}
					if (dealerStatus == "bust" && split2Status != "bust") {
						bankAmount = bankAmount + betAmount + betAmount;
						System.out.println("You win\nPlayers totals: " + splitHand2.handValue() + "\nDealers total: "
								+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
						split2Update.setText("Right Hand: YOU WIN! $" + betAmount);
					}

					if ((dealerStatus == "stand" || dealerStatus == "twentyone") && split1Status != "bust") {
						if (dealersHand.handValue() < splitHand1.handValue()) {
							bankAmount = bankAmount + betAmount + betAmount;
							System.out.println("You win\nPlayers total: " + splitHand1.handValue() + "\nDealers total: "
									+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
							split1Update.setText("Left: YOU WIN! $" + betAmount);
						}

						if (dealersHand.handValue() > splitHand1.handValue()) {

							System.out.println("You lose\nPlayers total: " + splitHand1.handValue()
									+ "\nDealers total: " + dealersHand.handValue() + "\nYour Bank: $"
									+ String.format("%.2f", bankAmount));
							split1Update.setText("Left: YOU LOST! $" + betAmount);
						}

						if (dealersHand.handValue() == splitHand1.handValue()) {
							bankAmount = bankAmount + betAmount;
							split1Update.setText("Left: DRAW!");
							System.out.println("Tie");
						}

					}

					if ((dealerStatus == "stand" || dealerStatus == "twentyone") && split2Status != "bust") {

						if (dealersHand.handValue() < splitHand2.handValue()) {
							bankAmount = bankAmount + betAmount + betAmount;
							System.out.println("You win\nPlayers total: " + splitHand2.handValue() + "\nDealers total: "
									+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
							split2Update.setText("Right: YOU WIN! $" + betAmount);
						}

						if (dealersHand.handValue() > splitHand2.handValue()) {

							System.out.println("You lose\nPlayers total: " + splitHand2.handValue()
									+ "\nDealers total: " + dealersHand.handValue() + "\nYour Bank: $"
									+ String.format("%.2f", bankAmount));
							split2Update.setText("Right: YOU LOST! $" + betAmount);
						}

						if (dealersHand.handValue() == splitHand2.handValue()) {
							bankAmount = bankAmount + betAmount;
							split2Update.setText("Right: DRAW!");
							System.out.println("Tie");
						}
					}
					labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
					labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", splitAmount));
					bDeal.setEnabled(false);
					bHit.setEnabled(false);
					bStand.setEnabled(false);
					bSplit.setEnabled(false);
					bDoubleDown.setEnabled(false);
					if (bankAmount <= 0) {
						labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
						bNewHand.setEnabled(false);
					} else {
						bNewHand.setEnabled(true);
					}

				}
			}
			
			playSounds("resources/Sounds/ButtonClick2.wav");
		}

	}

	public class ActClearBet implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			bankAmount += betAmount;
			betAmount = 0;
			labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			// bClearBet.setVisible(true);
			bClearBet.setEnabled(false);
			bDeal.setEnabled(false);
			labelUpdate.setText("You cleared the bet. Place New Bet");
			System.out.println("you clicked cleared bet");
			
			playSounds("resources/Sounds/WinChips.wav");
		}

	}

	public class ActDoubleDown implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bankAmount -= betAmount;
			betAmount += betAmount;

			labelUpdate.setText("YOU DOUBLED DOWN");
			playSound("resources/sounds/Yeaoneisalluneed.wav");
			labelUpdate.setOpaque(false);
			labelUpdate.setFont(new Font("Helvetica Neue", Font.PLAIN, 35));
			System.out.println("you clicked doubed down");

			bDeal.setEnabled(false);
			bHit.setEnabled(false);
			bStand.setEnabled(false);
			bSplit.setEnabled(false);
			bDoubleDown.setEnabled(false);

			for (int i = 2; i < 11; i++) {
				labelPlayerCard[i].setVisible(true);
			}

			playersHand.addCard(blackjackDeck.dealCard());
			pCardCount += 1;

			for (int i = pCardCount - 1; i >= 0; i--) {
				add(labelPlayerCard[i]);
				labelPlayerCard[i].setBounds(550 + ((i) * 20), 325, 80, 200);
				AnimationHand d2Animation = new AnimationHand(labelPlayerCard[i]);
				d2Animation.start();
				labelPlayerCard[i].setIcon(new ImageIcon("resources/cards/" + playersHand.printCardImage(i)));
			}

			// Text stuff
			System.out.println("Players Hand");
			playersHand.printHand();
			playersHand.printHandImage();
			System.out.println("Total " + playersHand.handValue());
			System.out.println("Status " + playersHand.handStatus());
			System.out.println();
			playerStatus = playersHand.handStatus();

			labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());

			// flips 2nd card
			labelDealerCard[2].setIcon(new ImageIcon("blackjack/resources/cards/" + dealersHand.printCardImage(1)));

			do {
				System.out.println("Dealer's Hand");
				dealersHand.printHand();
				dealersHand.printHandImage();
				System.out.println("Total " + dealersHand.handValue());
				System.out.println("Status " + dealersHand.dealersHandStatus());
				System.out.println();
				dealerStatus = dealersHand.dealersHandStatus();
				if (dealerStatus == "play") {
					dealersHand.addCard(blackjackDeck.dealCard());
					dCardCount += 1;

					// have to redisplay all cards otherwise it doesn't overlap properly
					for (int i = dCardCount - 1; i >= 0; i--) {
						add(labelDealerCard[i]);
						labelDealerCard[i].setBounds(550 + ((i) * 20), 125, 80, 200);
						AnimationHand d2Animation = new AnimationHand(labelDealerCard[i]);
						d2Animation.start();
						labelDealerCard[i].setIcon(new ImageIcon("resources/cards/" + dealersHand.printCardImage(i)));
					}
					dealerStatus = dealersHand.dealersHandStatus();
				}
			} while (dealerStatus == "play");

			if (dealerStatus == "bust") {
				// Bank = Bank + originalBet*2;
				bankAmount = bankAmount + betAmount * 2;
				// System.out.println("You win\nPlayers total: " + playersHand.handValue() +
				// "\nDealers total: " + dealersHand.handValue() + "\nYour Bank: $" + Bank);
				System.out.println("You win\nPlayers total: " + playersHand.handValue() + "\nDealers total: "
						+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
				labelUpdate.setText("YOU WIN! $" + String.format("%.2f", betAmount));
				labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
				labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
			}

			if (dealerStatus == "stand") {
				if (dealersHand.handValue() < playersHand.handValue()) {
					bankAmount = bankAmount + betAmount * 2;

					System.out.println("You win\nPlayers total: " + playersHand.handValue() + "\nDealers total: "
							+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
					labelUpdate.setText("YOU WIN! $" + String.format("%.2f", betAmount));
					labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
					labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
					betAmount = 0;
				}
				if (dealersHand.handValue() > playersHand.handValue()) {

					System.out.println("You lose\nPlayers total: " + playersHand.handValue() + "\nDealers total: "
							+ dealersHand.handValue() + "\nYour Bank: $" + String.format("%.2f", bankAmount));
					labelUpdate.setText("YOU LOST! $" + String.format("%.2f", betAmount));
					labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
					labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
					betAmount = 0;
				}
				if (dealersHand.handValue() == playersHand.handValue()) {

					bankAmount = bankAmount + betAmount;

					labelUpdate.setText("DRAW! $" + betAmount + "RETURNED");
					labelPlayer.setText("PLAYER'S HAND: " + playersHand.handValue());
					labelDealer.setText("DEALER'S HAND: " + dealersHand.handValue());
					System.out.println("Tie");
					betAmount = 0;
				}
			}

			labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			bStand.setEnabled(false);
			bHit.setEnabled(false);
			bSplit.setEnabled(false);
			bDoubleDown.setEnabled(false);
			if (bankAmount <= 0) {
				labelUpdate.setText("GAME OVER! YOUR BANK IS $0.00");
				bNewHand.setEnabled(false);
			} else {
				bNewHand.setEnabled(true);
			}
			
			playSounds("resources/Sounds/ButtonClick2.wav");

		}

	}

	public class ActSplit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelUpdate.setText("Split Cards...");
			System.out.println("you clicked split");

			labelPlayer.setVisible(false);
			split = 1;

			bankAmount -= betAmount;
			splitAmount = betAmount * 2;

			labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", splitAmount));

			labelUpdate.setText("Split Cards...");
			System.out.println("you clicked split");

			splitHand1.addCard(playersHand.getSplitCard(0));
			splitHand2.addCard(playersHand.getSplitCard(1));

			add(labelSplitCard1[1]);
			add(labelSplitCard2[1]);

			labelSplitCard1[1].setBounds(400, 325, 80, 200);
			labelSplitCard2[1].setBounds(750, 325, 80, 200);

			labelSplitCard1[1].setIcon(new ImageIcon("resources/cards/" + splitHand1.printCardImage(0)));
			labelSplitCard2[1].setIcon(new ImageIcon("resources/cards/" + splitHand2.printCardImage(0)));

			// clear the original hand
			playersHand.clearHand();
			for (int i = 0; i < labelPlayerCard.length; i++) {
				// labelPlayerCard[i].setVisible(false);
				labelPlayerCard[i].setIcon(new ImageIcon(" "));
			}

			s1CardCount = 2;
			s2CardCount = 2;
			splitHand1.addCard(blackjackDeck.dealCard());
			splitHand2.addCard(blackjackDeck.dealCard());

			for (int i = s1CardCount - 1; i >= 0; i--) {
				add(labelSplitCard1[i]);
				add(labelSplitCard2[i]);
				labelSplitCard1[i].setBounds(400 + ((i) * 20), 325, 80, 200);
				labelSplitCard2[i].setBounds(750 + ((i) * 20), 325, 80, 200);
				labelSplitCard1[i].setIcon(new ImageIcon("resources/cards/" + splitHand1.printCardImage(i)));
				labelSplitCard2[i].setIcon(new ImageIcon("resources/cards/" + splitHand2.printCardImage(i)));
			}

			s1CardCount = 2;
			s2CardCount = 2;

			labelSplit1.setText("Value: " + splitHand1.handValue());
			labelSplit2.setText("Value: " + splitHand2.handValue());
			labelSplit1.setVisible(true);
			labelSplit2.setVisible(true);

			split1Status = splitHand1.handStatus();
			split2Status = splitHand2.handStatus();

			labelUpdate.setText("Now playing: Left Hand");

			if (split1Status == "blackjack") {
				labelUpdate.setText("Left: Blackjack! Switching to Right Hand");
				leftDone = 1;
			}
			if (split1Status == "blackjack" && split2Status == "blackjack") {
				labelUpdate.setText("Double Blackjack!!!");
				bStand.doClick();
			}

			// Text stuff
			System.out.println("Players First Hand");
			splitHand1.printHand();
			splitHand1.printHandImage();
			System.out.println("Total " + splitHand1.handValue());
			System.out.println("Status " + splitHand1.handStatus());
			System.out.println();

			System.out.println("Players Second Hand");
			splitHand2.printHand();
			splitHand2.printHandImage();
			System.out.println("Total " + splitHand2.handValue());
			System.out.println("Status " + splitHand2.handStatus());
			System.out.println();

			bDeal.setEnabled(false);
			bSplit.setEnabled(false);
			bDoubleDown.setEnabled(false);
			
			playSounds("resources/Sounds/ButtonClick2.wav");
		}

	}

	public class ActOne implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			bClearBet.setVisible(true);
			bClearBet.setEnabled(true);
			bDeal.setEnabled(true);
			bNewHand.setEnabled(false);
			if ((bankAmount - 1) < 0) {
				labelUpdate.setText("You cannot bet more than your bank");
			} else {
				bankAmount -= 1;
				betAmount += 1;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			}
			System.out.println("you clicked $1");
			System.out.println("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			System.out.println("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			
			playSounds("resources/Sounds/ButtonClick.wav");
		}

	}

	public class ActFive implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bClearBet.setVisible(true);
			bClearBet.setEnabled(true);
			bDeal.setEnabled(true);
			bNewHand.setEnabled(false);
			if ((bankAmount - 5) < 0) {
				labelUpdate.setText("You cannot bet more than your bank");
			} else {
				bankAmount -= 5;
				betAmount += 5;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			}
			System.out.println("you clicked $5");
			System.out.println("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			System.out.println("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			
			playSounds("resources/Sounds/ButtonClick.wav");
		}
	}

	public class ActTwentyFive implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bClearBet.setVisible(true);
			bClearBet.setEnabled(true);
			bDeal.setEnabled(true);
			bNewHand.setEnabled(false);
			if ((bankAmount - 25) < 0) {
				labelUpdate.setText("You cannot bet more than your bank");
			} else {
				bankAmount -= 25;
				betAmount += 25;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			}
			System.out.println("you clicked $25");
			System.out.println("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			System.out.println("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			
			playSounds("resources/Sounds/ButtonClick.wav");
		}

	}

	public class ActOneHun implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bClearBet.setVisible(true);
			bClearBet.setEnabled(true);
			bDeal.setEnabled(true);
			bNewHand.setEnabled(false);
			if ((bankAmount - 100) < 0) {
				labelUpdate.setText("You cannot bet more than your bank");
			} else {
				bankAmount -= 100;
				betAmount += 100;
				labelBankAmount.setText("BANK AMOUNT: $" + bankAmount);
				labelBetAmount.setText("BET AMOUNT:  $" + betAmount);
			}
			System.out.println("you clicked $100");
			System.out.println("BANK AMOUNT: $" + bankAmount);
			System.out.println("BET AMOUNT:  $" + betAmount);
			
			playSounds("resources/Sounds/ButtonClick.wav");
		}

	}

	public class ActFivHun implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bClearBet.setVisible(true);
			bClearBet.setEnabled(true);
			bDeal.setEnabled(true);
			bNewHand.setEnabled(false);
			if ((bankAmount - 500) < 0) {
				bDeal.setEnabled(true);
				labelUpdate.setText("You cannot bet more than your bank");
			} else {
				bankAmount -= 500;
				betAmount += 500;
				labelBankAmount.setText("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
				labelBetAmount.setText("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			}
			System.out.println("you clicked $500");
			System.out.println("BANK AMOUNT: $" + String.format("%.2f", bankAmount));
			System.out.println("BET AMOUNT:  $" + String.format("%.2f", betAmount));
			
			playSounds("resources/Sounds/ButtonClick.wav");
		}

	}
	void playSound(String soundFile) {
	    File f = new File("./" + soundFile);
	    AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			clip.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    clip.start();
	}
	
	
	static JFrame f;

    // slider
    static JSlider a;

    // label
    static JLabel al;

    public class MenuNumOfDecks implements ActionListener {
       @Override

       public void actionPerformed(ActionEvent e) {
    	   
    	    String[] choices = { "1", "2", "3", "4", "5", "6" };
    	    ImageIcon cardIcon = new ImageIcon("resources/20%.png");
    	    String input = (String)JOptionPane.showInputDialog(null, "Select the number of decks to play.",
    	        "Number of Decks", JOptionPane.QUESTION_MESSAGE, 
    	        cardIcon, //card icon
    	        choices, // Array of choices
    	        choices[0]); // Initial choice
    	    System.out.println("Number of decks to play: " + input);
	      }
       }
    
    public class MenuBackGroundMusic implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
     	   
     	   String[] choices = { "ON", "OFF", };
     	   String backgroundMusicSelection;
     	   ImageIcon musicIcon = new ImageIcon("resources/music.png");
   	    String input = (String)JOptionPane.showInputDialog(null, "Play Background Music",
    	        "Background Music", JOptionPane.QUESTION_MESSAGE, 
    	        musicIcon, //card icon
    	        choices, // Array of choices
    	        choices[0]); // Initial choice
   	    	backgroundMusicSelection = input;
   	    	
   	    	if(backgroundMusicSelection == "ON"){
   	    		bgMusic.playBackgroundMusic();
   	    	}
   	    	if(backgroundMusicSelection == "OFF"){
   	    		bgMusic.stopBackgroundMusic();
   	    	}
  	    	
    	    System.out.println("Play music: " + backgroundMusicSelection);
 	      }
        }

public class MenuQuit implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {

		System.exit(0);
	}

}

public class MenuAbout implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {

		JOptionPane d = new JOptionPane();
		d.showMessageDialog(null, "Eric \n" + "John \n" + "Karl \n" + "Mohamed \n" + "Said \n" + "Sharon \n",
				"About Us", JOptionPane.PLAIN_MESSAGE);

	}

}

public class MenuVolume implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {

		f = new JFrame("Volume");
		al = new JLabel();
		al.setBounds(200, 200, 0, 0);

		JPanel p = new JPanel();
		a = new JSlider(0, 100, 10);
		// b = new JSlider(0, 2500, 200);

		a.setPaintTrack(true);
		a.setPaintTicks(true);
		a.setPaintLabels(true);
		a.setBounds(500, 300, 50, 30);

		// set spacing
		// a.setMinorTickSpacing(500);
		a.setMajorTickSpacing(10);

		a.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider asource = (JSlider) e.getSource();
				al.setText("value of Slider is =" + asource.getValue());

			}
		});

		// add slider to panel
		p.add(a);
		p.add(al);

		f.add(p);

		// set the text of label

//	         set the size of frame
		f.setSize(400, 200);

		f.show();
	}

}

public class MenuAmount implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {

		f = new JFrame("Amount");
		al = new JLabel();
		al.setBounds(200, 200, 0, 0);

		JPanel p = new JPanel();
		a = new JSlider(0, 2500, 100);

		a.setPaintTrack(true);
		a.setPaintTicks(true);
		a.setPaintLabels(true);
		a.setBounds(500, 300, 30, 30);

		// set spacing
		a.setMajorTickSpacing(2500);
		a.setMinorTickSpacing(0);

		a.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider asource = (JSlider) e.getSource();
				al.setText("Amount to start with =" + asource.getValue());

			}
		});

		// add slider to panel
		p.add(a);
		p.add(al);

		f.add(p);

//	         set the size of frame
		f.setSize(400, 200);

		f.show();
	}
 }
}
