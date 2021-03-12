package com.example.CovidTracker.model;

public class CasesStats {
private String State;
private String Country;
private int Cases;
private int changeincases;
public int getChangeincases() {
	return changeincases;
}
public void setChangeincases(int changeincases) {
	this.changeincases = changeincases;
}
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
public String getCountry() {
	return Country;
}
public void setCountry(String country) {
	Country = country;
}
public int getCases() {
	return Cases;
}
public void setCases(int cases) {
	Cases = cases;
}
@Override
public String toString() {
	return "CasesStats [State=" + State + ", Country=" + Country + ", Cases=" + Cases + "]";
}

}
