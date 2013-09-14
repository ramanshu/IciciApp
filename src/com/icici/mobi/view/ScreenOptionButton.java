package com.icici.mobi.view;


import com.example.iciciapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScreenOptionButton extends LinearLayout {
//	private View mValue;
	private ImageView mImage;
	boolean flip;
	Animation animation1;
	Animation animation2;
	public ScreenOptionButton(Context context) {
		super(context);
	}
	public ScreenOptionButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	@SuppressLint("NewApi")
	public ScreenOptionButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public void setValueColor(int color) {
		    //mValue.setBackgroundColor(color);
		  }

	public void setImageVisible(boolean visible) {
		    //mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
		  }
	void init(Context context)
	{
		flip =true;
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        inflater.inflate(R.layout.screen_option, this, true);
	        mImage = (ImageView) getChildAt(0);
	        mImage.setImageResource(R.drawable.bill);//.setBackgroundColor(valueColor);
	        TextView title = (TextView) getChildAt(1);
	        title.setText("Hello");

	}
	public void setAnimation(final Animation animation1,final Animation animation2)
	{
		this.animation1 = animation1;
		this.animation2 = animation2;
		setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				mImage.startAnimation(animation1);
				animation1.setAnimationListener(new AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						if(flip)
				        {
							mImage.setImageResource(R.drawable.air_ticket);
							flip = false;
				        }
						else
						{
							mImage.setImageResource(R.drawable.bill);
							flip = true;
						}
						mImage.startAnimation(animation2);
					}
				});
			}
		});
	}
	public void setSize(DisplayMetrics metrics)
	{
		int panelWidth = (int)(metrics.widthPixels/3);
		ScreenOptionButton.LayoutParams buttonParams;
		buttonParams = (ScreenOptionButton.LayoutParams)this.getLayoutParams();
		buttonParams.width = panelWidth;
		this.setLayoutParams(buttonParams);
	}
	public void changeImageResource(int res)
	{
        mImage.setImageResource(res);
	}
}
