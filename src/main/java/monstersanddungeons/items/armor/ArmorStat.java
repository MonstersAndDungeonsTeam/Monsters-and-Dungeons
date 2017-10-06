package monstersanddungeons.items.armor;

import monstersanddungeons.stats.Stat;

public class ArmorStat {
	private Stat stat;
	private int power;
	
	/**
	 * Creates an ArmorStat with the power of 1
	 * @param stat
	 */
	public ArmorStat(Stat stat){
		this(stat, 1);
	}
	
	public ArmorStat(Stat stat, int power){
		this.stat = stat;
		this.power = power;
	}
	
	public Stat getStat(){
		return this.stat;
	}
	
	public int getPower(){
		return this.power;
	}
	
	public void setPower(int newPower){
		this.power = newPower;
	}
}
