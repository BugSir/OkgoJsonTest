# OkgoJsonTest
okgoJson二层泛型解析demo kotlin/java<br/>
此demo仅对有特定格式的请求Json解析有用<br/>
适用json类型如：
<pre><code>
"{"data": {"name": "测试","phone":111111123213"},"state":{"code": 0,"success": true,"message": "请求成功！"}}"
</pre></code>
此时可以抽出公共代码CommonResult,这个类里面有state是固定的，data是不确定的，可能是String,可能是json 或是jsonarray<br/>
<pre><code>
class CommonResult<T> : Serializable {
    var state: CommonState? = null
    var data: T? = null
}
</pre></code>
其中有两个类很重要：<br/>
1、CommonResultDeserializer/CommonResultDeserializerJava<br/>
	这个类是一个辅助类，可要可不要。主要是用来解决某些"不规范"服务端返回的不是期望的数据类型导致客户端各种崩溃，有了这个类，你可以统一转成某一类型，这样就	可以愉快的做朋友了。<br/>
2、ParameterizedTypeImpl<br/>这个类也是个辅助类，对于返回的数据已是String的了，使用gson解析时，就要使用这个来生成二层泛型的Type给gson,才能正确解析出来。详细使用请参照AppGsonUtil里的fromJsonArray方法。<br/>
其它类 jsonbasecallback这里是继承abscallback，其中convertResponse方法主要是示例如何获取相关泛型的Type，并处理自己的业务逻辑。其它操作请具体参考okgo官方文档来使用。
