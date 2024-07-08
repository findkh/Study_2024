package strategy_puzzles;

public abstract class Character {
	WeaponBehavior weapon;
	
	public abstract void display();
	
	public void fight() {
		display();
		weapon.useWeapon();
		System.out.println("싸운다.");
	}

	public void setWeapon(WeaponBehavior w) {
		this.weapon = w;
	}
}
