package com.urbanbits.cubemanworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Character extends GameEntity {
	
	Context context;
	
	protected int intMoving; /*    1
					   402 
					   3     */
	protected int intMovingPhase = 0;
	final int intMovingIndexX[][] = {{64,48,64,80,64},
										{16,0,16,32,16},
										{64,48,64,80,64},
										{16,0,16,32,16},
										{64,48,64,80,64}};
	final int intMovingIndexY[] = {32,0,0,32,32};
	final int intMovingAddX[] = {0,4,4,-4,-4};
	final int intMovingAddY[] = {0,-2,2,2,-2};
	//final int intMovingRealIndex[] = {};
	
	
	public Character(){
		
	}
	
	public Character(Context context,int startX, int startY,boolean premade){
		intWidth = 16;
		intHeight = 32;
		bmpSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.dodo);
		System.out.println(bmpSprite.getHeight()+""+bmpSprite.getWidth());
		rectActual = new Rect(intWidth,intHeight,intWidth+intWidth,intHeight+intHeight);
		rectLayout = new Rect(startX,startY,startX+intWidth,startY+intHeight);
	}
	

	public void move(int intDirection){
		intMovingPhase++;
		rectActual.set(intMovingIndexX[intDirection][intMovingPhase],intMovingIndexY[intDirection],intMovingIndexX[intDirection][intMovingPhase]+intWidth,intMovingIndexY[intDirection]+intHeight);
		rectLayout.set((rectLayout.left+intMovingAddX[intDirection]),(rectLayout.top+intMovingAddY[intDirection]),(rectLayout.right+intMovingAddX[intDirection]),(rectLayout.bottom+intMovingAddY[intDirection]));
	}

	public Rect getRectActual(){
		/*int indexX = 1;
		int indexY = 1;
		
		rectActual.set(intWidth*indexX, intHeight*indexY, intWidth, intHeight);
		*/
		return rectActual;
	}
	
	public Rect getRectLayout(){
		
		return rectLayout;
	}
	
	
	
	
}
