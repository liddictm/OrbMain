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
import com.noahbutler.orb.World.Orbs.OrbCreator;
import com.noahbutler.orb.World.Orbs.Orbs;
import com.noahbutler.orb.World.Ship.Bullet;
import com.noahbutler.orb.World.Ship.MainShip;

public class World {
	
	private static final float SCALING_FACTOR = .05f;
	
	SpriteBatch mainBatch;
	OrthographicCamera camera;
	Physics physics;
	
	OrbCreator orbCreator;
	MainShip ship;
	
	public World() {
		camera     = new OrthographicCamera();
		mainBatch  = new SpriteBatch();
		physics    = new Physics(this.camera, this);
//		orbCreator = new OrbCreator(15, this);
		
		Gdx.input.setInputProcessor(new Input(this));
		
//		ship = new MainShip();
		
	}
	
	public void render() {
		physics.step();
		mainBatch.begin();
		
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
	
	//gets used in physics class
	public Array<Orbs> getOrbsObjectList() {
		return orbCreator.getOrbsObjectList();
	}
	
	//gets used in orb creator class
	public Array<Body> getOrbBodyList() {
		return physics.orbBodies;
	}
	
	public void addOrbToPhysicsWorld(Vector2 position) {
		physics.addOrb(position);
	}
	
	public void addBulletToPhysicsBullet(Vector2 position) {
		physics.addBullet(position);
	}
	
	public Vector2 getShipPosition() {
		return physics.getShipPosition();
	}

}
