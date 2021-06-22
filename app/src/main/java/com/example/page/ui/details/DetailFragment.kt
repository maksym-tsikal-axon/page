package com.example.page.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.page.R
import com.example.page.databinding.DetailFragmentBinding
import kotlinx.android.synthetic.main.detail_fragment.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val args by navArgs<DetailFragmentArgs>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DetailFragmentBinding.bind(view)
        val user = args.user
        binding.apply {


            Glide.with(this@DetailFragment)
                .load(user.photo.large)
                .transform(CircleCrop())
                .into(imageView)

            val startDate = user.dob.date
            userTitleTv.text = user.name.firstName + " " + user.name.lastName
            detailGender.text = user.gender
            detailDob.text = startDate.substring(0, 10)
            detailPhone.text = user.phone
            detailCell.text = user.cell

        }



        detail_phone.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: " + user.phone)
            startActivity(intent)
        }

        detail_cell.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel: " + user.cell)
            startActivity(intent)
        }

    }

}