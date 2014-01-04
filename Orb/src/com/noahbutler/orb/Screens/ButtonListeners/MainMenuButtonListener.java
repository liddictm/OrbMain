package com.noahbutler.orb.Screens.ButtonListeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.noahbutler.orb.OrbGame;
import com.noahbutler.orb.Screens.MasterScreen;

public class MainMenuButtonListener extends InputListener{
	
	private int index;
	private MasterScreen screen;
	private OrbGame game;

	public MainMenuButtonListener(int index, MasterScreen screen, OrbGame game) {
		this.index = index;
		this.screen = screen;
		this.game = game;
	}
	
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {

		return true;
	}
	
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		game.setScreen(screen);
	}
}
