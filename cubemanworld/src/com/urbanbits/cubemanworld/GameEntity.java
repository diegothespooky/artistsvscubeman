package com.urbanbits.cubemanworld;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class GameEntity {
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	
	int intWidth;
	int intHeight;
	
	int intX;
	int intY;
	
	Bitmap bmpSprite;
	
	Rect rectActual;
	Rect rectLayout;
	
	public abstract Rect getRectActual();
	public abstract Rect getRectLayout();
	
	public int getX(){
		return intX;
	}
	
	public int getY(){
		return intY;
	}
	
	public Bitmap getBitmap(){
		return bmpSprite;
	}
}
