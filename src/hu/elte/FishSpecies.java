package hu.elte;

public enum FishSpecies {

	BREAM(0, 2),
	CARP(30, 5),
	PIKE(40, 10),
	RUDD(0, 1);
	
	private final int minSizeInCm;
	private final int valueInPoints;
	
	FishSpecies(int minSizeInCm, int valueInPoints){
		this.minSizeInCm = minSizeInCm;
		this.valueInPoints = valueInPoints;
	}
	
	public boolean isProtected() {
		return this.minSizeInCm > 0 ? true : false;
	}
	
	public boolean canBeKept(int sizeInCm) {
		return this.minSizeInCm <= sizeInCm ? true : false;
	}
	
	public int getPoints() {
		return this.valueInPoints;
	}
}
