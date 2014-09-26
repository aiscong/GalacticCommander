///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GalacticCommander
// File:             Ship.java
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
 * This class represents a ship. A ship can be in one of three states:
 * <ul>
 * <li><b>CAPTURING</b>: The ship is able to fly about the playing area. It
 * can pick up the opponent's flag by colliding with it. The ship is visible
 * and collidable and can fire bullets.</li>
 * <li><b>SCORING</b>: The ship is carrying a flag. The player can score by
 * flying over the player's base. The ship is visible and collidable. It cannot
 * fire a bullet until it has dropped the flag (thereby entering the CAPTURING
 * state).</li>
 * <li><b>DESTROYED</b>: The ship has been destroyed. Its position is constant
 * and its rotation is zero. It is visible but not collidable.</li>
 * </ul>
 * A ship has two timers: the firing timer and the respawn timer. The firing
 * timer is set each time the ship fires; the ship cannot fire again until the
 * timer has expired. For the purposes of resetting the firing timer, dropping
 * the flag by attempting to fire counts as firing, even though no bullet is
 * actually fired. The respawn timer is set when the ship is destroyed. When it
 * expires, the ship is respawned with its starting position and rotation and in
 * the Capturing state.
 */
public class Ship extends GameObject {
	/**
	 * Indicates a ship that has not picked up a flag.
	 */
	public static final int CAPTURING  = 1;

	/**
	 * Indicates a ship that has picked up a flag.
	 */
	public static final int SCORING    = 2;

	/**
	 * Indicates a ship that has been destroyed.
	 */
	public static final int DESTROYED  = 3;

	// Declare additional class variables here if necessary. Remember that all
	// class variables you add must be declared private!

	private int playerID;//save reference for the ID of the player
	private int state;//save reference for the state of the ship

	//create a new timer
	private GameTimer timer = new GameTimer();

	private double xPos;//save reference for x position of the ship
	private double yPos;//save referecne for y position of the ship
	private double rotation;//save reference for the rotation of the ship
	private Flag flag;//save reference for the flag
	private Game game;//save reference for the game 
	/**
	 * Creates a new ship. The ship's position and rotation are set to the
	 * provided coordinates and rotation. The ship's speed is set to zero. The
	 * ship's state is {@code Ship.CAPTURING}, the image is
	 * {@code Config.SHIP_CAPTURING_IMG[playerID]}, and it is made visible and
	 * collidable. The firing timer and respawn timer are created (but not
	 * started).
	 * 
	 * @param game the parent game
	 * @param playerID the ID of the ship's owning player
	 * @param x the ship's initial x position.
	 * @param y the ship's initial y position.
	 * @param rotation the ship's initial rotation.
	 */
	public Ship(Game game, int playerID, double x, double y, double rotation) {
		super(game, new Position(x, y), Config.SHIP_RADIUS);
		// TODO
		this.game = game;//save initial game
		xPos = x;//save initial x position
		yPos = y;//save initial y position
		this.rotation = rotation;//save initial rotation
		this.playerID = playerID;//save initial player ID
		//save initial state of the ship and initiate it to capturing state
		state = CAPTURING;
		//set the image for each ship
		setImage(Config.SHIP_CAPTURING_IMG[getPlayerID()]);

		//make the ship visible using
		setVisible(true);
		//make the ship collidable 
		setCollidable(true);
		// Set the rotation of the ship using setRotation().
		setRotation(this.rotation);
	}

	/**
	 * Rotates the ship by the specified angle. That is, this method adds its
	 * argument to the ship's current rotation. This method does not rotate the
	 * ship if the ship is destroyed.
	 *
	 * @param angle the angle of rotation in radians
	 */
	public void rotate(double angle) {
		// TODO
		// To rotate the ship, you must add the angle parameter to the
		// ship's current rotation.  You can get the ship's current rotation
		// by calling getRotation().
		if(getState() != DESTROYED){
			setRotation(getRotation() + angle);

		}
	}

	/**
	 * Adds the specified change in velocity to the ship's speed. This method
	 * guarantees that the ship's speed is within the range 
	 * {@code -Config.SHIP_MAX_SPEED} to {@code Config.SHIP_MAX_SPEED}.
	 *
	 * @param speedDelta the change in speed in pixels/sec
	 */
	public void accelerate(double speedDelta) {
		// TODO

		// Add speedDelta to the ship's current speed.
		setSpeed(getSpeed() + speedDelta);
		
		// If the new speed is outside the allowed range, set the ship's speed
		// to the nearest allowed value.

		// Set the ship's speed by calling setSpeed().


		if (getSpeed() > Config.SHIP_MAX_SPEED){
			setSpeed (Config.SHIP_MAX_SPEED);
		}
		else if (getSpeed() < -Config.SHIP_MAX_SPEED){
			setSpeed(-Config.SHIP_MAX_SPEED);
		}
	}

