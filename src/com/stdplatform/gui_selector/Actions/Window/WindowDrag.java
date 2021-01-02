package com.stdplatform.gui_selector.Actions.Window;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.stdplatform.gui_selector.component.XFrame;

public class WindowDrag extends MouseAdapter  {

	private Component frame ;
	private int pX ,pY;
	private DragWindowListener WindowDrag ;

	public WindowDrag (final Component frame , JPanel panel){
		this.frame = frame ;
		// motion action
		panel.addMouseMotionListener(new MouseMotionAdapter() {
	            public void mouseDragged(MouseEvent me) {
	            	if (WindowDrag != null ){
	            		WindowDrag.WindowDragAction();
	            	}
	                frame.setLocation(frame.getLocation().x + me.getX() - pX,
	                        frame.getLocation().y + me.getY() - pY);
	            }
	        });
	}

    public void mouseDragged(MouseEvent me) {
		frame.setLocation(frame.getLocation().x + me.getX() - pX,
                 frame.getLocation().y + me.getY() - pY);
     }

    public void mousePressed(MouseEvent me) {
        // Get x,y and store them
        pX = me.getX();
        pY = me.getY();

    }

    public void setDragWindowListener(DragWindowListener WindowDrag){
    	this. WindowDrag = WindowDrag ;
    }


}
