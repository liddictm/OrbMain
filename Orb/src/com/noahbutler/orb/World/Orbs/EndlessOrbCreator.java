package com.noahbutler.orb.World.Orbs;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.noahbutler.orb.World.World;

/**
 * 
 * @author Noah Butler
 * 
 *         This class is used in the end mode of the game. It is in charge of
 *         creating the right orbs at the right time.
 * 
 *         For rendering, when an orb is created it is added to the orbs Array
 *         in the world object and is then passed to the Orb Renderer where it
 *         gets assigned a sprite and then the render method of the renderer
 *         gets called in the world object render method
 * 
 */

public class EndlessOrbCreator {

	private static final long NANO = 1000000000;

	private long lastSpawnTime;
	private long currentSpawnTime;

	private long currentLevelTime;
	private long lastLevelTime;

	private double levelLength;
	private double spawnInterval;

	private World world;

	/**
	 * 
	 * @param world
	 * 
	 *            the world is used to get the method to add orbs to the physics
	 *            object
	 */

	public EndlessOrbCreator(World world) {
		this.world = world;
		// used for spawning orbs at the right time
		// in nanoseconds
		currentSpawnTime = 0;
		lastSpawnTime = TimeUtils.nanoTime();

		// used for decreasing the spawn interval time
		// in nanoseconds
		currentLevelTime = 0;
		lastLevelTime = TimeUtils.nanoTime();

		levelLength = (12 * NANO);
		setSpawnInterval(3.0);
	}

	// used in the world render
	public void create() {

		// orb forging
		if ((currentSpawnTime - lastSpawnTime) >= spawnInterval) {
			// make orb
			addOrbToPhysics();
			// reset last spawn time
			lastSpawnTime = currentSpawnTime;
		} else {
			currentSpawnTime = TimeUtils.nanoTime();
		}

		// next level shit
		if ((currentLevelTime - lastLevelTime) >= levelLength) {
			// next level
			setSpawnInterval(increment());
			lastLevelTime = currentLevelTime;
		} else {
			currentLevelTime = TimeUtils.nanoTime();
		}
	}

	private void setSpawnInterval(double seconds) {
		spawnInterval = seconds * NANO;
	}

	private double increment() {
		// spawn time reduced by 10% every level
		return (spawnInterval / NANO) * .90;
	}

	private void addOrbToPhysics() {
		// random x coord, and static y coord
		world.addOrbToPhysicsWorld(new Vector2(MathUtils.random(20) + 5, 4));
	}
}
