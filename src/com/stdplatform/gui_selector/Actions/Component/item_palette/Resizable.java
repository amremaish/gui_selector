package com.stdplatform.gui_selector.Actions.Component.item_palette;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

public class Resizable {

	private int minSize = 20 ;
	private JPanel comp;
	private DragItemListener dil;

	public Resizable(JPanel comp) {
		this(comp, new ResizableBorder(8));
		this.comp = comp;
	}

	public Resizable(Component comp, ResizableBorder border) {

		comp.addMouseListener(resizeListener);
		comp.addMouseMotionListener(resizeListener);
		((JComponent) comp).setBorder(border);
	}

	private void resize() {
		if (comp.getParent() != null) {
			((JComponent) comp.getParent()).revalidate();
		}
	}



	public void setDragItemListener(DragItemListener dil) {
		this.dil = dil;
	}



	MouseInputListener resizeListener = new MouseInputAdapter() {

		@Override
		public void mouseMoved(MouseEvent me) {
			if (comp.hasFocus()) {
				ResizableBorder border = (ResizableBorder) comp.getBorder();
				comp.setCursor(Cursor.getPredefinedCursor(border.getCursor(me)));
			}
		}

		@Override
		public void mouseExited(MouseEvent mouseEvent) {
			comp.setCursor(Cursor.getDefaultCursor());
		}

		private int cursor;
		private Point startPos = null;

		@Override
		public void mousePressed(MouseEvent me) {

			ResizableBorder border = (ResizableBorder) comp.getBorder();
			cursor = border.getCursor(me);
			startPos = me.getPoint();
			comp.requestFocus();
			comp.repaint();
		}

		@Override
		public void mouseDragged(MouseEvent me) {

			if (startPos != null) {
				if (dil != null) {
					dil.DragItemAction();
				}
				int x = comp.getX();
				int y = comp.getY();
				int w = comp.getWidth();
				int h = comp.getHeight();

				int dx = me.getX() - startPos.x;
				int dy = me.getY() - startPos.y;

				switch (cursor) {
				case Cursor.N_RESIZE_CURSOR:
					if (!(h - dy < 50)) {
						comp.setBounds(x, y + dy, w, h - dy);
						resize();
					}
					break;

				case Cursor.S_RESIZE_CURSOR:
					if (!(h + dy < 50)) {
						comp.setBounds(x, y, w, h + dy);
						startPos = me.getPoint();
						resize();
					}
					break;

				case Cursor.W_RESIZE_CURSOR:
					if (!(w - dx < 50)) {
						comp.setBounds(x + dx, y, w - dx, h);
						resize();
					}
					break;

				case Cursor.E_RESIZE_CURSOR:
					if (!(w + dx < 50)) {
						comp.setBounds(x, y, w + dx, h);
						startPos = me.getPoint();
						resize();
					}
					break;

				case Cursor.NW_RESIZE_CURSOR:
					if (!(w - dx < minSize) && !(h - dy < minSize)) {
						comp.setBounds(x + dx, y + dy, w - dx, h - dy);
						resize();
					}
					break;

				case Cursor.NE_RESIZE_CURSOR:
					if (!(w + dx < minSize) && !(h - dy < minSize)) {
						comp.setBounds(x, y + dy, w + dx, h - dy);
						startPos = new Point(me.getX(), startPos.y);
						resize();
					}
					break;

				case Cursor.SW_RESIZE_CURSOR:
					if (!(w - dx < minSize) && !(h + dy < minSize)) {
						comp.setBounds(x + dx, y, w - dx, h + dy);
						startPos = new Point(startPos.x, me.getY());
						resize();
					}
					break;

				case Cursor.SE_RESIZE_CURSOR:
					if (!(w + dx < minSize) && !(h + dy < minSize)) {
						comp.setBounds(x, y, w + dx, h + dy);
						startPos = me.getPoint();
						resize();
					}
					break;

				case Cursor.MOVE_CURSOR:
					Rectangle bounds = comp.getBounds();
					bounds.translate(dx, dy);
					comp.setBounds(bounds);
					resize();
				}

				comp.setCursor(Cursor.getPredefinedCursor(cursor));
			}
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {

			startPos = null;
		}
	};

	public void setMinSize(int minSize) {
		this.setMinSize(minSize);
	}

}