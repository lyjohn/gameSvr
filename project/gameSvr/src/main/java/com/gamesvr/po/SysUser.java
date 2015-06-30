package com.gamesvr.po;

import java.util.Date;

public class SysUser{

	/**
	 * 系统用户
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private String loginName;
	
	public String getLoginName(){
		return loginName;
	}
	
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	private String loginPwd;
	
	public String getLoginPwd(){
		return loginPwd;
	}
	
	public void setLoginPwd(String loginPwd){
		this.loginPwd = loginPwd;
	}
	private String userName;
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	private String userAvatar;
	
	public String getUserAvatar(){
		return userAvatar;
	}
	
	public void setUserAvatar(String userAvatar){
		this.userAvatar = userAvatar;
	}
	private String userRemark;
	
	public String getUserRemark(){
		return userRemark;
	}
	
	public void setUserRemark(String userRemark){
		this.userRemark = userRemark;
	}
	private String tel;
	
	public String getTel(){
		return tel;
	}
	
	public void setTel(String tel){
		this.tel = tel;
	}
	private String email;
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	private Boolean isAdmin;
	
	public Boolean getIsAdmin(){
		return isAdmin;
	}
	
	public void setIsAdmin(Boolean isAdmin){
		this.isAdmin = isAdmin;
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
	private java.util.Date lastLoginTime;
	
	public java.util.Date getLastLoginTime(){
		return lastLoginTime;
	}
	
	public void setLastLoginTime(java.util.Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	private String lastLoginIP;
	
	public String getLastLoginIP(){
		return lastLoginIP;
	}
	
	public void setLastLoginIP(String lastLoginIP){
		this.lastLoginIP = lastLoginIP;
	}

	public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof SysUser))
        {
            return false;
        }
        final SysUser that = (SysUser)object;
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
	
	public SysUser cloneSysUser (){
        
		SysUser newObj = null;
        try
        {
            newObj = (SysUser)org.apache.commons.beanutils.BeanUtils.cloneBean(this);            
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