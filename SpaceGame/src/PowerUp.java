
public class PowerUp {
	private String type;
	private int duration;

	public PowerUp(String type, int duration) {
		super();
		this.type = type;
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
