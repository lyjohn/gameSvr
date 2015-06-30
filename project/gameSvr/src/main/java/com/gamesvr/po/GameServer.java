package com.gamesvr.po;

import java.util.Date;

public class GameServer{

	/**
	 * 服务器信息
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	private Long gameId;
	
	public Long getGameId(){
		return gameId;
	}
	
	public void setGameId(Long gameId){
		this.gameId = gameId;
	}
	private Long serverId;
	
	public Long getServerId(){
		return serverId;
	}
	
	public void setServerId(Long serverId){
		this.serverId = serverId;
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

	public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof GameServer))
        {
            return false;
        }
        final GameServer that = (GameServer)object;
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
	
	public GameServer cloneGameServer (){
        
		GameServer newObj = null;
        try
        {
            newObj = (GameServer)org.apache.commons.beanutils.BeanUtils.cloneBean(this);            
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