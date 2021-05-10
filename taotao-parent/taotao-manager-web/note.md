# manager-web
表现层工程

# ssm整合springMvc

# 配置前端静态资源
注意jsp和springmvc.xml中的视图解析器路径保持一致
删除初始的index.jsp
使得访问web.xml中<welcome-file-list>标签下的index.jsp为我们编写的在
jsp文件夹下的显示页面

防止css,js被拦截，在springmvc.xml中配置资源映射css和js

# 编写PageController类
访问页面

#返回前台需要的数据
新建一个pojo类EasyUIDataGridResult,放在common模块下作为公共服务

EasyUI中datagrid控件要求的数据格式为：
{total:"2",rows:[{"id":"1","name":"张三"},{"id":"2","name":"李四"}]}
返回过滤数据显示，该函数带一个参数"data"用来指向源数据 （即：获取的数据源，比如json对象）。
您可以改变源数据的标准数据格式，但是这个函数必须返回包含total和rows属性的标准数据对象。
还要就是rows当中的字段名称必须和<table>表格里面定义的字段名称一致。
我们一般采用用一个pojo类来返回前台所需要的数据， 而我们rows当中的json数据则一般是由Java对象转换而来，
多个对象的话就把一个集合转换为json串。

由于pojo要在服务端和客户端进行传输，因此一定要实现序列化接口。

# 新增商品
使用".selectItemCat"样式处理鼠标点击事件
在common.js文件中有关于".selectItemCat"的事件定义
index.jsp页面中引用了该js文件，而item-add.jsp文件只是index.jsp的一个代码片段而已，
因此可以直接使用Index.jsp引用的js文件。

在taotao-common工程新建一个pojo类EasyUITreeNode
pojo实体类和service层的代码实现，dao层使用逆向工程生成的xml即可，因为是单表查询
在applicationContext-service.xml发布服务

配置springmvc,编写ItemCatController类
ItemCatController类中添加获取分类节点的方法，其中@RequestMapping("/item/cat/list")这个请求路径是从我们common.js的窗口打开事件中访问的地址复制而来的
客户端使用dubbo引用下该服务，springmvc中引用

在ItemService接口当中添加一个"添加商品"的接口（这一个接口要操作两张表，一张是商品表，另一张是商品描述表）
要抛出异常是因为这个接口要操作两张表，而且这两张表的操作要都成功才叫成功，否则事务就回滚，因此异常要向上抛，
在实现类代码中不能用try catch来捕获异常，因为这样的话springmvc会认为代码正常结束了，便不会回滚。

然后在service层中实现该接口,商品ID（也叫商品编号）是采用当前毫秒数加两位随机数来生成的,
为了方便以后调用，我们专门封装了一个类，叫IDUtils，里面不仅封装了商品ID的生成方法还封装了图片名称的生成方法，由于该类会被多个工程使用，因此我们也放到taotao-common的utils目录下

最后在controller层添加additem方法，在Controller层我们要捕获从实现类抛出的异常，避免异常直接抛到前台页面
# Nginx搭建图片服务器
nginx来统一管理这些图片，这样用户要访问图片的时候，nginx直接把图片服务器上的图片给返回就可以了，
从而解决了tomcat集群资源无法共享的问题
FastDFS是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，
解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。它的优势是可以水平扩容，
FastDFS存储资源的设备是按组来区分的，当存储空间不足时，便可以通过水平增加分组并相应添加设备来达到扩容的目的，而且是没有上限的。还有个优势是高可用，
也就是说FastDFS集群能够做到当提供服务的nginx发生故障时，自动切换到另一台nginx设备上，保障服务的稳定。

作为练习只搭建单机版FastDFS
完成后对上传代码进行封装
封装类中使用的Storage客户端是StorageClient1而不是StorageClient，
这个客户端的好处是能够帮我们自动把文件所在的组以及存放位置拼接到一块。
