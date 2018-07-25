//package id.panicLabs.core.ui
//
//import android.databinding.BindingAdapter
//import android.graphics.drawable.Drawable
//import android.support.v4.widget.SwipeRefreshLayout
//import android.support.v7.widget.*
//import android.support.v7.widget.helper.ItemTouchHelper
//
///**
// * @author panicLabs
// * @createdOn 24/11/2017
// */
//class BindingAdapter {
//    /**
//     * @param recyclerView  RecyclerView to bind to RecyclerViewScrollCallback
//     * @param visibleThreshold  The minimum number of items to have below your current scroll position before loading more.
//     * @param resetLoadingState  Reset endless scroll listener when performing a new search
//     * @param onScrolledListener    OnScrolledListener for RecyclerView scrolled
//     */
//    @BindingAdapter(value = *arrayOf("visibleThreshold", "resetLoadingState", "onScrolledToBottom"), requireAll = false)
//    fun setRecyclerViewScrollCallback(recyclerView: RecyclerView, visibleThreshold: Int, resetLoadingState: Boolean,
//                                      onScrolledListener: RecyclerViewScrollCallback.OnScrolledListener) {
//
//        val callback = RecyclerViewScrollCallback
//                .Builder(recyclerView.layoutManager)
//                .visibleThreshold(visibleThreshold)
//                .onScrolledListener(onScrolledListener)
//                .resetLoadingState(resetLoadingState)
//                .build()
//
//        recyclerView.clearOnScrollListeners()
//        recyclerView.addOnScrollListener(callback)
//    }
//
//    /**
//     * @param recyclerView  RecyclerView to bind to SpaceItemDecoration
//     * @param spaceInPx space in pixels
//     */
//    @BindingAdapter("spaceItemDecoration")
//    fun setSpaceItemDecoration(recyclerView: RecyclerView, spaceInPx: Float) {
//        if (spaceInPx != 0f) {
//            val itemDecoration = SpaceItemDecoration(spaceInPx.toInt(), true, false)
//            recyclerView.addItemDecoration(itemDecoration)
//        } else {
//            recyclerView.addItemDecoration(null)
//        }
//    }
//
//    /**
//     * @param recyclerView  RecyclerView to bind to DividerItemDecoration
//     * @param orientation 0 for LinearLayout.HORIZONTAL and 1 for LinearLayout.VERTICAL
//     */
//    @BindingAdapter("dividerItemDecoration")
//    fun setDividerItemDecoration(recyclerView: RecyclerView, orientation: Int) {
//        val itemDecoration = DividerItemDecoration(recyclerView.context, orientation)
//        recyclerView.addItemDecoration(itemDecoration)
//    }
//
//    /**
//     * Bind ItemTouchHelper.SimpleCallback with RecyclerView
//     * @param recyclerView      RecyclerView to bind to SwipeItemTouchHelperCallback
//     * @param swipeEnabled      enable/disable swipe
//     * @param drawableLeft      drawable shown when swiped left
//     * @param drawableRight     drawable shown when swiped right
//     * @param bgColorSwipeLeft  background color when swiped left
//     * @param bgColorSwipeRight background color when swiped right
//     * @param onItemSwipeLeft   OnItemSwipeListener for Item swiped left
//     * @param onItemSwipeRight  OnItemSwipeListener for Item swiped right
//     */
//    @BindingAdapter(value = *arrayOf("swipeEnabled", "drawableSwipeLeft", "drawableSwipeRight", "bgColorSwipeLeft", "bgColorSwipeRight", "onItemSwipeLeft", "onItemSwipeRight"), requireAll = false)
//    fun setItemSwipeToRecyclerView(recyclerView: RecyclerView, swipeEnabled: Boolean, drawableLeft: Drawable, drawableRight: Drawable, bgColorSwipeLeft: Int, bgColorSwipeRight: Int,
//                                   onItemSwipeLeft: SwipeItemTouchHelperCallback.OnItemSwipeListener, onItemSwipeRight: SwipeItemTouchHelperCallback.OnItemSwipeListener) {
//
//        val swipeCallback = SwipeItemTouchHelperCallback
//                .Builder(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
//                .bgColorSwipeLeft(bgColorSwipeLeft)
//                .bgColorSwipeRight(bgColorSwipeRight)
//                .drawableLeft(drawableLeft)
//                .drawableRight(drawableRight)
//                .swipeEnabled(swipeEnabled)
//                .onItemSwipeLeftListener(onItemSwipeLeft)
//                .onItemSwipeRightListener(onItemSwipeRight)
//                .build()
//
//        val itemTouchHelper = ItemTouchHelper(swipeCallback)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//    }
//
//    /**
//     * Bind ItemTouchHelper.SimpleCallback with RecyclerView
//     * @param recyclerView RecyclerView to bind to DragItemTouchHelperCallback
//     * @param dragEnabled  enable/disable drag
//     * @param onItemDrag   OnItemDragListener for Item dragged
//     */
//    @BindingAdapter(value = *arrayOf("dragEnabled", "onItemDrag"), requireAll = false)
//    fun setItemDragToRecyclerView(recyclerView: RecyclerView, dragEnabled: Boolean,
//                                  onItemDrag: DragItemTouchHelperCallback.OnItemDragListener) {
//
//        val dragCallback = DragItemTouchHelperCallback
//                .Builder(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)
//                .dragEnabled(dragEnabled)
//                .onItemDragListener(onItemDrag)
//                .build()
//
//        val itemTouchHelper = ItemTouchHelper(dragCallback)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//    }
//
//    /**
//     * @param swipeRefreshLayout Bind swipeRefreshLayout with OnRefreshListener
//     * @param onRefresh Listener for onRefresh when swiped
//     */
//    @BindingAdapter("onPulledToRefresh")
//    fun setOnSwipeRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, onPulledToRefresh: Runnable) {
//        swipeRefreshLayout.setOnRefreshListener { onPulledToRefresh.run() }
//    }
//}