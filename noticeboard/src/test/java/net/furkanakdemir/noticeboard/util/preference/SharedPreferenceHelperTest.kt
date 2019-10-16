package net.furkanakdemir.noticeboard.util.preference

import androidx.test.core.app.ApplicationProvider
import net.furkanakdemir.noticeboard.util.fakes.TestData.TAG_CUSTOM
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

        val actual = preferenceHelper.getPins()
        val expected = 0

        assertThat(actual, Is(expected))
    }

    @Test
    fun testIncreaseNumberOfPin() {

        preferenceHelper.increase()

        val expected = 1
        val actual = preferenceHelper.getPins()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testMultiIncreaseNumberOfPin() {

        preferenceHelper.increase()
        preferenceHelper.increase()
        preferenceHelper.increase()
        preferenceHelper.increase()

        val expected = 4
        val actual = preferenceHelper.getPins()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testResetNumberOfPin() {

        preferenceHelper.increase()
        preferenceHelper.increase()
        preferenceHelper.reset()
        val expected = 0
        val actual = preferenceHelper.getPins()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testSetTag() {
        preferenceHelper.increase()
        val expected = 1
        val actual = preferenceHelper.getPins()
        assertThat(actual, Is(expected))

        preferenceHelper.setTag(TAG_CUSTOM)
        preferenceHelper.increase()
        val expectedOther = 1
        val actualOther = preferenceHelper.getPins()
        assertThat(actualOther, Is(expectedOther))
    }
}
