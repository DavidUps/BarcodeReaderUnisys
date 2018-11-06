package com.example.david.barcodescanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var listScanner : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openMainFragment()
    }

    fun getListScanner(): String{
        return if (listScanner != null){
            listScanner
        }else{
            getString(R.string.debe_escanar_un_producto)
        }
    }

    fun setListScanner(scanner : String){
        this.listScanner = scanner
    }

    fun openListFragment(result : String){
        supportFragmentManager.beginTransaction().replace(R.id.lyFragmentContainer, ListFragment.newInstance(result)).addToBackStack("listFragment").commit()
    }

    fun openMainFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.lyFragmentContainer, MainFragment()).addToBackStack("mainFragment").commit()
    }

    fun openScanActivity(){
        supportFragmentManager.beginTransaction().replace(R.id.lyFragmentContainer, ScannerFragment()).addToBackStack("scannerFragment").commit()
    }
}
