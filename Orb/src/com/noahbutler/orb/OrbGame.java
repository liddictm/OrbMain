package com.noahbutler.orb;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.noahbutler.orb.Screens.AchievementScreen;
import com.noahbutler.orb.Screens.GunUpgradeScreen;
import com.noahbutler.orb.Screens.MainMenuScreen;
import com.noahbutler.orb.Screens.MasterScreen;
import com.noahbutler.orb.Screens.OptionScreen;
import com.noahbutler.orb.Screens.OrbGameScreen;
import com.noahbutler.orb.Screens.ShipUpgradeScreen;
import com.noahbutler.orb.Screens.TutorialScreen;
import com.noahbutler.orb.Screens.LevelSelection.LevelDesertSelection;
import com.noahbutler.orb.Screens.LevelSelection.LevelPlanetSelection;
import com.noahbutler.orb.Screens.LevelSelection.LevelSelectScreen;
import com.noahbutler.orb.Screens.LevelSelection.LevelSpaceSelection;

public class OrbGame extends Game {
	
	private MainMenuScreen mainMenuScreen;
	private TutorialScreen tutorialScreen;
	private AchievementScreen achievementScreen;
	private OrbGameScreen gameScreen;
	private LevelSelectScreen levelSelectScreen;
	private OptionScreen optionScreen;
	
	private LevelDesertSelection levelDesertSelectionScreen;
	private LevelPlanetSelection levelPlanetSelectionScreen;
	private LevelSpaceSelection levelSpaceSelectionScreen;
	
	private ShipUpgradeScreen shipUpgradeScreen;
	private GunUpgradeScreen gunUpgradeScreen;
	
	private ArrayList<MasterScreen> screens;
	private ArrayList<MasterScreen> levelScreens;
	
	
	@Override
	public void create() {
		screens      = new ArrayList<MasterScreen>();
		levelScreens = new ArrayList<MasterScreen>();
		
		gameScreen = new OrbGameScreen();
		
		levelDesertSelectionScreen = new LevelDesertSelection();
		levelPlanetSelectionScreen = new LevelPlanetSelection();
		levelSpaceSelectionScreen  = new LevelSpaceSelection();
		
		achievementScreen = new AchievementScreen();
		tutorialScreen    = new TutorialScreen();
		optionScreen      = new OptionScreen();
		
		shipUpgradeScreen = new ShipUpgradeScreen();
		gunUpgradeScreen  = new GunUpgradeScreen();
		
		screens.add(levelSelectScreen);
		screens.add(optionScreen);
		screens.add(tutorialScreen);
		screens.add(achievementScreen);
		
		levelScreens.add(levelDesertSelectionScreen);
		levelScreens.add(levelPlanetSelectionScreen);
		levelScreens.add(levelSpaceSelectionScreen);
		
		levelSelectScreen = new LevelSelectScreen(this, levelScreens);
		mainMenuScreen = new MainMenuScreen(this, screens);
		
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
