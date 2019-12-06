package com.r4sh33d.slidingupmenu

import android.view.MenuItem

class SizeFloat(val height: Float, val width: Float)
class SizeInt(val height: Int, val width: Int)

internal fun splitMenuList(list: ArrayList<MenuItem>): List<List<MenuItem>> = list.chunked(8)