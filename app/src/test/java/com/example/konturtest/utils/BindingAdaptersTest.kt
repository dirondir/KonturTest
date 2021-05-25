package com.example.konturtest.utils

import org.junit.Test
import java.util.*

internal class BindingAdaptersTest{

    @Test
    fun checkFormat()
    {
        assert(String.format(Locale.US, "%.1f", 195.25) == "195.3")
    }

}