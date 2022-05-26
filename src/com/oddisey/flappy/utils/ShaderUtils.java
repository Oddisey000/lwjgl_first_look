package com.oddisey.flappy.utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
    private ShaderUtils() {}

    private static int load(String vertPath, String fragPath) {
        String vert = FileUtils.loadAsString(vertPath);
        String frag = FileUtils.loadAsString(fragPath);

        return create(vert, frag);
    }

    public static int create(String vert, String frag) {
        int program = glCreateProgram();

        // Initialize and get id of the created shaders
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);

        // Passing shader code into the function
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        // Compile the shader and check if there is no mistakes
        glCompileShader(vertID);
        if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println("Failed to compile vertex shader!");
            System.err.println(glGetShaderInfoLog(vertID, 2048));
        }
        glCompileShader(fragID);
        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println("Failed to compile fragment shader!");
            System.err.println(glGetShaderInfoLog(fragID, 2048));
        }

        // Attach shaders to the application, link and validate it
        glAttachShader(program, vertID);
        glAttachShader(program, fragID);
        glLinkProgram(program);
        glValidateProgram(program);

        return  program;
    }
}
