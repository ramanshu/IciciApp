package com.icici.mobi.view;

import com.icici.mobi.animation.CollapseAnimation;
import com.icici.mobi.animation.ExpandAnimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class SlidingMenuView extends LinearLayout{

	LinearLayout slidingPanel;
	boolean isExpanded;
	int panelWidth;
	float screenRatio = 0.75f;

	public SlidingMenuView(Context context) {
	
		super(context);
	
	}
	public SlidingMenuView(Context context, AttributeSet attrs) {
	
		super(context, attrs);

	}
	@SuppressLint("NewApi")
	public SlidingMenuView(Context context, AttributeSet attrs, int defStyle) {
	
		super(context, attrs, defStyle);

	}

	
	public void connect(LinearLayout slidingPanel,DisplayMetrics metrics){
		panelWidth = (int) ((metrics.widthPixels)*screenRatio);
		this.slidingPanel = slidingPanel;
		FrameLayout.LayoutParams menuPanelParameters;
		FrameLayout.LayoutParams slidingPanelParameters;
		menuPanelParameters = (FrameLayout.LayoutParams)this.getLayoutParams();
		menuPanelParameters.width = panelWidth;
		this.setLayoutParams(menuPanelParameters);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		this.slidingPanel.setLayoutParams(slidingPanelParameters);
		setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	toggle();
		    }
		});
	}
	public void toggle()
	{
    	if(!isExpanded){
    		isExpanded = true;   		    				        		
    		new ExpandAnimation(slidingPanel, panelWidth,
    	    Animation.RELATIVE_TO_SELF, 0.0f,
    	    Animation.RELATIVE_TO_SELF, screenRatio, 0, 0.0f, 0, 0.0f);		    			         	    
    	}else{
    		isExpanded = false;
    		new CollapseAnimation(slidingPanel,panelWidth,
    	    TranslateAnimation.RELATIVE_TO_SELF, screenRatio,
    	    TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);
    	}         	   
	}
}
