package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {
  public int code;
  public double temp;
  public double windSpeed;

  public Reading(int code, double temp, double windSpeed) {
    this.temp = temp;
    this.code = code;
    this.windSpeed = windSpeed;
  }
}