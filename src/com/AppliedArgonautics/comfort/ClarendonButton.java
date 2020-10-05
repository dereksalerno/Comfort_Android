package com.AppliedArgonautics.comfort;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;

public class ClarendonButton extends Button {

	public ClarendonButton(Context context) {
		super(context);
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/clarendon.ttf");
		this.setTypeface(tf);
	}

}
