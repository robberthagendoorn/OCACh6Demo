public class Monster extends Creature {
	
 	public Monster(int attack, int defense, int health, int gold, String name) {
	   super(attack, defense, health, gold, name);
  	}	   

   	public static Monster getMonster(int depth) {
		switch (depth) {
			case 1:
				return new Monster(1, 0, 3, 1, "Goblin");
			case 2:
				return new Monster(2, 0, 5, 3, "Bear");
			case 3:
				return new Monster(5, 1, 10, 100, "Dragon");
		}
		return new Monster(100, 100, 1000, 0, "Ivan");
	}	

}
