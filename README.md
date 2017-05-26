# fleaMarket
跳蚤市场，二手交易网站  
http://47.94.9.151:8080/WenLiFleaMarket 

下面是阿里部署的经验，记录一下
## 阿里云部署项目（jdk+mysql+tomcat）
> 

>版本：Java运行环境（Centos7 64 | JDK8|Tomcat8）
>
>因为购买的是拥有java运行环境的版本。磁盘预装了jdk8、tomcat8。后来发现里面还装好了mysql 但是有问题不能使用。下面会修改配置。 
>
> jdk8根目录：/user/java/jdk1.8.0_111/..  
>tomcat8根目录：/home/tomcat/apache-tomcat-8.5.8/..  
>mysql根目录： 没找到。。。

>- ## 安装AppNode
> 参照 ：++https://www.appnode.com/install++  
>可视化、简易高效、操作透明、高可扩展的 Linux 服务器集群管理面板。  
>用面板，主要用文件管理、上传下载功能。省去配置ftp的麻烦。
>> 问题：安装后无法进入面板（上述安装端口号设置为5555）  
>>解决：修改阿里云ECS安全组规则（入方向）  
>>>添加安全组规则  
>>>设置协议类型：自定义TCP  
>>>设置端口范围：5555/5555（相应的端口号）    
>>>设置授权对象：0.0.0.0/0

>- ## 登录AppNode面板
>节点列表中，安装受控端

>- ## 修改mysql密码
>>1、编辑 MySQL 配置文件 my.cnf(以实际 my.cnf 配置文件路径为准)
>>```
>>vim /etc/my.cnf[mysqld]skip-grant-tables  #增加
>>```
>>2、重启 MySQL 服务(以实际启动脚本位置为准)
>>```
>>/etc/init.d/mysqld restart
>>```
>>3、登录数据库  
>>/usr/bin/mysql 输入如下命令：
>>```
>>UPDATE user SET authentication_string = password ('root') WHERE User = 'root' ;
>>```
>>4、删除或者注释第一步骤中添加的 spip-grant-tables
>>5、重启 MySQL 服务
>>```
>>/etc/init.d/mysqld restart
>>```
>>6、使用新密码测试

>- ## 导入项目sql文件
>> 1、把本地的sql文件上传到服务器上。利用AppNode上传功能很方便。记着上传路径。  
>> 2、首先建空数据库
>> ```
>>mysql>create database abc;
>>```
>>3、导入数据库
>>方法一(我用的，亲测可用)：  
>>（1）选择数据库  
>> ```
>>mysql>use abc;  
>> ```
>>（2）设置数据库编码 
>> ```
>>mysql>set names utf8;  
>> ```
>>（3）导入数据（注意sql文件的路径）
>> ```
>>mysql>source /home/abc/abc.sql; 
>># /home/abc/abc.sql是上传的sql文件
>> ```
>>方法二(没用过，网上说可以)：
>>```
>>mysql -u用户名 -p密码 数据库名 < 数据库名.sql
>>#mysql -uabc_f -p abc < abc.sql
>>```

>- ## 启动tomcat
>>1、进入Tomcat下的bin目录
>>```
>>cd //home/tomcat/apache-tomcat-8.5.8/bin
>>```
>>2、使用Tomcat关闭命令
>>```
>>./shutdown.sh
>>```
>>3、查看Tomcat是否以关闭
>>```
>>ps -ef|grep java
>>```
>>4、如果显示以下相似信息，说明Tomcat还没有关闭
>>
>>```
>>root      7010     1  0 Apr19 ?        00:30:13 /usr/local/java/bin/java 
>>-Djava.util.logging.config.file=/usr/local/tomcat/conf/logging.properties 
>>-Djava.awt.headless=true 
>>-Dfile.encoding=UTF-8 -server -Xms1024m -Xmx1024m 
>>-XX:NewSize=256m -XX:MaxNewSize=256m -XX:PermSize=256m -XX:MaxPermSize=256m 
>>-XX:+DisableExplicitGC  -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager 
>>-Djava.endorsed.dirs=/usr/local/tomcat/endorsed 
>>-classpath /usr/local/tomcat/bin/bootstrap.jar 
>>-Dcatalina.base=/usr/local/tomcat 
>>-Dcatalina.home=/usr/local/tomcat 
>>-Djava.io.tmpdir=/usr/local/tomcat/temp org.apache.catalina.startup.Bootstrap start
>>```
>>5、如果你想直接干掉Tomcat，你可以使用kill命令，直接杀死Tomcat进程
>>```
>> kill -9 7010
>>```
>>6、然后继续查看Tomcat是否关闭
>>```
>> ps -ef|grep java
>>```
>>7、如果出现以下信息，则表示Tomcat已经关闭
>>```
>>root      7010     1  0 Apr19 ?        00:30:30 [java] <defunct>
>>```
>>8、最后，启动Tomcat
>>```
>>./startup.sh 
>>```

>- ## 上传war包
>把自己的项目打包为war  
>上传到tomcat/webapps/下
