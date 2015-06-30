package com.gamesvr.model;

import java.util.List;
import java.util.Map;

import com.gamesvr.po.ServerInfoExt;
import com.gamesvr.framework.util.Pagination;

public class ServerInfoModel {

	/************************系统预定义字段，勿动！*****************************/
	private ServerInfoExt serverInfoExt = new ServerInfoExt();
	
	private ServerInfoExt serverInfoQueryCon = new ServerInfoExt();
	
	private Pagination pp = new Pagination();
	
	private Map<String, Object> queryParams;
	
	private Long[] checkId;
	
	private Long dataId;
	
	private List<ServerInfoExt> items;
	
	private String sortType;
	
	private String querySort;
	
	public ServerInfoExt getServerInfoExt() {
		return serverInfoExt;
	}

	public void setServerInfoExt(ServerInfoExt serverInfoExt) {
		this.serverInfoExt = serverInfoExt;
	}

	public ServerInfoExt getServerInfoQueryCon() {
		return serverInfoQueryCon;
	}

	public void setServerInfoQueryCon(ServerInfoExt serverInfoQueryCon) {
		this.serverInfoQueryCon = serverInfoQueryCon;
	}

	public Pagination getPp() {
		return pp;
	}

	public void setPp(Pagination pp) {
		this.pp = pp;
	}
	
	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
	
	public Long[] getCheckId() {
		return checkId;
	}

	public void setCheckId(Long[] checkId) {
		this.checkId = checkId;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public List<ServerInfoExt> getItems() {
		return items;
	}

	public void setItems(List<ServerInfoExt> items) {
		this.items = items;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	public String getQuerySort() {
		return querySort;
	}

	public void setQuerySort(String querySort) {
		this.querySort = querySort;
	}
	
	/************************自定义字段区域开始*****************************/
	
	
	/************************自定义字段区域结束*****************************/
}