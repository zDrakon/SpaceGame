
public class Player {
	private int x;
	private int y;
	private int direction;
	private int id;
	private int speed;
	private int hp;
	private int powerup;
	private int level;

	public Player(int x, int y, int id, int speed, int hp) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
		this.speed = speed;
		this.hp = hp;
		this.level = 1;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPowerup() {
		return powerup;
	}

	public void setPowerup(int powerup) {
		this.powerup = powerup;
	}
}
