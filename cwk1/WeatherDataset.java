package Skeletion;

import java.time.LocalDate;

public class WeatherDataset {

	WeatherDataset(){
		
	}
	
	WeatherDataset(String filename){
		
	}
	
	void readFile(String filename){
		
	}
	
	int size() {
		return 0;
	}
	
	WeatherRecord get(int index) {
		return null;
	}
	
	WeatherRecord add(WeatherRecord record) {
		return null;
	}
	
	WeatherRecord maxWindSpeed() {
		return null;
	}

	WeatherRecord maxTemperature() {
		return null;
	}
	
	WeatherRecord minHUmidity() {
		return null;
	}
	
	double insolation(LocalDate date) {
		return 0;
	}
}
