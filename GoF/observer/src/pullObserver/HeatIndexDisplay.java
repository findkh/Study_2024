package pullObserver;

public class HeatIndexDisplay implements Observer, DisplayElement {
	float heatIndex = 0.0f;
	private WeatherData weatherData;
	
	public HeatIndexDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	public void update() {
		float temperature = weatherData.getTemperature();
		float humidity = weatherData.getHumidity();
		heatIndex = computeHeatIndex(temperature, humidity);
		display();
	}
	
	//섭씨를 사용하는 체감 기온 계산 함수
	private float computeHeatIndex(float t, float rh) {
		// 화씨로 변환
		float tF = t * 9 / 5 + 32;
		
		// 화씨를 사용하는 체감 기온 공식
		float indexF = (float)((16.923 + (0.185212 * tF) + (5.37941 * rh) - (0.100254 * tF * rh)
			+ (0.00941695 * (tF * tF)) + (0.00728898 * (rh * rh)) 
			+ (0.000345372 * (tF * tF * rh)) - (0.000814971 * (tF * rh * rh)) +
			(0.0000102102 * (tF * tF * rh * rh)) - (0.000038646 * (tF * tF * tF)) + (0.0000291583 * 
			(rh * rh * rh)) + (0.00000142721 * (tF * tF * tF * rh)) + 
			(0.000000197483 * (tF * rh * rh * rh)) - (0.0000000218429 * (tF * tF * tF * rh * rh)) +
			0.000000000843296 * (tF * tF * rh * rh * rh)) -
			(0.0000000000481975 * (tF * tF * tF * rh * rh * rh)));

		// 다시 섭씨로 변환
		float indexC = (indexF - 32) * 5 / 9;
		return indexC;
	}

	public void display() {
		System.out.println("체감기온: " + heatIndex + "°C");
	}
}