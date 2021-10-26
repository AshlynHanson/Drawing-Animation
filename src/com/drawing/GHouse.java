package com.drawing;

import com.jogamp.opengl.GL2;

import java.util.Arrays;

public class GHouse implements GShape {

    private float[] vertex2f;
    private GQuad blueRect;
    private GQuad yellowRect;
    private GTriangle pinkTriangle;

    GHouse(float[] vertex2f) {
        this.vertex2f = Arrays.copyOf(vertex2f, vertex2f.length);

        this.blueRect = new GQuad(vertex2f);
        float[] blue = {
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
        };
        this.blueRect.setColor(blue);

        float[] yellowVertex2f = new float[] {
                this.vertex2f[0] + 2f, this.vertex2f[1] + 2f, this.vertex2f[2], // 0: location
                this.vertex2f[3] - 4f, this.vertex2f[4] - 4f, this.vertex2f[5], // 2: scale factor
                1f, 1f, 0f, // 4: main color
                2f, 1f, 0f
        }; // 7: outline color

        this.yellowRect = new GQuad(yellowVertex2f);
        float[] yellow = {
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
        };
        this.yellowRect.setColor(yellow);

        float[] triangleVertex2f = {
                this.vertex2f[0] + this.vertex2f[3] + 1.5f, this.vertex2f[1] + this.vertex2f[3], //bottom right
                this.vertex2f[0] + (this.vertex2f[3] / 2), this.vertex2f[1] + (1.8f * this.vertex2f[3]), //top middle
                this.vertex2f[0] - 1.5f, this.vertex2f[1] + this.vertex2f[3], //top left
        };
        this.pinkTriangle = new GTriangle(triangleVertex2f);
        float[] pink = {
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
        };
        this.pinkTriangle.setColor(pink);

    }

    /**
    * Renders the House object to the screen by rendering the roof, walls, and windows
    * of the house
    * @param gl The GL object
    */
    @Override
    public void render(final GL2 gl) {
        this.blueRect.render(gl);
        this.yellowRect.render(gl);
        this.pinkTriangle.render(gl);
    }


}