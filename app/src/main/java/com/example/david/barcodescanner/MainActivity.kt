package com.example.david.barcodescanner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.lyFragmentContainer, MainFragment()).addToBackStack("mainFragment").commit()
    }

    fun openListFragment(result : String){
        supportFragmentManager.beginTransaction().replace(R.id.lyFragmentContainer, ListFragment.newInstance(result)).addToBackStack("listFragment").commit()

    }

    fun openMainFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.lyFragmentContainer, MainFragment()).addToBackStack("mainFragment").commit()
    }

    fun openScanActivity(){
        startActivity(Intent(this@MainActivity, ScannerActivity::class.java))
    }
}
