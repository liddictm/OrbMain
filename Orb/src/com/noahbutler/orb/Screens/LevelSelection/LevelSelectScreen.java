package com.noahbutler.orb.Screens.LevelSelection;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noahbutler.orb.OrbGame;
import com.noahbutler.orb.Screens.MasterScreen;
import com.noahbutler.orb.Screens.ButtonListeners.MainMenuButtonListener;

public class LevelSelectScreen extends MasterScreen{
	
	OrbGame game;
	TextureAtlas levelButtonAtlas;
	
	ArrayList<ImageButton> imageButtons;
	ArrayList<ImageButtonStyle> imageButtonStyle;
	ArrayList<Boolean> unlocked;
	
	ArrayList<MasterScreen> levelScreens;

	private Skin skin;
	
	public LevelSelectScreen(OrbGame game, ArrayList<MasterScreen> levelScreens) {
		this.game = game;
		this.levelScreens = levelScreens;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		if(levelButtonAtlas == null) {
			levelButtonAtlas = new TextureAtlas();
		}
		
		if(skin == null){
			skin = new Skin();
			for(int i = 0; i < 3 * 2; i++) {
				skin.add("levelGroup" + i, levelButtonAtlas.createSprite("levelGroup" + i));
			}
		}
		
		if(imageButtonStyle == null) {
			imageButtonStyle = new ArrayList<ImageButtonStyle>();
			
			for(int i = 0; i < 3; i++) {
				imageButtonStyle.add(new ImageButtonStyle());
			}
			
			for(int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					imageButtonStyle.get(i).imageUp = skin.newDrawable("levelGroup" + i);
					imageButtonStyle.get(i).imageDown = skin.newDrawable("levelGroup" + i + 1);
					break;
				case 1:
					imageButtonStyle.get(i).imageUp = skin.newDrawable("levelGroup" + i + 1);
					imageButtonStyle.get(i).imageDown = skin.newDrawable("levelGroup" + i + 2);
					break;
				case 2:
					imageButtonStyle.get(i).imageUp = skin.newDrawable("levelGroup" + i + 2);
					imageButtonStyle.get(i).imageDown = skin.newDrawable("levelGroup" + i + 3);
					break;
				default:
					break;
				}
			}
		}
		
		if(imageButtons == null) {
			imageButtons = new ArrayList<ImageButton>();
			
			for(int i = 0; i < 3; i++) {
				imageButtons.add(new ImageButton(imageButtonStyle.get(i)));
				imageButtons.get(i).addListener(new MainMenuButtonListener(i, levelScreens.get(i), game));
			}
		}
		
		if(unlocked == null) {
			unlocked = new ArrayList<Boolean>();
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		imageButtons = null;
		imageButtonStyle = null;
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		//save booleans
		
	}
	

}
