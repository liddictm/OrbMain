package com.noahbutler.orb.World.Orbs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * @author Noah Butler I made this class so that the orb creation is seperated
 *         from rendering them. This is because there are to different was to
 *         add orbs but one way to render them.
 * 
 *         So whatever creator is being used, they just add the new orb to an
 *         Array in the world class that will then be passed to the renderer for
 *         rendering and sprite assigning.
 */

public class OrbRenderer {

	public OrbRenderer() {

	}

	public void render(SpriteBatch b, Array<Orbs> orbs) {
		if (orbs != null) {
			for (Orbs orb : orbs) {
				orb.render(b);
			}
		}
	}
}
