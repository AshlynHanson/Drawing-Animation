package com.drawing;

import com.jogamp.opengl.GL2;

public class GWheel implements GShape {
    private float radius;
    private float x, y;
    private GCircle wheel;
    private GTriangleCircle rim;
    private GCircle center;
    private GLine line1;
    private GLine line2;
    private GLine line3;
    private GLine line4;

    private float theta;
    private final float thetaDisp = 360.0f / 100.0f; //speed

    GWheel(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.radius = r;

        this.wheel = new GCircle(this.x, this.y, this.radius);
        float[] black = new float[] {0f, 0f, 0f, 0f, 0f, 0f};
        this.wheel.setColor(black);

        this.rim = new GTriangleCircle(this.x, this.y, (this.radius / 1.5f));
        float[] red = new float[] {1f, 0f, 0f, 1f, 0f, 0f};
        this.rim.setColor(red);

        this.center = new GCircle(this.x, this.y, (this.radius / 4f) );
        float[] grey = new float[] {0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f};
        this.center.setColor(grey);

        float[] line1Vertex2f = {this.x, this.y, this.x + this.radius, this.y, 0f, 0f, 0f, 0f, 0f, 0f};
        this.line1 = new GLine(line1Vertex2f);

        float[] line2Vertex2f = {this.x, this.y, this.x, this.y + this.radius, 0f, 0f, 0f, 0f, 0f, 0f};
        this.line2 = new GLine(line2Vertex2f);

        float[] line3Vertex2f = {this.x, this.y, this.x - this.radius, this.y, 0f, 0f, 0f, 0f, 0f, 0f};
        this.line3 = new GLine(line3Vertex2f);

        float[] line4Vertex2f = {this.x, this.y, this.x, this.y - this.radius, 0f, 0f, 0f, 0f, 0f, 0f};
        this.line4 = new GLine(line4Vertex2f);

    }


    /**
     * Renders the Wheel object by rendering each of the shapes
     * @param gl The GL object that displays to the screen
     */
    @Override
    public void render(final GL2 gl) {
        gl.glPushMatrix();

        gl.glTranslatef(this.x, this.y, 0.0f);
        gl.glRotatef(-theta, 0f, 0f, 1f);
        gl.glTranslatef(-this.x, -this.y, 0.0f);

        gl.glVertex2f(0f, 0f); // Center of circle

        this.updateTheta();

        this.wheel.render(gl);
        this.rim.render(gl);
        this.line1.render(gl);
        this.line2.render(gl);
        this.line3.render(gl);
        this.line4.render(gl);
        this.center.render(gl);

        gl.glPopMatrix();
    }

    /**
     * Updates the theta angle position of the wheel so it can rotate
     */
    public void updateTheta() {
        theta += this.thetaDisp;
        if (theta > 360.0f)
            theta = 0.f;
    }
}
