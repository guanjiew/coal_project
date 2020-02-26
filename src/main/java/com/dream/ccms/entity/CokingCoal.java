package com.dream.ccms.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "coal")
public class CokingCoal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String code;
	private BigDecimal price;
	private BigDecimal ad;
	private BigDecimal vdaf;
	private BigDecimal s;
	private BigDecimal g;
	private BigDecimal y;
	private BigDecimal re;
	
	private BigDecimal minPercent=BigDecimal.ZERO;
	@Transient
	private boolean opFlag=true;
	protected boolean   objectInactive = false;
	@Version
	@Column(name = "version")
	protected int version = 0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public boolean isObjectInactive() {
		return objectInactive;
	}
	public void setObjectInactive(boolean objectInactive) {
		this.objectInactive = objectInactive;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAd() {
		return ad;
	}
	public void setAd(BigDecimal ad) {
		this.ad = ad;
	}
	public BigDecimal getVdaf() {
		return vdaf;
	}
	public void setVdaf(BigDecimal vdaf) {
		this.vdaf = vdaf;
	}
	public BigDecimal getS() {
		return s;
	}
	public void setS(BigDecimal s) {
		this.s = s;
	}
	public BigDecimal getG() {
		return g;
	}
	public void setG(BigDecimal g) {
		this.g = g;
	}
	public BigDecimal getY() {
		return y;
	}
	public void setY(BigDecimal y) {
		this.y = y;
	}
	public BigDecimal getRe() {
		return re;
	}
	public void setRe(BigDecimal re) {
		this.re = re;
	}
	public BigDecimal getMinPercent() {
		return minPercent;
	}
	public void setMinPercent(BigDecimal minPercent) {
		this.minPercent = minPercent;
	}
	public boolean isOpFlag() {
		return opFlag;
	}
	public void setOpFlag(boolean opFlag) {
		this.opFlag = opFlag;
	}
	
   
	
}
