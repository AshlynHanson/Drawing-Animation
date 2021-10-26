package com.drawing;

import com.jogamp.opengl.GL2;

public class GWindmill implements GShape {
    private GRotor rotor;
    private GPole pole;
    private float[] vertex2f;

    GWindmill(float[] vertex2f) {
        this.vertex2f = vertex2f;

        this.rotor = new GRotor(this.vertex2f);

        float[] poleVertex2f = {
                this.vertex2f[0] - (this.vertex2f[3] / 2), this.vertex2f[1] - this.vertex2f[4], this.vertex2f[2],
                this.vertex2f[3], this.vertex2f[4], this.vertex2f[5],
                this.vertex2f[6], this.vertex2f[7], this.vertex2f[8]
        };
        this.pole = new GPole(poleVertex2f);
    }

    /**
     * Renders the Windmill object to the screen by rendering each shape
     * @param gl the GL object
     */
    @Override
    public void render(GL2 gl) {
        this.rotor.render(gl);
        this.pole.render(gl);

    }
}
