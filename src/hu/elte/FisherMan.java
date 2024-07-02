package hu.elte;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FisherMan {
	private String name;
	private List<Fish> caught;
	
	public FisherMan(String name) {
		this.name = name;
		this.caught = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	Stream<Fish> streamCatches(){
		return caught.stream();
	}
	
	void addCatch(Fish fish) {
		caught.add(fish);
	}
}
