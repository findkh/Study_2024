package pullObserver;

public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
		ForcastDisplay forcastDisplay = new ForcastDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		
		weatherData.setMeasurements(28, 60, 30.4f);
		weatherData.setMeasurements(32, 40, 29.2f);
		weatherData.setMeasurements(29, 65, 30.4f);
	}
}
