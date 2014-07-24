package com.urbanbits.cubemanworld;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.urbanbits.cubemanworld.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    
	public void startGame(View view){
		//Intent intent = new Intent(this, GameActivity.class);
	    //intent.putExtra("msg", "testin");
	    //startActivity(intent);
	    
	}
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ViewGroup layout = (ViewGroup) findViewById(R.id.mainLayout);
        GameRunView gameRunView = new GameRunView(this);
       /* gameRunView.setLayoutParams(new FrameLayout.LayoutParams(
        											FrameLayout.LayoutParams.MATCH_PARENT,
        											FrameLayout.LayoutParams.MATCH_PARENT));*/
        
       // Display display = getWindowManager().getDefaultDisplay();
       
        //int width = display.getWidth();
        //int height = display.getHeight();
        
        
        layout.addView(gameRunView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        
    }


}
