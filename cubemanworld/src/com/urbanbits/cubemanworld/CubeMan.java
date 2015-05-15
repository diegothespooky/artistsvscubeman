package com.urbanbits.cubemanworld;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class CubeMan extends GameEntity {
    
	public static final int[][] statingCoords = {{0,0,4,0},
												 {0,1,4,1},
												 {0,2,4,2},
												 {0,3,4,3},
												 {0,4,4,4},
												 {4,0,0,0},
												 {4,1,0,1},
												 {4,2,0,2},
												 {4,3,0,3},
												 {4,4,0,4},
												 {0,0,0,4},
												 {1,0,1,4},
												 {2,0,2,4},
												 {3,0,3,4},
												 {4,0,4,4},
												 {0,4,0,0},
												 {1,4,1,0},
												 {2,4,2,0},
												 {3,4,3,0},
												 {4,4,4,0},
												};
	
	protected int intMovingPhase = 0;
	
	public boolean isMoving;
	public boolean isJumping = false;
	public boolean isDisapearing = false;
	
	
	public CubeMan (Context context,int intStartX, int intStartY, int intColor){
		intWidth = 16;
		intHeight = 32;
		intX = intStartX;
		intY = intStartY;
		intX2 = intStartX+intWidth*GameItemSizes.sizeMultiplicator;
		intY2 = intStartY+intHeight*GameItemSizes.sizeMultiplicator;
		bmpSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.cubeman);
		rectActual = new Rect(intWidth,intHeight,intWidth+intWidth,intHeight+intHeight);
		rectLayout = new Rect(intX,intY,intX2,intY2);
	}
	
	@Override
	public Rect getRectActual() {
		// TODO Auto-generated method stub
		return rectActual;
	}

	@Override
	public Rect getRectLayout() {
		// TODO Auto-generated method stub
		return rectLayout;
	}
	
	int targetX;
	int targetY;

	int intGoingRight;
	int intGoingDown;
	int intDirectionX;
	int intDirectionY;
	
	
	int intAddX;
	int intAddY;

	public void resetPath(){
		setPath(targetX,targetY);		
	}
	
	public void setPath(int toX, int toY) {
		targetX = toX;
		targetY = toY;
		
		intGoingRight = targetX - intX;
		intGoingDown =  targetY - intY;
		
		intDirectionX = (int)Math.signum(intGoingRight);
		intDirectionY = (int)Math.signum(intGoingDown);
		
		intAddX = intDirectionX*GameItemSizes.playerStepSize*2;
		intAddY = intDirectionY*GameItemSizes.playerStepSize;
		isMoving = true;
	}
	
	int intMovinPhases[] = {0,0,0,1,1,1,2,2,2,3,3,3}; 
	
	public boolean move(){
		int intLayoutX1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][0];
		int intLayoutY1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][1];
		int intLayoutX2 = intLayoutX1+intWidth;
		int intLayoutY2 = intLayoutY1+intHeight;
		
		rectActual.set(intLayoutX1,intLayoutY1,intLayoutX2,intLayoutY2);
	
		intX += intAddX;
		intY += intAddY;
		intX2 = intX+intWidth*GameItemSizes.sizeMultiplicator;
		intY2 = intY+intHeight*GameItemSizes.sizeMultiplicator;
				
		intMovingPhase++;
		if(intMovingPhase >= 12){
			intMovingPhase = 0;
		}
		
		if(targetX == intX && targetY == intY){
			intMovingPhase = 3;
			intLayoutX1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][0];
			intLayoutY1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][1];
			intLayoutX2 = intLayoutX1+intWidth;
			intLayoutY2 = intLayoutY1+intHeight;
			
			rectActual.set(intLayoutX1,intLayoutY1,intLayoutX2,intLayoutY2);
			isMoving = false;
			isJumping = true;
			return false;
		}
		
		return true;
	}
	
	public void jump(){
		isDisapearing = true;
	}

}
