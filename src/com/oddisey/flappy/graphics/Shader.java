package com.oddisey.flappy.graphics;

import static org.lwjgl.opengl.GL20.*;

import com.oddisey.flappy.math.Vector3f;
import com.oddisey.flappy.utils.ShaderUtils;

public class Shader {
    private int id;
    public static Shader BASIC;

    private Shader(String vertex, String fragment) {
        id = ShaderUtils.load(vertex, fragment);
    }
    public static void loadAll() {
        BASIC = new Shader("shaders/shader.vert", "shaders/shader.frag");
    }

    public int getUniform(String name) {
        return glGetUniformLocation(id, name);
    }

    public void setUniform3f(String name, Vector3f vector3f) {
        glUniform3f(getUniform(name), vector3f.x, vector3f.y, vector3f.z);
    }

    public void enable() { glUseProgram(id); }
    public void disable() { glUseProgram(0); }
}
