# SSMTemplate

## v 0.1.0

2020.9.2

该项目基于maven，使用spring、springmvc、mybatis、mysql为技术栈，实现了一个restAPI的框架，基本功能实现。

后续会加入配置缓存，跨域解决，数据格式化等问题。

## v 0.2.0

2020.9.8

增加跨域解决filter。

## v 0.3.0

2020.9.18

增加security包，一个token工具，可以进行身份识别和鉴权。

## v0.4.0

2020.9.19

配置jedis，编写redisCache，可以使用redis作为mybatis缓存

## v 0.5.1

基于apache httpClient的发送请求框架，可以很方便的发送post和get请求。
对前面的功能进行了一些优化

## v 0.5.2

优化系统，增加错误码；token工具完善，可以进行刷新

## v 0.6.0

2020.10.15
重构整个系统，将迁移至springboot，同时进行多项优化：
* 增加logFilter，可以方便快捷的debug
* 将两个token字符串合为一个TokenObject对象。
* 优化了整体项目结构，后续会尝试增加更多的扩展性，解耦