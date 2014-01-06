package com.noahbutler.orb.World;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Physics {
	
	private static final float BOX_STEP = 1/60f;
	private static final int  BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;
	private float accumulator;

	public World world;
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private static final float SCALING_FACTOR = .05f;
	
	public Array<Body> bulletBodies;
	public Array<Body> orbBodies;
	public Array<Body> bounds;
	
	private Array<Body> bulletBodyDeletable;
	private Array<Body> orbBodyDeletable;
	private com.noahbutler.orb.World.World gameWorld;
	
	private Body shipBody;
	
	public Physics(OrthographicCamera camera, com.noahbutler.orb.World.World gameWorld) {
		this.gameWorld = gameWorld;
		this.camera = camera;
		createWorld();
	}
	
	private void createWorld() {
        Vector2 gravity = new Vector2(0, 0);
        world           = new World(gravity, true);
        debug           = new Box2DDebugRenderer();
        
        bulletBodies   = new Array<Body>();
        orbBodies      = new Array<Body>();
        bounds         = new Array<Body>();
        
        bulletBodyDeletable = new Array<Body>();
        orbBodyDeletable    = new Array<Body>();
        
        createCollisionListener();
        addBounds(new Vector2(0, -35), 20, 1);
        addBounds(new Vector2(0, 35), 20, 1);
        addBounds(new Vector2(-20 , 0), 1, 35);
        addBounds(new Vector2(20, 0), 1, 35);
        addShip();
    }
	
	public void step(float delta) {
		float xAccel = -Gdx.input.getAccelerometerX(); //get the x position of the accelerometer
		shipBody.setLinearVelocity(xAccel * 10.0f, 0); //set a velocity for the ship body a according to the position of the accelerometer
		//update object
		updateObjects();
		//step and body deletion
		accumulator += delta;
		while(accumulator > BOX_STEP) {
			world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
			accumulator -= BOX_STEP;
		}
		//safe to remove
		removeDeadBodies();
		//so I can see the bodies
		debug.render(world, camera.combined);
	}
	
	public void addOrb(Vector2 position) {

		//make the shape of the body of fixture could be made in constructor, same for every orb
		PolygonShape orbShape = new PolygonShape();
		orbShape.setAsBox(1.5f, 1.5f);
		
		//make a body to add to the world, could use the same object each time
		BodyDef orbBodyDef = new BodyDef();
		orbBodyDef.type = BodyType.DynamicBody;
		orbBodyDef.position.set(position);
		
		//add bodydef to a world body
		Body orbBody = world.createBody(orbBodyDef);
		
		//add fixture to orbFixture and shape to it
		FixtureDef orbFixture = new FixtureDef();
		orbFixture.shape = orbShape;
		
		//add fixture to the world body
		orbBody.createFixture(orbFixture);
		
		//add body to list for position updating and collision detection
		orbBodies.add(orbBody);
		
		//add speed
		orbBody.setLinearVelocity(0, -8.0f);
		
		//dispose uneeded shape
		orbShape.dispose();
	}
	
	public void addBullet(Vector2 position) {
		
		//make the shape of the body of fixture could be made in constructor, same for every bullet
		PolygonShape bulletShape = new PolygonShape();
		bulletShape.setAsBox(.5f, 1f);
		
		//make a body to add to the world
		BodyDef bulletBodyDef = new BodyDef();
		bulletBodyDef.type = BodyType.DynamicBody; //not sure this is the right body type
		bulletBodyDef.position.set(position); //still need to convert correctly
		
		//add bodydef to a world body, this is where it will probably add to the arraylist
		Body bulletBody = world.createBody(bulletBodyDef);
		
		//make a fixture for the body and shape to it
		FixtureDef bulletFixture = new FixtureDef();
		bulletFixture.shape = bulletShape;
		
		//add fixture to the world body 
		bulletBody.createFixture(bulletFixture);
		
		//add speed
		bulletBody.setLinearVelocity(0.0f, 30.0f);
		
		//add body to list for position updating and collision detection
		bulletBodies.add(bulletBody);
	}
	
	public void addShip() {
		//starting position
		Vector2 position = new Vector2(0, -25);
		
		//make the shape of the body of fixture could be made in constructor, same for every bullet
		PolygonShape shipShape = new PolygonShape();
		shipShape.setAsBox(2f, 3f);
		
		//make a body to add to the world
		BodyDef shipBodyDef = new BodyDef();
		shipBodyDef.type = BodyType.DynamicBody; //not sure this is the right body type
		shipBodyDef.position.set(position); //still need to convert correctly
		
		//add bodydef to a world body
		shipBody = world.createBody(shipBodyDef);
		
		//make a fixture for the body and shape to it
		FixtureDef shipFixture = new FixtureDef();
		shipFixture.shape = shipShape;
		
		//add fixture to the world body
		shipBody.createFixture(shipFixture);
		shipShape.dispose();
				
	}
	
	private void addBounds(Vector2 position, float xSize, float ySize) {
		PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(xSize, ySize);
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyType.StaticBody;
        groundBodyDef.position.set(position);
        Body groundBody = world.createBody(groundBodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        groundBody.createFixture(fixtureDef);
        bounds.add(groundBody);
        groundShape.dispose();
	}
	
	public void createCollisionListener() {
		world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				
				boolean bulletIsBodyA = false;

				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureB().getBody();
				
				Gdx.app.log("Contact", "fixtureA has made contact with fixtureB");
			}
		});
	}
	
	private void removeDeadBodies() {
		for(int i = 0; i < bulletBodyDeletable.size; i++) {
			Body body = bulletBodyDeletable.get(i);
			if(!world.isLocked() && body != null) {
				removeBodySafely(body);
				bulletBodyDeletable.removeIndex(i);
			}
		}
	}
	
	private void removeBodySafely(Body body) {
		final ArrayList<JointEdge> list = body.getJointList();
		while(list.size() > 0) {
			world.destroyJoint(list.get(0).joint);
		}
		
		world.destroyBody(body);
	}
	
	private void updateObjects() {
		//bodies are the parents
		//update the objects with the bodies vector2
		
	}
	
	public void resize(int width, int height) {
        float cameraWidth = Gdx.graphics.getWidth() * SCALING_FACTOR;
        float cameraHeight = Gdx.graphics.getHeight() * SCALING_FACTOR;
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        camera.position.set(0, 0, 0);
    }
	
	//Get position for bullet creation
	public Vector2 getShipPosition() {
		return new Vector2 (shipBody.getTransform().getPosition().x, shipBody.getTransform().getPosition().y + 4.0f);
	}
}
