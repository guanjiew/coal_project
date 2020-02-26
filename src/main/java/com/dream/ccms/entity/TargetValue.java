package com.dream.ccms.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name = "targetValue")
public class TargetValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	BigDecimal ad;
	BigDecimal adScope;
	BigDecimal vdaf;
	BigDecimal vdafScope;
	BigDecimal s;
	BigDecimal sScope;
	BigDecimal g;
	BigDecimal gScope;
	BigDecimal y;
	BigDecimal yScope;
	BigDecimal re;
	BigDecimal reScope;
	
	@Version
	@Column(name = "version")
	protected int version = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public BigDecimal getAd() {
		return ad;
	}
	public void setAd(BigDecimal ad) {
		this.ad = ad;
	}
	public BigDecimal getAdScope() {
		return adScope;
	}
	public void setAdScope(BigDecimal adScope) {
		this.adScope = adScope;
	}
	public BigDecimal getVdaf() {
		return vdaf;
	}
	public void setVdaf(BigDecimal vdaf) {
		this.vdaf = vdaf;
	}
	public BigDecimal getVdafScope() {
		return vdafScope;
	}
	public void setVdafScope(BigDecimal vdafScope) {
		this.vdafScope = vdafScope;
	}
	public BigDecimal getS() {
		return s;
	}
	public void setS(BigDecimal s) {
		this.s = s;
	}
	public BigDecimal getsScope() {
		return sScope;
	}
	public void setsScope(BigDecimal sScope) {
		this.sScope = sScope;
	}
	public BigDecimal getG() {
		return g;
	}
	public void setG(BigDecimal g) {
		this.g = g;
	}
	public BigDecimal getgScope() {
		return gScope;
	}
	public void setgScope(BigDecimal gScope) {
		this.gScope = gScope;
	}
	public BigDecimal getY() {
		return y;
	}
	public void setY(BigDecimal y) {
		this.y = y;
	}
	public BigDecimal getyScope() {
		return yScope;
	}
	public void setyScope(BigDecimal yScope) {
		this.yScope = yScope;
	}
	public BigDecimal getRe() {
		return re;
	}
	public void setRe(BigDecimal re) {
		this.re = re;
	}
	public BigDecimal getReScope() {
		return reScope;
	}
	public void setReScope(BigDecimal reScope) {
		this.reScope = reScope;
	}
	
	
}
