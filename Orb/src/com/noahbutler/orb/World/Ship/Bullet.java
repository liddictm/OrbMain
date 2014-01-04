package com.noahbutler.orb.World.Ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
	
	int x;
	int y;
	
	int dx;
	int dy = 10;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(SpriteBatch b, Sprite sprite) {
		this.y -= dy;
		sprite.setX(x);
		sprite.setY(y);
		sprite.draw(b);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
