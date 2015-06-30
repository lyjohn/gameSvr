package com.gamesvr.po;

import java.util.Date;

public class GameInfo{

	/**
	 * 游戏信息
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	private String gameName;
	
	public String getGameName(){
		return gameName;
	}
	
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	private Long gameCategory;
	
	public Long getGameCategory(){
		return gameCategory;
	}
	
	public void setGameCategory(Long gameCategory){
		this.gameCategory = gameCategory;
	}
	private String gameRemark;
	
	public String getGameRemark(){
		return gameRemark;
	}
	
	public void setGameRemark(String gameRemark){
		this.gameRemark = gameRemark;
	}
	private String gameVersion;
	
	public String getGameVersion(){
		return gameVersion;
	}
	
	public void setGameVersion(String gameVersion){
		this.gameVersion = gameVersion;
	}
	private java.util.Date updateTime;
	
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	private java.util.Date releaseTime;
	
	public java.util.Date getReleaseTime(){
		return releaseTime;
	}
	
	public void setReleaseTime(java.util.Date releaseTime){
		this.releaseTime = releaseTime;
	}
	private Boolean isDel;
	
	public Boolean getIsDel(){
		return isDel;
	}
	
	public void setIsDel(Boolean isDel){
		this.isDel = isDel;
	}
	private java.util.Date createTime;
	
	public java.util.Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	private String createBy;
	
	public String getCreateBy(){
		return createBy;
	}
	
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	private java.util.Date lastUpdateTime;
	
	public java.util.Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	private String lastUpdateBy;
	
	public String getLastUpdateBy(){
		return lastUpdateBy;
	}
	
	public void setLastUpdateBy(String lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof GameInfo))
        {
            return false;
        }
        final GameInfo that = (GameInfo)object;
        if (this.id == null || that.getId() == null || !this.id.equals(that.getId()))
        {
            return false;
        }
        return true;
    }
	
	public int hashCode() {
		int hashCode = 0;
		hashCode = 29 * hashCode + (id == null ? 0 : id.hashCode());
		return hashCode;
	}
	
	public String toString() {
		try {
			return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this);
		} catch (Exception e) {
			return "";
		}

	}
	
	public GameInfo cloneGameInfo (){
        
		GameInfo newObj = null;
        try
        {
            newObj = (GameInfo)org.apache.commons.beanutils.BeanUtils.cloneBean(this);            
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (java.lang.reflect.InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return newObj;
	}
}