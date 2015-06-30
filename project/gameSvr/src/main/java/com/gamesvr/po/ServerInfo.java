package com.gamesvr.po;

import java.util.Date;

public class ServerInfo{

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
	
	private String serverName;
	
	public String getServerName(){
		return serverName;
	}
	
	public void setServerName(String serverName){
		this.serverName = serverName;
	}
	private String serverIp;
	
	public String getServerIp(){
		return serverIp;
	}
	
	public void setServerIp(String serverIp){
		this.serverIp = serverIp;
	}
	private Long serverAlias;
	
	public Long getServerAlias(){
		return serverAlias;
	}
	
	public void setServerAlias(Long serverAlias){
		this.serverAlias = serverAlias;
	}
	private String serverRemark;
	
	public String getServerRemark(){
		return serverRemark;
	}
	
	public void setServerRemark(String serverRemark){
		this.serverRemark = serverRemark;
	}
	private Integer status;
	
	public Integer getStatus(){
		return status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
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
        if (!(object instanceof ServerInfo))
        {
            return false;
        }
        final ServerInfo that = (ServerInfo)object;
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
	
	public ServerInfo cloneServerInfo (){
        
		ServerInfo newObj = null;
        try
        {
            newObj = (ServerInfo)org.apache.commons.beanutils.BeanUtils.cloneBean(this);            
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