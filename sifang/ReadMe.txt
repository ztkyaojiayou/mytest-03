目录
  |--cloud-base：            统一POM
  |--common                  基础包
  |    |--common-utils       基础工具包
  |    |--rtdb-base          实时库接口定义
  |    |--rtdb-redis         实时库redis实现
  |    |--tsdb-base          时序库接口定义
  |    |--tsdb-pro           时序库prometheus实现
  |    |--algorithm-lib      算法库
  |--gateway                 数据接入网关
  |    |--gateway-base       网关基类
  |    |--gateway-a5000      A5000网关实现
  |    |--gateway-cyber      Cyber网关实现
  |--thingmodel：            物模型
  |    |--model-base         物模型基础类
  |    |--model-dp           数据处理
  |    |--model-fep          数据接入处理
  |    |--model-calculate    模型计算处理
  |    |--model-service      模型访问服务
  |--mservice                微服务框架 
  |    |--api-gateway        API网关
  |    |--codegen            代码生成微服务
  |    |--job-man            任务调度管理
  |    |--oauth              认证
  |    |--uum                用户权限管理
  |    |--ms-common          微服务通用包
  |        |--core           微服务基础包
  |        |--feign          服务间调用
  |        |--job            任务管理
  |        |--log            日志
  |        |--mybatis        数据库ORM封装
  |        |--swagger        文档封装
  |--bussiness               业务应用
  |    |--……
  |--web                     前端
       |--……