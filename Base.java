///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GalacticCommander
// File:             Base.java
// Semester:         CS302 Fall 2012
//
// Author:           Cong Sun  csun27@wisc.edu
// CS Login:         cong
// Lecturer's Name:  Ben Miller
// Lab Section:      361
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     no pair partner
// CS Login:         no pair partner
// Lecturer's Name:  no pair partner
// Lab Section:      no pair partner
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          None
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This class represents a base.  Each player has one Base; the player to whom
 * the base belongs is referred to as the owning player. Each player's base,
 * ship, and flag start on the same side of the screen.
 */
public class Base extends GameObject {
	// Declare additional class variables here if necessary. Remember that all
	// class variables you add must be declared private!
	private int playerID;//reference of the player ID

	/**
	 * Creates a new Base. The image is set to {@code Config.BASE_IMG[playerID]}
	 * and it is visible and collidable.
	 * 
	 * @param game the {@link Game} to which this Base belongs.
	 * @param playerID the owning player (one of Config.PLAYER1 or Config.PLAYER2).
	 * @param x the initial x position
	 * @param y the initial y position
	 */
	public Base(Game game, int playerID, double x, double y) {
		super(game, new Position(x, y), Config.BASE_RADIUS);
		// TODO
		this.playerID = playerID;// save the reference of player ID
		//if it is player 1
		if (playerID == Config.PLAYER1_ID){
		//set the image of player 1
			setImage(Config.BASE_IMG[getPlayerID()]);
		}
		//if it is player 2
		if (playerID == Config.PLAYER2_ID){
			//set the image of player 2
			setImage(Config.BASE_IMG[getPlayerID()]);
		}
		//set the base visible
		setVisible(true);
		//set the base collidable
		setCollidable(true);
	}

	/**
	 * Returns the ID of the owning player of this base. One of
	 * {@code Config.PLAYER1_ID} or {@code Config.PLAYER2_ID}.
	 * 
	 * @return the owning player.
	 */
	public int getPlayerID() {
		// TODO		
		return playerID;
	}

	/**
	 * Returns true if the specified player can score a flag at this base.
	 * (i.e., the player is the owning player).
	 *  
	 * @param playerID one of {@link Config#PLAYER1_ID} or {@link
	 * Config#PLAYER2_ID}.
	 * @return {@code true} if the player can score a flag.
	 */
	public boolean canScoreFlag(int playerID) {
		// TODO
		//if the returning player is the owning player
		if (this.playerID == playerID){
			return true;
		}
		return false;
	}

	/**
	 * Called automatically at each game tick to handle game logic. However,
	 * bases have no associated logic that must be run at every tick.
	 */
	@Override
	public void update(double delta) {
		// This method does nothing.  DO NOT ADD CODE HERE!
	}
}
