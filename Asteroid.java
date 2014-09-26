/**
 * This class represents an asteroid. This code is completed for you, but you
 * may find it useful to study this code to understand how subclasses of
 * GameObject should be implemented.
 */
public class Asteroid extends GameObject {
	
	/**
	 * Creates a new Asteroid at the location specified.
	 * 
	 * @param game the parent Game
	 * @param position the location of the asteroid
	 * @param type the type of asteroid
	 */
	public Asteroid(Game game, Position position, int type) {
		// Call the super class constructor to initialize this object.
		// The first argument, game, is a reference to the parent game.
		// The second argument, position, gives the position of this asteroid in
		// the playing field.
		// The third argument, Config.ASTEROID_RADIUS[type], gives the radius of
		// this asteroid. The radius defines the asteroid's collision boundary.
		super(game, position, Config.ASTEROID_RADIUS[type]);
		
		// Set the image for this asteroid. setImage() is a method defined in
		// GameObject, the parent class of Asteroid.
		setImage(Config.ASTEROID_IMG[type]);
		
		// Make the asteroid visible. This will cause the asteroid to be drawn
		// automatically. visible is a field declared in GameObject.
		setVisible(true);
		
		// Make the asteroid collidable. This will cause the game to detect when
		// other objects collide with this asteroid. collidable is a field
		// declared in GameObject.
		setCollidable(true);
	}
	
	/**
	 * Called automatically at each game tick to handle game logic. However,
	 * asteroids have no associated logic that must be run at every tick.
	 */
	@Override
	public void update(double deltaTime) {
		// Does nothing. Do not add code here.
	}
}
