package com.drawing;

import com.jogamp.opengl.GL2;

public class GLeaves implements GShape {
    private float radiusX, radiusY;
    private float x, y;
    private GEllipse leaves;
    private float[] color;

    private float theta;
    private final float thetaDisp = 360.0f / 150.0f; //speed

    GLeaves(float x, float y, float rx, float ry) {
        this.x = x;
        this.y = y;
        this.radiusX = rx;
        this.radiusY = ry;

        this.leaves = new GEllipse(this.x, this.y, this.radiusX, this.radiusY);
    }

    void setColor(float color[]) {
        this.leaves.setColor(color);
    }

    /**
     * Renders the Leaves object by rendering each of the shapes
     * @param gl The GL object that displays to the screen
     */
    @Override
    public void render(final GL2 gl) {
        gl.glPushMatrix();

        gl.glTranslatef(this.x, this.y, 0.0f);
        gl.glRotatef(theta, 0f, 0f, 1f);
        gl.glTranslatef(-this.x, -this.y, 0.0f);

        gl.glVertex2f(0f, 0f); // Center of circle

        this.updateTheta();

        this.leaves.render(gl);

        gl.glPopMatrix();
    }

    public void updateTheta() {
        theta += this.thetaDisp;
        if (theta > 360.0f)
            theta = 0.f;
    }
}
