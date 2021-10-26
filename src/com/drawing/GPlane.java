package com.drawing;

import com.jogamp.opengl.GL2;

public class GPlane implements GShape {
    private GQuad body;
    private GTriangle front;
    private GTriangle back;
    private GTriangle backWing;
    private GTriangle topWing;
    private GTriangle bottomWing;
    private float[] vertex2f;
    private float drawing_width;
    private float disp;

    GPlane(
            float[] vertex2f,
            float drawing_width,
            float disp
    ) {
        this.vertex2f = vertex2f;
        this.drawing_width = drawing_width;
        this.disp = disp;

        float[] grey = {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
        };
        float[] darkGrey = new float[] {
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
        };
        float[] blue = new float[] {
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
        };

        this.body = new GQuad(this.vertex2f);
        this.body.setColor(grey);

        float[] frontVertex2f = {
                this.vertex2f[0] + this.vertex2f[3] + (this.vertex2f[3] / 3f), this.vertex2f[1],
                this.vertex2f[0] + this.vertex2f[3], this.vertex2f[1] + this.vertex2f[4],
                this.vertex2f[0] + this.vertex2f[3], this.vertex2f[1]
        };
        this.front = new GTriangle(frontVertex2f);
        this.front.setColor(grey);

        float[] backVertex2f = {
                this.vertex2f[0], this.vertex2f[1],
                this.vertex2f[0], this.vertex2f[1] + this.vertex2f[4],
                this.vertex2f[0] - (this.vertex2f[3] / 3f), this.vertex2f[1] + this.vertex2f[4]
        };
        this.back = new GTriangle(backVertex2f);
        this.back.setColor(grey);

        float[] backWingVertex2f = {
                this.vertex2f[0], this.vertex2f[1] + this.vertex2f[4],
                this.vertex2f[0] - (this.vertex2f[3] / 3f), this.vertex2f[1] + (2 * this.vertex2f[4]),
                this.vertex2f[0] - (this.vertex2f[3] / 3f), this.vertex2f[1] + this.vertex2f[4]
        };
        this.backWing = new GTriangle(backWingVertex2f);
        this.backWing.setColor(blue);

        float[] bottomWingVertex2f = {
                this.vertex2f[0] + (this.vertex2f[3] / 1.5f), this.vertex2f[1],
                this.vertex2f[0] + (this.vertex2f[3] / 2.5f), this.vertex2f[1],
                this.vertex2f[0] + (this.vertex2f[3] / 2.5f), this.vertex2f[1] - this.vertex2f[4]
        };
        this.bottomWing = new GTriangle(bottomWingVertex2f);
        this.bottomWing.setColor(grey);

        float[] topWingVertex2f = {
                this.vertex2f[0] + (this.vertex2f[3] / 1.5f), this.vertex2f[1] + this.vertex2f[4],
                this.vertex2f[0] + (this.vertex2f[3] / 2.5f), this.vertex2f[1] + (2 * this.vertex2f[4]),
                this.vertex2f[0] + (this.vertex2f[3] / 2.5f), this.vertex2f[1] + this.vertex2f[4]
        };
        this.topWing = new GTriangle(topWingVertex2f);
        this.topWing.setColor(darkGrey);

    }
    /**
     * Renders the Plane object to the screen by rendering each shape
     * @param gl the GL object
     */
    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity(); // reset projection matrix

        this.updatePoint(this.disp, this.drawing_width, gl);

        this.body.render(gl);
        this.front.render(gl);
        this.back.render(gl);
        this.backWing.render(gl);
        this.topWing.render(gl);
        this.bottomWing.render(gl);

        gl.glPopMatrix();
    }

    /**
     * Moves the plane's position across the screen. If the plane's position
     * goes above the set max, the plane starts at the left side of the screen
     * and continues to move forward
     * @param disp the rate that the plane moves at
     * @param max the screen limit
     * @param gl the GL object
     */
    public void updatePoint(float disp, float max, final GL2 gl) {
        int index = 0;
        if (this.vertex2f[index] < max + 10f) {
            this.vertex2f[index] += disp;
            gl.glTranslatef(this.vertex2f[index], 0.0f, 0.0f);
        }
        else {
            this.vertex2f[index] = -max;
            gl.glTranslatef(this.vertex2f[index], 0.0f, 0.0f);
        }
    }
}
