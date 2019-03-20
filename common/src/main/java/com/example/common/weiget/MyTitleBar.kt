package cn.com.eado.smartpatrol.ui.weiget

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.common.R
import kotlinx.android.synthetic.main.layout_my_title_bar.view.*

class MyTitleBar(ctx: Context, abstract:AttributeSet) : LinearLayout(ctx, abstract) {

    init {
        LayoutInflater.from(ctx).inflate(R.layout.layout_my_title_bar, this)
        val typedArray:TypedArray = ctx.obtainStyledAttributes(abstract, R.styleable.MyTitleBar)
        val title = typedArray.getString(R.styleable.MyTitleBar_title)
        if (title != null && title != "") tv_title.text = title
        val backgroundColor = typedArray.getColor(R.styleable.MyTitleBar_backgroundColor, 0x50888888)
        setBackgroundColor(backgroundColor)
        iv_back.setOnClickListener { (ctx as Activity).finish() }
    }
}