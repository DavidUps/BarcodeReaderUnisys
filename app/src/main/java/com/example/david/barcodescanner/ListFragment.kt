package com.example.david.barcodescanner

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ListFragment : Fragment() {
    private var resulCode: String? = null
    private var param2: String? = null

    companion object {
        fun newInstance(result : String) : ListFragment{
            val fragment = ListFragment()
            val args = Bundle()
            args.putString("result", result)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!!.getString("result") as String != null){
            resulCode = arguments!!.getString("result") as String
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}
