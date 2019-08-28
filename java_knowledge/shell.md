TOP 
-----------------------------------
U cpu 排序， M 内存排序
顶部的内存信息可以在top运行时按E切换，每次切换转换率为1000，只是没有单位，切换的单位为 k,m,g,t,p：
底下的进程信息按e切换，每次切换转换率为1000，切换的单位也是 k,m,g,t,p：

Debug args:
-----------------------------------
-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8029,server=y,suspend=n

Shell:
-----------------------------------
- $0	当前脚本的文件名
- $n	传递给脚本或函数的参数。n 是一个数字，表示第几个参数。例如，第一个参数是$1，第二个参数是$2。
- $#	传递给脚本或函数的参数个数。
- $*	传递给脚本或函数的所有参数。
- $@	传递给脚本或函数的所有参数。但是当它们被双引号(" ")包含时，"$\*" 会将所有的参数作为一个整体，以"$1 $2 … $n"的形式输出所有参数；"$@" 会将各个参数分开，以"$1" "$2" … "$n" 的形式输出所有参数。
- $?	上个命令的退出状态，或函数的返回值。
- $$	当前Shell进程ID。对于 Shell 脚本，就是这些脚本所在的进程ID。
- PS命令查看进程启动时间和运行时间 ps -p PID -o lstart,etime,cmd
- 	ps -eo pid,lstart,etime,cmd | grep nginx
- 批量解压多个文件 ls obs_service_osc.run.log.2019-07-19*.tar.gz | xargs -n1 tar xzvf
Git 删除无版本文件 git clean -f -d or git clean -fd.
查看网络连接各项资本统计： netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'
查看linux系统参数：sysctl -a|grep net.ipv4.tcp_tw
	修改参数： vim /etc/sysctl.conf 启动重用 net.ipv4.tcp_tw_reuse=1
	参数生效： sysctl -p
	socket最大链接数配置： /etc/security/limits.conf * soft nofile 32768 和 * hard nofile 32768
for i in {1..20}; do echo $i & done
ps -ef|grep -w xx|grep java|awk -F " " '{print $2}'|xargs kill -9;
Xshell
	清屏：Ctrl+Shift+l   （相当于翻页，历史日志可以网上翻能看到）                       
	滚动缓冲区清除：Ctrl+Shift+b  （只保留当前显示的日志，在滚动条上方的历史被清除）
	屏幕和滚动区清除：Ctrl+Shift+a  （日志全部清空，且当前页面显示空白）


查看连接相关命令
-----------------------------------
- 查看哪些IP连接本机
	netstat -an
- 统计httpd协议连接数
	ps -ef|grep httpd|wc -l
- 统计已连接上的，状态为“established
	netstat -na|grep ESTABLISHED|wc -l
- 查看apache当前并发访问数：
	netstat -an | grep ESTABLISHED | wc -l
- 查看有多少个进程数：
	ps aux|grep httpd|wc -l

防火墙：
-----------------------------------
- #centos7启动防火墙
	systemctl start firewalld.service
- #centos7停止防火墙/关闭防火墙
	systemctl stop firewalld.service
- #centos7重启防火墙
	systemctl restart firewalld.service

- #设置开机启用防火墙
	systemctl enable firewalld.service
- #设置开机不启动防火墙
	systemctl disable firewalld.service

- #centos7查看防火墙所有信息
	firewall-cmd --list-all
- #centos7查看防火墙开放的端口信息
	firewall-cmd --list-ports
- 端口防火墙
	firewall-cmd --zone=public --add-port=80/tcp --permanent
- 重启防火墙
	firewall-cmd --reload
- 查看防火墙状态
	firewall-cmd --get-services

Hadoop开启调试模式
-----------------------------------
- 打开 export HADOOP_ROOT_LOGGER=DEBUG,console
- 关闭 export HADOOP_ROOT_LOGGER=INFO,console

Hdfs常用命令: https://hadoop.apache.org/docs/r1.0.4/cn/hdfs_shell.html
-----------------------------------
上传文件
hdfs dfs -put localfile hdfs://host:port/hadoop/hadoopfile
查询目录文件列表
hdfs dfs -ls hdfs://host:port/tmp
下载文件到本地
hdfs dfs -get hdfs://host:port/tmp/test.log aaa.log
创建目录(递归创建)
hdfs dfs -mkdir hdfs://host:port/tmp/user/hadoop/xxx
将文件从源路径移动到目标路径。
hdfs dfs -mv hdfs://host:port/file1 hdfs://host:port/file2 hdfs://host:port/file3 hdfs://host:port/dir1
查询文件状态 (%b 文件大小，%o Block大小，%n 文件名，%r 副本个数，%y 最后一次修改日期和时间)
hdfs dfs -stat "%b %o %n %r %y" hdfs://host:port/tmp/test.log
移除文件
hdfs dfs -rm hdfs://host:port/file
拷贝文件到本地
hdfs dfs -copyToLocal hdfs://host:port/sampson/better ./
浏览文件
hdfs dfs -cat hdfs://host:port/sampson/better ./
df -h
du -sh *
替换用户和用户组
chown obs root.war && chgrp obsgrp root.war
ps -ef | grep myhdpc | awk -F ' ' '{print $2}' | xargs kill -9

