package view;



import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeView extends JPanel{

	private TimeThread thread;
	private JLabel timeLabel = new JLabel("");
	
	public TimeView(int time) {
		thread = new TimeThread(time);
		updateView();
		add(timeLabel);
		thread.start();
	}
	
	public void reset() {
		thread.reset();
	}
	
	private void updateView() {
		timeLabel.setText(Integer.toString(thread.getCurrentTime()));
		validate();
	}
	
	private class TimeThread extends Thread {
		private int time;
		private int currentTime;
		
		public TimeThread(int time) {
			super();
			this.time = time;
			reset();
		}
		
		@Override
		public void run() {
			while (currentTime > -1) {
				try {
					sleep(1000);
					currentTime--;
					updateView();
					while(currentTime == 0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void reset() {
			currentTime = time;
		}
		
		public int getCurrentTime() {
			return currentTime;
		}
	}
	
}
