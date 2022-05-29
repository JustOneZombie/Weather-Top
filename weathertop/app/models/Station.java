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
  //METHODS

  public Reading getLatestReading() {
    int lastIndex = readings.size() - 1;
    lastReading = readings.get(lastIndex);
    return lastReading;
  }

  public String weatherCode() {
    Reading reading = getLatestReading();
    int code = reading.code;
    String codeOutput = "null";

    if (code == 100) codeOutput = "Clear";
    else if (code == 200) codeOutput = "Partial Clouds";
    else if (code == 300) codeOutput = "Cloudy";
    else if (code == 400) codeOutput = "Light Showers";
    else if (code == 500) codeOutput = "Heavy Showers";
    else if (code == 600) codeOutput = "Rain";
    else if (code == 700) codeOutput = "Snow";
    else if (code == 800) codeOutput = "Thunder";
    else codeOutput = "null";
    return codeOutput;

  }
  public double getTempCel() {
    Reading reading = getLatestReading();
    double temp = reading.temp;
    return temp;
  }
  public double getTempFar() {
    Reading reading = getLatestReading();
    double temp = reading.temp;

    return (temp * 9.5) + 32;
  }

  public String windCode() {
    Reading reading = getLatestReading();
    double code = reading.windSpeed;
    String windOutput = "null";

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
    else if ((code >= 103) && (code <= 117)) windOutput = "11";

    else windOutput = "null";
    return windOutput;
  }
  public double latestPressure() {
    Reading reading = getLatestReading();
    double pressure = reading.pressure;
    return pressure;
  }
}
