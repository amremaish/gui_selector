package com.stdplatform.gui_selector.util;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import com.stdplatform.gui_selector.Constant.Color.FixedColor;

public class UIColor {

	public UIColor (){
		ScrollBarColors () ;
	}

	public void ScrollBarColors (){
		 UIManager.put("ScrollBar.trackHighlightForeground", FixedColor.BACKGROUND);
		    UIManager.put("scrollbar", (new Color(57,57,57)));
		    UIManager.put("ScrollBar.thumb", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.thumbHeight", 2);
		    UIManager.put("ScrollBar.background", (FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.trackForeground", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.foreground", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.shadow", new ColorUIResource(FixedColor.BACKGROUND));
		    UIManager.put("ScrollBar.highlight", new ColorUIResource(FixedColor.BACKGROUND));
		    
	}

}
