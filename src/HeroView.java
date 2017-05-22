import java.io.Console;

public class HeroView {
		
	private static Console cons = System.console();
	private Hero hero;
	
	public HeroView(Hero hero) {
		this.hero = hero;
	}

	public String getOption() {
		System.out.println("\nYou have " + hero.getHealth() + " health and " + hero.getGold() + " gold, What would you like to do?");
		String input = cons.readLine("\n (1) Go deeper \t (2) Shop \t (3) Exit game \n");
		return input;
	}
	
	public String getAttackOption(Monster monster) {
		System.out.println("\nHealth: " + monster.getHealth());
		System.out.println("\nYour Health: " + hero.getHealth());
		String input = cons.readLine("\n (1) Attack \t (2) Use potion \t (3) Run \n");
		return input;
	}

	public String shopInput() {
		System.out.println("\nYou have " + hero.getGold() + " gp");
		String input = cons.readLine("\nWhat would you like to buy? \n (1) Potions \t (1g) \n (2) Dagger \t (3g) \n (3) Sword \t (5g) \n (4) Armor \t (4g) \n (5) Leave shop\n");
		return input;
	}

	public void printMessage(String message) {
		System.out.println(message);
	}
}
