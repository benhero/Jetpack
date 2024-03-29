package com.ben.jetpack

import com.ben.jetpack.livedata.LiveDataActivity
import com.ben.jetpack.room.RoomActivity
import com.ben.jetpack.viewmodel.ViewModelActivity

/**
 * 主界面列表选项
 *
 * @author Benhero
 * @date 2019-10-29
 */
object MainListItems {
    val ITEMS = ArrayList<Item>()
    private val ITEM_MAP = HashMap<Class<*>, Item>()

    init {
        addItem(Item(LiveDataActivity::class.java, "LiveData"))
        addItem(Item(ViewModelActivity::class.java, "ViewModel"))
        addItem(Item(RoomActivity::class.java, "Room"))
    }

    private fun addItem(item: Item) {
        ITEMS.add(item)
        ITEM_MAP[item.className] = item
    }

    fun getIndex(className: Class<*>): Int {
        return ITEMS.indexOf(ITEM_MAP[className])
    }

    fun getClass(index: Int): Class<*> {
        return ITEMS[index].className
    }

    class Item(val className: Class<*>, val content: String) {
        override fun toString(): String {
            return content
        }
    }
}