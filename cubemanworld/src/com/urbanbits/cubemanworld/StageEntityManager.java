package com.urbanbits.cubemanworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;

public class StageEntityManager {
	
	Context context;
	StageLevel stageLevel;
	GameItemSizes gameItemSizes;
	
	Character player1;
	Character player2;
	
	PlayerAction actionPlayer1;
	
	
	ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	ArrayList<CubeMan> cubeMen = new ArrayList<CubeMan>();
	
	ArrayList<Action> actionStack;
	public static StageEntityManager instance;
	//ArrayList<Action> nextStack;
	
	
	StageEntityManager(Context context,StageLevel stageLevel,GameItemSizes gameItemSizes){
		this.stageLevel = stageLevel;
		this.context = context;
		actionStack = new ArrayList<Action>();
		finishedActions = new ArrayList<Action>();
		this.gameItemSizes = gameItemSizes;
		instance = this;
	}
	
	public void setTestEntities(){
		player1 = new Character(context,gameItemSizes.intCharWidth,gameItemSizes.intCharHeight,stageLevel.getStartPositonX(),stageLevel.getStartPositionY(),true);
		entities.add(player1);
	}
	
	public void spawnCubeMan(){
		int place = (int) Math.floor(Math.random()*20);
		int intStartX = CubeMan.statingCoords[place][0];
		int intStartY = CubeMan.statingCoords[place][1];
		int intEndX = CubeMan.statingCoords[place][2];
		int intEndY = CubeMan.statingCoords[place][3];
		
		
		CubeMan cubeMan = new CubeMan(context,gameItemSizes.stageEntityPosition[intStartX][intStartY][0],gameItemSizes.stageEntityPosition[intStartX][intStartY][1],0);
		entities.add(cubeMan);
		cubeMen.add(cubeMan);
		ActionCubeManMove startWalkingCubeMan = new ActionCubeManMove(cubeMan,gameItemSizes.stageEntityPosition[intEndX][intEndY][0],gameItemSizes.stageEntityPosition[intEndX][intEndY][1]);
		cubeMan.actions.add(startWalkingCubeMan);
		
		//-64,+32
	}
	
	ArrayList<Action> finishedActions;
	
	public void solveStack(){
		if(actionPlayer1!=null){
			switch (actionPlayer1.actionType){
				case PLAYERMOVE :{
						player1.setPath(actionPlayer1.toX, actionPlayer1.toY);
					break;
				}
			}
			actionPlayer1 = null;
		}
		
		if(player1.isMoving){
			player1.move();
		}
		
		Iterator<CubeMan> cubeManIterator = cubeMen.iterator();
		while(cubeManIterator.hasNext()){
			CubeMan cubeMan = cubeManIterator.next();
			if(cubeMan.isMoving){
				cubeMan.move();
			} if(cubeMan.isJumping){
				cubeMan.jump();
			} if (cubeMan.isDisapearing){
				cubeManIterator.remove();
			}
		}
		
		/*for(GameEntity entity : entities){
			int intActions = entity.actions.size();
			for(int i=0; i<entity.actions.size(); ){
				Action action = entity.actions.get(i);
				if(!action.hasFinished){
					action.execute();
					i++;
				}else{
					entity.actions.remove(action);
					
				}
			}		
		} */
		/*for(Action action : actionStack){
			if(!action.hasFinished){
				action.execute();
			}else{
				finishedActions.add(action);
			}
		}
		if(actionPlayer1!=null){
			if(!actionPlayer1.hasFinished){
				actionPlayer1.execute();
			} else {
				actionPlayer1 = null;
			}
		}
		
		if(finishedActions.size()>10){
			actionStack.removeAll(finishedActions);
			finishedActions.clear();
		}*/
		
	}

	
	public void movePlayer1(int eventX, int eventY) {
		int fixedX = eventX-8*GameItemSizes.sizeMultiplicator;
		int fixedY = eventY-27*GameItemSizes.sizeMultiplicator;
		fixedX = fixedX - (fixedX%GameItemSizes.playerStepSize);
		fixedY = fixedY - (fixedY%GameItemSizes.playerStepSize);
		
		actionPlayer1 = new PlayerAction(fixedX,fixedY);
		actionPlayer1.actionType = ActionType.PLAYERMOVE;
		
	}

	public void drawEntities(Canvas canvas, int canvasWidth, int canvasHeight) {
		for(GameEntity mob : entities){
			mob.updateLayout();
			canvas.drawBitmap(mob.getBitmap(), mob.rectActual, mob.getRectLayout(),null);
		} 
    }

	public void solveReactions(){
		/*
		 *          long start = System.nanoTime();
			        Collections.sort(nums);
			        long end = System.nanoTime();
			
			        System.out.println((end-start)/1e9);
		 */
		//this could be in the draw method for
		//player1.updateLayout();
		Collections.sort(entities,new GameEntity.YComparator());

	}
	
	
	public void checkAllCollisions(){
		for(GameEntity thisMob : entities){
			for(GameEntity targetMob : entities){
				if(thisMob==targetMob){
					continue;
				}
				
			}
		}
	}
	
	public void collisionPlayerAndCubeMan(){
		for(GameEntity mob : cubeMen){
			//if()
		}
	}
}
