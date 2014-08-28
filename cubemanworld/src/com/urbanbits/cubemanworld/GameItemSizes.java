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
	
	int intStageX;
	int intStageY;
	final int[][][] realCellCoords = {
			{{80,8},{96,16},{112,24},{128,32},{144,40}},
			{{64,16},{80,24},{96,32},{112,40},{128,48}},
			{{48,24},{64,32},{80,40},{96,48},{112,56}},
			{{32,32},{48,40},{64,48},{80,56},{96,64}},
			{{16,40},{32,48},{48,56},{64,64},{80,72}},
		 };
	int[][][] stageCellCoords = new int[5][5][2];
	int[][][] stageEntityPosition = new int[5][5][2];
	
	public GameItemSizes(int canvasWidth, int canvasHeight){
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		if(canvasWidth>=320){
			if(canvasHeight>=184){
				sizeMultiplicator = 2;
				playerStepSize = 2;
				rectStage = new Rect(0,30,320,214);		
			}
		} else {
			//Unsupported
		}
		intStageX=0;
		intStageY=30;
		rectStage = new Rect(intStageX,intStageY,320,214);		
		intCharWidth*=sizeMultiplicator;
		intCharHeight*=sizeMultiplicator;
		platformHeight*=sizeMultiplicator;
		platformWidth*=sizeMultiplicator;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				stageCellCoords[i][j][0] = (realCellCoords[i][j][0]*sizeMultiplicator)+intStageX;
				stageCellCoords[i][j][1] = (realCellCoords[i][j][1]*sizeMultiplicator)+intStageY;
				stageEntityPosition[i][j][0] = stageCellCoords[i][j][0]-8*GameItemSizes.sizeMultiplicator;;
				stageEntityPosition[i][j][1] = stageCellCoords[i][j][1]-28*GameItemSizes.sizeMultiplicator;;
			}
		}
	}
	

	public Rect getRectStage() {
		return rectStage;
	}

	public void setRectStage(Rect rectStage) {
		this.rectStage = rectStage;
	}
	
	
	
}
