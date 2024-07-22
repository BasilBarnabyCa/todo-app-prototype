package ca.georgiancollege.todoit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val deleteIcon: Drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)!!
    private val editIcon: Drawable = ContextCompat.getDrawable(context, R.drawable.ic_edit)!!
    private val intrinsicWidthDelete = deleteIcon.intrinsicWidth
    private val intrinsicHeightDelete = deleteIcon.intrinsicHeight
    private val intrinsicWidthEdit = editIcon.intrinsicWidth
    private val intrinsicHeightEdit = editIcon.intrinsicHeight
    private val background: ColorDrawable = ColorDrawable()
    private val backgroundColor = Color.parseColor("#f44336")
    private val swipeThreshold = 0.25f // 25% of item width

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false // No move operations needed for swipe functionality
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // This will be implemented in the class that extends this one
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return swipeThreshold
    }

    override fun onChildDraw(
        canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        directionX: Float, directionY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        val maxSwipeDistance = itemView.width * swipeThreshold
        val limitedDX = directionX.coerceAtMost(maxSwipeDistance)

        background.color = backgroundColor
        background.setBounds(itemView.right + limitedDX.toInt(), itemView.top, itemView.right, itemView.bottom)
        background.draw(canvas)

        // Calculate position of delete icon
        val deleteIconTop = itemView.top + (itemHeight - intrinsicHeightDelete) / 2
        val deleteIconMargin = (itemHeight - intrinsicHeightDelete) / 2
        val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidthDelete
        val deleteIconRight = itemView.right - deleteIconMargin
        val deleteIconBottom = deleteIconTop + intrinsicHeightDelete

        // Calculate position of edit icon
        val editIconTop = itemView.top + (itemHeight - intrinsicHeightEdit) / 2
        val editIconMargin = deleteIconMargin + intrinsicWidthDelete + 20 // 20 for spacing
        val editIconLeft = deleteIconLeft - editIconMargin
        val editIconRight = editIconLeft + intrinsicWidthEdit
        val editIconBottom = editIconTop + intrinsicHeightEdit

        // Draw the delete icon
        deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteIcon.draw(canvas)

        // Draw the edit icon
        editIcon.setBounds(editIconLeft, editIconTop, editIconRight, editIconBottom)
        editIcon.draw(canvas)

        // Translate itemView
        itemView.translationX = limitedDX
    }
}
