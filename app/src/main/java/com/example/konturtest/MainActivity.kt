package com.example.konturtest

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.konturtest.ui.interfaces.SearchHolder
import com.example.konturtest.utils.gone
import com.example.konturtest.utils.visible

class MainActivity : AppCompatActivity(), SearchHolder {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mSearchView: SearchView? = null

    private var mSearchAction: (text: String?) -> Unit = {}
    private var mFinishAction: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSearchView()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment

        setSupportActionBar(findViewById(R.id.tb_toolbar))

        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun initSearchView() {

        mSearchView = findViewById<SearchView>(R.id.sv_search).apply {
            isIconifiedByDefault = false

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    mSearchAction("%$query%")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty())
                        mFinishAction()
                    else
                        mSearchAction("%$newText%")
                    return true
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_container)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun showSearch() {
        super.showSearch()

        mSearchView?.visible()

    }

    override fun hideSearch() {
        super.hideSearch()
        mSearchView?.gone()
    }

    override fun setActions(search: (text: String?) -> Unit, finish: () -> Unit) {
        super.setActions(search, finish)

        mSearchAction = search
        mFinishAction = finish

    }
}