package com.cyou.config.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INDEX_PIC")
public class IndexPic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//自增id
		@Id @GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="ID")
		private Integer id;
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@Column(name="TITLE",length=64)
		private String title;
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Column(name="DESCRIPTION",length=512)
		private String description;
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Column(name="PATH",length=255)
		private String path;
		
		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		@Column(name="RANK")
		private Integer rank;
		

		public Integer getRank() {
			return rank;
		}

		public void setRank(Integer rank) {
			this.rank = rank;
		}

		@Column(name="STATUS")
		private Integer status;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
}
