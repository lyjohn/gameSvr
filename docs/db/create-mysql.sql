    DROP TABLE IF EXISTS SysUser;
    DROP TABLE IF EXISTS GameInfo;
    DROP TABLE IF EXISTS ServerInfo;
    DROP TABLE IF EXISTS GameServer;
    DROP TABLE IF EXISTS News;
    DROP TABLE IF EXISTS OperLog;
    create table SysUser (
        id VARCHAR(32) NOT NULL,
        loginName VARCHAR(20) not null unique,
        loginPwd VARCHAR(64) not null,
        userName VARCHAR(20) not null,
        userAvatar VARCHAR(50),
        userRemark VARCHAR(500),
        tel VARCHAR(11),
        email VARCHAR(30),
        isAdmin TINYINT(3) not null,
        createTime DATETIME not null,
        createBy VARCHAR(20) not null,
        lastLoginTime DATETIME not null,
        lastLoginIP VARCHAR(20) not null,
        PRIMARY KEY (id)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
    create table GameInfo (
        id INT(9) NOT NULL AUTO_INCREMENT,
        gameName VARCHAR(20) not null unique,
        gameCategory BIGINT(13) not null,
        gameRemark VARCHAR(200) not null,
        gameVersion VARCHAR(40) not null,
        updateTime DATE not null,
        releaseTime DATE not null,
        isDel TINYINT(3) not null,
        createTime DATETIME not null,
        createBy VARCHAR(20) not null,
        lastUpdateTime DATETIME,
        lastUpdateBy VARCHAR(20),
        PRIMARY KEY (id)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
    create table ServerInfo (
        id INT(9) NOT NULL AUTO_INCREMENT,
        serverName VARCHAR(32) not null,
        serverIp VARCHAR(32) not null,
        serverAlias BIGINT(13) not null,
        serverRemark VARCHAR(200),
        status INT(10) not null,
        isDel TINYINT(3) not null,
        createTime DATETIME not null,
        createBy VARCHAR(20) not null,
        lastUpdateTime DATETIME,
        lastUpdateBy VARCHAR(20),
        PRIMARY KEY (id)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
    create table GameServer (
        id INT(9) NOT NULL AUTO_INCREMENT,
        gameId BIGINT(13) not null,
        serverId BIGINT(13) not null,
        isDel TINYINT(3) not null,
        createTime DATETIME not null,
        createBy VARCHAR(20) not null,
        PRIMARY KEY (id)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
    create table News (
        id INT(9) NOT NULL AUTO_INCREMENT,
        newsTitle VARCHAR(32) not null,
        newsContent VARCHAR(5000) not null,
        readCount INT(10) not null,
        createBy VARCHAR(32) not null,
        createTime DATETIME not null,
        PRIMARY KEY (id)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
    create table OperLog (
        id INT(9) NOT NULL AUTO_INCREMENT,
        userName VARCHAR(40) not null,
        userIp VARCHAR(20) not null,
        logAction INT(10) not null,
        logDesc VARCHAR(20) not null,
        logTime DATETIME not null,
        logObjId BIGINT(13),
        logContent VARCHAR(1000) not null,
        PRIMARY KEY (id)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

	alter table SysUser add index SysUser_loginName(loginName);
	alter table SysUser add index SysUser_email(email);
	alter table GameInfo add index GameInfo_gameName(gameName);
	alter table GameInfo add index GameInfo_gameCategory(gameCategory);
	alter table GameInfo add index GameInfo_createBy(createBy);
	alter table GameInfo add index GameInfo_lastUpdateBy(lastUpdateBy);
	alter table ServerInfo add index ServerInfo_serverName(serverName);
	alter table ServerInfo add index ServerInfo_createBy(createBy);
	alter table ServerInfo add index ServerInfo_lastUpdateBy(lastUpdateBy);
	alter table GameServer add index GameServer_gameId(gameId);
	alter table GameServer add index GameServer_serverId(serverId);
	alter table GameServer add index GameServer_createBy(createBy);
	alter table News add index News_createBy(createBy);

