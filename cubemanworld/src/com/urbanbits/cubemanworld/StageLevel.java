package com.urbanbits.cubemanworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class StageLevel {
	Context context;
	GameItemSizes gameItemSizes;
	
	int[][] currentMap;
	int[][] targetMap;
	
	int intStartX;
	int intStartY;
	

	Bitmap bmpPlatform;
	
	
	public StageLevel(Context context,GameItemSizes gameItemSizes){
		this.gameItemSizes = gameItemSizes;
		this.context = context;
	}
	
	
	public void loadPlatform(){
		bmpPlatform =  BitmapFactory.decodeResource(context.getResources(), R.drawable.platform25);
		
		intStartX = 160;
		intStartY = 112;
		
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


	public void drawStage(Canvas canvas,GameItemSizes gameItemSizes) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
		canvas.drawRect(new Rect(0,0,gameItemSizes.canvasWidth,gameItemSizes.canvasHeight),myPaint);
		canvas.drawBitmap(bmpPlatform,null,gameItemSizes.getRectStage(),null);
		
	}
}
