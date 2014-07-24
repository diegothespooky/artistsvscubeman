package com.urbanbits.cubemanworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class StageLevel {
	Context context;
	
	int[][] currentMap;
	int[][] targetMap;
	
	int intStartX;
	int intStartY;
	

	Bitmap bmpPlatform;
	
	
	public StageLevel(Context context){
		this.context = context;
	}
	
	
	public void loadPlatform(){
		bmpPlatform =  BitmapFactory.decodeResource(context.getResources(), R.drawable.platform32);
		intStartX = 104;
		intStartY = 20;
	}
	
	public Bitmap getPlatform(){
		return bmpPlatform;
	}
	
	
	public int getStartPositonX() {
		return intStartX;
	}

	public int getStartPositionY() {
		return intStartY;
	}
}
