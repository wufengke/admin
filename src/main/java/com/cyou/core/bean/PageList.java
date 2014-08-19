package com.cyou.core.bean;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

public class PageList implements PaginatedList{
	/**总记录数 初始为0*/
	private int fullListSize=0;
	
	/**每页列表 */
	private List<?> list;
	
	/**每页记录数 初始为15*/
	private int objectsPerPage=10;
	
	/**当前页码 初始为1*/
	private int pageNumber=1;
	
	/**返回一个查询的id */  
	private String searchId;
	
	/** 获取根据哪一列排序*/  
	private String sortCriterion;
	
	/**升序还是降序 */ 
	private SortOrderEnum sortDirection;
	
	/**是否导出 true为导出 false为不导出*/
	private boolean isExport = false;
	
	public boolean isExport() {
		return isExport;
	}


	public void setExport(boolean isExport) {
		this.isExport = isExport;
	}


	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}


	public void setList(List<?> list) {
		this.list = list;
	}


	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public int getFullListSize() {
		return fullListSize;
	}

	public List<?> getList() {
		return list;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getSearchId() {
		return searchId;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

}
