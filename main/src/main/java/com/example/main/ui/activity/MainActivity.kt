package com.example.main.ui.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Scroller
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.BaseActivity
import com.example.common.utils.LogUtils
import com.example.main.R
import com.example.main.databinding.ActivityMainBinding
import com.example.main.model.entity.ArticleEntity
import com.example.main.model.remote.NetworkState
import com.example.main.ui.adapter.BaseBindingAdapter
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
        vm.getEntity()?.observe(this, Observer { list -> subscribeToModel(list)})
        vm.getNetworkState()?.observe(this, Observer { stat -> subscribeToNetworkState(stat)})
        initBanner()
        initRecyclerView()
        bt.setOnClickListener { vm.refresh(ArticleEntity::class.java) }  //for test

    }

    //更新UI，或者简单的可以直接在布局定义
    private fun subscribeToModel(list: List<Any>?) {
        if (list == null || list.isEmpty()){
            LogUtils.e("tag","ArticleEntity: ${list?.size}")
        } else {
            val article = list[0] as ArticleEntity
            LogUtils.e("tag","ArticleEntity.total_count: ${article.total_count}")
            articleAdapter.refresh(article.items!!)
        }
    }

    private fun subscribeToNetworkState(stat: NetworkState){
        LogUtils.e("tag","network state: ${stat}")
    }


    private fun initBanner() {
        val viewList:List<ImageView> = listOf(ImageView(this), ImageView(this), ImageView(this))
        val urlList:List<String> = listOf("http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg",
            "http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg",
            "http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg")
        myBanner.setUrlList(urlList)
        myBanner.setViewList(viewList)
//        indicator.setViewPager(myBanner)
        myBanner?.adapter?.registerDataSetObserver(indicator.dataSetObserver)
    }

    private val articleAdapter = BaseBindingAdapter<ArticleEntity.ItemsBean>(R.layout.layout_item_article, BR.articleItem)
    private fun initRecyclerView(){
        articleAdapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.layout_header,
            findViewById<ViewGroup>(android.R.id.content), false))
        articleAdapter.addFootView(LayoutInflater.from(this).inflate(R.layout.layout_footer,
            findViewById<ViewGroup>(android.R.id.content), false))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = articleAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        articleAdapter.onItemClick = { pos, t->
//            Log.e("tag", "$pos, ${t.title}")
        }
        recyclerView.addOnScrollListener(listener)
        var mLastY = 0f
        val scroller = Scroller(this)
        recyclerView.setOnTouchListener { v, event ->
            if (isTop){
                when(event.action){
                    MotionEvent.ACTION_DOWN -> mLastY = event.rawY
                    MotionEvent.ACTION_UP -> mLastY = 0f
                    MotionEvent.ACTION_MOVE -> {
                        val moveY = event.rawY
                        if(moveY - mLastY > 0) {
                            if(mLastY < 300) articleAdapter.setHeaderViewHeight(mLastY++.toInt())
                        }else if(moveY - mLastY < 0) {
                            if(mLastY > 0) articleAdapter.setHeaderViewHeight(mLastY--.toInt())
                        }
                        Log.e("tag","distanceY = $mLastY")
                    }
                }
            }

            false
        }
    }


    private var isTop = false
    private val listener =  object: RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            //-1代表顶部,返回true表示没到顶,还可以滑
            //1代表底部,返回true表示没到底部,还可以滑
            isTop = !recyclerView.canScrollVertically(-1)
//            val isBottom = !recyclerView.canScrollVertically(1)
//            Log.e("tag","isTop = $isTop")
//            Log.e("tag","isBottom = $isBottom")
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        myBanner?.adapter?.unregisterDataSetObserver(indicator.dataSetObserver)
        myBanner.stop()
    }
}
