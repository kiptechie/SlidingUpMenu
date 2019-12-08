package com.r4sh33d.slidingupmenu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.r4sh33d.slidingupmenu.R
import com.r4sh33d.slidingupmenu.extensions.saveSetTextColor
import com.r4sh33d.slidingupmenu.extensions.saveSetTypeFace
import com.r4sh33d.slidingupmenu.extensions.show
import com.r4sh33d.slidingupmenu.utils.BodyTextStyle
import com.r4sh33d.slidingupmenu.utils.MenuModel
import com.r4sh33d.slidingupmenu.utils.MenuType
import com.r4sh33d.slidingupmenu.utils.MenuType.GRID
import com.r4sh33d.slidingupmenu.views.WidthFitSquareImageView

internal class MenuModelAdapter(
    private val menuType: MenuType,
    private val bodyTextStyle: BodyTextStyle,
    private val menuItemClickListener: (MenuModel) -> Unit
) :
    ListAdapter<MenuModel, MenuModelAdapter.GridItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        return GridItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                if (menuType == GRID) R.layout.menu_item_grid_layout else R.layout.menu_list_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MenuModel>() {

        override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
            oldItem.id == newItem.id
    }

    inner class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView = itemView.findViewById<WidthFitSquareImageView>(R.id.iconImageView)
        private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)

        init {
            titleTextView.saveSetTypeFace(font = bodyTextStyle.font)
            titleTextView.saveSetTextColor(color = bodyTextStyle.textColor)
            itemView.setOnClickListener {
                menuItemClickListener(getItem(adapterPosition))
            }
        }

        fun bind(menuModel: MenuModel) {
            titleTextView.text = menuModel.title
            if (menuModel.iconDrawable != null) {
                iconImageView.show()
                iconImageView.setImageDrawable(menuModel.iconDrawable)
            }
        }
    }
}