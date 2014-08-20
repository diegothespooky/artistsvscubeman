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
	
	int intWidth;
	int intHeight;
	int intX;
	int intY;
	int intX2;
	int intY2;
	
	
	Bitmap bmpSprite;
	
	Rect rectActual;
	Rect rectLayout;
	
	public abstract Rect getRectActual();
	public abstract Rect getRectLayout();
	
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
	
	
}
