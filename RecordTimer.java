
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JLabel;


public class RecordTimer extends Thread {
	private DateFormat dateFormater = new SimpleDateFormat("HH:mm:ss");	
	private boolean isRunning = false;
	private boolean isReset = false;
	private long startTime;
	private JLabel labelRecordTime;
        
	RecordTimer(JLabel labelRecordTime) {
		this.labelRecordTime = labelRecordTime;
	}
        @Override
	public void run() {
		isRunning = true;
		startTime = System.currentTimeMillis();
	
		while (isRunning) {
			try 
			{
				Thread.sleep(1000);
				labelRecordTime.setText("Timer: " + toTimeString());
                                        
			} catch (InterruptedException ex) {
				if (isReset) {
					labelRecordTime.setText("Time: 00:00:00");
					isRunning = false;		
					
					break;
				}
			}
		}
	}
	
	void cancel() {
		isRunning = false;		
	}
	
	void reset() {
		isReset = true;
		isRunning = false;
	}
	
	private String toTimeString() {
		long now = System.currentTimeMillis();
		Date current = new Date(now - startTime);
		dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timeCounter = dateFormater.format(current);
		return timeCounter;
	}
}