package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {
  public int code;
  public double temp;
  public double windSpeed;
  public double pressure;

  public Reading(int code, double temp, double windSpeed, double pressure) {
    this.temp = temp;
    this.code = code;
    this.windSpeed = windSpeed;
    this.pressure = pressure;

  }

  public int getCode() {
    return code;
  }

  public double getTemp() {
    return temp;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public double getPressure() {
    return pressure;
  }
}
