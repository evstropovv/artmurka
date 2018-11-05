package com.artmurka.artmurkaapp.android.views.activities.checkout


import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.artmurka.artmurkaapp.android.views.fragments.FragmenZakaz
import com.artmurka.artmurkaapp.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class CheckoutActivity : AppCompatActivity(), ICheckoutActivity, HasSupportFragmentInjector {

    var fragmentZakaz: FragmenZakaz? = null
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        loadTestFragment()
        setUI()
    }


    private fun setUI() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun changeFragment(currentFragment: Int) {

    }

    private fun loadTestFragment() {
        val fragment = supportFragmentManager.findFragmentByTag("ZAKAZ")
        if (fragment == null) {
            fragmentZakaz = FragmenZakaz()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.pager, fragmentZakaz!!, "ZAKAZ")
                    .commit()
            supportFragmentManager.executePendingTransactions()
        } else {
            fragmentZakaz = fragment as FragmenZakaz
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = dispatchingAndroidInjector
}

