abstract class Creature {

	private int attack;
	private int defense;
	private int health;
	private int gold;
	private String name;

	public Creature(int attack, int defense, int health, int gold, String name) {
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.gold = gold;
		this.name = name;
	}

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

	public int getGold() {
		return gold;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setGold(int gold) throws IllegalArgumentException {
		if (gold < 0) throw new IllegalArgumentException("You do not have enough gold");
		this.gold = gold;
	}

	public void attack(Creature target) {
		int damage = getAttack() - target.getDefense();
		if (damage < 0) damage = 0;
		target.setHealth(target.getHealth() - damage);
		System.out.println("\nThe " + getName() + " has dealt " + damage + " damage");
	}
}
