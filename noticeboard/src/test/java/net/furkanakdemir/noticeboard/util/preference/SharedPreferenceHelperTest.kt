package net.furkanakdemir.noticeboard.util.preference

import androidx.test.core.app.ApplicationProvider
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.hamcrest.CoreMatchers.`is` as Is

@RunWith(RobolectricTestRunner::class)
class SharedPreferenceHelperTest {

    private lateinit var preferenceHelper: PreferenceHelper

    @Before
    fun setUp() {
        preferenceHelper = SharedPreferenceHelper(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testGetNumberOfPin() {

        val actual = preferenceHelper.getNumberOfPin()
        val expected = 0

        assertThat(actual, Is(expected))
    }

    @Test
    fun testIncreaseNumberOfPin() {

        preferenceHelper.increaseNumberOfPin()

        val expected = 1
        val actual = preferenceHelper.getNumberOfPin()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testMultiIncreaseNumberOfPin() {

        preferenceHelper.increaseNumberOfPin()
        preferenceHelper.increaseNumberOfPin()
        preferenceHelper.increaseNumberOfPin()
        preferenceHelper.increaseNumberOfPin()

        val expected = 4
        val actual = preferenceHelper.getNumberOfPin()

        assertThat(actual, Is(expected))
    }
}
