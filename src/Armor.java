public class Armor extends Item {
	private int armor = 1;

	public Armor(String name, int cost, int armor) {
		super(name, cost);
		this.armor = armor;
	}
	
	public int getArmor() {
		return armor;
	}
}
