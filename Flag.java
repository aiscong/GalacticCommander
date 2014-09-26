///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GalacticCommander
// File:             Flag.java
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
 * This class represents a flag.  A flag can be in one of four states.
 * <ul>
 * <li><b>PICKED</b>: The flag is being carried by the opposing player's ship.
 * The opposing player will score a point if this flag can be brought to the
 * opposing player's base. The flag is not visible and not collidable.</li>
 * <li><b>DROPPING</b>: The flag is being dropped by the opposing player's ship.
 * It cannot be picked up again until the dropping timer has expired. The flag
 * is visible and collidable.</li>
 * <li><b>DROPPED</b>: The flag has been dropped on the playing field. It can be
 * picked up by the opposing player's ship. The flag is visible and collidable.
 * </li>
 * </ul>
 * The flag has a timer which determines when the flag can be picked up off the
 * field (the dropping timer). The timer is started when the flag is initially
 * dropped by a ship. However, the dropping timer is restarted every time the
 * opponent's ship collides with the flag and the flag is still in the DROPPING
 * state. This allows a ship to drop the flag while moving slowly and not
 * immediately pick it up again. In order to pick up a flag, the ship must move
 * off the flag so that they are no longer colliding. This allows the dropping
 * timer to expire. The {@code update()} method, which is called automatically
 * at every game tick, sees that the dropping timer has expired and sets the
 * flag's state to DROPPED. If the ship then moves back and collides with the
 * flag again, it can be picked up because it is in the DROPPED state.
 */
public class Flag extends GameObject {
	/**
	 * Indicates a flag that has been picked up by the opposing player's ship. 
	 */
	public static final int PICKED     = 1;

	/**
	 * Indicates a flag that is being dropped (the dropping timer has not
	 * expired).
	 */
	public static final int DROPPING   = 2;

	/**
	 * Indicates a flag that has been dropped on the playing field (the
	 * dropping timer has expired).
	 */
	public static final int DROPPED    = 3;

	// Declare additional class variables here if necessary. Remember that all
	// class variables you add must be declared private!
	private int state;//the reference of the state
	private int playerID;//the reference of player ID
	//create a new timer
	private GameTimer timer = new GameTimer();
	private double xPos;//reference of x position of the flag
	private double yPos;//reference of y position of the flag
	/**
	 * Creates a new flag. The flag starts in the Dropped state. The image is
	 * set to {@code Config.FLAG_IMG[playerID]}. It is visible and collidable.
	 * The dropping timer is created but not started.
	 * 
	 * @param game the parent {@link Game}.
	 * @param playerID the owning player, one of {@code Config.PLAYER1_ID} or
	 * {@code Config.PLAYER2_ID}.
	 */
	public Flag(Game game, int playerID, double x, double y) {
		super(game, new Position(x, y), Config.FLAG_RADIUS);
		// TODO
		this.xPos = x;//save the initial x position
		this.yPos = y;//save the initial y position
		this.playerID = playerID;//save the owning player ID of the flag
		state = DROPPED;//save the initial state of the flag
		//set the image of the flag of the player
		setImage(Config.FLAG_IMG[getPlayerID()]);
		//set the flag visable
		setVisible(true);
		//set the flag collidable
		setCollidable(true);
	}

	/**
	 * Returns the ID of the owning player of this flag.
	 * 
	 * @return the owning player's ID.
	 */
	public int getPlayerID() {
		// TODO
		return playerID;
	}

	/**
	 * Returns the flag's current state: one of {@code PICKED},
	 * {@code DROPPING}, or {@code DROPPED}.
	 * 
	 * @return the flag's state.
	 */
	public int getState() {
		// TODO
		return state;
	}

	/**
	 * Returns {@code true} if this flag can be picked up by the specified
	 * player. That is, it returns true if the specified player is not the
	 * flag's owning player and the flag's state is DROPPED.
	 * 
	 * @param playerID one of {@code Config.PLAYER1} or {@code Config.PLAYER2}
	 * @return {@code true} if the flag can be picked up by the player.
	 */
	public boolean canBePicked(int playerID) {
		// TODO
		//it returns true if the specified player is not the
		// flag's owning player and the flag's state is DROPPED.
		if(playerID != getPlayerID() && getState() == DROPPED){
			return true;
		}
		return false;
	}

	/**
	 * Puts the flag in the PICKED state. If the flag is not in the DROPPED
	 * state, does nothing. Otherwise, makes the flag invisible and not
	 * collidable.
	 */
	public void pickUp() {
		// TODO
		// if the state is dropped
		if(getState() == DROPPED){
			//set the state to picked
			state = PICKED;
			//the flag is picked and should not be visible
			setVisible(false);
			//the flag is picked and should not be collidable
			setCollidable(false);
		}
	}

	/**
	 * Called automatically at each game tick to handle game logic. If this flag
	 * is in the DROPPING state and the dropping timer has expired, it enters
	 * the DROPPED state.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically each
	 * tick.
	 * </p>
	 * 
	 * @param deltaTime the time in seconds between this and the previous tick
	 */
	@Override
	public void update(double deltaTime) {
		// TODO
		//update the flag's position
		updatePosition(deltaTime);
		// If the flag is in the DROPPING state and the dropping timer has
		// expired, put the flag in the DROPPED state.
		if(state == DROPPING && timer.hasTimerExpired()){
			state = DROPPED;
		}

	}

	/**
	 * Handles a collision with a ship. This method is called automatically by
	 * the game at each tick that this flag and a ship collide. If the flag is
	 * in the DROPPING state and the other ship's owning player is not the same
	 * as the flag's owning player, this method restarts the dropping timer.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically when
	 * this ship collides with a ship.
	 * </p>
	 * 
	 * @param ship the {@link Ship} the flag collided with
	 */
	public void collideShip(Ship ship) {
		// TODO
		//If the flag is
		// in the DROPPING state and the other ship's owning player is not the same
		//as the flag's owning player
		if(state == DROPPING && ship.getPlayerID() != playerID){
			//restarts the dropping timer
			timer.startTimer(Config.FLAG_DROP_TIME);
		}

	}

	/**
	 * Drops the flag at the location specified by {@code here}. The flag enters
	 * the DROPPING state and the dropping timer is started. The flag becomes
	 * collidable and visible.
	 * 
	 * @param here a {@link Position} giving the flag's dropped position 
	 */
	public void drop(Position here) {
		// TODO
		//set the state to Dropping
		state = DROPPING;
		//set the position here
		setPosition(here);
		//set the flag visible
		setVisible(true);
		//set the flag collidable
		setCollidable(true);
		//start the timer
		timer.startTimer(Config.FLAG_DROP_TIME);
	}

	/**
	 * Sets the flag to its initial configuration. The position is set to
	 * the flag's starting position (i.e., the position it had when it was
	 * first created), it is made visible and collidable, and the state is
	 * set to DROPPED.
	 */
	public void reset() {
		// TODO
		//set the position back to the initial position
		setPosition(xPos, yPos);
		//set the state to DROPPED
		state = DROPPED;
		//set the flag back visible
		setVisible(true);
		//set the flag back collidable
		setCollidable(true);
	}
}
