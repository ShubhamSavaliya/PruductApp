package com.example.pruductapp.ExpenseFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruductapp.IncomeExpenseDatabase
import com.example.pruductapp.R


class ExpenseFragment : Fragment() {
lateinit var rcvCategoryList :RecyclerView
lateinit var v :View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_expense, container, false)
        initView()
        return v
    }

    private fun initView() {
        rcvCategoryList = v.findViewById(R.id.rcvCategoryList)
        var incomeExpenseDatabase = IncomeExpenseDatabase(requireActivity())
        var inExList = incomeExpenseDatabase.displayData()
        val adapter = ExpenseDBAdapter(inExList)

        val manager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        rcvCategoryList.layoutManager = manager
        rcvCategoryList.adapter = adapter
    }

}