package com.dream.ccms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/*  
 * javax.persistence.GeneratedValue
 * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO. 
 * TABLE：使用一个特定的数据库表格来保存主键。 
 * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列
 * IDENTITY：主键由数据库自动生成（主要是自动增长型） 
 * AUTO：主键由程序控制。
 * 
 * org.hibernate.annotations.GenericGenerator
 */

//@Entity
//@Table(name = "testperson")
public class TestPerson {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long peson_id;
	private String name;
	private int age;
	@Version
	@Column(name = "version")
	protected int version = 0;

	public Long getPeson_id() {
		return peson_id;
	}

	public void setPeson_id(Long peson_id) {
		this.peson_id = peson_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
