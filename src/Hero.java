public class Hero extends Creature {

	private Weapon weapon;
	private Armor armor;
	private int numPotions = 0;

	public Hero() {
		super(1, 0, 10, 0, "Hero");
	}

	public int getAttack() {
		int base = super.getAttack();
		int added = (weapon != null) ? weapon.getAttack() : 0;
		return base + added;	
	}		
	
	public int getDefense() {
		int base = super.getDefense();
		int added = (armor != null) ? armor.getArmor() : 0;
		return base + added;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public int getNumPotions() {
		return numPotions;
	}

	public void setNumPotions(int numPotions) throws IllegalArgumentException {
		if (numPotions < 0) throw new IllegalArgumentException("");
		this.numPotions = numPotions;
	}
}

