package org.d3if4082.galerihewan2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if4082.galerihewan2.Hewan
import org.d3if4082.galerihewan2.R
import org.d3if4082.galerihewan2.databinding.FragmentMainBinding
import org.d3if4082.galerihewan2.network.HewanApiService

class MainFragment : Fragment() {
    private val data = mutableListOf<Hewan>()
    fun updateData(newData: List<Hewan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        TODO("Not yet implemented")
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var myAdapter: MainAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        myAdapter = MainAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
    }
    private fun updateProgress(status: HewanApiService.HewanApi.ApiStatus) {
        when (status) {
            HewanApiService.HewanApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            HewanApiService.HewanApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            HewanApiService.HewanApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

}