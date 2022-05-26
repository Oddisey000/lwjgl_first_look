package com.oddisey.flappy;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Main implements Runnable {

    // Define main window parameters
    private int width = 1280;
    private int height = 720;
    private String title = "Flappy Bird";

    // Define initial game state parameters
    private boolean isRunning = false;
    private Thread thread;

    private void start() {
        isRunning = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    /*
    * Create new application window
    * Set window parameters and create new window
    * Make sure application will use opengl 3.3 or higher and for macOS set 3.2
    * While game window is open, update data accordingly
    * If user close the window then stop process exit the loop and destroy process
    * */
    public void run() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            ContextAttribs context = new ContextAttribs(3,3);
            if (System.getProperty("os.name").contains("Mac")) context = new ContextAttribs(3,2);
            Display.create(new PixelFormat(), context.withProfileCore(true));
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        while (isRunning) {
            Display.update();
            if (Display.isCloseRequested()) isRunning = false;
        }
        Display.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
