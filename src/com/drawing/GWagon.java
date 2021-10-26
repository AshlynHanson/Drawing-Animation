package com.drawing;

import com.jogamp.opengl.GL2;

public class GWagon implements GShape {
    private GQuad box;
    private GWheel left;
    private GWheel right;
    private float[] vertex2f;

    GWagon(float[] vertex2f) {
        this.vertex2f = vertex2f;

        this.box = new GQuad(vertex2f);
        float[] brown = {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8]
        };
        this.box.setColor(brown);

        this.left = new GWheel(this.vertex2f[0] + (this.vertex2f[4] / 2.4f), this.vertex2f[1], this.vertex2f[3] / 6);
        this.right = new GWheel(this.vertex2f[0] + (this.vertex2f[4]), this.vertex2f[1], this.vertex2f[3] / 6);
    }


    /**
     * Renders the Wagon object by rendering each of the shapes
     * @param gl The GL object that displays to the screen
     */
    @Override
    public void render(final GL2 gl) {
        this.box.render(gl);
        this.left.render(gl);
        this.right.render(gl);
    }
}
