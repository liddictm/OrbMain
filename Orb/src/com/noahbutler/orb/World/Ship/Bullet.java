package com.noahbutler.orb.World.Ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.noahbutler.orb.World.Entity;

public class Bullet extends Entity{
	
	private float x;
	private float y;
	
	int dx;
	int dy = 10;
	
	public Bullet(Vector2 vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public void render(SpriteBatch b, Sprite sprite) {
		this.y -= dy;
		sprite.setX(x);
		sprite.setY(y);
		sprite.draw(b);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	
	public void startContact(String isGround) {
		
	}
	
	public String getCheckDeletable() {
		return "yes";
	}
	
	public String getIsBullet(){
		return "1";
	}
	
	public void setPosition(Vector2 vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

}
