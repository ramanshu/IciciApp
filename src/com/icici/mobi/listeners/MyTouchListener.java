package com.icici.mobi.listeners;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;

@SuppressLint("NewApi")
public class MyTouchListener implements OnTouchListener {
	public boolean onTouch(View view, MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	      ClipData data = ClipData.newPlainText("", "");
	      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	      view.startDrag(data, shadowBuilder, view, 0);
	      view.setVisibility(View.INVISIBLE);
	      return true;
	    } else  if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL){
		      view.setVisibility(View.INVISIBLE);
	    return true;
	    }
	    else  if (motionEvent.getAction() == MotionEvent.ACTION_UP){
		      view.setVisibility(View.VISIBLE);
	    return true;
	    }
	    else
	    {
	       view.setVisibility(View.INVISIBLE);
		    	return false;
	    }
	  }
	
	} 
