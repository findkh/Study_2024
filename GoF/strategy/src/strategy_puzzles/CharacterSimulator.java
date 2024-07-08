package strategy_puzzles;

public class CharacterSimulator {
	public static void main(String[] args) {
		Character king = new King();
		Character queen = new Queen();
		Character knight = new Knight();
		Character troll = new Troll();
		
		king.fight();
		queen.fight();
		knight.fight();
		troll.fight();
		
		System.out.println("무기를 변경한다.");
		king.setWeapon(new AxeBehavior());
		king.fight();
	}
}
