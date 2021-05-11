# 前台显示系统

# web.xml配置
可以看到我们配置的拦截器拦截的请求是以.html结尾的。我们可以称之为"伪静态"，
之所以称伪静态是因为我们可以把各种动态请求都写成以.html结尾，这样响应的是动态数据，
但由于请求是以.html结尾的，浏览器便认为要访问静态资源。SEO（搜索引擎优化）便喜欢收录以.html结尾的请求，
从而有利于搜索排名。

由于拦截器拦截的请求是以"*.html"结尾的，springmvc根本就不会去拦截静态资源，这样即使配置资源映射也不管用。
因此将css，js，images放到WEB-INF的话，将无法访问到这些静态资源，因此需要直接放到webapp目录下才行。

web.xml文件中的<welcome-file-list>配置的是默认的访问页（原来是index.jsp，现在是index.html），
也就是请求过来后会默认访问index.html，由于这个页面并不存在，DispatcherServlet会处理该请求，
这时由于请求是以index.html结尾了，因此满足了Controller层的拦截要求，因此便执行showIndex方法，
返回"index"，由于springmvc.xml中配置了视图解析器，会自动在"index"的后面加上".jsp"，这样请求便成了访问index.jsp，index.jsp是我们的商城首页，因此便可以访问到商城首页了。

# CMS内容管理系统表设计及内容工程搭建
