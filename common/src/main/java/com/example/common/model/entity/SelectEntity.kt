package com.example.common.model.entity

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "SelectEntity")
@TypeConverters(SelectEntity.ItemsBeanConverters::class)
class SelectEntity {

    /**
     * incomplete_results : false
     * items : [{"channel":1,"click":3,"comments":0,"content":"empty content in list","downvote":0,"id":12677,"pubDate":"23小时以前","stow":0,"thumbnail":"","title":"金三银四背后，一个 Android 程序员的面试心得","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0319/12677.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":223,"comments":0,"content":"empty content in list","downvote":1,"id":12676,"pubDate":"1周以前","stow":0,"thumbnail":"","title":"2019 Android 高级面试题总结","upvote":2,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0312/12676.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":48,"comments":0,"content":"empty content in list","downvote":0,"id":12674,"pubDate":"1周以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z311093112-O58-0-lp.jpeg","title":"再见，Python！你好，Go语言","upvote":0,"url":"http://www.jcodecraeer.com/a/webdesign/2019/0311/12674.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":108,"comments":0,"content":"empty content in list","downvote":0,"id":12673,"pubDate":"1周以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/5282/155213N16-4I3.png","title":"MVVM架构在Flutter中的简单实践","upvote":1,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/2019/0309/12673.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/5282/myface.jpeg","id":5282,"nickname":"ditclear"}},{"channel":1,"click":82,"comments":0,"content":"empty content in list","downvote":1,"id":12671,"pubDate":"2周以前","stow":0,"thumbnail":"","title":"为什么滴滴裁员2000人，被裁员工却像中奖一样开心？","upvote":0,"url":"http://www.jcodecraeer.com/a/chengxusheji/chengxuyuan/2019/0304/12671.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":109,"comments":0,"content":"empty content in list","downvote":0,"id":12669,"pubDate":"3周以前","stow":1,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z301124100-4227-0-lp.png","title":" Flutter终将逆袭！1.2版本发布，或将统一江湖","upvote":1,"url":"http://www.jcodecraeer.com/a/qianduankaifa/2019/0301/12669.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":103,"comments":0,"content":"empty content in list","downvote":0,"id":12668,"pubDate":"3周以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/10585/1Z22GTR0-5529-0-lp.png","title":"APT案例之点击事件","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/2019/0227/12668.html","user":{"face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=yangchong211&size=30","id":10585,"nickname":"yangchong211"}},{"channel":1,"click":371,"comments":0,"content":"empty content in list","downvote":0,"id":12666,"pubDate":"3周以前","stow":2,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z22G11351-A42-0-lp.jpeg","title":"资本寒冬下的android面经","upvote":2,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0227/12666.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":414,"comments":0,"content":"empty content in list","downvote":0,"id":12658,"pubDate":"4周以前","stow":2,"thumbnail":"","title":"分享一套Android快速开发模板，包含常用主流框架，下载即用。","upvote":2,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0222/12658.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/10524/myface.jpg","id":10524,"nickname":"MirkoWu"}},{"channel":1,"click":132,"comments":0,"content":"empty content in list","downvote":0,"id":12655,"pubDate":"4周以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/10585/1Z2201K610-4923-0-lp.png","title":"ARouter路由解析","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/2019/0220/12655.html","user":{"face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=yangchong211&size=30","id":10585,"nickname":"yangchong211"}},{"channel":1,"click":42,"comments":0,"content":"empty content in list","downvote":0,"id":12632,"pubDate":"1个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z211140552-4I6-0-lp.jpeg","title":"停发年终奖背后，是程序员\u201c失宠\u201d了？","upvote":0,"url":"http://www.jcodecraeer.com/a/chengxusheji/chengxuyuan/2019/0211/12632.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":26,"comments":0,"content":"empty content in list","downvote":0,"id":12631,"pubDate":"1个月以前","stow":0,"thumbnail":"","title":"微信小程序发送短信验证码完整实例","upvote":0,"url":"http://www.jcodecraeer.com/a/javascript/2019/0208/12631.html","user":{"face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=doliangzhe&size=30","id":4280,"nickname":"doliangzhe"}},{"channel":1,"click":88,"comments":0,"content":"empty content in list","downvote":0,"id":12630,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/10001/1Z202131449-LL-0-lp.png","title":"KotlinAndroid项目实战","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/2019/0202/12630.html","user":{"face":"http://www.jcodecraeer.com/plugin/Identicon/index.php?string=��ɽ&size=30","id":10001,"nickname":"蓝山"}},{"channel":1,"click":76,"comments":0,"content":"empty content in list","downvote":0,"id":12629,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/14296/1Z2011PH0-3438-0-lp.png","title":"Android-打包AAR步骤以及注意事项","upvote":1,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/2019/0201/12629.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/14296/myface.jpg","id":14296,"nickname":"秦子帅"}},{"channel":1,"click":32,"comments":0,"content":"empty content in list","downvote":0,"id":12625,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z131162233-3502-0-lp.png","title":"Okhttp同步请求源码分析","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/2019/0131/12625.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":56,"comments":0,"content":"empty content in list","downvote":0,"id":12621,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/20190129/1548755914888434-lp.png","title":"短视频SDK在广电系统的解决方案","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0129/12621.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/12878/myface.png","id":12878,"nickname":"动不动"}},{"channel":1,"click":237,"comments":0,"content":"empty content in list","downvote":0,"id":12616,"pubDate":"2个月以前","stow":1,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z12Q05339-33T-0-lp.png","title":"Android的路接下来该怎么走？","upvote":1,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0128/12616.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":127,"comments":0,"content":"empty content in list","downvote":0,"id":12615,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z126135646-3302-0-lp.png","title":"还在期待安卓9.0吗？Android 10.0要来了","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0126/12615.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":25,"comments":0,"content":"empty content in list","downvote":0,"id":12607,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/15185/1Z123130943-5U4-0-lp.jpeg","title":"锤子科技&quot;临死前&quot;被&quot;接盘&quot; ，内部人士爆料已改签今日头条母公司","upvote":0,"url":"http://www.jcodecraeer.com/a/chengxusheji/chengxuyuan/2019/0123/12607.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}},{"channel":1,"click":104,"comments":0,"content":"empty content in list","downvote":0,"id":12599,"pubDate":"2个月以前","stow":0,"thumbnail":"http://www.jcodecraeer.com/uploads/userup/5822/1Z111155551-43Y-0-lp.gif","title":"一起来实现网易云音乐引导页效果","upvote":0,"url":"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0111/12599.html","user":{"face":"http://www.jcodecraeer.com/uploads/userup/5822/myface.png","id":5822,"nickname":"兔子吃过窝边草"}}]
     * total_count : 4389
     */

