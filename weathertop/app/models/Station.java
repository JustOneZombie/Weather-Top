package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
  //FIELDS
  public String name;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();
  public String codeOutput;
  public Reading lastReading;
  public double tempFar;
  public double tempCel;
  public double latestPressure;
  public String windOutput;
  public String windDirection;
  public double windChill;
  //CONSTRUCTORS

  public Station(String name) {
    this.name = name;
  }

//GETTERS

  public String getCodeOutput() {
    this.codeOutput = weatherCode();
    return codeOutput;
  }
  public double getFarOutput() {
    this.tempFar = getTempFar();
    return tempFar;
  }
  public double getCelOutput() {
    this.tempCel = getTempCel();
    return tempCel;
  }
  public String getWindOutput() {
    this.windOutput = windCode();
    return windOutput;
  }
  public double getLatestPressure(){
    this.latestPressure = latestPressure();
    return latestPressure;
  }
  public String getWindDirection() {
    this.windDirection = windDirection();
    return windDirection;
  }
  public double getWindChill(){
    this.windChill = windChill();
    return windChill;
  }
  //METHODS

  public Reading getLatestReading() {
    int lastIndex = readings.size() - 1;
    lastReading = readings.get(lastIndex);
    return lastReading;
  }

  public String weatherCode() {
    String codeOutput = "null";
    if (readings.size()!=0) {
      Reading reading = getLatestReading();
      int code = reading.code;
      if (code == 100) codeOutput = "Clear";
      else if (code == 200) codeOutput = "Partial Clouds";
      else if (code == 300) codeOutput = "Cloudy";
      else if (code == 400) codeOutput = "Light Showers";
      else if (code == 500) codeOutput = "Heavy Showers";
      else if (code == 600) codeOutput = "Rain";
      else if (code == 700) codeOutput = "Snow";
      else if (code == 800) codeOutput = "Thunder";
      else codeOutput = "null";
    }
    return codeOutput;

  }
  public double getTempCel() {
    double temp = 0.0;
    if (readings.size()!=0) {
      Reading reading = getLatestReading();
      temp = reading.temperature;
    }
    return temp;
  }
  public double getTempFar() {
    double temp = 0.0;
    if (readings.size()!=0) {
      Reading reading = getLatestReading();
      temp = (reading.temperature * 9.5) + 32;
    }
    return temp;
  }

  public String windCode() {
    double code = 0.0;
    String windOutput = "null";
    if (readings.size()!=0) {
      Reading reading = getLatestReading();
      code = reading.windSpeed;
      windOutput = "null";

      if ((code >= 0) && (code <= 1)) windOutput = "0";
      else if ((code >= 1) && (code <= 5)) windOutput = "1";
      else if ((code >= 6) && (code <= 11)) windOutput = "2";
      else if ((code >= 12) && (code <= 19)) windOutput = "3";
      else if ((code >= 20) && (code <= 28)) windOutput = "4";
      else if ((code >= 29) && (code <= 38)) windOutput = "5";
      else if ((code >= 39) && (code <= 49)) windOutput = "6";
      else if ((code >= 50) && (code <= 61)) windOutput = "8";
      else if ((code >= 62) && (code <= 74)) windOutput = "9";
      else if ((code >= 75) && (code <= 88)) windOutput = "10";
      else if ((code >= 89) && (code <= 102)) windOutput = "11";
      else if ((code >= 103) && (code <= 117)) windOutput = "12";

      else windOutput = "null";
    }
    return windOutput;
  }
  public double latestPressure() {
    double pressure = 0.0;
    if (readings.size()!=0) {
      Reading reading = getLatestReading();
      pressure = reading.pressure;
    }
      return pressure;
  }
  public String windDirection() {
    double code = 0.0;
    String windDirection = "null";
    if (readings.size()!=0) {
      Reading reading = getLatestReading();
      code = reading.windDirection;
      windDirection = "null";

      if ((code >= 348.75) && (code <= 360)) windDirection = "North";
      else if ((code >= 0) && (code <= 11.25)) windDirection = "North";
      else if ((code >= 11.25) && (code <= 33.75)) windDirection = "North North East";
      else if ((code >= 33.75) && (code <= 56.25)) windDirection = "North East";
      else if ((code >= 56.25) && (code <= 78.75)) windDirection = "East North East";
      else if ((code >= 78.75) && (code <= 101.25)) windDirection = "East";
      else if ((code >= 101.25) && (code <= 123.75)) windDirection = "East South East";
      else if ((code >= 123.75) && (code <= 146.25)) windDirection = "South East";
      else if ((code >= 146.25) && (code <= 168.75)) windDirection = "South South East";
      else if ((code >= 168.75) && (code <= 191.25)) windDirection = "South";
      else if ((code >= 191.25) && (code <= 213.75)) windDirection = "South South West";
      else if ((code >= 213.75) && (code <= 236.25)) windDirection = "South West";
      else if ((code >= 236.25) && (code <= 258.75)) windDirection = "West South West";
      else if ((code >= 258.75) && (code <= 281.25)) windDirection = "West";
      else if ((code >= 281.25) && (code <= 303.75)) windDirection = "West North West";
      else if ((code >= 303.75) && (code <= 326.25)) windDirection = "North West";
      else if ((code >= 326.25) && (code <= 348.75)) windDirection = "North North West";

      else windDirection = "null";
    }
    return windDirection;
  }
  public double windChill() {
    double windChill = 0.0;
    if (readings.size()!=0) {
      windChill = 0.0;
      Reading reading = getLatestReading();
      double temp = reading.temperature;
      double speed = reading.windSpeed;

      windChill = 13.12 + (0.6215 * temp) - (11.37 * Math.pow(speed, 0.16)) + ((0.3965 * temp) * Math.pow(speed, 0.16));
      windChill = (Math.round(windChill * 100.0) / 100.0);
    }
    return windChill;
  }
}
