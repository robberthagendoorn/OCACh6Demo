import java.io.Console;

public class RPG {

	private static final String INCREASE_DEPTH = "1";
	private static final String VISIT_SHOP = "2";
	private static final String EXIT_GAME = "3";

	private static final String ATTACK = "1";
	private static final String USE_POTION = "2";
	private static final String RUN = "3";

	private static final String BUY_POTION = "1";
	private static final String BUY_DAGGER = "2";
	private static final String BUY_SWORD = "3";
	private static final String BUY_ARMOR = "4";
	private static final String LEAVE_SHOP = "5";

	private static Hero hero= new Hero();

	private static int depth = 0;
	
	private static Console cons = System.console();
	
	public static void main(String[] args) {
		performAction();
	}

	private static void performAction() {
		boolean keepGoing = true;
		while (keepGoing) {
			String option = getOption();
			try {
			keepGoing = executeOption(option);
			} catch (IllegalOptionException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static String getOption() {
		System.out.println("\nYou have " + hero.getHealth() + " health and " + hero.getGold() + " gold, What would you like to do?");
		String input = cons.readLine("\n (1) Go deeper \t (2) Shop \t (3) Exit game \n");
		return input;
	}
	
	private static boolean executeOption(String option) throws IllegalOptionException {
		switch (option){
			case INCREASE_DEPTH: 
				depth++;
				return encounterMonster();
			case VISIT_SHOP:
				visitShop();
				return true;
			case EXIT_GAME:
				return false;
			default:
				throw new IllegalOptionException("\nPlease choose a value of 1-3");
		}
	}

	private static boolean encounterMonster() {
		Monster monster = Monster.getMonster(depth);
		boolean keepGoing = true;
		System.out.println("\nYou have encountered a " + monster.getName());
		while (keepGoing) {
			String input = getAttackOption(monster);
			try {
				keepGoing = executeAction(input, monster);
			} catch (IllegalOptionException e) {
				System.out.println(e.getMessage());
			}
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

	private static boolean executeAction(String input, Monster monster) throws IllegalOptionException {
		boolean keepGoing = true;
		switch (input){
			case ATTACK:
				hero.attack(monster);
				keepGoing = (monster.getHealth() > 0) ? true : false;
				break;
			case USE_POTION:
				hero.useItem();
				break;
			case RUN:
				System.out.println("You have escaped");
				depth = 0;
				keepGoing = false;
				break;	
			default:
				throw new IllegalOptionException("\nPlease choose a value of 1-3");
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
			try{
				keepGoing = performShopAction(input);
			} catch (IllegalOptionException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static String shopInput() {
		System.out.println("\nYou have " + hero.getGold() + " gp");
		String input = cons.readLine("\nWhat would you like to buy? \n (1) Potions \t (1g) \n (2) Dagger \t (3g) \n (3) Sword \t (5g) \n (4) Armor \t (4g) \n (5) Leave shop\n");
		return input;
	}

	private static boolean performShopAction(String input) throws IllegalOptionException {
		boolean keepGoing = true;
		switch (input) {
			case BUY_POTION:
				hero.buyItem(new Potion());
				return true;
			case BUY_DAGGER:
				hero.buyItem(new Dagger());
				return true;
			case BUY_SWORD:
				hero.buyItem(new Sword());
				return true;
			case BUY_ARMOR:
				hero.buyItem(new Armor());
				return true;
			case LEAVE_SHOP:
				return false;
			default:
				throw new IllegalOptionException("\nPlease choose a value of 1-5");
		}		
	}
}




