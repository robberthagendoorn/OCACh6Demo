public class Weapon extends Item { private int attack;

	public Weapon(String name, int cost, int attack){ super(name, cost);
		this.attack = attack; }

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}
