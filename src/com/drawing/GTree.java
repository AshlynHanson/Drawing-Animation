package com.drawing;

import com.jogamp.opengl.GL2;

public class GTree implements GShape {
    private float[] vertex2f;
    private GQuad trunk;
    private GLeaves leaves;
    private GCircle apple1;
    private GCircle apple2;
    private GCircle apple3;
    private GCircle apple4;
    private GCircle apple5;
    private GCircle apple6;
    private GCircle apple7;

    GTree(float[] vertex2f) {
        this.vertex2f = vertex2f;

        this.trunk = new GQuad(this.vertex2f);
        float[] trunkColor = new float[]{
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
                this.vertex2f[6],
                this.vertex2f[7],
                this.vertex2f[8],
        };
        this.trunk.setColor(trunkColor);

        float[] red = new float[] {
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14],
                this.vertex2f[12],
                this.vertex2f[13],
                this.vertex2f[14]
        };
        this.apple1 = new GCircle(this.vertex2f[0] + this.vertex2f[3] - 6f, this.vertex2f[1] + this.vertex2f[4] - 4f, this.vertex2f[3] / 2.8f);
        this.apple2 = new GCircle(this.vertex2f[0] + this.vertex2f[3] + 3f, this.vertex2f[1] + this.vertex2f[4] - 1.5f , this.vertex2f[3] / 2.8f);
        this.apple3 = new GCircle(this.vertex2f[0] + this.vertex2f[3] - 6.5f, this.vertex2f[1] + this.vertex2f[4] + 1.3f, this.vertex2f[3] / 2f);
        this.apple4 = new GCircle(this.vertex2f[0] + this.vertex2f[3] + 4.3f, this.vertex2f[1] + this.vertex2f[4] + 3f , this.vertex2f[3] / 2.8f);
        this.apple5 = new GCircle(this.vertex2f[0] + this.vertex2f[3] - 6.5f, this.vertex2f[1] + this.vertex2f[4] + 7f, this.vertex2f[3] / 2f);
        this.apple6 = new GCircle(this.vertex2f[0] + this.vertex2f[3] + 2.7f, this.vertex2f[1] + this.vertex2f[4] + 7.5f , this.vertex2f[3] / 2f);
        this.apple7 = new GCircle(this.vertex2f[0] + this.vertex2f[3] - 1.5f, this.vertex2f[1] + this.vertex2f[4] + 11f , this.vertex2f[3] / 2.8f);

        this.apple1.setColor(red);
        this.apple2.setColor(red);
        this.apple3.setColor(red);
        this.apple4.setColor(red);
        this.apple5.setColor(red);
        this.apple6.setColor(red);
        this.apple7.setColor(red);

        this.leaves = new GLeaves(
                this.vertex2f[0] + (this.vertex2f[3] / 2),
                this.vertex2f[1] + this.vertex2f[4] + 3f,
                this.vertex2f[4] / 2.2f,
                this.vertex2f[4] / 2.1f
        );
        float[] green = new float[] {
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
                this.vertex2f[9],
                this.vertex2f[10],
                this.vertex2f[11],
        };
        this.leaves.setColor(green);
    }

    /**
     * Renders the Tree object by rendering each of the shapes on the tree,
     * including the leaves, trunk, and apples
     * @param gl The GL object that displays to the screen
     */
    @Override
    public void render(final GL2 gl) {
        this.leaves.render(gl);
        this.apple1.render(gl);
        this.apple2.render(gl);
        this.apple3.render(gl);
        this.apple4.render(gl);
        this.apple5.render(gl);
        this.apple6.render(gl);
        this.apple7.render(gl);
        this.trunk.render(gl);
    }

}
