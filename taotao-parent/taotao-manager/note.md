# manager模块
manager模块是一个聚合工程，有四个子模块,最后三个jar包整合到war包中
## dao模块
dao模块负责和数据库进行交互，有mapper文件


## interface模块
### 简介
接口模块,放置service层的接口
整合service层时，把接口放在interface模块，实体类放在service模块的com.taotao.service.impl包下


## pojo模块

## service模块
### 简介
1.这是一个war工程，通过该工程将所有的模块聚合起来
用于部署
### 配置spring和mybatis整合
在service模块下配置spring和mybatis的整合 ，
之所以不把Spring和Mybatis的整合放到taotao-manager-dao工程当中，
是因为dao工程只是个jar工程，将来会被打包成一个jar包，配置文件也会被打包到jar包里面，我们调用起来比较麻烦，
所以taotao-manager-service工程中（因为该工程是war工程，taotao-manager聚合工程最终都会打包成一个war包，war包整合了聚合工程的所有内容，因此更适合来进行框架整合

在applicationContext-dao.xml配置文件中我们把mapper映射文件的路径直接写成了com.taotao.com.taotao.mapper,
也就是manager-dao工程下的com.taotao.com.taotao.mapper,这是因为taotao-manager-dao工程会被打包成jar包放到war工程下的WEB-INF/lib目录下,
我们的applicationContext-dao.xml文件就是在war工程中，因此扫描是没有问题的。

### 分页
java.lang.NoClassDefFoundError: net/sf/jsqlparser/expression/Expression
要去补jsqlparserjar包
### 配置spring事务控制
applicationContext-trans..xml 文件下配置，使用的还是applicationContext-dao.xml中的数据源

开启基于注解的事务控制模式，依赖tx名称空间
使用xml加注解的方式配置事务

### 初始化spring容器
在web.xml中

### ImagesUpload上传图片到fastDNS服务器并修改数据库中的地址
imagesPathMapper
imagesPathMapper.xml
ImagePath
