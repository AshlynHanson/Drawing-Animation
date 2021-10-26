package com.drawing;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.*;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;

// import com.jogamp.opengl.util.gl2.GLUT;

/*
* JOGL 2.0 Program Template (GLCanvas) This is a "Component" which can be added
* into a top-level "Container". It also handles the OpenGL events to render
* graphics.
*/
@SuppressWarnings("serial")
class GLUTCanvas extends GLCanvas implements GLEventListener {

	public static int CANVAS_WIDTH = 1024; // width of the drawable
	public static int CANVAS_HEIGHT = 1024; // height of the drawable

	private final float DRAWING_WIDTH = 100f, DRAWING_HEIGHT = 100f;
	// Setup OpenGL Graphics Renderer
	public static float GL_Width, GL_Height;
	// Setup OpenGL Graphics Renderer
	GDrawOrigin myOrigin;
	GKeyBoard keyBoard;
	GMouse mouse;


	private GCircle myCircle;


	//private GQuad myQuad;

	//private GSQuad fooQuad;
	private GCQuad cenQuad;
	private GSun sun;
	private GGrass grass;
	private GCloud cloud1;
	private GCloud cloud2;
	private GHouse house;
	private GTracks tracks;
	private GTree tree;
	private GTrain train;
	private GRocket rocket;
	private GPlane plane;
	private GPlane plane2;
	private GWindmill windmill;
	private GWindmill windmill2;


	ArrayList<GShape> drawingObjects;

	/** Constructor to setup the GUI for this Component */
	public GLUTCanvas() {
		this.addGLEventListener(this);
		this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

	}

	// ------ Implement methods declared in GLEventListener ------

	/**
	 * 
	 * Called back immediately after the OpenGL context is initialized. Can be used
	 * to perform one-time initialization. Run only once.
	 */
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context

		// ----- Your OpenGL initialization code here -----

