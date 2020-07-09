package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {

    @Test
    fun zero()
    {
        val testNum = TipCalculator().calculatePerPersonTotal(0.0, 1, 0, 0.0, tax = false, split = false)
        assertEquals(0.0, testNum, 0.0)
    }

    @Test
    fun normalTest()
    {
        val testNum = TipCalculator().calculatePerPersonTotal(10.0, 2, 50, 10.0, tax = true, split = true)
        assertEquals(15.0, testNum, 0.0)
    }

    @Test
    fun negativeBill()
    {
        val testNum = TipCalculator().calculatePerPersonTotal(-5.0, 1, 0, 0.0, tax = false, split = false)
        assertEquals(0.0, testNum, 0.0)
    }

    @Test fun negativeTax()
    {
        val testNum = TipCalculator().calculatePerPersonTotal(0.0, 1, 0, -5.0, tax = false, split = false)
        assertEquals(0.0, testNum, 0.0)
    }

}