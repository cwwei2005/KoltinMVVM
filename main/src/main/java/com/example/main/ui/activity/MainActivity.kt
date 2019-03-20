package com.example.main.ui.activity

import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.common.BaseActivity
import com.example.common.utils.LogUtils
import com.example.main.R
import com.example.main.databinding.ActivityMainBinding
import com.example.main.model.data.Article
import com.example.main.model.data.User
import com.example.main.vm.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(null) {

    private lateinit var vm:HomeViewModel

    override fun activityInit() {
        //需要在(xml)布局文件定义HomeViewModel
        vm = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main) as ActivityMainBinding
        binding.viewModel = vm
        //定义返回类型为LiveData<>，所以当Data变化时会调用Observer
//        vm.getArticle(this)?.observe(this, Observer { article -> subscribeToModel(article)})
        vm.getUser(this)?.observe(this, Observer { list -> subscribeToModel1(list)})
        initBanner()
    }

    //更新UI，或者简单的可以直接在布局定义
    private fun subscribeToModel(article: Article?) {
        LogUtils.e("tag","subscribeToModel")
    }

    private fun subscribeToModel1(list: List<User>?) {
        if (list == null){
            LogUtils.e("tag","user: ${list?.size}")
        } else {
            val user = list[0]
            LogUtils.e("tag","user1: ${user.name}")
        }
    }

    private fun initBanner() {
        val viewList:List<ImageView> = listOf(ImageView(this), ImageView(this), ImageView(this))
        val urlList:List<String> = listOf("http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg",
            "http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg",
            "http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg")
        myBanner.setUrlList(urlList)
        myBanner.setViewList(viewList)
        indicator.setViewPager(myBanner)
        myBanner?.adapter?.registerDataSetObserver(indicator.dataSetObserver)
    }



    override fun onDestroy() {
        super.onDestroy()
        myBanner?.adapter?.unregisterDataSetObserver(indicator.dataSetObserver)
        myBanner.stop()
    }
}
