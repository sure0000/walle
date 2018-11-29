# walle
walle是用于对ELK栈的监控与告警，包括对filebeat、logstash、elasticsearch组件的支持，项目包含监控模块、过滤模块、告警规则模块、客户端模块以及任务调度模块。

## 监控模块
filebeat: 存活检测
logstash: 存活检测
es: 集群健康检测、索引插入拒绝数检测

## 过滤模块
主要针对index级别的文档过滤，包括文档长度、文档出现频率以及黑名单过滤

## 告警规则模块
在不断扩展的监控指标中用于设置哪些指标需要进行告警

## 客户端模块
restful形式进行动态的查看或添加、修改配置

**添加filebeat配置**

GET /filebeat/addFilebeatConf 
Param: hostname

**查看filebeat配置**

GET /filebeat/getFilebeatConf

**添加logstash配置**

GET /logstash/addLogstashConf
Param: hostname

**查看logstash配置**

GET /logstash/getLogstashConf

**查看index配置**
GET /index/getAllIndexConf

**添加index配置**

GET /index/addIndexConf
Param:index,filed,keywords

**添加forbid doc id**

GET /index/addForbidId
Param: errorTypeId

**查看forbid doc id**

GET /index/getAllForbidId

## 调度模块
对不同监控任务进行调度