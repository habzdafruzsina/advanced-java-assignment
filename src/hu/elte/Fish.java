package hu.elte;

public class Fish {
	private FishSpecies species;
	private int length;
	
	public Fish(FishSpecies species, int length) {
		this.species = species;
		this.length = length;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public FishSpecies getSpecies() {
		return this.species;
	}
	
	public boolean isProtected() {
		return species.isProtected();
	}
	
	public boolean isValid() {
		return species.canBeKept(length);
	}

}
