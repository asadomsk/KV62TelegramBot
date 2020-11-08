package com.botTrainTable;

public class StationInfo {

	int id;
	String heb;
	String eng;
	String rus;

	// String arb;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeb() {
		return heb;
	}

	public void setHeb(String heb) {
		this.heb = heb;
	}

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getRus() {
		return rus;
	}

	public void setRus(String rus) {
		this.rus = rus;
	}

	@Override
	public String toString() {
		return "StationInfo [id=" + id + ", heb=" + heb + ", eng=" + eng + ", rus=" + rus + "]";
	}

}
