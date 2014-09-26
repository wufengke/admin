package com.cyou.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dictionary entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="DICTIONARY")
public class Dictionary implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6216057797182379811L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Short id;
	@Column
	private String groupName;
	@Column
	private String dictKey;
	@Column
	private String dictValue;
	@Column
	private Short rank;
	@Column
	private Byte status;
	@Column
	private String content;
	@Column
	private Short pid;
	@Column
	private String index;

	// Constructors

	/** default constructor */
	public Dictionary() {
	}

	/** minimal constructor */
	public Dictionary(String groupName, String dictKey, String dictValue,
			Short rank, Byte status, Short pid) {
		this.groupName = groupName;
		this.dictKey = dictKey;
		this.dictValue = dictValue;
		this.rank = rank;
		this.status = status;
		this.pid = pid;
	}

	/** full constructor */
	public Dictionary(String groupName, String dictKey, String dictValue,
			Short rank, Byte status, String content, Short pid, String index) {
		this.groupName = groupName;
		this.dictKey = dictKey;
		this.dictValue = dictValue;
		this.rank = rank;
		this.status = status;
		this.content = content;
		this.pid = pid;
		this.index = index;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDictKey() {
		return this.dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getDictValue() {
		return this.dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Short getRank() {
		return this.rank;
	}

	public void setRank(Short rank) {
		this.rank = rank;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getPid() {
		return this.pid;
	}

	public void setPid(Short pid) {
		this.pid = pid;
	}

	public String getIndex() {
		return this.index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dictKey == null) ? 0 : dictKey.hashCode());
		result = prime * result
				+ ((dictValue == null) ? 0 : dictValue.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Dictionary other = (Dictionary) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dictKey == null) {
			if (other.dictKey != null)
				return false;
		} else if (!dictKey.equals(other.dictKey))
			return false;
		if (dictValue == null) {
			if (other.dictValue != null)
				return false;
		} else if (!dictValue.equals(other.dictValue))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
}