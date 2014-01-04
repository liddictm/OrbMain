package com.noahbutler.orb;

import java.util.ArrayList;

public class SaveFile {

	int bulletSelectedIndex = 0;
	
	int shipSelectedIndex = 0;
	int gunTipSelectedIndex = 0;
	int gunBarrelSelectedIndex = 0;
	int gunBaseSelectedIndex = 0;
	public SaveFile() {
		//try loading a saved file
	}
	
	public int getBulletSelectedIndex() {
		return this.bulletSelectedIndex;
	}

	public int getShipSelectedIndex() {
		return this.shipSelectedIndex;
	}

	public int getGunTipSelectedIndex() {
		return this.gunTipSelectedIndex;
	}

	public int getGunBarrelSelectedIndex() {
		return this.gunBarrelSelectedIndex;
	}

	public int getGunBaseSelectedIndex() {
		return this.gunBaseSelectedIndex;
	}

	public void setBulletSelectedIndex(int bulletSelectedIndex) {
		this.bulletSelectedIndex = bulletSelectedIndex;
	}

	public void setShipSelectedIndex(int shipSelectedIndex) {
		this.shipSelectedIndex = shipSelectedIndex;
	}

	public void setGunTipSelectedIndex(int gunTipSelectedIndex) {
		this.gunTipSelectedIndex = gunTipSelectedIndex;
	}

	public void setGunBarrelSelectedIndex(int gunBarrelSelectedIndex) {
		this.gunBarrelSelectedIndex = gunBarrelSelectedIndex;
	}

	public void setGunBaseSelectedIndex(int gunBaseSelectedIndex) {
		this.gunBaseSelectedIndex = gunBaseSelectedIndex;
	}

}
