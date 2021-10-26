package com.drawing;

import com.jogamp.opengl.GL2;

public class GCar implements GShape {
	private int ref_x, ref_y;

	float vertex2f[];

	GCar(float vertex2f[]) {

	}

	public void drawOutline(final GL2 gl) {

	}

	public void render(final GL2 gl) {

		gl.glPushMatrix();


		gl.glTranslatef(ref_x, ref_y, 0.0f);


		drawOutline(gl);

		gl.glPopMatrix();


	}

	// for animation purposes
	void updatePoint(float disp, float max) {
		if (this.ref_x < max)
			this.ref_x += disp;
		else
			this.ref_x = 0;
	}

}
