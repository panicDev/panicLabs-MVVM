package id.panicLabs.core.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.panicLabs.core.R
import id.panicLabs.core.databinding.ItemFooterBinding


class LoadmoreAdapter(private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, mRecyclerView: PRecyclerView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mRecylcerView: PRecyclerView = mRecyclerView
    private var itemFooterBinding: ItemFooterBinding? = null
    var finish: Boolean = false

    init {
        if (mRecylcerView.layoutManager is GridLayoutManager) {
            (mRecylcerView.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {

                    val type = getItemViewType(position)

                    return if (type == 1) {
                        (mRecylcerView.layoutManager as GridLayoutManager).getSpanCount()
                    } else {
                        1
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return mAdapter.itemCount + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < mAdapter.itemCount) {
            0
        } else {
            1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is FooterViewHolder) {
            if (finish) {
                finish()
            } else {
                progress()
            }
        } else {
            mAdapter.onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType != 0) {
            val itemFooterBinding = DataBindingUtil.inflate<ItemFooterBinding>(LayoutInflater.from(parent.context), R.layout.item_footer, parent, false)
            this.itemFooterBinding = itemFooterBinding
            FooterViewHolder(itemFooterBinding)
        } else {
            mAdapter.onCreateViewHolder(parent, viewType)
        }
    }

    fun progress() {
        finish = false
        itemFooterBinding?.progressView?.visibility = View.VISIBLE
        itemFooterBinding?.overView?.visibility = View.INVISIBLE
    }

    fun finish() {
        finish = true
        itemFooterBinding?.progressView?.visibility = View.INVISIBLE
        itemFooterBinding?.overView?.visibility = View.VISIBLE
    }

    inner class FooterViewHolder(itemFooterBinding: ItemFooterBinding) : RecyclerView.ViewHolder(itemFooterBinding.root)
}
