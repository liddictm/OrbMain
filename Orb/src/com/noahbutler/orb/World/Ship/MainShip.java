package com.noahbutler.orb.World.Ship;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class MainShip {
	int shipIndex;
	TextureAtlas shipTexture;
	Sprite image;
	int x;
	int y;
	
	int dx;
	
	Gun gun;
	
	public MainShip() {
		shipTexture = new TextureAtlas();
		image = shipTexture.createSprite("ship" + shipIndex);
		gun = new Gun();
		y = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight()/4);
		x = Gdx.graphics.getWidth()/2 - 64;
		image.setPosition(x, y);
	}
	
	public void render(SpriteBatch b) {
		this.x += dx;
		image.draw(b);
		gun.x = this.y;
		gun.y = this.x;
		gun.render(b);
	}
	
	public void update() {

	}
	
	public void moveLeft(int i) {
		dx = i;
	}
	
	public void moveRight(int i) {
		dx = i;
	}

	public Array<Bullet> getBulletObjectList() {
		// TODO Auto-generated method stub
		return gun.getBulletObjectList();
	}

}
