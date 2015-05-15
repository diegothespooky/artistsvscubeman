package com.urbanbits.cubemanworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Character extends GameEntity {
	
	Context context;
	
	//border color 101008
	//shadow color 1a1a1a
	
	protected int intMoving; /*     0
								   3.1 
								   2     */
	protected int intMovingPhase = 0;

	
	public boolean isMoving;
	public boolean isPainting;
	

	public Character(){
		
	}
	
	public Character(Context context,int intWidth,int intHeight,int startX, int startY,boolean premade){
		this.intWidth = 16;
		this.intHeight = 32;
		intX = startX;
		intY = startY;
		intX2 = startX+16*GameItemSizes.sizeMultiplicator;
		intY2 = startY+32*GameItemSizes.sizeMultiplicator;
		bmpSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.dodofinal);
		rectActual = new Rect(16,32,16+16,32+32);
		rectLayout = new Rect(intX,intY,intX2,intY2);
	}
	
	int targetX;
	int targetY;

	int intGoingRight;
	int intGoingDown;
	int intDirectionX;
	int intDirectionY;
	
	int intStepX;
	int intStepY;
	
	int intAddX;
	int intAddY;
	
	
	float floatDeltaError;
	float floatError;
	
	boolean isLowPendant;
	boolean isNinetyDegrees;
	
	
	public void setPath(int toX, int toY) {
		targetX = toX;
		targetY = toY;
		
		intGoingRight = targetX - intX;
		intGoingDown =  targetY - intY;
		
		intDirectionX = (int)Math.signum(intGoingRight);
		intDirectionY = (int)Math.signum(intGoingDown);
		
		intStepX = intDirectionX*GameItemSizes.playerStepSize;
		intStepY = intDirectionY*GameItemSizes.playerStepSize;
		
		int intDeltaX = Math.abs(intGoingRight);
		int intDeltaY = Math.abs(intGoingDown);
		isLowPendant = (intDeltaX > intDeltaY);
		isNinetyDegrees = (intDeltaX == intDeltaY);
		floatError = 0;
		if(isNinetyDegrees){
			floatDeltaError=0;
			intAddX = intStepX;
			intAddY = intStepY;
		} else if(isLowPendant){
			floatDeltaError = (float)intDeltaY/intDeltaX;
			intAddX = intStepX;
			intAddY = 0;
		} else {
			floatDeltaError = (float)intDeltaX/intDeltaY;
			intAddY = intStepY;
			intAddX = 0;
		}
		
		isMoving = true;
	}
	
	int intMovinPhases[] = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3}; 
	
	public boolean move(){
		int intLayoutX1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][0];
		int intLayoutY1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][1];
		int intLayoutX2 = intLayoutX1+intWidth;
		int intLayoutY2 = intLayoutY1+intHeight;
		
		rectActual.set(intLayoutX1,intLayoutY1,intLayoutX2,intLayoutY2);
	
		
		if(intDirectionX !=0 && intDirectionY !=0){
			floatError += floatDeltaError;
			if(isLowPendant){
				if(floatError>=0.5){
					intAddY = intStepY;
					floatError -= 1;
				} else {
					intAddY = 0;
				}
			} else {
				intAddY = intStepY;
				if(floatError>=0.5){
					intAddX = intStepX;
					floatError -= 1;
				} else {
					intAddX = 0;
				} 
			}
			
		}
		
		intX += intAddX;
		intY += intAddY;
		intX2 = intX+intWidth*GameItemSizes.sizeMultiplicator;
		intY2 = intY+intHeight*GameItemSizes.sizeMultiplicator;
				
		intMovingPhase++;
		if(intMovingPhase >= 16){
			intMovingPhase = 0;
		}
		
		if(targetX == intX && targetY == intY){
			stopMoving();
			return false;
		}
		
		return true;
	}
	
	public void stopMoving(){
		intMovingPhase = 4;
		int intLayoutX1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][0];
		int intLayoutY1 = intMovingIndex[intDirectionX+1][intDirectionY+1][intMovinPhases[intMovingPhase]][1];
		int intLayoutX2 = intLayoutX1+intWidth;
		int intLayoutY2 = intLayoutY1+intHeight;
		
		rectActual.set(intLayoutX1,intLayoutY1,intLayoutX2,intLayoutY2);
		isMoving = false;
	}
	
	
	
}
