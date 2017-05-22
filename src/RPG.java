import java.io.Console;

public class RPG {

	
	private int depth = 0;
	
	
	public static void main(String[] args) {
		Hero hero= new Hero();
		HeroView view = new HeroView(hero);
		HeroController heroController = new HeroController(hero, view);
		heroController.performAction();
	}

}




