package com.noahbutler.orb.World.Orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.noahbutler.orb.World.Entity;

public class Orbs extends Entity{
	
	private int type;
	private boolean canBeDeleted;
	
	//test texture
	private Texture texture;
	
	private float x;
	private float y;
	private Sprite sprite;
	public Orbs(int type) {
		this.type = type;
		texture = new Texture(Gdx.files.internal("data/orbs/white.png"));
		sprite = new Sprite(texture);
	}
	
	public void render(SpriteBatch b) {
		
		sprite.setBounds(x, y, 64, 64);
		sprite.draw(b);
	}

	public int getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	public void updatePosition(Vector2 position) {
		this.x = position.x;
		this.y = position.y;
	}
	
	public void startContact(String isBullet) {
		try{
			int isBulletNum = Integer.parseInt(isBullet);
			//is a bullet
			Gdx.app.log("kill", "kill has been made");
		}catch(NumberFormatException nfe) {
			//not a bullet, ground or ship
		}
	}
	
	public String getCheckDeletable() {
		return "yes";
	}

}