	/**
	 * Called automatically at each game tick to handle the ship's game logic.
	 * If the ship is not in the Destroyed state, updates the ship's position
	 * and destroys the ship if it has collided with the boundary of the playing
	 * field. Otherwise, if the ship is destroyed and the respawn timer has
	 * expired, respawns the ship in the Capturing state at its original
	 * position and with its original speed and rotation.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically each
	 * tick.
	 * </p>
	 *
	 * @param deltaTime the time since the previous tick in seconds.
	 */
	@Override
	public void update(double deltaTime) {
		// TODO

		// If the ship is destroyed, check if the respawn timer has expired, and
		// if so respawn the ship.

		if(getState() == DESTROYED && timer.hasTimerExpired()){
			respawn();
		}

		// Otherwise, if the ship is not destroyed, update its position.
		if(getState() != DESTROYED){

			updatePosition(deltaTime);
			// check if the ship is out of bounds
			if(outOfBounds()){
				//if it is carrying a flag and out of bounds
				if (state == SCORING){
					//it drops the flag at the current position
					flag.drop(getPosition());
				}
				//if the ship is out of bounds and it is not carrying a flag
				//just destroy the ship
				destroyShip();

			}
		}
	}


	/**
	 * Returns the ID of the owning player of this ship: one of {@code
	 * Config.PLAYER1} or {@code Config.PLAYER2}.
	 * 
	 * @return the ID owning player
	 */
	public int getPlayerID() {
		// TODO
		//get the player ID
		return playerID;
	}

	/**
	 * Returns the ship's current state: one of {@code Ship.CAPTURING},
	 * {@code Ship.SCORING}, or {@code Ship.DESTROYED}.
	 * 
	 * @return the ship's state.
	 */
	public int getState() {
		// TODO
		// get the state
		return state;
	}

	/**
	 * Handles a collision with a ship. This method is called automatically by
	 * the game at each tick that this ship and another ship collide. This
	 * method destroys the ship.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically when
	 * this ship collides with another ship.
	 * </p>
	 */
	public void collideShip() {
		// TODO
		// if the ship collides with each other
		//if the ship is carrying a flag
		if (state == SCORING){
			//it should drop the flag
			flag.drop(getPosition());
		}
		//it should be destroyed anyway
		destroyShip();

	}

	/**
	 * Handles a collision with an asteroid. This method is called automatically
	 * by the game at each tick that this ship collides with an asteroid. This
	 * method destroys the ship.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically when
	 * this ship collides with an asteroid.
	 * </p>
	 */
	public void collideAsteroid() {
		// TODO
		//if the ship cllides with an Asteroid
		//if the ship is carring a flag
		if (state == SCORING){
			//it should drop the flag
			flag.drop(getPosition());
		}
		//it should be destroyed anyway
		destroyShip();
	}

	/**
	 * If this ship is in the Scoring state, returns the flag this ship is
	 * carrying. If the ship is not in the Scoring state, returns {@code null}.
	 *
	 * @return the {@link Flag} this ship is carrying, or null.
	 */
	public Flag getFlag() {
		// TODO
		//if the ship is in the scoring state
		//it should return the flag it is carring
		if(state == SCORING){
			return flag;
		}
		//if the flag is not carried, then return nothing
		return null;
	}

	/**
	 * Handles a collision with a flag. This method is called automatically by
	 * the game at each tick that this ship collides with a flag. This method
	 * checks whether the flag can be picked up by this ship. If so, it picks it
	 * up, saving a reference to the flag and changing the ship's state to
	 * SCORING.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically when
	 * this ship collides with a flag.
	 * </p>
	 * 
	 * @param flag the {@link Flag} this ship collided with.
	 */
	public void collideFlag(Flag flag) {
		// TODO

		// If this ship collided with a flag that can be picked up, as
		// determined by the flag's canBePicked() method, save a reference in
		// this ship object, notify the flag that it has been picked up, and
		// put this ship in the Scoring state.
		this.flag = flag; //save a reference to flag
		//if the flag can be picked
		if(flag.canBePicked(getPlayerID())){
			this.flag.pickUp();// this flag is picked up
			state = SCORING;//change the state of ship to Scoring
			//set the image of ship to shinining one
			setImage(Config.SHIP_SCORING_IMG[getPlayerID()]);
		}
	}

	/**
	 * Handles a collision with a base. This method is called automatically by
	 * the game at each tick that this ship collides with a base. If the ship
	 * is in the Scoring state and the flag can be scored at this base, this
	 * method scores the flag and the ship returns to the Capturing state.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically when
	 * this ship collides with a base.
	 * </p>
	 * 
	 * @param base the {@link Base} this ship collided with.
	 */

