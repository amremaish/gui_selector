package com.stdplatform.gui_selector.animation;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.stdplatform.gui_selector.Frames.Palette;

public class GrowingUp extends TimerTask implements Actions {

	private AfterAnimationListener afterAnime;
	private Timer timer;
	private Component comp;
	private GrowingUp secondTask;
	private boolean Running;
	private float OriginalWidth, OriginalHeight;
	private float CurWidth, CurHeight;
	private float OriginalX, OriginalY;
	private float CurX, CurY;
	private float StepSizeWidth;
	private float StepSizeHeight;

	private int NumberOfSteps = 15;

	public GrowingUp(Component comp) {
		timer = new Timer();
		Running = true;
		this.comp = comp;
		OriginalX = comp.getX();
		OriginalY = comp.getY();
	}

	public void SetOriginalSize(int width, int height) {
		OriginalWidth = width;
		OriginalHeight = height;
		comp.setSize(0, 0);

	}
	@Override
	public synchronized void Resume() {
		Running = true;
	}

	@Override
	public synchronized void Pause() {
		Running = false;
	}

	@Override
	public void Start() {
		StepSizeWidth = OriginalWidth / NumberOfSteps;
		StepSizeHeight = OriginalHeight / NumberOfSteps;
		CurX = OriginalX + OriginalWidth / 2;
		CurY = OriginalY + OriginalHeight / 2;

		timer.schedule(this, 0, 1);
	}

	@Override
	public synchronized void run() {
		update();
	}


	private void update(){
		if (secondTask != null && secondTask.isRunning() == false ) {
			this.Running = true;
		}

		if (!Running) {
			return;
		}
		if (CurWidth >= OriginalWidth || CurHeight >= OriginalHeight) {
			if (secondTask != null) {
				secondTask.Resume();
			}
			if (afterAnime != null) {
				afterAnime.afterAction();
			}
			Running = false ;
			this.cancel();

			return;
		}
		CurWidth += StepSizeWidth;
		CurHeight += StepSizeHeight;
		CurX -= (StepSizeWidth / 2);
		CurY -= StepSizeHeight / 2;
		comp.setBounds((int) CurX, (int) CurY, (int) CurWidth+1, (int) CurHeight+1);
		comp.repaint();

	}


	public void setNumberOfSteps(int number) {
		this.NumberOfSteps = number;
	}

	public synchronized void RunFirst(GrowingUp secondTask) {
		secondTask.Pause();
		this.secondTask = secondTask;
	}

	public synchronized void WaitForFinish(GrowingUp secondTask) {
		this.Pause();
		this.secondTask = secondTask;
	}

	public void setSpeed(int speed) {
		NumberOfSteps = speed;
	}

	public void setAfterAnimationListener(AfterAnimationListener afterAnime) {
		this.afterAnime = afterAnime;
	}

	public boolean isRunning() {
		return Running;
	}
}
