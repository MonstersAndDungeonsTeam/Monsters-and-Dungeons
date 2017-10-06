package monstersanddungeons.stats;

public class Stat {
	private String shortName, longName;
	
	public Stat(String name, String shortName){
		longName = name;
		this.shortName = shortName;
	}

	public String getName(){
		return this.longName;
	}
	
	public String getShortName(){
		return this.shortName;
	}
}
