package com.dream.ccms.controller.request;

/**
 * @author asus
 *
 */
public class Op {

	private boolean ad =true;
	private boolean vdaf =true;
	private boolean s =true;
	private boolean g =true;
	private boolean y =true;
	private boolean re =true ;
	
	public int opnum() {
		int i=0;
		if (this.ad) i++;
		if (this.vdaf) i++;
		if (this.s) i++;
		if (this.g) i++;
		if (this.y) i++;
		if (this.re) i++;
		return i;
	}
	public boolean isAd() {
		return ad;
	}
	public void setAd(boolean ad) {
		this.ad = ad;
	}
	public boolean isVdaf() {
		return vdaf;
	}
	public void setVdaf(boolean vdaf) {
		this.vdaf = vdaf;
	}
	public boolean isS() {
		return s;
	}
	public void setS(boolean s) {
		this.s = s;
	}
	public boolean isG() {
		return g;
	}
	public void setG(boolean g) {
		this.g = g;
	}
	public boolean isY() {
		return y;
	}
	public void setY(boolean y) {
		this.y = y;
	}
	public boolean isRe() {
		return re;
	}
	public void setRe(boolean re) {
		this.re = re;
	}
	

}
