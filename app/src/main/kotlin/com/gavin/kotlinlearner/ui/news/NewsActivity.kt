package com.gavin.kotlinlearner.ui.news

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-31
 * Time: 10:50
 */
class NewsActivity : BaseActivity() {

    override var TAG: String = this.javaClass.simpleName

    var mChars: String = "QqWwEeRrTtYyUuIiOoPpAa,SsDdFfGgHhJjKkLlZzXxCcVvBbNnMm."
    var mTitleList: ArrayList<String> = ArrayList()
    var mContentList: ArrayList<String> = ArrayList()
    var mFragmentList: ArrayList<NewsFragment> = ArrayList()
    var mPagerAdapter: CustomPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
//        supportActionBar?.elevation = .0f
        setSupportActionBar(toolbar)
        setTitle(R.string.news_title)
        initUI()
    }

    private fun initUI() {
        mTitleList = setupTitleList()
        mContentList = setupContentList()
        tabLayout.tabMode = TabLayout.MODE_FIXED
        for (i in 0..(mTitleList.size-1)) {
            mFragmentList.add(NewsFragment(mTitleList.get(i), mContentList[i]))
            tabLayout.addTab(tabLayout.newTab().setText(mTitleList[i]))
        }
        mPagerAdapter = CustomPagerAdapter(supportFragmentManager, mFragmentList)
        viewPager.adapter = mPagerAdapter
//        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)
//        tabLayout.setTabsFromPagerAdapter(mPagerAdapter) // 上一条方法已经绑定了PagerAdapter
    }

    private fun setupTitleList(): ArrayList<String> {
        var _titles: ArrayList<String> = ArrayList()
        _titles.add("Java")
        _titles.add("Kotlin")
        _titles.add("Android")
        _titles.add("ObjectC")
        _titles.add("Swift")
        _titles.add("IOS")
        return _titles
    }

    private fun setupContentList(): ArrayList<String> {
        var _contents: ArrayList<String> = ArrayList()
//        var byteArray: ByteArray = assets.open("content_1").readBytes()
//        println("获取的内容是：" + String(byteArray))
        var _random: Random = Random(System.currentTimeMillis())
        for (i in 1..mTitleList.size) {
            var _randomChar: StringBuilder = StringBuilder(10)
            for (j in 1..1000) {
                _randomChar.append(" " + mChars[_random.nextInt(mChars.length - 1)] + " ")
            }
            _contents.add("All in these are random. \n\n    $_randomChar")
        }

//        var stream = assets.open("content_1")
//        stream.buffered().reader().use { reader ->
//            println(reader.readText())
//        }
        return _contents
    }

}
