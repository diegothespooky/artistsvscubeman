package com.urbanbits.cubemanworld;

public class ActionMove extends Action{

	GameEntity target;
	int toX;
	int toY;
	

	public ActionMove(GameEntity target, int toX, int toY) {
		super();
		this.target = target;
		this.toX = toX;
		this.toY = toY;
		target.setPath(toX,toY);
	}

	@Override
	public void execute() {
		if(!target.move()){
			hasFinished = true;
		};
	}
	

}
