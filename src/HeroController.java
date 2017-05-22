public class HeroController {

	private Hero hero;
	private HeroView view;
	private int depth = 0;
	private int turns = 0;

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

	public HeroController(Hero hero, HeroView view) {
		this.hero = hero;
		this.view = view;
	}

	public void performAction() {
		try {
			executeOption();
		} catch (IllegalOptionException e) {
			view.printMessage(e.getMessage());
		}
	}

	public void executeOption() throws IllegalOptionException {
		boolean keepGoing = true;
		while (keepGoing) {
			String option = view.getOption();
			switch (option){
				case INCREASE_DEPTH: 
					encounterMonster();
					break;
				case VISIT_SHOP:
					visitShop();
					break;
				case EXIT_GAME:
					System.exit(0);
					break;
				default:
					throw new IllegalOptionException("\nPlease choose a value of 1-3");
			}
		}
	}

	public void encounterMonster() {
		depth++;
		Monster monster = Monster.getMonster(depth);
		view.printMessage("\nYou have encountered a " + monster.getName());
		fightMonster(monster);
	}

	public boolean fightMonster(Monster monster) {
		boolean keepGoing = true;
		while(keepGoing) {
			try {
				keepGoing =	attackRound(monster);
			} catch (IllegalOptionException e) {
				view.printMessage(e.getMessage());
			}
		}
		return keepGoing;
	}
	
	public boolean attackRound(Monster monster) throws IllegalOptionException {
		String input = view.getAttackOption(monster);
		switch (input) {
			case ATTACK:
				turns++;	
				hero.attack(monster);
				if (monster.getHealth() <= 0) { 
					youWin(monster);
					return false;
				}
				break;
			case USE_POTION:
				turns++;
				useItem();
				break;
			case RUN:
				turns++;
				view.printMessage("You have escaped");
				depth = 0;
				return false;	
			default:
				throw new IllegalOptionException("\nPlease choose a value of 1-3");
		}
		monster.attack(hero);
		if (hero.getHealth() <= 0) {
			view.printMessage("\nYou have died");
			System.exit(0);
		}
		return true;
	}

	private void youWin(Monster monster) {
		if (depth < 3) {
			view.printMessage("\nYou have defeated the " + monster.getName() +  "\nYou have gained " + monster.getGold() + "gp");
			hero.setGold(hero.getGold() + monster.getGold());
		} 
		else {
			view.printMessage("\nYou have defeated the final boss, you have beaten the game in " + turns + " turns");
			System.exit(0);
		}
	}

	public void useItem() {
		try {
			hero.setNumPotions(hero.getNumPotions() - 1);
			hero.setHealth(10);
		} catch (IllegalArgumentException e) {
			System.out.println("\nYou do not have anymore potions");
		}
	}

	private void visitShop() {
		depth = 0;
		boolean keepGoing = true;
		while (keepGoing) {
			String input = view.shopInput();
			try{
				keepGoing = performShopAction(input);
			} catch (IllegalOptionException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public boolean performShopAction(String input) throws IllegalOptionException {
		boolean keepGoing = true;
		switch (input) {
			case BUY_POTION:
				buyItem(new Item("Potion", 1));
				return true;
			case BUY_DAGGER:
				buyItem(new Weapon("Dagger", 3, 1));
				return true;
			case BUY_SWORD:
				buyItem(new Weapon("Sword", 5, 2));
				return true;
			case BUY_ARMOR:
				buyItem(new Armor("Armor", 4, 1));
				return true;
			case LEAVE_SHOP:
				return false;
			default:
				throw new IllegalOptionException("\nPlease choose a value of 1-5");
		}		
	}

	public void upgradeHero(Item item) {
		if (item instanceof Weapon) {
			Weapon weapon = (Weapon)item;
			hero.setWeapon(weapon);
		} else if (item instanceof Armor) {
			Armor armor = (Armor)item;
			hero.setArmor(armor);
		} else {
			hero.setNumPotions(hero.getNumPotions() + 1);
		}
	}
	
	public void buyItem(Item item) {
		try {
			hero.setGold(hero.getGold() - item.getCost());
		} catch (IllegalArgumentException e) {
			view.printMessage(e.getMessage());
			return;
		}
		view.printMessage("\nYou have bought a " + item.getName());		
		upgradeHero(item);
	}	
}