FSShell 对应 ClientProtocol
ls  -> ClientProtocol.getFileInfo
	   ClientProtocol.getListing
put -> ClientProtocol.getFileInfo
	   ClientProtocol.getFileInfo
	   ClientProtocol.getFileInfo
	   ClientProtocol.create
	   ClientProtocol.getFileInfo
	   ClientProtocol.addBlock
	   ClientProtocol.getServerDefaults
	   ClientProtocol.complete
	   ClientProtocol.rename
rm  -> ClientProtocol.getFileInfo
	   ClientProtocol.getFileInfo
	   ClientProtocol.getServerDefaults
	   ClientProtocol.getFileInfo
	   ClientProtocol.mkdirs
	   ClientProtocol.getFileInfo
	   ClientProtocol.rename2
mkdir -> ClientProtocol.getFileInfo
	   ClientProtocol.getFileInfo
	   ClientProtocol.mkdirs
mv  -> ClientProtocol.getFileInfo
	   ClientProtocol.getFileInfo
	   ClientProtocol.getFileInfo
	   ClientProtocol.rename

Mongo Shell:
-----------------------------------
mongo --username alice --password --authenticationDatabase admin --host mongodb0.examples.com --port 28015
show dbs
use databasename

查询表
	show collections
	show tables
	db.getCollectionNames()

use collectionname

查询：
	db.表名.find():查询表中所有数据
	db.表名.find(条件):按条件查询
	db.表名.findOne(条件):查询第一条(支持条件)
	db.表名.find().limit(数量):限制数量
	db.表名.find().skip(数量):跳过指定数量

修改：
	db.表名.update({"条件字段名":"字段值"},{$set:{"要修改的字段名":"修改后的字段值"}});

删除：
	db.表名.remove(条件);

技巧：
	pretty() 格式化打印

正则匹配查询：
	db.kv_data.find({_id: {$regex:"^hotdir*"}}).pretty();


1、posix POSIX表示可移植操作系统接口（Portable Operating System Interface of UNIX，缩写为 POSIX ），POSIX标准定义了操作系统应该为应用程序提供的接口标准，是IEEE为要在各种UNIX操作系统上运行的软件而定义的一系列API标准的总称，其正式称呼为IEEE 1003，而国际标准名称为ISO/IEC 9945。POSIX标准意在期望获得源代码级别的软件可移植性。换句话说，为一个POSIX兼容的操作系统编写的程序，应该可以在任何其它的POSIX操作系统（即使是来自另一个厂商）上编译执行。

2、hadoop federation架构单集群200PB，单组织600PB，单NN支持4K节点

3、HDDS Hadoop Distributed Data Storage

4、HBase深度分析 https://zhuanlan.zhihu.com/p/30414252

5、Hadoop RPC 框架 https://www.jianshu.com/p/7d4f8bbd77e9

-------------------------------------
1、Elasticsearch索引原理 https://blog.csdn.net/cyony/article/details/65437708
	高可用&高伸缩 https://segmentfault.com/a/1190000015185481
	 https://dbaplus.cn/news-73-1492-1.html
2、RPC框架性能测试 https://colobu.com/2018/01/31/benchmark-2018-spring-of-popular-rpc-frameworks/
3、异地多活-----核心思想：采用多种手段，保证大部分用户的核心业务异地多活
	https://yq.aliyun.com/articles/57715
	https://zhuanlan.zhihu.com/p/42150666
	很重要的思路就是本地搞不定就用全局路由规则去异地获取，如果远程宕机，重新生成规则本地启动
	https://www.infoq.cn/article/interview-alibaba-bixuan
	阿里的成功案例：https://developer.aliyun.com/article/711635
4、btrace可以动态查看运行时方法入参和运行时间 （BTrace 是基于动态字节码修改技术(Hotswap)来实现运行时 java 程序的跟踪和替换。）
	参考：https://www.cnblogs.com/fengzheng/p/7416942.html
5、高性能的内存队列 Disruptor 
	https://tech.meituan.com/2016/11/18/disruptor.html
	https://blog.csdn.net/a78270528/article/details/79925404
jctools 高并發工具
6、开发工具 https://www.infoq.cn/article/yeQU4f_BujTYCMxaXNFc

7、plan B https://www.infoq.cn/article/8JWytrdhd*a8l7TUaOsk
8、淘宝千万级并发分布式架构的14次演进 http://ifeve.com/%e6%b7%98%e5%ae%9d%e5%8d%83%e4%b8%87%e7%ba%a7%e5%b9%b6%e5%8f%91%e5%88%86%e5%b8%83%e5%bc%8f%e6%9e%b6%e6%9e%84%e7%9a%8414%e6%ac%a1%e6%bc%94%e8%bf%9b/

9、raft 协议https://www.infoq.cn/article/building-flexible-storage-system-based-on-raft
	https://www.bilibili.com/video/av21667358/
  paxos https://zhuanlan.zhihu.com/p/31780743
10、CAP理论&NWR策略 http://www.choudan.net/2013/08/07/CAP%E7%90%86%E8%AE%BA%E5%92%8CNWR%E7%AD%96%E7%95%A5.html
	https://www.iteye.com/blog/yangshangchuan-2010574
11、两地三中心 https://www.jianshu.com/p/e0a521592c2b

12、全面理解Java内存模型 https://blog.csdn.net/suifeng3051/article/details/52611310
