package telran.pulse.monitoring.entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class SensorList {
	@Id
	int id;
	ArrayList<Integer> listValues = new ArrayList<>();
	
	public SensorList(int id) {
		this.id = id;
	}
}
