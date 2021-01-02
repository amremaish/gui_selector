package  com.stdplatform.gui_selector.animation;

import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

public class Die extends TimerTask implements Actions {


	private AfterAnimationListener afterAnime ;
	private Timer timer;
	private Component comp;
	private GrowingUp secondTask;
	private boolean Running;

	private float OriginalWidth, OriginalHeight;
	private float CurWidth, CurHeight;

	private float OriginalX, OriginalY;
	private float CurX, CurY;

	private float StepSizeWidth;
	private float  StepSizeHeight;

	private int NumberOfSteps = 15;

	public Die(Component comp) {
		timer = new Timer();
		Running = true;
		this.comp = comp;
		OriginalX = comp.getX();
		OriginalY = comp.getY();
		OriginalWidth = comp.getWidth();
		OriginalHeight = comp.getHeight();
	}

	@Override
	public void Resume() {
		Running = true;
	}

	@Override
	public void Pause() {
		Running = false;
	}

	@Override
	public void Start() {
		StepSizeWidth = OriginalWidth / NumberOfSteps;
		StepSizeHeight = OriginalHeight / NumberOfSteps;
		CurX = OriginalX ;
		CurY = OriginalY ;
		CurWidth = OriginalWidth;
		CurHeight = OriginalHeight;
		timer.schedule(this, 0, 1);
	}

	@Override
	public void run() {
		if (!Running) {
			return;
		}
		if (CurWidth <= 0 || CurHeight <= 0) {
			comp.setVisible(false);
			if (afterAnime !=null){
				afterAnime.afterAction();
			}
			if (secondTask != null) {
				secondTask.Resume();
			}
			this.cancel();
			return;
		}
		CurWidth -= StepSizeWidth;
		CurHeight -= StepSizeHeight;
		CurX += (StepSizeWidth / 2)  ;
		CurY += StepSizeHeight / 2;

		comp.setBounds((int) CurX, (int) CurY, (int) CurWidth, (int) CurHeight);
		comp.repaint();
	}

	public void setNumberOfSteps(int number) {
		this.NumberOfSteps = number;
	}

	public synchronized void RunFirst(GrowingUp secondTask)
			throws InterruptedException {
		secondTask.Pause();
		this.secondTask = secondTask;
	}

	public void setSpeed(int speed) {
		NumberOfSteps = speed;
	}

	public void setAfterAnimationListener(AfterAnimationListener afterAnime) {
		this.afterAnime = afterAnime;
	}

	public boolean isRunning(){
		return Running ;
	}

}
