public class Monster extends Creature {
	
    public static Monster getMonster(int depth) {
		switch (depth) {
			case 1:
				return new Goblin();
			case 2:
				return new Bear();
			case 3:
				return new Dragon();
		}
		return new Monster();
	}	

}
