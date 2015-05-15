package com.urbanbits.cubemanworld;

public class ActionCubeManMove extends Action{

	GameEntity target;
	int toX;
	int toY;
	boolean moveNow;

	public ActionCubeManMove(GameEntity target, int toX, int toY) {
		super();
		this.target = target;
		this.toX = toX;
		this.toY = toY;
		target.setPath(toX,toY);
		moveNow = true;
	}

	@Override
	public void execute() {
		if(moveNow){
			if(!target.move()){
				hasFinished = true;
				StageEntityManager.instance.entities.remove(target);
				StageEntityManager.instance.cubeMen.remove(target);
				
			};			
		}
		moveNow=!moveNow;
		
	}
	

}
