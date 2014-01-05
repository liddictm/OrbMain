package com.noahbutler.orb;

import com.badlogic.gdx.assets.AssetManager;

/*
 *@Author: Noah Butler
 *
 *This class is used to load in all the assets
 *it will house the main asset manager, which is
 *a lot faster than loading them all at different times
 *
 *It also lets us have a loading screen in the beginning 
 *of the game.
 */

public class MainAssetLoader {
	
	public AssetManager mainManager;
	
	public MainAssetLoader() {
		
		mainManager = new AssetManager();
	}
	
	//used for the loading bar
	public float getPercent() {
		return mainManager.getProgress();
	}
	
	
	//if this is true we will switch to main menu
	public boolean isDone() {
		return mainManager.update();
	}
	
	
}
