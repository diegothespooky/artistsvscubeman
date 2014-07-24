package com.urbanbits.cubemanworld;

import android.content.Context;

public class StageEntityManager {
	Character player1;
	Context context;
	StageLevel stageLevel;
	
	
	StageEntityManager(Context context,StageLevel stageLevel){
		this.stageLevel = stageLevel;
		this.context = context;
		
	}
	
	public void setTestEntities(){
		player1 = new Character(context,stageLevel.getStartPositonX(),stageLevel.getStartPositionY(),true);
	}
	
	//
	public boolean movePlayer1(int intDirection){
		player1.move(intDirection);
		if(player1.intMovingPhase == 0 || player1.intMovingPhase == 4 ){
			player1.intMovingPhase = 0;
		}
		return true;
	}
	
}
