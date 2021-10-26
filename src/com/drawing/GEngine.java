package com.drawing;

import com.jogamp.opengl.GL2;

public class GEngine implements GShape {
    private GQuad top;
    private GQuad window;
    private GQuad body;
    private GQuad hook;
    private GQuad front;
    private GQuad pipe;
    private GWheel backWheel;
    private GWheel frontWheel;
    private float[] vertex2f;

    GEngine(float[] vertex2f) {
        this.vertex2f = vertex2f;

        float[] blue = new float[] {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8]
        };
        float[] red = new float[] {
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11]
        };
        float[] black = new float[] {
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14]
        };
        this.body = new GQuad(vertex2f);
        this.body.setColor(blue);

        float[] windowVertex2f = new float[] {
                this.vertex2f[0] + 1.5f, this.vertex2f[1] + this.vertex2f[4], 0f,
                this.vertex2f[3] / 1.6f, 1.1f * this.vertex2f[4], 1f
        };
        this.window = new GQuad(windowVertex2f);
        this.window.setColor(red);

        float[] topVertex2f = new float[] {
                this.vertex2f[0] + 1.5f, this.vertex2f[1] + this.vertex2f[4] + (1.1f * this.vertex2f[4]), 0f,
                this.vertex2f[3] / 1.6f, this.vertex2f[4] / 6, 1f
        };
        this.top = new GQuad(topVertex2f);
        this.top.setColor(blue);

        float[] pipeVertex2f = new float[] {
                this.vertex2f[0] + (this.vertex2f[3] / 1.3f), this.vertex2f[1] + this.vertex2f[4], 0f,
                this.vertex2f[3] / 8.5f, this.vertex2f[4] / 2, 1f
        };
        this.pipe = new GQuad(pipeVertex2f);
        this.pipe.setColor(blue);

        float[] frontVertex2f = new float[] {
                this.vertex2f[0] + this.vertex2f[3], this.vertex2f[1] + this.vertex2f[4] / 3.8f, 0f,
                this.vertex2f[3] / 10f, this.vertex2f[4] / 2, 1f
        };
        this.front = new GQuad(frontVertex2f);
        this.front.setColor(black);

        float[] hookVertex2f = new float[] {
                this.vertex2f[0] - this.vertex2f[3] / 3f, this.vertex2f[1] + this.vertex2f[4] / 6, 0f,
                this.vertex2f[3] / 3, this.vertex2f[4] / 6, 1f
        };
        this.hook = new GQuad(hookVertex2f);
        this.hook.setColor(blue);

        this.backWheel = new GWheel(this.vertex2f[0] + (this.vertex2f[3] / 3.8f), this.vertex2f[1] + (this.vertex2f[4] / 8.3f), this.vertex2f[4] / 2.7f);
        this.frontWheel = new GWheel(this.vertex2f[0] + (this.vertex2f[3] / 1.3f), this.vertex2f[1], this.vertex2f[4] / 4.1f);
    }


    /**
     * Renders the Engine to the screen by rendering the engine body, hook, and wheels
     * @param gl the GL object
     */
    @Override
    public void render(final GL2 gl) {
        this.body.render(gl);
        this.window.render(gl);
        this.top.render(gl);
        this.pipe.render(gl);
        this.front.render(gl);
        this.hook.render(gl);
        this.backWheel.render(gl);
        this.frontWheel.render(gl);

    }
}
