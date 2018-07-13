/*
This project was made with the help of "Brent Aureli's - Code School" channel on Youtube with the purpose of practicing
Android development
* */

package com.shahmianhashim.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shahmianhashim.game.FlappyDappy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        //edit config so we can set size of desktop screen according to variables in FlappyDappy class.
        config.width = FlappyDappy.WIDTH;
        config.height = FlappyDappy.HEIGHT;
        config.title = FlappyDappy.TITLE;

		new LwjglApplication(new FlappyDappy(), config);
	}
}
