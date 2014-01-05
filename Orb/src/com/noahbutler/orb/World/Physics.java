package com.noahbutler.orb.World;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.noahbutler.orb.World.Orbs.Orbs;
import com.noahbutler.orb.World.Ship.Bullet;

public class Physics {
	
	public World world;
	private Box2DDebugRenderer debug;
	private OrthographicCamera camera;
	private static final float SCALING_FACTOR = .05f;
	
	private Array<Fixture> orbFixtures;
	private Array<Fixture> bulletFixtures;
	private Array<Orbs> orbObjectList;
	private Array<Bullet> bulletObjectList;
	
	public Array<Body> bulletBodies;
	public Array<Body> orbBodies;
	
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
        
        orbFixtures    = new Array<Fixture>(); //dont really need these, instead use fixtureA.getBody()
        bulletFixtures = new Array<Fixture>(); // and compare the bodies instead of the fixtures
        bulletBodies   = new Array<Body>();
        orbBodies      = new Array<Body>();
        
        bulletBodyDeletable = new Array<Body>();
        orbBodyDeletable    = new Array<Body>();
        
        createCollisionListener();
        addTestGround(new Vector2(0, -32), 18, 1);
        addTestGround(new Vector2(0, 32), 18, 1);
        addTestGround(new Vector2(-18 , 0), 1, 32);
        addTestGround(new Vector2(18, 0), 1, 32);
        addShip();
    }
	
	public void step() {
		float xAccel = -Gdx.input.getAccelerometerX(); //get the x position of the accelerometer
		shipBody.setLinearVelocity(xAccel * 10.0f, 0); //set a velocity for the ship body a according to the position of the accelerometer
		
		//step
		world.step(Gdx.graphics.getDeltaTime(), 8, 3);
		
		//so I can see the bodies
		debug.render(world, camera.combined);
	}
	
	public void addOrb(Vector2 position) {

		//make the shape of the body of fixture could be made in constructor, same for every orb
		PolygonShape orbShape = new PolygonShape();
		orbShape.setAsBox(1, 1);
		
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
		
		//add fixture for collision detection
		for(Fixture fixture: orbBody.getFixtureList()) {
			orbFixtures.add(fixture);
		}
		//add body to list for position updating
		orbBodies.add(orbBody);
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
		
		//add fixtures for collision detection
		for(Fixture fixture: bulletBody.getFixtureList()) {
			bulletFixtures.add(fixture);
		}
		
		//add speed
		bulletBody.setLinearVelocity(0.0f, 30.0f);
		
		//add body to list for position updating
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
	
	private void addTestGround(Vector2 position, float xSize, float ySize) {
		PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(xSize, ySize);
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyType.StaticBody;
        groundBodyDef.position.set(position);
        Body groundBody = world.createBody(groundBodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        groundBody.createFixture(fixtureDef);
        
        for(Fixture fixture: groundBody.getFixtureList()) {
        	bulletFixtures.add(fixture);
        }
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
				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				
				Gdx.app.log("Contact", fixtureA.toString() + " has stopped making contact with " + fixtureB.toString());
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				
				boolean bulletIsFixtureA = false;

				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				
				for(int i = 0; i < bulletFixtures.size; i++) {
					if(fixtureA.equals(bulletFixtures.get(i))) {
						//bullet fixture is fixtureA
						bulletIsFixtureA = true;
					}else if(fixtureB.equals(bulletFixtures.get(i))) {
						//bullet fixture is fixtureB
						bulletIsFixtureA = false;
					}
				}
				
				if(bulletIsFixtureA) {
					for(int i = 0; i < orbFixtures.size; i++) {
						if(fixtureB.equals(orbFixtures.get(i))) {
							//bullet is fixture a and orb is fixture b
							Gdx.app.log("Contact", "bullet is A and orb is B");
							
							//delete both bodies
							bulletBodyDeletable.add(fixtureA.getBody());
							
							orbBodyDeletable.add(fixtureB.getBody());
							orbBodies.removeValue(fixtureB.getBody(), true);
							
							//switch sprite to death animation
							gameWorld.orbs.get(i).canBeDeleted(true);
						}
					}
				}else{
					for(int i = 0; i < orbFixtures.size; i++) {
						if(fixtureA.equals(orbFixtures.get(i))) {
							//bullet is fixture b and orb is fixture a
						}
					}
				}
				
				Gdx.app.log("Contact", "fixtureA has made contact with fixtureB");
			}
		});
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
