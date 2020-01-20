package com.zlsrj.wms.common.api;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

public class CommonPage<T> {
	private Long pageNum;
	private Long pageSize;
	private Long totalPage;
	private Long total;
	private List<T> list;
	private T footer;

//	/**
//	 * 将PageHelper分页后的list转为分页信息
//	 */
//	public static <T> CommonPage<T> restPage(List<T> list) {
//		CommonPage<T> result = new CommonPage<T>();
//		PageInfo<T> pageInfo = new PageInfo<T>(list);
//		result.setTotalPage(pageInfo.getPages());
//		result.setPageNum(pageInfo.getPageNum());
//		result.setPageSize(pageInfo.getPageSize());
//		result.setTotal(pageInfo.getTotal());
//		result.setList(pageInfo.getList());
//		return result;
//	}
//
//	/**
//	 * 将SpringData分页后的list转为分页信息
//	 */
//	public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
//		CommonPage<T> result = new CommonPage<T>();
//		result.setTotalPage(pageInfo.getTotalPages());
//		result.setPageNum(pageInfo.getNumber());
//		result.setPageSize(pageInfo.getSize());
//		result.setTotal(pageInfo.getTotalElements());
//		result.setList(pageInfo.getContent());
//		return result;
//	}
	
	public static <T> CommonPage<T> restPage(IPage<T> page) {
		CommonPage<T> result = new CommonPage<T>();
		result.setTotalPage(page.getPages());
		result.setPageNum(page.getCurrent());
		result.setPageSize(page.getSize());
		result.setTotal(page.getTotal());
		result.setList(page.getRecords());
		
		return result;
	}
	
	public static <T> CommonPage<T> restPage(IPage<T> page,T footer) {
		CommonPage<T> result = new CommonPage<T>();
		result.setTotalPage(page.getPages());
		result.setPageNum(page.getCurrent());
		result.setPageSize(page.getSize());
		result.setTotal(page.getTotal());
		result.setList(page.getRecords());
		result.setFooter(footer);
		
		return result;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	public T getFooter() {
		return footer;
	}
	
	public void setFooter(T footer) {
		this.footer = footer;
	}
}
