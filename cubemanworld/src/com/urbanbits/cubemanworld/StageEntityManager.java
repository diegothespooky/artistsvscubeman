package com.urbanbits.cubemanworld;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;

public class StageEntityManager {
	
	Context context;
	StageLevel stageLevel;
	GameItemSizes gameItemSizes;
	
	Character player1;
	Character player2;
	
	Action actionPlayer1;
	Action actionPlayer2;
	
	ArrayList<GameEntity> mobs = new ArrayList<GameEntity>();
	
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

	public void spawnCubeMan(){
		CubeMan cubeMan = new CubeMan(context,gameItemSizes.stageEntityPosition[0][1][0],gameItemSizes.stageEntityPosition[0][1][1],0);
		mobs.add(cubeMan);
		ActionMove startWalkingCubeMan = new ActionMove(cubeMan,cubeMan.intX-64,cubeMan.intY+32);
		actionStack.add(startWalkingCubeMan);
		
		//-64,+32
	}
	
	public void solveStack(){
		for(Action action : actionStack){
			if(!action.hasFinished){
				action.execute();
			} else {
				actionStack.remove(action);
			}
		}
		if(actionPlayer1!=null){
			if(!actionPlayer1.hasFinished){
				actionPlayer1.execute();
			} else {
				actionPlayer1 = null;
			}
		}
		
	}

	public void solveReactions(){
		//this could be in the draw method for
		player1.updateLayout();
		for(GameEntity mob : mobs){
			mob.updateLayout();
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

	public void drawEntities(Canvas canvas, int canvasWidth, int canvasHeight) {
		canvas.drawBitmap(player1.getBitmap(),player1.rectActual,player1.getRectLayout(),null);
		for(GameEntity mob : mobs){
			canvas.drawBitmap(mob.getBitmap(), mob.rectActual, mob.getRectLayout(),null);
		} 
    }

	
}
