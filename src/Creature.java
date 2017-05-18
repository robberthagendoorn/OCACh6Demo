abstract class Creature {

	private int attack;

	private int defense;

	private int health;

	private int gold;
	
	private int numPotions;

	private String name;

	public String getName() {
		return name;
	}	

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHealth() {
		return health;
	}

	public int getNumPotions() {
		return numPotions;
	}

	public int getGold() {
		return gold;
	}

	public void setName(String nm) {
		name = nm;
	}

	public void setAttack(int ar) {
		attack = ar;
	}

	public void setDefense(int dr) {
		defense = dr;
	}

	public void setHealth(int hr) {
		health = hr;
	}

	public void setNumPotions(int np) {
		numPotions = np;
	}

	public void setGold(int gp) {
		gold = gp;
	}

	public void attack(Creature target) {
		int damage = getAttack() - target.getDefense();
		if (damage < 0) damage = 0;
		target.setHealth(target.getHealth() - damage);
		System.out.println("\nThe " + getName() + " has dealt " + damage + " damage");
	}
}
