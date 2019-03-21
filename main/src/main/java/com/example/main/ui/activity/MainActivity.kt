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
import com.example.main.model.entity.ArticleEntity
import com.example.main.model.remote.NetworkState
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
        vm.getData(this, ArticleEntity::class.java)?.observe(this, Observer { list -> subscribeToModel(list)})
        vm.getNetworkState(this)?.observe(this, Observer { stat -> subscribeToNetworkState(stat)})
        initBanner()
    }

    //更新UI，或者简单的可以直接在布局定义
    private fun subscribeToModel(list: List<Any>?) {
        if (list == null || list.isEmpty()){
            LogUtils.e("tag","ArticleEntity: ${list?.size}")
        } else {
            val article = list[0] as ArticleEntity
            LogUtils.e("tag","ArticleEntity.total_count: ${article.total_count}")
        }
    }

    private fun subscribeToNetworkState(stat: NetworkState){
        LogUtils.e("tag","network state: ${stat}")
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
