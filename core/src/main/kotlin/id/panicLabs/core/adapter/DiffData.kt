package id.panicLabs.core.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author panicDev
 * @created 27/07/18.
 * @project panicLabs-MVVM.
 */
class DiffData<Type>(private val recyclerView: RecyclerView,
                     private val adapter: RecyclerView.Adapter<*>,
                     private val isItemTheSame: (Type, Type) -> Boolean) {

    private val data = ArrayList<Type>()

    fun updateData(newData: List<Type>) {
        if (data.size > 0 && newData.size > 1) {
            var clipTop = true
            if (recyclerView.layoutManager is LinearLayoutManager) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                clipTop = !layoutManager.reverseLayout
            }
            val offset = recyclerView.computeVerticalScrollOffset()

            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return data.size
                }

                override fun getNewListSize(): Int {
                    return newData.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    println("DiffData.areItemsTheSame ${isItemTheSame.invoke(
                            data[oldItemPosition],
                            newData[newItemPosition])}")

                    return isItemTheSame.invoke(
                            data[oldItemPosition],
                            newData[newItemPosition])
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val equals = data[oldItemPosition] == newData[newItemPosition]
                    println("DiffData.areContentsTheSame $equals")
                    if (!equals) {
                        println("DiffData.areContentsTheSame are not contents the same")
                    }
                    return equals
                }
            }, false)

            data.clear()
            data.addAll(newData)
            diffResult.dispatchUpdatesTo(adapter)

            if (clipTop && offset == 0) {
                recyclerView.scrollToPosition(0)
            }
        } else {
            data.clear()
            data.addAll(newData)
            adapter.notifyDataSetChanged()
        }
    }

    operator fun get(position: Int): Type {
        return data[position]
    }

    fun size(): Int {
        return data.size
    }
}