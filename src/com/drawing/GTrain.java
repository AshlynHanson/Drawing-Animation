package com.drawing;

import com.jogamp.opengl.GL2;

public class GTrain implements GShape {
    private GWagon wagon;
    private GEngine engine;
    private float[] vertex2f;
    private float drawing_width;
    private float trainSpeed;

    GTrain(float[] vertex2f, float drawing_width, float trainSpeed) {
        this.vertex2f = vertex2f;
        this.drawing_width = drawing_width;
        this.trainSpeed = trainSpeed;

        this.engine = new GEngine(this.vertex2f);


        float[] wagonVertex2f = new float[] {
                this.vertex2f[0] - (1.28f * this.vertex2f[3]), this.vertex2f[1], 0f,
                this.vertex2f[3], this.vertex2f[4], 1f,
                this.vertex2f[15], this.vertex2f[16], this.vertex2f[17],
        };
        this.wagon = new GWagon(wagonVertex2f);
    }

    /**
     * Renders the Train to the screen by rendering the engine and wagon objects
     * @param gl the GL object
     */
    @Override
    public void render(final GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity(); // reset projection matrix

        this.updatePoint(this.trainSpeed, this.drawing_width, gl);

        this.wagon.render(gl);
        this.engine.render(gl);

        gl.glPopMatrix();

    }

    /**
     * Moves the train's position across the screen. If the trains' positions
     * goes above the set max, the train starts at the left side of the screen
     * and continues to move forward
     * @param disp the rate that the train moves at
     * @param max the screen limit
     * @param gl the GL object
     */
    public void updatePoint(float disp, float max, final GL2 gl) {
        int index = 0;
        if (this.vertex2f[index] < (max + (1.5f * this.vertex2f[3]))) {
            this.vertex2f[index] += disp;
            gl.glTranslatef(this.vertex2f[index], 0.0f, 0.0f);
        }
        else {
            this.vertex2f[index] = -(max + (1.5f * this.vertex2f[3]));
            gl.glTranslatef(this.vertex2f[index], 0.0f, 0.0f);
        }

    }
}
