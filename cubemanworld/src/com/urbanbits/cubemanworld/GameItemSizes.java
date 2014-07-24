package com.urbanbits.cubemanworld;

import android.graphics.Rect;

public class GameItemSizes {
	Rect rectStage;
	
	
	public GameItemSizes(){
		rectStage = new Rect(0,0,192,108);
	}
	

	public Rect getRectStage() {
		return rectStage;
	}

	public void setRectStage(Rect rectStage) {
		this.rectStage = rectStage;
	}
	
	
	
}
