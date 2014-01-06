package com.noahbutler.orb.World;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.noahbutler.orb.World.Input.Input;
import com.noahbutler.orb.World.Orbs.EndlessOrbCreator;
import com.noahbutler.orb.World.Orbs.OrbCreator;
import com.noahbutler.orb.World.Orbs.OrbRenderer;
import com.noahbutler.orb.World.Orbs.Orbs;
import com.noahbutler.orb.World.Ship.Bullet;
import com.noahbutler.orb.World.Ship.MainShip;

/**
 * 
 * @author Noah Butler
 * 
 * Main class that brings all the
 * elements of the game together
 *
 */

public class World {
	
	private static final float SCALING_FACTOR = .05f;
	
	SpriteBatch mainBatch;
	OrthographicCamera camera;
	Physics physics;
	
	
	MainShip ship;
	private OrbCreator orbCreator;
	private EndlessOrbCreator endlessOrbCreator;
	private OrbRenderer orbRenderer;
	public Array<Orbs> orbs;
	
	/**
	 * 
	 * @param endless
	 * 
	 */
	public World(boolean endless) {
		camera     = new OrthographicCamera();
		mainBatch  = new SpriteBatch();
		physics    = new Physics(this.camera, this);
		
		Gdx.input.setInputProcessor(new Input(this));
		
//		ship = new MainShip();
		
		orbRenderer = new OrbRenderer();
		orbs        = new Array<Orbs>();
		
		if(endless) {
			endlessOrbCreator = new EndlessOrbCreator(this);
		}else{
//			orbCreator = new OrbCreator(15, this); //not currently working. Because of the use of Random
		}
	}
	
	public void render(float delta) {
		endlessOrbCreator.create();
		physics.step(delta);
		mainBatch.begin();
//		orbRenderer.render(mainBatch, orbs);
//		orbCreator.render(mainBatch);
		mainBatch.end();
		camera.update();
	}
	
	public void update() {
		
	}
	
	public void resize(int width, int height) {
		physics.resize(width, height);
	}
	
	public void dispose() {
		
	}
	
	//gets used in physics class
	public Array<Bullet> getBulletObjectList() {
		return ship.getBulletObjectList();
	}
	
	//gets used in Gun class
	public Array<Body> getBulletBodyList() {
		return physics.bulletBodies;
	}
	
	//gets used in OrbCreator
	public void addOrbToPhysicsWorld(Vector2 position) {
		physics.addOrb(position);
	}
	
	//get Used in Input
	public void addBulletToPhysicsBullet(Vector2 position) {
		physics.addBullet(position);
	}
	
	//gets Used as the bullet's position in Input
	public Vector2 getShipPosition() {
		return physics.getShipPosition();
	}
	
	public void addOrbForRendering(int type) {
		orbs.add(new Orbs(type));
	}

}
