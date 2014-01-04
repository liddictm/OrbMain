package com.noahbutler.orb.World.Orbs;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.noahbutler.orb.World.World;

public class OrbCreator {
	
	private Random gen;
	
	TextureAtlas orbs;
	//level 40
	private boolean green;
	//level 25
	private boolean orange;
	//level 15
	private boolean yellow;
	//level 30
	private boolean red;
	//level 10
	private boolean purple;
	//level 20
	private boolean black;
	//level 3
	private boolean blue;
	//level 1
	private boolean white;
	
	private static final int WHITE_INT  = 1;
	private static final int BLUE_INT   = 2;
	private static final int PURPLE_INT = 3;
	private static final int YELLOW_INT = 4;
	private static final int BLACK_INT  = 5;
	private static final int ORANGE_INT = 6;
	private static final int GREEN_INT  = 7;

	private int level;
	private int numbOfOrbs;
	private int amountCap;
	
	Array<Orbs> orbsList;

	private Sprite whiteOrb;
	private Sprite blueOrb;
	private Sprite purpleOrb;
	private Sprite yellowOrb;
	private Sprite blackOrb;
	private Sprite orangeOrb;
	private Sprite greenOrb;
	
	Texture testTextureWhite;

	private World world;
	
	//Constructor
	public OrbCreator(int level, World world) {
		this.level = level;
		this.world = world;
		numbOfOrbs = 0;
		gen = new Random();
		orbsList = new Array<Orbs>();
		
		green  = false;
		orange = false;
		yellow = false;
		red    = false;
		purple = false;
		black  = false;
		blue   = false;
		white  = false;
		
		if(level >= 1){
			white = true;
		}
		
		if(level >= 3){
			blue = true;
		}
		
		if(level >= 10){
			purple = true;
		}
		
		if(level >= 15) {
			yellow = true;
		}
		
		if(level >= 20) {
			black = true;
		}
		
		if(level >= 25) {
			orange = true;
		}
		
		if(level >= 40) {
			green = true;
		}
		
		amountCap = level * gen.nextInt(3);
		
		
		createSprites();
		makeOrbs();
		
	}
	
	public void createSprites() {
//		orbs = new TextureAtlas();
		
		testTextureWhite = new Texture(Gdx.files.internal("data/orbs/white.png"));
		whiteOrb         = new Sprite(testTextureWhite);
	}
	
	private void makeOrbs() {
		while(numbOfOrbs <= (amountCap + 5)){
			if(level < 3) {
				orbsList.add(new Orbs(WHITE_INT));
				world.addOrbToPhysicsWorld(new Vector2(gen.nextInt(30), 25));
				numbOfOrbs++;
			}
		}

		Gdx.app.log("ORBS", "" + numbOfOrbs);
	}
	
	public void render(SpriteBatch b) {
		//render orbs in the group of orbs
//		for(int i = 0; i < numbOfOrbs; i++) {
//			if(orbsList.get(i).getType() == WHITE_INT){
//				orbsList.get(i).render(b, whiteOrb);
//			}
//			//if orb dies, remove from group
//			if(orbsList.get(i).canBeDeleted()){
//				orbsList.removeIndex(i);
//			}
//		}
	}
	
	public Array<Orbs> getOrbsObjectList() {
		return orbsList;
	}

}
