/**
 * Defines constant game configuration values. You should refer to these
 * constants in your program. Do not put these magic numbers directly into your
 * program's code. Instead, access them like this: {@code Config.PLAYER1_ID},
 * {@code Config.SHIP_RADIUS}, etc.
 * 
 * <p><b>DO NOT ADD, REMOVE, OR RENAME VARIABLES IN THIS FILE.</b> You may
 * change values in order to change the gameplay, but your code must work with
 * all legal values for any of these variables.</p>
 */
public class Config {
	/*
	 * GAME CONSTANTS
	 */
	
	/**
	 * Name of the game.
	 */
	public static final String GAME_TITLE = "Galactic Commander";
	
	/**
	 * Width of the playing field in pixels.
	 */
	public static final int GAME_WIDTH = 1024;
	
	/**
	 * Height of the playing field in pixels.
	 */
	public static final int GAME_HEIGHT = 600;
	
	/**
	 * Identifier for Player 1. Can be used to index into ship image array,
	 * among other uses.
	 */
	public static final int PLAYER1_ID	= 0;
	
	/**
	 * Identifier for Player 2. Can be used to index into ship image array,
	 * among other uses.
	 */
	public static final int PLAYER2_ID	= 1;
	
	/**
	 * Background image.
	 */
	public static final String BACKGROUND_IMG = "images/Background.png";
	
	/**
	 * Number of points needed to win.
	 */
	public static final int POINTS_TO_WIN = 3;

	/*
	 * SHIP CONSTANTS
	 */
	
	/**
	 * Collision radius of a ship in pixels.
	 */
	public static final double SHIP_RADIUS = 27.;
	
	/**
	 * Speed at which the ship rotates in rad/s.
	 */
	public static final double SHIP_ROTATE_SPEED = 1.4*Math.PI;
	
	/**
	 * Acceleration (change in velocity) in pixels/s^2.
	 */
	public static final double SHIP_ACCELERATION = 700.;
	
	/**
	 * Maximum absolute speed (positive or negative) in pixels/s.
	 */
	public static final double SHIP_MAX_SPEED = 350.;
	
	/**
	 * Time between ship being destroyed and ship being respawned in ms.
	 */
	public static final long SHIP_RESPAWN_TIME = 2000;
	
	/**
	 * Minimum time between shots in ms.
	 */
	public static final long SHIP_FIRE_DELAY = 600;
	
	/**
	 * Images for ships in Capturing state.
	 */
	public static final String[] SHIP_CAPTURING_IMG = {
		"images/ShipOrange.png",
		"images/ShipGreen.png"
	};
	
	/**
	 * Images for ships in Scoring state.
	 */
	public static final String[] SHIP_SCORING_IMG = {
		"images/ShipOrangeScoring.png",
		"images/ShipGreenScoring.png"
	};

	/**
	 * Image for ships in Destroyed state.
	 */
	public static final String SHIP_DESTROYED_IMG = "images/ShipDestroyed.png";

	/**
	 * Starting X coordinate for Player 1's ship.
	 */
	public static final int SHIP_PLAYER1_X = 180;
	
	/**
	 * Starting Y coordinate for Player 1's ship.
	 */
	public static final int SHIP_PLAYER1_Y = GAME_HEIGHT/2;
	
	/**
	 * Starting X coordinate for Player 2's ship.
	 */
	public static final int SHIP_PLAYER2_X = GAME_WIDTH-180;
	
	/**
	 * Starting Y coordinate for Player 2's ship.
	 */
	public static final int SHIP_PLAYER2_Y = GAME_HEIGHT/2;

	/**
	 * Starting rotation for Player 1's ship.
	 */
	public static final double SHIP_PLAYER1_ROTATION = 0;
	
	/**
	 * Starting rotation for Player 2's ship.
	 */
	public static final double SHIP_PLAYER2_ROTATION = Math.PI;
	
	/*
	 * FLAG CONSTANTS
	 */
	
	/**
	 * Collision radius of a flag in pixels.
	 */
	public static final double FLAG_RADIUS = 25.;
	
	/**
	 * Time between flag being dropped and flag being pickable in ms.
	 */
	public static final long FLAG_DROP_TIME = 500;
	
	/**
	 * Images for flags dropped on the field.
	 */
	public static final String[] FLAG_IMG = {
		"images/FlagOrange.png",
		"images/FlagGreen.png"
	};

	/*
	 * BASE CONSTANTS
	 */
	
	/**
	 * Collision radius of a base in pixels.
	 */
	public static final double BASE_RADIUS = 40.;
	
	/**
	 * Images for bases.
	 */
	public static final String[] BASE_IMG = {
		"images/StationOrangeEmpty.png",
		"images/StationGreenEmpty.png"
	};

	/**
	 * Starting X coordinate for Player 1's base.
	 */
	public static final int BASE_PLAYER1_X = 80;
	
	/**
	 * Starting Y coordinate for Player 1's base.
	 */
	public static final int BASE_PLAYER1_Y = GAME_HEIGHT/2;
	
	/**
	 * Starting X coordinate for Player 2's base.
	 */
	public static final int BASE_PLAYER2_X = GAME_WIDTH-80;
	
	/**
	 * Starting Y coordinate for Player 2's base.
	 */
	public static final int BASE_PLAYER2_Y = GAME_HEIGHT/2;
	
	
	/*
	 * BULLET CONSTANTS
	 */
	
	/**
	 * Collision radius of a bullet in pixels.
	 */
	public static final double BULLET_RADIUS = 10.;
	
	/**
	 * Speed at which bullets travel in pixels/s.
	 */
	public static final double BULLET_SPEED = 700.;
	
	/**
	 * Bullet image.
	 */
	public static final String BULLET_IMG = "images/Bullet.png";

	/*
	 * ASTEROID CONSTANTS
	 */
	
	/**
	 * Asteroid radiuses in pixels.
	 */
	public static final double[] ASTEROID_RADIUS = {28., 50., 25.};
	
	/**
	 * Images for asteroids.
	 */
	public static final String[] ASTEROID_IMG = {
        "images/Asteroid2.png",
		"images/Asteroid4.png",
        "images/Asteroid3.png"
    };
	
	/**
	 * Positions of asteroids.
	 */
	public static final Position[] ASTEROID_POSITIONS = {
		new Position(GAME_WIDTH/2-160, GAME_HEIGHT/2-160),
		new Position(GAME_WIDTH/2, GAME_HEIGHT/2),
		new Position(GAME_WIDTH/2+160, GAME_HEIGHT/2+160)
    };
	
	/*
	 * DEBUG FLAGS
	 */ 
	
	/**
	 * If true, the velocity vector will be drawn on the screen for each object.
	 */
	public static final boolean DRAW_VELOCITY = false;
	
	/**
	 * If true, the bounding circle for each object will be drawn on the screen.
	 */
	public static final boolean DRAW_BOUNDS = false;
}
