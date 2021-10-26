package com.drawing;

import com.jogamp.opengl.GL2;

public class GCloud implements GShape {
    private float radius;
    private float x, y;
    private GCircle largeCircle;
    private GCircle smallLeftCircle;
    private GCircle smallRightCircle;
    private float drawing_width;
    private float disp;

    GCloud(float x, float y, float radius, float drawing_width, float disp) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.drawing_width = drawing_width;
        this.disp = disp;

        this.largeCircle = new GCircle(this.x, this.y, this.radius);
        float color[] = { 1f, 1f, 1f, 1f, 1f, 1f };
        this.largeCircle.setColor(color);

        this.smallLeftCircle = new GCircle(this.x - this.radius, this.y - (this.radius / 3), this.radius - (this.radius/ 4f));
        this.smallLeftCircle.setColor(color);

        this.smallRightCircle = new GCircle(this.x + this.radius, this.y - (this.radius / 3), this.radius - (this.radius/ 4f));
        this.smallRightCircle.setColor(color);
    }


    /**
     * Renders the Cloud object to the screen by rendering each small circle of the cloud
     * @param gl the GL object
     */
    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity(); // reset projection matrix

        this.updatePoint(this.disp, this.drawing_width, gl);

        this.largeCircle.render(gl);
        this.smallLeftCircle.render(gl);
        this.smallRightCircle.render(gl);

        gl.glPopMatrix();
    }

    /**
     * Moves the cloud's position across the screen. If the cloud's position
     * goes above the set max, the cloud starts at the left side of the screen
     * and continues to move forward
     * @param disp the rate that the cloud moves at
     * @param max the screen limit
     * @param gl the GL object
     */
    public void updatePoint(float disp, float max, final GL2 gl) {
        if (this.x < max) {
            this.x += disp;
            gl.glTranslatef(this.x, 0.0f, 0.0f);
        }
        else {
            this.x = - (max);
            gl.glTranslatef(this.x, 0.0f, 0.0f);
        }
    }
}
