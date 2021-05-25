package com.example.konturtest.ui.interfaces

interface SearchHolder {

    fun setActions(search : (text : String?)->Unit, finish : () ->Unit){}
    fun showSearch(){}
    fun hideSearch(){}

}