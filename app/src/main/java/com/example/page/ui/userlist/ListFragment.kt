package com.example.page.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.page.R
import com.example.page.data.User
import com.example.page.databinding.ListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.list_fragment), ListAdapter.OnItemClickListener{

    private val viewModel by viewModels<ListFragmentViewModel>()

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = ListFragmentBinding.bind(view)

        val adapter = ListAdapter(this)

        binding.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
        }

        viewModel.users.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


    }

   override fun onItemClick(user: User) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment2(user, user.dob)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}