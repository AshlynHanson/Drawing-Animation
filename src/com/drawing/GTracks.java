package com.drawing;

import com.jogamp.opengl.GL2;

public class GTracks implements GShape {

    private float vertex2f[];
    private GQuad track;

    GTracks(float trackVertex2f[]) {
        this.vertex2f = trackVertex2f;

        this.track = new GQuad(vertex2f);
        float[] brown = {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8]
        };
        this.track.setColor(brown);
    }

    /**
     * Renders the Train Tracks to the screen
     * @param gl The GL object
     */
    @Override
    public void render(final GL2 gl) {
        this.track.render(gl);
    }
}
