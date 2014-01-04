package com.noahbutler.orb.World.Orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Orbs {
	
	private int type;
	private boolean canBeDeleted;
	
	private float x;
	private float y;
	
	public Orbs(int type) {
		this.type = type;
		canBeDeleted = false;
	}
	
	public void render(SpriteBatch b, Sprite orb) {
		
		orb.setBounds(x, y, 64, 64);
		orb.draw(b);
	}

	public int getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	//gets called in collision with bullet
	public void canBeDeleted(boolean canBeDeleted) {
		
	}
	
	public void updatePosition(Vector2 position) {
		this.x = position.x;
		this.y = position.y;
	}

}
