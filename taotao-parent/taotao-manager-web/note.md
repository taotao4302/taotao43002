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