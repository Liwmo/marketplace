package marketplace;

public enum Course {
	SIDE(1), MAIN(2), DESSERT(3);
	
	private int numVal;
	
	Course(int numVal) {
		this.numVal = numVal;
	}
	
	public int getNumVal() {
		return numVal;
	}
}
