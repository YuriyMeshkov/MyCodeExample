package com.jobc.j112kilo.ui.preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PreviewPagerAdapter (
    fm: FragmentManager, private val list: List<PreviewItemFragment>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return list.get(index = position)
    }

    override fun getCount(): Int {
        return list.size
    }
}