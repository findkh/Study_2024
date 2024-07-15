package singleton.enumS;

public enum Singleton {
	UNIQUE_INSTANCE;
	
	public String getDescription() {
		return "쓰레드에 안전한 싱글톤";
	}
}
