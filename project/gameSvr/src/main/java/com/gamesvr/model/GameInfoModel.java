package com.gamesvr.model;

import java.util.List;
import java.util.Map;

import com.gamesvr.po.GameInfoExt;
import com.gamesvr.framework.util.Pagination;

public class GameInfoModel {

	/************************系统预定义字段，勿动！*****************************/
	private GameInfoExt gameInfoExt = new GameInfoExt();
	
	private GameInfoExt gameInfoQueryCon = new GameInfoExt();
	
	private Pagination pp = new Pagination();
	
	private Map<String, Object> queryParams;
	
	private Long[] checkId;
	
	private Long dataId;
	
	private List<GameInfoExt> items;
	
	private String sortType;
	
	private String querySort;
	
	public GameInfoExt getGameInfoExt() {
		return gameInfoExt;
	}

	public void setGameInfoExt(GameInfoExt gameInfoExt) {
		this.gameInfoExt = gameInfoExt;
	}

	public GameInfoExt getGameInfoQueryCon() {
		return gameInfoQueryCon;
	}

	public void setGameInfoQueryCon(GameInfoExt gameInfoQueryCon) {
		this.gameInfoQueryCon = gameInfoQueryCon;
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

	public List<GameInfoExt> getItems() {
		return items;
	}

	public void setItems(List<GameInfoExt> items) {
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