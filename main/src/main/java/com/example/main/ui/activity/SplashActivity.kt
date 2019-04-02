package com.example.main.ui.activity

import android.content.Intent
import android.os.SystemClock
import cn.com.eado.smartpatrol.extensions.DelegatesExt
import com.example.common.BaseActivity
import com.example.common.BaseApplication
import com.example.common.utils.LogUtils
import com.example.main.R
//import com.example.main.debug.MainApplication
//import com.github.mzule.activityrouter.annotation.Router
//import com.github.mzule.activityrouter.router.Routers

//@Route(path = "/main/SplashActivity")
class SplashActivity : BaseActivity(R.layout.activity_splash) {

//    private var startFirst by DelegatesExt.preference(MainApplication.mainApp, "start_first", false)
//    private var startFirst by DelegatesExt.preference(BaseApplication.getInstance(), "start_first", false)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)

//        bt_news.setOnClickListener { ARouter.getInstance().build("/news/NewsMainActivity").navigation() }
//        bt_getFragment.setOnClickListener {
//            val fragment:Fragment = ARouter.getInstance().build("/news/NewsFragment").navigation() as Fragment
//            toast("find fragment ${fragment.toString()}")
//        }
//        bt_music.setOnClickListener {
//            ARouter.getInstance()
//                .build("/news/NewsWebviewActivity")
//                .withString("url", "file:///android_asset/schame-test.html")
//                .withBoolean("boolean", false)
//                .navigation()
//        }
//    }

    override fun activityInit() {
//        if (!startFirst){
//            startFirst = true
//            //init todo
//            SystemClock.sleep(1000)
//            LogUtils.e("tag","startFirst")
//        } else {
            SystemClock.sleep(300)
//        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
