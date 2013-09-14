package com.icici.mobi.view;

import com.icici.mobi.animation.DisplaySlideDownAnimation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class PathView extends HorizontalScrollView {
	//ImageButton toggleButton;
	int height;
	public PathView(Context context) {
		super(context);
		initAnimations(context);
	}
	public PathView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAnimations(context);
	}
	public PathView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAnimations(context);
	}
	
    private void initAnimations(Context context)
    {
//    	toggleButton = new ImageButton(context);
//    	LayoutParams  lllp=(LayoutParams)toggleButton.getLayoutParams();
//        lllp.gravity=Gravity.CENTER_HORIZONTAL;
//        lllp.height = 20;
//        lllp.width =60;
//        toggleButton.setLayoutParams(lllp);
//        this.addView(toggleButton,this.getChildCount());
        this.setHorizontalScrollBarEnabled(false);
    }


    public boolean isVisible()
    {
        return (this.getVisibility() == View.VISIBLE);
    }
	public void toggle() {
		    if(isVisible()){

		    	DisplaySlideDownAnimation a = new DisplaySlideDownAnimation(this, 100, DisplaySlideDownAnimation.COLLAPSE);
		        height = a.getHeight();
		        this.startAnimation(a);

		    }
		    else{

		    	DisplaySlideDownAnimation a = new DisplaySlideDownAnimation(this, 100, DisplaySlideDownAnimation.EXPAND);
		        a.setHeight(height);
		        this.startAnimation(a);

		    }
	}

}