    @PrimaryKey
    var id: Int = 0
    var isIncomplete_results: Boolean = false
    var total_count: Int = 0
    var items: List<ItemsBean>? = null

    class ItemsBean {
        /**
         * channel : 1
         * click : 3
         * comments : 0
         * content : empty content in list
         * downvote : 0
         * id : 12677
         * pubDate : 23小时以前
         * stow : 0
         * thumbnail :
         * title : 金三银四背后，一个 Android 程序员的面试心得
         * upvote : 0
         * url : http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2019/0319/12677.html
         * user : {"face":"http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg","id":15185,"nickname":"codeGoogler"}
         */

        var channel: Int = 0
        var click: Int = 0
        var comments: Int = 0
        var content: String? = null
        var downvote: Int = 0
        var id: Int = 0
        var pubDate: String? = null
        var stow: Int = 0
        var thumbnail: String? = null
        var title: String? = null
        var upvote: Int = 0
        var url: String? = null
        @Embedded
        var user: UserBean? = null

        class UserBean {
            /**
             * face : http://www.jcodecraeer.com/uploads/userup/15185/myface.jpg
             * id : 15185
             * nickname : codeGoogler
             */

            var face: String? = null
            var id: Int = 0
            var nickname: String? = null
        }
    }

    class ItemsBeanConverters{
        @TypeConverter
        fun stringToObject(value: String): List<ItemsBean> {
            val listType = object : TypeToken<List<ItemsBean>>() {
            }.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun objectToString(list: List<ItemsBean>): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}
