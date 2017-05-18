import java.io.Console;

public class RPG {

	private static Hero hero;

	private static int depth = 0;
	
	private static Console cons = System.console();
	
	public static void main(String[] args) {
		startGame();
		performAction();
	}

	private static void startGame() {
		hero = new Hero();
	}

	private static void performAction() {
		boolean keepGoing = true;
		while (keepGoing) {
			String option = getOption();
			keepGoing = executeOption(option);
		}
	}

	private static String getOption() {
		System.out.println("\nYou have " + hero.getHealth() + " health and " + hero.getGold() + " gold, What would you like to do?");
		String input = cons.readLine("\n (1) Go deeper \t (2) Shop \t (3) Exit game \n");
		return input;
	}
	
	private static boolean executeOption(String option) {
		switch (option){
			case "1":
				depth++;
				return encounterMonster();
			case "2":
				visitShop();
				return true;
			case "3":
				return false;
		}
		return true;
	}

	private static boolean encounterMonster() {
		Monster monster = Monster.getMonster(depth);
		boolean keepGoing = true;
		System.out.println("\nYou have encountered a " + monster.getName());
		while (keepGoing) {
			String input = getAttackOption(monster);
			keepGoing = executeAction(input, monster);
		}
		if (monster.getHealth() <= 0) {
			return youWin(monster);		
		} else if (hero.getHealth() <= 0) {
			System.out.println("\nYou have died");
			return false;
		}
		return true;
	}
	
private static String getAttackOption(Monster monster) {
		
		System.out.println("\nHealth: " + monster.getHealth());
		System.out.println("\nYour Health: " + hero.getHealth());
		String input = cons.readLine("\n (1) Attack \t (2) Use potion \t (3) Run \n");
		return input;
	}

	private static boolean executeAction(String input, Monster monster) {
		boolean keepGoing = true;
		switch (input){
			case "1":
				hero.attack(monster);
				keepGoing = (monster.getHealth() > 0) ? true : false;
				break;
			case "2":
				hero.useItem();
				break;
			case "3":
				System.out.println("You have escaped");
				depth = 0;
				keepGoing = false;
				break;	
		}
		if (keepGoing) {
			monster.attack(hero);
			keepGoing = (hero.getHealth() > 0) ? true : false;
		}
		return keepGoing;
	}

	private static boolean youWin(Monster monster) {
		if (depth < 3) {
			System.out.println("\nYou have defeated the " + monster.getName() +  "\nYou have gained " + monster.getGold() + "gp");
			hero.setGold(hero.getGold() + monster.getGold());
			return true;
		} 
		else {
			System.out.println("\nYou have defeated the final boss, you have beaten the game");
			return false;
		}
	}

	private static void visitShop() {
		depth = 0;
		boolean keepGoing = true;
		while (keepGoing) {
			String input = shopInput();
			keepGoing = performShopAction(input);
		}
	}

	private static String shopInput() {
		System.out.println("\nYou have " + hero.getGold() + " gp");
		String input = cons.readLine("\nWhat would you like to buy? \n (1) Potions \t (1g) \n (2) Dagger \t (3g) \n (3) Sword \t (5g) \n (4) Armor \t (4g) \n (5) Leave shop\n");
		return input;
	}

	private static boolean performShopAction(String input) {
		boolean keepGoing = true;
		switch (input) {
			case "1":
				buyItem(new Potion());
				return true;
			case "2":
				buyItem(new Dagger());
				return true;
			case "3":
				buyItem(new Sword());
				return true;
			case "4":
				buyItem(new Armor());
				return true;
			case "5":
				return false;
		}		
		return true;
	}

	private static void buyItem(Item item) {
		if (hero.getGold() < item.getCost()) {
			System.out.println("\nYou do not have enough gold for this item");
		} else {
			System.out.println("\nYou have bought a " + item.getName());		
			hero.setGold(hero.getGold() - item.getCost());
			hero.upgradeHero(item);
		}
	}
}




