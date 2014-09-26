///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GalacticCommander
// File:             Bullet.java
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
 * This class represents a bullet. Each time a ship fires, a new bullet is
 * created and added to the game. Bullets travel at a constant speed and in a
 * constant direction. The direction is determined by the rotation of the ship
 * that fired the bullet at the moment the bullet is fired. The bullet continues
 * traveling forward at constant speed until it collides with a ship or an
 * asteroid, or it leaves the playing field completely. When one of these events
 * occurs, the bullet must call the game's {@link
 * Game#removeGameObject(GameObject) removeGameObject()} method, providing
 * itself as the argument.
 */
public class Bullet extends GameObject {
    // Declare additional class variables here if necessary. Remember that all
	// class variables you add must be declared private!
	private double rotation;//save reference of the rotation
	private Game game;//save reference of the game
	/**
	 * Creates a new bullet at the given position traveling in the given
	 * direction. The bullet's speed is set to {@code Config.BULLET_SPEED}, its
	 * image is set to {@code Config.BULLET_IMG}, and it is made visible and
	 * collidable.
	 * 
	 * @param game the parent {@link Game}
	 * @param position the initial position
	 * @param rotation the direction of travel
	 */
    public Bullet(Game game, Position position, double rotation) {
        super(game, position, Config.BULLET_RADIUS);
    	// TODO
        this.game = game;//save the game
        this.rotation = rotation;//save the rotation
        setRotation(this.rotation);//set rotation to initial rotation
        setSpeed(Config.BULLET_SPEED);//set the speed of bullet
        setImage(Config.BULLET_IMG);//set the image of bullet
    	setVisible(true);//set the bullet visable
    	setCollidable(true);//set the bullet collidable
    }

    /**
     * Called automatically at each game tick to handle game logic. At each game
     * tick, the bullet moves itself by calling the super class method
     * updatePosition(). If it has moved completely off the playing field, it is
     * removed from the game.
     * 
     * @param deltaTime the time in seconds since the last tick
     */
    @Override
    public void update(double deltaTime) {
    	// TODO
    	
    	// Move the bullet by calling updatePosition()
    	updatePosition(deltaTime);
        // If the bullet has left the playing field altogether, it must be
        // removed from the game. This is different from ships! A ship is
        // destroyed if it collides with the boundary of the playing field. A
        // bullet is removed from the game if it leaves the playing field.
        
        // The bullet leaves the playing field if its position goes beyond 
        // any edge of the playing field (i.e., if the X coordinate is less than
        // 0 or more than the width of the game and similarly for the Y
		// coordinate).
    	if(outOfBounds() == true){
    		//remove the bullet
    		game.removeGameObject(this);
    	}
    }

    /**
     * Called automatically by the game when this bullet collides with a ship.
     * The bullet removes itself from the game.
     */
    public void collideShip() {
    	// TODO
    	// remove the bullet
    	game.removeGameObject(this);
    }

    /**
     * Called automatically by the game when this bullet collides with an
     * asteroid. The bullet removes itself from the game.
     */
	public void collideAsteroid() {
		// TODO
		//remove the bullet
		game.removeGameObject(this);
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
}
