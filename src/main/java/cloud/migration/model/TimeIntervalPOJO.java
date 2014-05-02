package cloud.migration.model;

public class TimeIntervalPOJO {
	
	private int startTime;
	private String date;
	public TimeIntervalPOJO(int startTime, String date) {
		
		this.startTime = startTime;
		this.date = date;
	}
	
	public TimeIntervalPOJO()
	{
		
		
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TimeIntervalPOJO [startTime=" + startTime + ", date="
				+ date + "]";
	}
	
	
	
	
	

}
