package com.gamesvr.model;

import java.util.List;
import java.util.Map;

import com.gamesvr.po.GameServerExt;
import com.gamesvr.framework.util.Pagination;

public class GameServerModel {

	/************************系统预定义字段，勿动！*****************************/
	private GameServerExt gameServerExt = new GameServerExt();
	
	private GameServerExt gameServerQueryCon = new GameServerExt();
	
	private Pagination pp = new Pagination();
	
	private Map<String, Object> queryParams;
	
	private Long[] checkId;
	
	private Long dataId;
	
	private List<GameServerExt> items;
	
	private String sortType;
	
	private String querySort;
	
	public GameServerExt getGameServerExt() {
		return gameServerExt;
	}

	public void setGameServerExt(GameServerExt gameServerExt) {
		this.gameServerExt = gameServerExt;
	}

	public GameServerExt getGameServerQueryCon() {
		return gameServerQueryCon;
	}

	public void setGameServerQueryCon(GameServerExt gameServerQueryCon) {
		this.gameServerQueryCon = gameServerQueryCon;
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

	public List<GameServerExt> getItems() {
		return items;
	}

	public void setItems(List<GameServerExt> items) {
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