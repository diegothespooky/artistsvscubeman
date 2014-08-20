package com.urbanbits.cubemanworld;

import android.graphics.LinearGradient;
import android.graphics.Rect;


public class GameItemSizes {
	Rect rectStage; 
	
	int canvasWidth;
	int canvasHeight;
		
	int platformWidth = 160;
	int platformHeight = 92;
	
	static int sizeMultiplicator = 2;
	static int playerStepSize = 2;
	
	int intCharWidth = 16;
	int intCharHeight = 32;
	
	public GameItemSizes(int canvasWidth, int canvasHeight){
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		if(canvasWidth>=320){
			if(canvasHeight>=184){
				sizeMultiplicator = 2;
				playerStepSize = 4;
				rectStage = new Rect(0,30,320,214);		
			}
		} else {
			//Unsupported
		}
		rectStage = new Rect(0,30,320,214);		
		intCharWidth*=sizeMultiplicator;
		intCharHeight*=sizeMultiplicator;
		platformHeight*=sizeMultiplicator;
		platformWidth*=sizeMultiplicator;
	}
	

	public Rect getRectStage() {
		return rectStage;
	}

	public void setRectStage(Rect rectStage) {
		this.rectStage = rectStage;
	}
	
	
	
}
