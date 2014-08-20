package com.urbanbits.cubemanworld;

import java.util.ArrayList;

import android.content.Context;

public class StageEntityManager {
	
	Context context;
	StageLevel stageLevel;
	GameItemSizes gameItemSizes;
	
	Character player1;
	Character player2;
	
	Action actionPlayer1;
	Action actionPlayer2;
	
	
	ArrayList<Action> actionStack;
	//ArrayList<Action> nextStack;
	
	
	StageEntityManager(Context context,StageLevel stageLevel,GameItemSizes gameItemSizes){
		this.stageLevel = stageLevel;
		this.context = context;
		actionStack = new ArrayList<Action>();
		this.gameItemSizes = gameItemSizes;
	}
	
	public void setTestEntities(){
		player1 = new Character(context,gameItemSizes.intCharWidth,gameItemSizes.intCharHeight,stageLevel.getStartPositonX(),stageLevel.getStartPositionY(),true);
	}

	
	public void solveStack(){
		for(Action action : actionStack){
			action.execute();
		}
	}

	public void solveReactions(){
		player1.updateLayout();
		//player2.updateLayout();
		
		actionStack.clear();
		
		//modify for: n players, priority turn
		if(actionPlayer1 != null){
			if(!actionPlayer1.hasFinished){
				actionStack.add(actionPlayer1);
			}else{
				actionPlayer1 = null;
			}
		}
		if(actionPlayer2 != null){
			if(!actionPlayer2.hasFinished){
				actionStack.add(actionPlayer2);
			}else{
				actionPlayer2 = null;
			}
		}
	}

	public void movePlayer1(int eventX, int eventY) {
		int fixedX = eventX-8*GameItemSizes.sizeMultiplicator;
		int fixedY = eventY-27*GameItemSizes.sizeMultiplicator;
		fixedX = fixedX - (fixedX%GameItemSizes.playerStepSize);
		fixedY = fixedY - (fixedY%GameItemSizes.playerStepSize);
		
		actionPlayer1 = new ActionMove(player1,
										fixedX,
										fixedY);
	    player1.resetPath();
		
	}

	
}
