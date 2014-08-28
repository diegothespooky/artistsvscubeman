package com.urbanbits.cubemanworld;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class GameEntity {
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int ABS_UP = 4;
	public static final int ABS_RIGHT = 5;
	public static final int ABS_DOWN = 6;
	public static final int ABS_LEFT = 7;
	
	static final int intMovingAddX[] = {2,2,-2,-2,0,2,0,-2};
	static final int intMovingAddY[] = {-1,1,1,-1,-1,0,1,0};
	
	static final int intMovingUp[][] = {{0,0},{16,0},{32,0},{16,0}};
	static final int intMovingRight[][] = {{48,0},{64,0},{80,0},{64,0}};
	static final int intMovingDown[][] = {{48,32},{64,32},{80,32},{64,32}};
	static final int intMovingLeft[][] =  {{0,32},{16,32},{32,32},{16,32}};
	static final int intMovingIndex[][][][] = {
										{//is moving left
											intMovingLeft,intMovingLeft,intMovingDown
										},
										{//is up or down
											intMovingUp,intMovingUp,intMovingDown
										},
										{//is moving right
											intMovingUp,intMovingRight,intMovingRight
										}
									   };
										//moving up, left or right, moving down
	
	
	
	int intWidth;
	int intHeight;
	int intX;
	int intY;
	int intX2;
	int intY2;
	
	
	Bitmap bmpSprite;
	
	Rect rectActual;
	Rect rectLayout;
	
	
	public int getX() {
		return intX;
	}
	public void setX(int intX) {
		this.intX = intX;
	}
	public int getY() {
		return intY;
	}
	public void setY(int intY) {
		this.intY = intY;
	}
	
	
	
	public Bitmap getBitmap(){
		return bmpSprite;
	}
	
	public Rect getRectActual(){

		return rectActual;
	}
	
	public Rect getRectLayout(){
		return rectLayout;
	}
	
	public void updateLayout(){
		rectLayout.set(intX,intY,intX2,intY2);
	}

	
	public abstract void setPath(int toX, int toY);
	public abstract boolean move();
	
}
