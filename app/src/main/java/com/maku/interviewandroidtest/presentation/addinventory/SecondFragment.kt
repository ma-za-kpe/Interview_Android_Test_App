package com.maku.interviewandroidtest.presentation.addinventory

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.maku.interviewandroidtest.R
import com.maku.interviewandroidtest.common.data.local.db.entities.addProduct.AddItemEntity
import com.maku.interviewandroidtest.common.presentation.BaseFragment
import com.maku.interviewandroidtest.common.utils.HandleResult
import com.maku.interviewandroidtest.common.utils.toast
import com.maku.interviewandroidtest.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class SecondFragment : BaseFragment(), CompoundButton.OnCheckedChangeListener {

    private var checked: Boolean? = null
    private var mProfileUri: Uri? = null
    private var category_item: String? = null

    val categoryList = arrayListOf("All","Cereal Seeds", "Equipment", "Minerals", "Machinery", "Machines")

    private var _binding: FragmentSecondBinding? = null
    private lateinit var viewModel: ProductViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCategorySpinner(categoryList)

        // check if vat is checked or not
        binding.vat.setOnCheckedChangeListener(this)

        // pick image
        binding.addImg.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        // pick drop down data
        binding.categoryTypeAuto.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, pos, id ->

                // fetch the user selected value
                val item = parent.getItemAtPosition(pos).toString()

                // set category item selected
                requireActivity().toast("category_item $item")
                category_item = item
            }


        binding.create.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            requireActivity().toast("category_item $category_item")
            // check if fields are valid
            if (isValidate()){
               if (category_item != null){
                   if (!binding.vat.isChecked){
                       // set vat false in the db, don't calculate the vat value
                       insertIntoDb(false)
                   } else {
                       insertIntoDb(true)
                   }
               } else {
                   requireActivity().toast("please select a category")
               }
            }

        }
    }

    private fun insertIntoDb(vat: Boolean) {
        viewModel.insertProduct(AddItemEntity(
            binding.pdtName.text.toString(),
            binding.pdtCode.text.toString(),
            category_item.toString(),
            binding.pdtType.text.toString(),
            binding.pdtManufaturer.text.toString(),
            binding.pdtDistributor.text.toString(),
            vat,
            binding.pdtUnitCost.text.toString().toInt(),
            binding.pdtRetailPrice.text.toString().toInt(),
            binding.pdtAgentPrice.text.toString().toInt(),
            binding.pdtWholeSalePrice.text.toString().toInt(),
            mProfileUri.toString()
        ))

        viewModel.productsResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is HandleResult.RoomSuccess -> {
                    binding.progressBar2.visibility = View.GONE
                    // go back to all products page
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                    requireActivity().toast("Successfully added product")
                }

                is HandleResult.Error -> {
                    binding.progressBar2.visibility = View.GONE
                    requireActivity().toast(response.message.toString())
                }

                is HandleResult.Loading -> {
                    binding.progressBar2.visibility = View.VISIBLE
                    requireActivity().toast("Adding product ... ")
                }

                else ->  {
                    binding.progressBar2.visibility = View.GONE
                }
            }

        })
    }

    private fun setUpCategorySpinner(list: ArrayList<String>) {
        // initiate an auto complete text view
        val adapter: ArrayAdapter<*> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list.toArray())
        binding.categoryTypeAuto.setAdapter(adapter)
        binding.categoryTypeAuto.threshold = 1 //start searching from 1 character
        binding.categoryTypeAuto.setAdapter(adapter) //set the adapter for displaying category name list
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        checked = isChecked
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    mProfileUri = fileUri
                    binding.pdtImg.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    requireActivity().toast("ImagePicker.getError(data) ${ImagePicker.getError(data)}")
                }
                else -> {
                    requireActivity().toast("Task Cancelled")
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isValidate(): Boolean =
                   validateField(binding.pdtName.text.toString(), binding.name, binding.pdtName)
                && validateField(binding.pdtCode.text.toString(), binding.code, binding.pdtCode)
                && validateField(binding.pdtType.text.toString(), binding.type, binding.pdtType)
                && validateField(binding.pdtManufaturer.text.toString(), binding.manufaturer, binding.pdtManufaturer)
                && validateField(binding.pdtDistributor.text.toString(), binding.distributor, binding.pdtDistributor)
                && validateField(binding.pdtUnitCost.text.toString(), binding.unitCost, binding.pdtUnitCost)
                && validateField(binding.pdtRetailPrice.text.toString(), binding.retailPrice, binding.pdtRetailPrice)
                && validateField(binding.pdtAgentPrice.text.toString(), binding.agentPrice, binding.pdtAgentPrice)
                && validateField(binding.pdtWholeSalePrice.text.toString(), binding.wholeSalePrice, binding.pdtWholeSalePrice)

}