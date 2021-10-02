# test-repeater-starter
使用场景：

用于对采集线上数据，并且在特定情况下回放(例如在灰度链路下验证新功能)，验证线功能的准确性。

项目中预留了多种类型的扩展点（基于java SPI）
使用方法：
1.自定义实现了接口的处理类
2.在/MATA-INF/services/目录中新增文件，文件名为接口全引用名，文件内容是实现接口类的全引用名

可扩展的SPI接口:
1.com.test.repeater.starter.filter.ActiveFilter    切面生效拦截器
2.com.test.repeater.starter.filter.ResultFilter    结果落盘拦截器
3.com.test.repeater.starter.generator.IdGenerator  ID生成器
4.com.test.repeater.starter.storage.Storage        存储器
5.com.test.repeater.starter.parser.ServiceParser   服务解析器
