package com.drawing;

import com.jogamp.opengl.GL2;

public class GSun implements GShape {
    private float radius;
    private float x, y;
    private GCircle sun;
    private GLine line1;
    private GLine line2;
    private GLine line3;
    private GLine line4;
    private GLine line5;
    private GLine line6;
    private GLine line7;
    private GLine line8;
    private float theta;
    private final float thetaDisp = 360.0f / 100.0f; //speed

    GSun(float radius, float x, float y) {
        this.radius = radius;
        this.x = x;
        this.y = y;

        this.theta = 180.0f;

        this.sun = new GCircle(this.x, this.y, this.radius);
        float color[] = { 1f, 1f, 0.1f, 1f, 1f, 0.1f };
        this.sun.setColor(color);

        float[] line1Vertex2f = {this.x, this.y, this.x + this.radius + 2f, this.y + this.radius + 2f, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line1 = new GLine(line1Vertex2f);

        float[] line2Vertex2f = {this.x, this.y, this.x - this.radius - 2f, this.y - this.radius - 2f, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line2 = new GLine(line2Vertex2f);

        float[] line3Vertex2f = {this.x, this.y, this.x, this.y + this.radius + 4f, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line3 = new GLine(line3Vertex2f);

        float[] line4Vertex2f = {this.x, this.y, this.x, this.y - this.radius - 4f, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line4 = new GLine(line4Vertex2f);

        float[] line5Vertex2f = {this.x, this.y, this.x + this.radius + 4f, this.y, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line5 = new GLine(line5Vertex2f);

        float[] line6Vertex2f = {this.x, this.y, this.x - this.radius - 4f, this.y, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line6 = new GLine(line6Vertex2f);

        float[] line7Vertex2f = {this.x, this.y, this.x - this.radius - 3f, this.y + this.radius +2f, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line7 = new GLine(line7Vertex2f);

        float[] line8Vertex2f = {this.x, this.y, this.x + this.radius + 3f, this.y - this.radius - 2f, 1f, 1f, 0.1f, 1f, 1f, 0.1f};
        this.line8 = new GLine(line8Vertex2f);

    }
    /**
     * Renders the Sun object to the screen
     * @param gl The GL object
     */
    @Override
    public void render(final GL2 gl) {
        gl.glPushMatrix();

        gl.glTranslatef(this.x, this.y, 0.0f);
        gl.glRotatef(theta, 0f, 0f, 1f);
        gl.glTranslatef(-this.x, -this.y, 0.0f);

        gl.glVertex2f(0f, 0f); // Center of circle

        this.updateTheta();

        this.sun.render(gl);
        this.line1.render(gl);
        this.line2.render(gl);
        this.line3.render(gl);
        this.line4.render(gl);
        this.line5.render(gl);
        this.line6.render(gl);
        this.line7.render(gl);
        this.line8.render(gl);

        gl.glPopMatrix();
    }


    public void updateTheta() {
        theta += this.thetaDisp;
        if (theta > 360.0f)
            theta = 0.f;
    }
}
