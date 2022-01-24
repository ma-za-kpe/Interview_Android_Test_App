package com.maku.interviewandroidtest.presentation.invetorylist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maku.interviewandroidtest.R
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.data.local.db.entities.readProduct.ResultItem
import com.maku.interviewandroidtest.common.data.local.db.entities.readProduct.ResultItemEntity
import com.maku.interviewandroidtest.common.data.local.model.FilterItem
import com.maku.interviewandroidtest.common.data.local.model.Item
import com.maku.interviewandroidtest.databinding.FragmentFirstBinding
import com.maku.interviewandroidtest.presentation.addinventory.ProductViewModel
import com.maku.interviewandroidtest.presentation.invetorylist.adapters.FilterListAdapter
import com.maku.interviewandroidtest.presentation.invetorylist.adapters.FilterResultsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment : Fragment(), SearchView.OnQueryTextListener {

    private var items = mutableListOf<Item>()
    private var result_items: List<AddItemEntity> ? = null
    private var filter_term: String? = null

    private lateinit var viewModel: ProductViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mAdapter by lazy { FilterListAdapter { item ->
        onClick(item as Item)
    }
    }

    private fun onClick(item: Item) {
        // here we leak data with the other recycler view (use data as search filter)
        if (result_items?.size!! > 0) {
            if (item.item == "All"){
                mResultsAdapter.setData(ResultItemEntity(result_items!!))
            } else {
                mResultsAdapter.setData(ResultItemEntity(filterResults(result_items!!, item.item)))
            }
        }
    }

    private val mResultsAdapter by lazy { FilterResultsListAdapter { item ->
        onClickResult(item as ResultItem)
    }
    }

    private fun onClickResult(item: ResultItem) {
        Toast.makeText(requireContext(), "Result item clicked ${item.pdt_image}", Toast.LENGTH_SHORT).show()
        filter_term = item.category
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        handleData()
        handleFilterTerms()
        return binding.root
    }

    private fun handleFilterTerms() {
        items.add(Item("All"))
        items.add(Item("Cereal Seeds"))
        items.add(Item("Equipment"))
        items.add(Item("Minerals"))
        items.add(Item("Machinery"))
        items.add(Item("Machines"))
    }

    private fun handleData() {
        viewModel.readProducts.observe(viewLifecycleOwner, {
            Timber.d("all item ${it.size}")
            result_items = it
            mResultsAdapter.setData(ResultItemEntity(it))
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // all binding go here...
        setUpHorizontalRecyclerView()
        setUpVerticalRecyclerView()
    }

    private fun setUpHorizontalRecyclerView() {
        binding.horizontalRecyclerview.adapter = mAdapter
        binding.horizontalRecyclerview.layoutManager =   LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mAdapter.setData(FilterItem(items))
    }

    private fun setUpVerticalRecyclerView() {
        binding.verticalRecyclerview.adapter = mResultsAdapter
        binding.verticalRecyclerview.layoutManager =   LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun filterResults(result_items: List<AddItemEntity>, term: String): List<AddItemEntity> {
        return result_items.filter { it.category == term }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_products, menu)

        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        searchView!!.setOnCloseListener {
            mResultsAdapter.setData(ResultItemEntity(result_items!!))
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_create -> {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                return true
            }

            R.id.action_scan -> {
                Toast.makeText(requireContext(), "Scan feature coming soon.", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchDbData(query)
        }
        return true
    }

    // this method is not good for performance, its expensive through its calls to the API
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    fun searchDbData(search_term: String?){
        // 0. check list/dataset size
        // 1. implement search logic() same filter loic as above
        if (result_items?.size!! > 0) {
            mResultsAdapter.setData(ResultItemEntity(filterResults(result_items!!, search_term!!)))
        } else {
            Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show()
            binding.nodata.visibility = View.VISIBLE
        }

        // 2. update the recyclerview as needed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}