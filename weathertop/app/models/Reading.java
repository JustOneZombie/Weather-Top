package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public double pressure;
  public double  windDirection;

  public Reading(int code, double temperature, double windSpeed, double windDirection, double pressure) {
    this.temperature = temperature;
    this.code = code;
    this.windSpeed = windSpeed;
    this.pressure = pressure;
    this.windDirection = windDirection;

  }

  public int getCode() {return code;}

  public double getTemp() {
    return temperature;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public double getPressure() {
    return pressure;
  }

  public double getWindDirection() {return windDirection;}
}
