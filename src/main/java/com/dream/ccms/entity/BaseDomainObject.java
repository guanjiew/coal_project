package com.dream.ccms.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDomainObject {

	protected Long objectId = null;
	protected String code = null;
	protected String name = null;
	protected String description = null;
	protected boolean objectInactive = false;
	protected int version = 0;
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isObjectInactive() {
		return objectInactive;
	}
	public void setObjectInactive(boolean objectInactive) {
		this.objectInactive = objectInactive;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	/* String newCodeSql = "select max(t.code) as mcode from "
             + entity.getClass().getName()
             + " as t";
             
         protected String stepUpNewBusinessCode(String maxCode) {
        long maxCodeValue = Long.parseLong(maxCode) + 1;
        String newCodeStr = StringUtil.padToLeft(String.valueOf(maxCodeValue), "0",4);
        return newCodeStr;
    }
    
             */
}
