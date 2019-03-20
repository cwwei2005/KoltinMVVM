package com.example.common.weiget

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.common.animationlibrary.DepthPageTransformer
import com.example.common.utils.LogUtils
import kotlin.concurrent.thread
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.Scroller
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


/**
 * 一个图片轮播控件
 */
class MyBanner(val ctx: Context, abstract : AttributeSet) : ViewPager(ctx, abstract){

    private lateinit var viewList: List<ImageView>
    private lateinit var urlList: List<String>
    private var touch = false
    private var activity:Activity = ctx as Activity
    private val mExecutor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private var mTimerTask: TimerTask = object : TimerTask() {
        override fun run() {
            if (!touch && viewList.isNotEmpty()){
                activity.runOnUiThread {
                    if (currentItem < viewList.size-1) currentItem += 1
                    else setCurrentItem(0, false)  //禁止动画
                }
            }
        }
    }

    fun setUrlList(urlList: List<String>){
        this.urlList = urlList
    }

    fun setViewList(viewList: List<ImageView>){
        this.viewList = viewList
        for (i in 0 until viewList.size){
            Glide.with(this).load(urlList[i]).into(viewList[i])
            viewList[i].scaleType = ImageView.ScaleType.FIT_XY
        }
        adapter = MyPagerAdapter()
    }

    fun refresh(){
        adapter?.notifyDataSetChanged()
    }

    fun stop(){
        mExecutor.shutdownNow()
    }


    init {
        setViewPagerScroller()  //设置滑动时间
        setPageTransformer(true, DepthPageTransformer())  //设置滑动动画
        mExecutor.scheduleAtFixedRate(mTimerTask, 3, 3, TimeUnit.SECONDS)  //设置自动滑动

        setOnTouchListener { v, event ->
            LogUtils.e("tag","touch:${MotionEvent.ACTION_DOWN}")
            if (event.action == MotionEvent.ACTION_DOWN) touch = true
            else if (event.action == MotionEvent.ACTION_UP) touch = false
            false
        }
    }



    //通过反射设置MyBanner的滑动时间
    private fun setViewPagerScroller(){
        try {
            val aClass = ViewPager::class.java
            val sInterpolator = aClass.getDeclaredField("sInterpolator")
            sInterpolator.isAccessible = true
            val scroller = object : Scroller(ctx, sInterpolator.get(this) as Interpolator) {
                override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
                    //最后一个参数即viewpager自动滑动的时间
                    super.startScroll(startX, startY, dx, dy, 2000)
                }
            }
            val mScroller = aClass.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            mScroller.set(this, scroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



    //adapter
    inner class MyPagerAdapter : PagerAdapter(){
        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }

        override fun getCount(): Int {
            return viewList.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = viewList[position]
            container.addView(view)
            view.setOnClickListener { LogUtils.e("tag", "position: $position") }
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(viewList[position])  //滑动过程超过缓存范围就要调用这个删除
        }

//        override fun finishUpdate(container: ViewGroup) {
//            var position = currentItem
//            if (position == 0) {
//                position = viewList.size
//                setCurrentItem(position, false)
//            } else if (position == viewList.size*50 - 1) {
//                position = viewList.size - 1
//                setCurrentItem(position, false)
//            }
//        }
    }
}