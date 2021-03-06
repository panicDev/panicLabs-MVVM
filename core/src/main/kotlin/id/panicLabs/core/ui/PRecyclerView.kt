package id.panicLabs.core.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PRecyclerView : RecyclerView {

    var onLoadMoreListener: OnLoadmoreListener? = null
    var loadmoreAdapter: LoadmoreAdapter? = null

    var loadingFinish: Boolean = false

    fun setOnRefreshFinishListener(onLoadmoreListener: OnLoadmoreListener) {
        this.onLoadMoreListener = onLoadmoreListener
    }

    interface OnLoadmoreListener {
        fun onLoadmore()
    }

    constructor(context: Context?) : super(context!!) {
        setLoadmoreListener()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        setLoadmoreListener()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        setLoadmoreListener()
    }

    override fun setAdapter(adapter: Adapter<ViewHolder>?) {
        loadmoreAdapter = LoadmoreAdapter(adapter!!, this)
        super.setAdapter(loadmoreAdapter)
    }

    fun loadingFinish() {
        loadmoreAdapter?.finish()
    }

    fun notifyDataSetChanged() {
        loadmoreAdapter?.notifyDataSetChanged()
    }

    fun setLoadmoreUnable() {
        loadingFinish = true
        loadmoreAdapter?.finish()
    }

    fun setLoadmoreListener() {
        this.addOnScrollListener(object : OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!canScrollVertically(1)) {
                    if (!loadingFinish) {
                        onLoadMoreListener?.onLoadmore()
                        loadingFinish = true
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}