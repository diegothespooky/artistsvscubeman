package com.urbanbits.cubemanworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameRunView  extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener{
	  MainThread mainThread;
	  StageLevel stageLevel;
	  GameItemSizes gameItemSizes;
	  Character chrPlayer;
	  StageEntityManager stageEntityManager;
	  
	
	  public GameRunView (Context context) {
	    super(context);
	    
	    getHolder().addCallback(this);
	    
	    
	    //getHolder().setFixedSize(193, 108);v
	    
	    stageLevel = new StageLevel(context);
	    gameItemSizes = new GameItemSizes();
	    stageEntityManager = new StageEntityManager(context, stageLevel);
	    //chrPlayer = new Character(context);
	    
	  }


	  class MainThread extends Thread {
		     
	        private SurfaceHolder surfaceHolder;
	        private boolean runFlag = false;
	        boolean firstTime = true;
	 
	        public MainThread(SurfaceHolder surfaceHolder) {
	            this.surfaceHolder = surfaceHolder;
	        }
	 
	        public void setRunning(boolean run) {
	            this.runFlag = run;
	        }
	        
	        @Override
	        public void run() {
	            Canvas canvas;
	            
	            Paint myPaint = new Paint();
	            myPaint.setColor(Color.rgb(255, 0, 0));
	            myPaint.setStrokeWidth(2);
	            
	            //> some of these are for tests
	            
	            int a=0;
	            
	            stageLevel.loadPlatform();
	            stageEntityManager.setTestEntities();
	            
	            while (this.runFlag) {
	             
	             if(firstTime){
	                     firstTime = false;
	                     continue;
	                 }
	             
	                canvas = null;
	                
	                try {
	                   
	                    canvas = this.surfaceHolder.lockCanvas(null);
	                    synchronized (this.surfaceHolder) {
	                    	/*
		                        canvas.drawRect(new Rect(x1,y1,x2,y2),myPaint);
	                    	*/
	                    	
	                    	
	                    	canvas.drawBitmap(stageLevel.getPlatform(),null,gameItemSizes.getRectStage(),null);
	                    	canvas.drawBitmap(stageEntityManager.player1.getBitmap(),stageEntityManager.player1.rectActual,stageEntityManager.player1.getRectLayout(),null);
	                    	//canvas.drawLine(190, 0, 190, 15, myPaint);
	                    	//canvas.drawPoint(, y, paint);
	                    	//System.out.println(canvas.getWidth()+","+canvas.getHeight());
	                    	
	                    	if(a>25 && a<30){
	                    		stageEntityManager.movePlayer1(GameEntity.UP);
	                    	} else if(a>10 && a<15){
	                    		stageEntityManager.movePlayer1(GameEntity.DOWN);
	                    	} else if(a>15 && a<20){
	                    		stageEntityManager.movePlayer1(GameEntity.LEFT);
	                    	} else if(a>20 && a<25){
	                    		stageEntityManager.movePlayer1(GameEntity.RIGHT);
	                    	}
	                    	
	                    	a++;
	                    	
	                    }
	                } finally {
	                   
	                    if (canvas != null) {
	                        this.surfaceHolder.unlockCanvasAndPost(canvas);
	                    }
	                }
	                try {
						sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
	                
	                
	                
	            }
	        }
	        
	    }

	  

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas = null;
	    try {
            canvas = getHolder().lockCanvas(null);
            System.out.println(canvas.getWidth()+","+canvas.getHeight()+",");
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
		
		mainThread = new MainThread(getHolder()); 
		mainThread.setRunning(true);
		mainThread.start();
        setOnTouchListener(this);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			System.out.println("destroying surface");
			mainThread.setRunning(false);
			mainThread.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float eventX = event.getX();
	    float eventY = event.getY();

	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	      //path.moveTo(eventX, eventY);
	      return true;
	    case MotionEvent.ACTION_MOVE:
	      //path.lineTo(eventX, eventY);
	      break;
	    case MotionEvent.ACTION_UP:
	      // nothing to do
	      break;
	    default:
	      return false;
	    }
		return false;
	}
	
	}