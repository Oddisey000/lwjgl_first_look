package com.oddisey.flappy.graphics;

import com.oddisey.flappy.utils.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Texture {
    private int width, height, textureID;

    public Texture(String path) {
        textureID = load(path);
    }

    private int load(String filePath) {
        int[] imagePixels = null;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(filePath));
            width = image.getWidth();
            height = image.getHeight();

            imagePixels = new int[width * height]; // get the data of each image inside the array
            image.getRGB(0, 0, width, height, imagePixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (imagePixels[i] & 0xff000000) >> 24;
            int r = (imagePixels[i] & 0xff0000) >> 16;
            int g = (imagePixels[i] & 0xff00) >> 8;
            int b = (imagePixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);

        // Disable antialiasing to prevent picture from blurring
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
        glBindTexture(GL_TEXTURE_2D, 0);

        return texture;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getTextureID() { return textureID; }
}