	public void collideBase(Base base) {
		// TODO

		// If the ship is in the Scoring state and the ship can score a flag at
		// this base, call the game's score() method, the flag's reset() method,
		// and put the ship in the Capturing state.
		if(state == SCORING && base.canScoreFlag(getPlayerID()) == true){
			// set the image of scoring ship 
			//back to the image of ship of capturing state 
			setImage(Config.SHIP_CAPTURING_IMG[getPlayerID()]);
			// the player get the score
			game.score(getPlayerID());
			flag.reset();//the flag shou be reset
			state = CAPTURING;//change state of ship back to capturing
		}
	}
	/**
	 * Fires a bullet if this ship is able to fire. If the ship is destroyed or
	 * the firing timer has not expired yet, this method does nothing. If the
	 * ship is in the Scoring state, drops the flag, resets the firing timer,
	 * and enters the capturing state. Otherwise, creates a new bullet object
	 * and adds it to the game.
	 */
	public void fire() {
		// TODO

		// If the ship is destroyed or the timer hasn't expired, it can't fire
		// and this method does nothing.

		// Otherwise, set the firing timer.
		if(state != DESTROYED && timer.hasTimerExpired()){
			timer.startTimer(Config.SHIP_FIRE_DELAY);
			// If the ship is in the Capturing state, first calculate the position
			// of the new bullet. Get a copy of the ship's position, then move that
			// position in the direction of the ship's rotation using the
			// moveInDirection() method provided by Position objects. The distance
			// moved should be equal to the ship's radius plus the bullet's radius.
			// Finally, create the new bullet and add it to the game.
			if(getState() == CAPTURING){
				//Get a copy of the ship's position
				Position bulletPosi = getPosition();
				//then move that
				// position in the direction of the ship's rotation using the
				// moveInDirection() method provided by Position objects.
				bulletPosi.moveInDirection(getRotation(),
						Config.SHIP_RADIUS + Config.BULLET_RADIUS);
				//Finally, create the new bullet and add it to the game.
				Bullet bullet = new Bullet(this.game, bulletPosi,
						getRotation());
				// and add it to the game.
				this.game.addGameObject(bullet);
			}

			// If the ship is in the Scoring state, drop the flag and enter the
			// Capturing state without firing a bullet. 
			else if(state == SCORING){
				//drop the flag
				flag.drop(getPosition());
				//start the game delay
				timer.startTimer(Config.SHIP_FIRE_DELAY);
				//set the image back to capuring state
				setImage(Config.SHIP_CAPTURING_IMG[getPlayerID()]);
				// set the state of the ship back to capturing
				state = CAPTURING;
			}
		}
	}

	/**
	 * Handles a collision with a bullet. This method is called automatically by
	 * the game at each tick that this ship collides with a bullet. This method
	 * destroys the ship.
	 * <p>
	 * Do NOT call this method from your code! It is called automatically when
	 * this ship collides with a bullet.
	 * </p>
	 */
	public void collideBullet() {
		// TODO
		//if the ship is carrying a flag
		if (state == SCORING){
			//drop the flag 
			flag.drop(getPosition());
		}
		//ship will be destroyed any way
		destroyShip();
	}
	/**
	 * Destroy the ship
	 */
	private void destroyShip(){
		//set the ship not collidable
		setCollidable(false);
		//set the image to debris
		setImage(Config.SHIP_DESTROYED_IMG);
		//set the state to detroyed
		state = DESTROYED;
		//start the respawning timer
		timer.startTimer(Config.SHIP_RESPAWN_TIME);
	}
	/**
	 * respawn ship
	 */
	private void respawn(){
		//set the state to capturing
		state = CAPTURING;
		//set the image to the image capturing ship
		setImage(Config.SHIP_CAPTURING_IMG[getPlayerID()]);
		//set the position to original position
		setPosition(xPos,yPos);
		//set the speed equal to 0
		setSpeed(0);
		//if it is player 1 repawning
		if(getPlayerID() == Config.PLAYER1_ID){
			//set the rotation to the original rotation of ship 1
			setRotation(Config.SHIP_PLAYER1_ROTATION);
		}
		//if it is player 2 repawning
		else if (getPlayerID() == Config.PLAYER2_ID){
			//set the rotation to original rotation of ship 2
			setRotation(Config.SHIP_PLAYER2_ROTATION);
		}
		//set the ship visable
		setVisible(true);
		//set the ship collidable
		setCollidable(true);
	}
	/**
	 * Check to see if the ship is out of bounds
	 * 
	 * @return true if the ship is out of bounds
	 */
	private boolean outOfBounds(){
		//either x position or y position out of bounds
		if(getPosition().getX()<0 || getPosition().getY() < 0
				|| getPosition().getX() > Config.GAME_WIDTH
				|| getPosition().getY() > Config.GAME_HEIGHT){
			// then return true
			return true;
		}
		//otherwise, return false
		return false;
	}
	/**
	 * get the state of the ship
	 * 
	 * @return the current state of ship
	 */
}
