package com.urbanbits.cubemanworld;

public class ActionCubeManJump extends Action{

	CubeMan target;
	int toX;
	int toY;
	boolean moveNow;

	public ActionCubeManJump(CubeMan target, int toX, int toY) {
		super();
		this.target = target;
		this.toX = toX;
		this.toY = toY;
		target.setPath(toX,toY);
		moveNow = true;
	}

	@Override
	public void execute() {
		
		StageEntityManager.instance.cubeMen.remove(this);
		StageEntityManager.instance.entities.remove(this);
	}
	

}
