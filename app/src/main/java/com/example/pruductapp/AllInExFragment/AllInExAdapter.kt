package com.example.pruductapp.AllInExFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pruductapp.ExpenseFragment.ExpenseFragment
import com.example.pruductapp.incomeFragment.IncomeFragment

class AllInExAdapter(supportFragmentManager: FragmentManager, val tabCount: Int) :
    FragmentPagerAdapter(supportFragmentManager, tabCount) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return AllInExFragment()
        } else if (position == 1) {
            return  IncomeFragment()
        } else  {
            return ExpenseFragment()
        }
    }
}