		GL_Width = DRAWING_WIDTH / 2.0f;
		GL_Height = DRAWING_HEIGHT / 2.0f;
		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		gl.glOrtho(-DRAWING_WIDTH / 2, DRAWING_WIDTH / 2, -DRAWING_HEIGHT / 2, DRAWING_HEIGHT / 2, -1.0f, 0.0f); // 2D

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW); // specify coordinates
		gl.glLoadIdentity(); // reset

		gl.glClearColor(0.74f, 0.84f, 0.9f, 1.0f); // color used to clean the canvas - light blue
		gl.glColor3f(1.0f, 0.0f, 0.0f); // drawing color

		this.sun = new GSun(3.5f, 40f, 40f);

		this.grass = new GGrass(0, -100, DRAWING_WIDTH);

		float cloud1Speed = 0.1f;
		this.cloud1 = new GCloud(5f, 30f, 3.5f, DRAWING_WIDTH / 1.3f, cloud1Speed);
		float cloud2Speed = 0.08f;
		this.cloud2 = new GCloud(-12f, 37f, 5f, DRAWING_WIDTH / 1.3f, cloud2Speed);


		float[] houseVertex2f = new float[] {
				-10f, -2f, 0f, // 0: location
				12f, 12f, 1f, // 3: scale factor
				0.1f, 0.1f, 1f, // 6: blue house color
				1f, 1f, 0f, // 9: yellow house color
				0.9f, 0.3f, 0.7f // 9: pink house color
		}; // 7: outline color

		this.house = new GHouse(houseVertex2f);


		float[] trackVertex2f = {
				-(DRAWING_WIDTH / 1.9f), -(DRAWING_WIDTH / 2) + 5f, 0f, // position - bottom left
				DRAWING_WIDTH + 5f, 3f, 1f, // scaling
				//0.9f, 0.6f, 0.2f // color
				0.545f, 0.271f, 0.075f // track color
		};
		this.tracks = new GTracks(trackVertex2f);

		float[] treeVertex2f = new float[] {
				-30f, -10f, 0f, //0: location of tree
				3f, 28f, 1f,  //3: scaling factor
				0.545f, 0.271f, 0.075f, // 6: trunk color
				0f, 0.8f, 0f, //9: leaves color
				1f, 0f, 0f //12: apple color
		};
		this.tree = new GTree(treeVertex2f);

		float[] engineVertex2f = new float[] {
				-6f, -38.2f, 0f, //0: location of engine body
				25f, 15f, 1f,  //3: scaling factor
				0f, 0f, 1f, // 6: train body color
				1f, 0f, 0f, // 9: window color
				0f, 0f, 0f, // 12: front color
				0.545f, 0.271f, 0.075f // 15: wagon color
		};
		float trainSpeed = 0.25f;
		this.train = new GTrain(engineVertex2f, DRAWING_WIDTH / 2, trainSpeed);

		float[] rocketVertex2f = new float[] {
				30f, -25f, 0f, //0: location of rocket
				3f, 10f, 1f, //3: scaling of rocket
				1f, 1f, 1f, //6: color of rocket body
				1f, 0f, 0f, //9: color of top
				0f, 0f, 0f // 12: color of smoke
		};
		float rocketSpeed = 1.5f;
		this.rocket = new GRocket(rocketVertex2f, DRAWING_HEIGHT, rocketSpeed);

		float[] planeVertex2f = new float[] {
				-10f, 30f, 0f, //0: location of plane
				15f, 3.5f, 1f, //3: scaling of plane
				0.6f, 0.6f, 0.6f, //6: color of plane body
				0.45f, 0.45f, 0.45f, //9: color of dark grey
				0.2f, 0.2f, 0.9f // 12: color of blue wing
		};
		float planeSpeed = 0.8f;
		this.plane = new GPlane(planeVertex2f, DRAWING_WIDTH, planeSpeed);

		float[] plane2Vertex2f = new float[] {
				-40f, 43f, 0f, //0: location of plane
				10f, 2.3f, 1f, //3: scaling of plane
				0.6f, 0.6f, 0.6f, //6: color of plane body
				0.45f, 0.45f, 0.45f, //9: color of dark grey
				0.5f, 0.5f, 0.9f // 12: color of blue wing
		};
		float plane2Speed = 0.4f;
		this.plane2 = new GPlane(plane2Vertex2f, DRAWING_WIDTH, plane2Speed);

		float[] windmillVertex2f = new float[] {
				18f, 20.5f, 1.2f, //0: location of pole
				1.5f, 24f, 1f, //3: scaling
				1f, 1f, 1f, // 6: color of pole
		};
		this.windmill = new GWindmill(windmillVertex2f);

		float[] windmill2Vertex2f = new float[] {
				38f, 6f, 1.2f, //0: location of pole
				0.7f, 15f, 1f, //3: scaling
				1f, 1f, 1f, // 6: color of pole
		};
		this.windmill2 = new GWindmill(windmill2Vertex2f);

		// adding them all in the arrayList
		drawingObjects = new ArrayList<GShape>();

		drawingObjects.add(this.grass);
		drawingObjects.add(this.sun);
		drawingObjects.add(this.cloud1);
		drawingObjects.add(this.plane);
		drawingObjects.add(this.plane2);
		drawingObjects.add(this.cloud2);
		drawingObjects.add(this.house);
		drawingObjects.add(this.tracks);
		drawingObjects.add(this.tree);

		drawingObjects.add(this.windmill);
		drawingObjects.add(this.windmill2);
		drawingObjects.add(this.train);
		drawingObjects.add(this.rocket);

	}


	/**
	 * Call-back handler for window re-size event. Also called when the drawable is
	 * first set to visible.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context

		// Have to add this in order for the canvas to properly scale in the window
		// Found at https://forum.jogamp.org/canvas-not-filling-frame-td4040092.html
		double dpiScalingFactorX = ((Graphics2D) getGraphics()).getTransform().getScaleX();
		double dpiScalingFactorY = ((Graphics2D) getGraphics()).getTransform().getScaleY();
		width = (int) (width / dpiScalingFactorX);
		height = (int) (height / dpiScalingFactorY);
		System.out.println(width + ":" + height);

		GLUTCanvas.CANVAS_HEIGHT = height;
		GLUTCanvas.CANVAS_WIDTH = width;

		// we want this aspect ratio in our drawing
		float target_aspect = DRAWING_WIDTH / DRAWING_HEIGHT;

		if (height < 1)
			height = 1;
		// this is the new aspect ratio based on the resize
		float calc_aspect = (float) width / (float) height;

		float aspect = calc_aspect / target_aspect;

		if (calc_aspect >= target_aspect) {
			GL_Width = DRAWING_WIDTH / 2.0f * aspect;
			GL_Height = DRAWING_HEIGHT / 2.0f;
		} else {
			GL_Width = DRAWING_WIDTH / 2.0f;
			GL_Height = DRAWING_HEIGHT / 2.0f / aspect;
		}

		//myOrigin.updateOriginVertex(GLUTCanvas.GL_Width, GLUTCanvas.GL_Height);

		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		gl.glOrtho(-GL_Width, GL_Width, -GL_Height, GL_Height, -2.0f, 2.0f); // 2D

		gl.glViewport(0, 0, (int) GL_Width * 2, -(int) GL_Height * 2);

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW); // specify coordinates
		gl.glLoadIdentity(); // reset

	}


	/**
	 * Called back by the animator to perform rendering.
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context
		gl.glClear(GL_COLOR_BUFFER_BIT); // clear the canvas with color
		gl.glLoadIdentity(); // reset the model-view matrix

		gl.glTranslatef(0.0f, 0.0f, 0.0f); // translate into the screen


		for (GShape artObject : drawingObjects) {
			artObject.render(gl);
		}

		gl.glFlush();
	}

	/**
	 * Called back before the OpenGL context is destroyed. Release resource such as
	 * buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}
}