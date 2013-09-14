package com.icici.mobi.activity;


import com.example.iciciapp.R;
import com.icici.mobi.listeners.MyTouchListener;
import android.view.ViewGroup;
import com.icici.mobi.settings.Standard;
import com.icici.mobi.view.PathView;
import com.icici.mobi.view.ScreenOptionButton;
import com.icici.mobi.view.SlidingMenuView;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class HomeScreenActivity extends Activity {
	ImageView menuViewButton;
	SlidingMenuView slideMenuView;
	LinearLayout slidingPanel;
	DisplayMetrics metrics;
	RelativeLayout headerPanel;
	RelativeLayout menuPanel;
	TextView textView;
	PathView pathView;
	ScreenOptionButton target1;
	ScreenOptionButton target2;
	ScreenOptionButton target3;
	Animation animation;
	Animation animation1;
	Animation animation2;
	ObjectAnimator anim;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		Standard.optionsXML = getResources().openRawResource(R.raw.options);
		Standard.screensXML = getResources().openRawResource(R.raw.screens);
		Standard.skeletonXML = getResources().openRawResource(R.raw.skeleton);
		textView = (TextView) findViewById(R.id.textView);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		headerPanel = (RelativeLayout) findViewById(R.id.header);
		// menuPanel = (RelativeLayout) findViewById(R.id.menuPanel);
		slidingPanel = (LinearLayout) findViewById(R.id.mainScreen);
		slideMenuView = (SlidingMenuView) findViewById(R.id.menuPanel);
		pathView = (PathView) findViewById(R.id.pathView);
		slideMenuView.connect(slidingPanel, metrics);
		target1 = (ScreenOptionButton) findViewById(R.id.targetButton1);
		target2 = (ScreenOptionButton) findViewById(R.id.targetButton2);
		target3 = (ScreenOptionButton) findViewById(R.id.targetButton3);
		target1.setSize(metrics);
		target2.setSize(metrics);
		target3.setSize(metrics);
		// Slide the Panel
		animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
		animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);
    	target1.setAnimation(animation1, animation2);
    	target2.setAnimation(animation1, animation2);
    	target3.setAnimation(animation1, animation2);
    	anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip); 
    	menuViewButton = (ImageView) findViewById(R.id.menuViewButton);
		menuViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		    	anim.setTarget(target1);
		    	anim.setDuration(3000);
		    	anim.start();

				//slideMenuView.toggle();
			}
		});
		ImageButton pathViewToggleButton = (ImageButton) findViewById(R.id.pathViewToggle);
		LinearLayout dropZone = (LinearLayout) findViewById(R.id.dropZone);
		pathViewToggleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pathView.toggle();
			}
		});

		// Assign the touch listener to your view which you want to move
		ImageView draggable = (ImageView)findViewById(R.id.draggable);
		ImageView draggable1 = (ImageView)findViewById(R.id.draggable1);
		ImageView draggable2 = (ImageView)findViewById(R.id.draggable2);
		draggable.setOnTouchListener(new MyTouchListener());
		draggable1.setOnTouchListener(new MyTouchListener());
		draggable2.setOnTouchListener(new MyTouchListener());
		dropZone.setOnDragListener(new MyDragListener());

		
		//Skeleton skeleton = new Skeleton();
		//String temp = skeleton.getSkeletonFromXML();
	}
	@SuppressLint("NewApi")
	class MyDragListener implements OnDragListener {
//		  Drawable enterShape = getResources().getDrawable(R.drawable.bill);
//		  Drawable normalShape = getResources().getDrawable(R.drawable.air_ticket);
//		  
		  @Override
		  public boolean onDrag(View v, DragEvent event) {
		    switch (event.getAction()) {
		    case DragEvent.ACTION_DRAG_STARTED:
		    	/// Do nothing
		      break;
		    case DragEvent.ACTION_DRAG_ENTERED:
		      //v.setBackgroundDrawable(enterShape);
		      break;
		    case DragEvent.ACTION_DRAG_EXITED:        
		      //v.setBackgroundDrawable(normalShape);
		      break;
		    case DragEvent.ACTION_DROP:
		      // Dropped, reassign View to ViewGroup
		      View view = (View) event.getLocalState();
		      ViewGroup owner = (ViewGroup) view.getParent();
		      owner.removeView(view);
		      LinearLayout container = (LinearLayout) v;
		      container.addView(view);
		      view.setVisibility(View.VISIBLE);
		      break;
		    case DragEvent.ACTION_DRAG_ENDED:
		      //v.setBackgroundDrawable(normalShape);
		      default:
		      break;
		    }
		    return true;
		  }

		} 
}
