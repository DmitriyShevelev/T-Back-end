package telran.sport;

public class Footballer implements Sportsman {
String team;
public Footballer (String team) {
	this.team = team;
}
	@Override
	public void action() {
		System.out.println("I'm footballer in the team " + team);

	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass() )
			return false;
		Footballer other = (Footballer) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

}
