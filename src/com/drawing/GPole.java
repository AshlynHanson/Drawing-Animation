package com.drawing;

import com.jogamp.opengl.GL2;

public class GPole implements GShape {
    private GQuad pole;
    private float[] vertex2f;

    GPole(float[] vertex2f) {
        this.vertex2f = vertex2f;

        float[] white = {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
        };

        this.pole = new GQuad(this.vertex2f);
        this.pole.setColor(white);
    }

    /**
     * Renders the Pole object to the screen by rendering each shape
     * @param gl the GL object
     */
    @Override
    public void render(GL2 gl) {
        this.pole.render(gl);
    }
}
