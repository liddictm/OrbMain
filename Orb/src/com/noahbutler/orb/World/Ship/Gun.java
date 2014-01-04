package com.noahbutler.orb.World.Ship;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Gun {
	int bulletIndex;
	int barrelIndex;
	int baseIndex;
	
	public int x;
	public int y;
	
	public int barrelX;
	public int barrelY;
	
	Sprite base;
	Sprite barrel;
	Sprite bullet;
	
	private TextureAtlas bulletTexture;
	private TextureAtlas barrelTexture;
	private TextureAtlas baseTexture;
	
	public Array<Bullet> bullets;
	public int numBullets = 0;
	
	public Gun() {
		bulletIndex = 1;
		barrelIndex = 1;
		baseIndex   = 1;
		
		bullets = new Array<Bullet>();
		
		bulletTexture = new TextureAtlas();
		bullet        = new Sprite(bulletTexture.createSprite("bullet" + bulletIndex));
		bullet.setSize(16, 16);
		
		barrel = new Sprite(barrelTexture.createSprite("barrel" + barrelIndex));
		barrel.setSize(16, 32);
		barrel.setPosition(this.x + barrel.getWidth()/2, this.y + 10);
		
		base = new Sprite(baseTexture.createSprite("base" + baseIndex));
		base.setSize(32,32);
		base.setPosition(this.x, this.y);
	}
	
	public void render(SpriteBatch b) {
//		if(bullets != null) {
//			for(int i = 0; i< bullets.size(); i++) {
//				bullets.get(i).render(b, bullet);
//			}
//		}
	}

	public Array<Bullet> getBulletObjectList() {
		// TODO Auto-generated method stub
		return bullets;
	}

}
