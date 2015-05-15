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
	  double actualFrame;
	  
	  int canvasHeight;
	  int canvasWidth;
	  
	
	  public GameRunView (Context context) {
	    super(context);
	    getHolder().addCallback(this);
	    System.out.println("GameRunView Created");
	   }


	  class MainThread extends Thread {
		    Context context;
	        private SurfaceHolder surfaceHolder;
	        private boolean runFlag = false;
	        boolean firstTime = true;
	        
	        
	        public MainThread(SurfaceHolder surfaceHolder,Context context) {
	            this.surfaceHolder = surfaceHolder;
	            this.context = context;
	        }
	 
	        public void setRunning(boolean run) {
	            this.runFlag = run;
	        }
	        
	        @Override
	        public void run() {
	        	Canvas canvas;
	            gameItemSizes = new GameItemSizes(canvasHeight,canvasWidth);
	            stageLevel = new StageLevel(context,gameItemSizes);
	            stageEntityManager = new StageEntityManager(context, stageLevel,gameItemSizes);
	            
	            stageLevel.loadPlatform();
	            stageEntityManager.setTestEntities();
	            

	            
	            while (this.runFlag) {
	             
	             if(firstTime){
	                     firstTime = false;
	                     continue;
	                 }
	             
	                canvas = null;
	                
	                try {
	                    
	                	if(actualFrame%60==0){
	                		stageEntityManager.spawnCubeMan();
	                		System.out.println("Cube spawned");
	                	}
	                	
	                    canvas = this.surfaceHolder.lockCanvas(null);
	                    synchronized (this.surfaceHolder) {
	                    	
	                    	stageEntityManager.solveStack();
	                    	stageEntityManager.solveReactions();
	                    	
	                    	stageLevel.drawStage(canvas,gameItemSizes);
	                    	stageEntityManager.drawEntities(canvas,canvasWidth,canvasHeight);
	                    		                    	
	                    }
	                } finally {
	                   
	                    if (canvas != null) {
	                        this.surfaceHolder.unlockCanvasAndPost(canvas);
	                    }
	                }
	                try {
	                	actualFrame++;
						sleep(25);
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
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
            System.out.println(canvas.getWidth()+","+canvas.getHeight()+",");
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
		
		mainThread = new MainThread(getHolder(),super.getContext()); 
		mainThread.setRunning(true);
		mainThread.start();
        setOnTouchListener(this);
        System.out.println("Surface created called");
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
	      stageEntityManager.movePlayer1(Math.round(eventX),Math.round(eventY));
	      //path.moveTo(eventX, eventY);
	      System.out.println("clic on "+eventX+","+eventY);
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