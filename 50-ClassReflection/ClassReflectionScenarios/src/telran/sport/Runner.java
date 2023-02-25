package telran.sport;

public class Runner implements Sportsman{
int speed;
public Runner(String speed) {
	this.speed = Integer.parseInt(speed);
}
	@Override
	public void action() {
		System.out.println("I'm runner with speed " + speed + "km/h");
		
	}

}
