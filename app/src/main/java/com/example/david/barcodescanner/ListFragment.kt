package com.example.david.barcodescanner

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private var resulCode: String? = null

    companion object {
        fun newInstance(result : String) : ListFragment{
            val fragment = ListFragment()
            val args = Bundle()
            args.putString("result", result)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resulCode = arguments!!.getSerializable("result") as String
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!resulCode.equals("")){
            (activity as MainActivity).setListScanner((activity as MainActivity).getListScanner() + "\n-" + resulCode.toString() + ".")
            tvQrCode.text = (activity as MainActivity).getListScanner()
        }

        btnAdd.setOnClickListener { (activity as MainActivity).openScanActivity()}

        btnDelete.setOnClickListener {
            (activity as MainActivity).setListScanner("")
            tvQrCode.text = (activity as MainActivity).getListScanner()
        }
    }
}
