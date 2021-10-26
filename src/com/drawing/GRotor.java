package com.drawing;

import com.jogamp.opengl.GL2;

public class GRotor implements GShape {

    private GCircle hub;
    private GTriangle blade1;
    private GTriangle blade2;
    private GTriangle blade3;
    private float[] vertex2f;
    private float theta;
    private final float thetaDisp = 360.0f / 200.0f; //speed

    GRotor(float[] vertex2f) {
        this.vertex2f = vertex2f;

        float[] white = {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
        };

        this.hub = new GCircle(vertex2f[0], vertex2f[1], vertex2f[2]); // x, y, r are first 3 vertices
        this.hub.setColor(white);

        float[] blade1Vertex2f = {
                this.vertex2f[0] + (this.vertex2f[2] / 2f), this.vertex2f[1],
                this.vertex2f[0] - (this.vertex2f[3] / 2f), this.vertex2f[1] + (this.vertex2f[4] / 1.9f),
                this.vertex2f[0] - (this.vertex2f[2] / 2f), this.vertex2f[1]
        };
        this.blade1 = new GTriangle(blade1Vertex2f);
        this.blade1.setColor(white);

        float[] blade2Vertex2f = {
                this.vertex2f[0] + (this.vertex2f[2] / 2f), this.vertex2f[1],
                this.vertex2f[0] - (this.vertex2f[2] / 2f), this.vertex2f[1] + (this.vertex2f[2] / 2f),
                this.vertex2f[0] - (this.vertex2f[4] / 2f), this.vertex2f[1] - (this.vertex2f[4] / 3f)
        };
        this.blade2 = new GTriangle(blade2Vertex2f);
        this.blade2.setColor(white);

        float[] blade3Vertex2f = {
                this.vertex2f[0] + (this.vertex2f[4] / 2f), this.vertex2f[1] - (this.vertex2f[4] / 3f),
                this.vertex2f[0] + (this.vertex2f[2] / 2f), this.vertex2f[1] + (this.vertex2f[2] / 2f),
                this.vertex2f[0] - (this.vertex2f[2] / 2f), this.vertex2f[1]
        };
        this.blade3 = new GTriangle(blade3Vertex2f);
        this.blade3.setColor(white);

    }

    /**
     * Renders the Rotor object to the screen by rendering each blade and hub
     * @param gl the GL object
     */
    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();

        gl.glTranslatef(this.vertex2f[0], this.vertex2f[1], 0.0f);
        gl.glRotatef(theta, 0f, 0f, 1f);
        gl.glTranslatef(-this.vertex2f[0], -this.vertex2f[1], 0.0f);

        gl.glVertex2f(0f, 0f); // Center of circle

        this.updateTheta();

        this.hub.render(gl);
        this.blade1.render(gl);
        this.blade2.render(gl);
        this.blade3.render(gl);

        gl.glPopMatrix();
    }

    public void updateTheta() {
        theta += this.thetaDisp;
        if (theta > 360.0f)
            theta = 0.f;
    }
}
