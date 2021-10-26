package com.drawing;

import com.jogamp.opengl.GL2;

public class GRocket implements GShape {
    private GQuad body;
    private GTriangle top;
    private GTriangle triangleLeft;
    private GTriangle triangleRight;
    private GQuad smoke;
    private float[] vertex2f;
    private float disp;
    private float drawing_height;

    GRocket(float[] vertex2f,
            float drawing_height,
            float disp
    ) {
        this.vertex2f = vertex2f;
        this.disp = disp;
        this.drawing_height = drawing_height;

        float[] white = new float[] {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
        };
        float[] red = new float[] {
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
        };
        float[] black = new float[] {
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
        };

        this.body = new GQuad(this.vertex2f);
        this.body.setColor(white);

        float[] triangleVertex2f = {
                this.vertex2f[0] + this.vertex2f[3] + 2f, this.vertex2f[1] + this.vertex2f[4],
                this.vertex2f[0] + (this.vertex2f[3] / 2), this.vertex2f[1] + this.vertex2f[4] + (this.vertex2f[4] / 4f),
                this.vertex2f[0] - 2f, this.vertex2f[1] + this.vertex2f[4]
        };
        this.top = new GTriangle(triangleVertex2f);
        this.top.setColor(red);

        float[] triangleLeftVertex2f = {
                this.vertex2f[0], this.vertex2f[1],
                this.vertex2f[0], this.vertex2f[1] + (this.vertex2f[4] / 3f),
                this.vertex2f[0] - (this.vertex2f[3] / 2), this.vertex2f[1]
        };
        this.triangleLeft = new GTriangle(triangleLeftVertex2f);
        this.triangleLeft.setColor(red);

        float[] triangleRightVertex2f = {
                this.vertex2f[0] + this.vertex2f[3] + (this.vertex2f[3] / 2), this.vertex2f[1],
                this.vertex2f[0] + this.vertex2f[3], this.vertex2f[1] + (this.vertex2f[4] / 3f),
                this.vertex2f[0] + this.vertex2f[3], this.vertex2f[1]
        };
        this.triangleRight = new GTriangle(triangleRightVertex2f);
        this.triangleRight.setColor(red);

        float[] smokeVertex2f = {
                this.vertex2f[0] + (this.vertex2f[3] / 4f), this.vertex2f[1] - (this.vertex2f[4] / 4f), 0f,
                this.vertex2f[3] / 2f, this.vertex2f[4] / 4f, 1f
        };
        this.smoke = new GQuad(smokeVertex2f);
        this.smoke.setColor(black);
    }

    /**
     * Renders the Rocket object to the screen by rendering each shape
     * @param gl the GL object
     */
    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity(); // reset projection matrix

        this.updatePoint(this.disp, this.drawing_height, gl);

        this.body.render(gl);
        this.top.render(gl);
        this.triangleLeft.render(gl);
        this.triangleRight.render(gl);
        this.smoke.render(gl);

        gl.glPopMatrix();
    }

    /**
     * Moves the rocket's position across the screen. If the rocket's position
     * goes above the set max, the rocket starts at the left side of the screen
     * and continues to move forward
     * @param disp the rate that the rocket moves at
     * @param max the screen limit
     * @param gl the GL object
     */
    public void updatePoint(float disp, float max, final GL2 gl) {
        int index = 1;
        if (this.vertex2f[index] < max ) {
            this.vertex2f[index] += disp;
            gl.glTranslatef(0f, this.vertex2f[index], 0.0f);
        }
        else {
            this.vertex2f[index] = -(max);
            gl.glTranslatef(0f, this.vertex2f[index], 0.0f);
        }
    }
}
