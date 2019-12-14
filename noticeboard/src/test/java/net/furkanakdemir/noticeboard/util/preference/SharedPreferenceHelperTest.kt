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

        val actual = preferenceHelper.getPinCount()
        val expected = 0

        assertThat(actual, Is(expected))
    }

    @Test
    fun testIncreaseNumberOfPin() {

        preferenceHelper.plusPin()

        val expected = 1
        val actual = preferenceHelper.getPinCount()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testMultiIncreaseNumberOfPin() {

        preferenceHelper.plusPin()
        preferenceHelper.plusPin()
        preferenceHelper.plusPin()
        preferenceHelper.plusPin()

        val expected = 4
        val actual = preferenceHelper.getPinCount()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testResetNumberOfPin() {

        preferenceHelper.plusPin()
        preferenceHelper.plusPin()
        preferenceHelper.resetPinCount()
        val expected = 0
        val actual = preferenceHelper.getPinCount()

        assertThat(actual, Is(expected))
    }

    @Test
    fun testSetTag() {
        preferenceHelper.plusPin()
        val expected = 1
        val actual = preferenceHelper.getPinCount()
        assertThat(actual, Is(expected))

        preferenceHelper.setTag(TAG_CUSTOM)
        preferenceHelper.plusPin()
        val expectedOther = 1
        val actualOther = preferenceHelper.getPinCount()
        assertThat(actualOther, Is(expectedOther))
    }
}
