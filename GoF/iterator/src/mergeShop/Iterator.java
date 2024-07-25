package mergeShop;

public interface Iterator {
	boolean hasNext(); //반복 작업을 수행할 항목이 있는지 확인한 다음 그 결과를 불리언값으로 표현
	MenuItem next();
}
