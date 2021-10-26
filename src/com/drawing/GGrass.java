package com.drawing;

import com.jogamp.opengl.GL2;

public class GGrass implements GShape {
    private float radius;
    private float x, y;
    private GCircle grass;

    GGrass(float x, float y, float radius) {
        this.radius = radius;
        this.x = x;
        this.y = y;

        this.grass = new GCircle(this.x, this.y, this.radius);
        float color[] = { 0f, 0.8f, 0f, 0f, 0.8f, 0f };
        this.grass.setColor(color);
    }


    /**
     * Renders the Grass object to the screen
     * @param gl The GL object that displays to the screen
     */
    @Override
    public void render(GL2 gl) {
        this.grass.render(gl);
    }
}
