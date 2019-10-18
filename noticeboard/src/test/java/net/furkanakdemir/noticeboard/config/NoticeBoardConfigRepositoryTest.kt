package net.furkanakdemir.noticeboard.config

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.ChangeType.ADDED
import net.furkanakdemir.noticeboard.ChangeType.CHANGED
import net.furkanakdemir.noticeboard.ChangeType.DEPRECATED
import net.furkanakdemir.noticeboard.ChangeType.FIXED
import net.furkanakdemir.noticeboard.ChangeType.REMOVED
import net.furkanakdemir.noticeboard.ChangeType.SECURITY
import net.furkanakdemir.noticeboard.DisplayOptions
import net.furkanakdemir.noticeboard.DisplayOptions.ACTIVITY
import net.furkanakdemir.noticeboard.DisplayOptions.DIALOG
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.fakes.FakeColorProvider
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class NoticeBoardConfigRepositoryTest {

    private lateinit var configRepository: ConfigRepository
    private var colorProvider: ColorProvider = FakeColorProvider()

    @Before
    fun setUp() {
        configRepository = NoticeBoardConfigRepository(colorProvider)
    }

    @Test
    fun testDefaultColorProvider() {
        val actual = configRepository.getColorProvider()
        assertThat(actual, Is(colorProvider))
        assertEquals(1, actual.getChangeTypeBackgroundColor(ADDED))
        assertEquals(2, actual.getChangeTypeBackgroundColor(CHANGED))
        assertEquals(3, actual.getChangeTypeBackgroundColor(DEPRECATED))
        assertEquals(4, actual.getChangeTypeBackgroundColor(REMOVED))
        assertEquals(5, actual.getChangeTypeBackgroundColor(FIXED))
        assertEquals(6, actual.getChangeTypeBackgroundColor(SECURITY))
        assertEquals(7, actual.getBackgroundColor())
        assertEquals(8, actual.getDescriptionColor())
        assertEquals(9, actual.getTitleColor(ACTIVITY))
        assertEquals(9, actual.getTitleColor(DIALOG))
    }

    @Test
    fun testCustomColorProvider() {

        val expected: ColorProvider = object : ColorProvider {
            override fun getBackgroundColor(): Int = 1

            override fun getDescriptionColor(): Int = 2

            override fun getTitleColor(displayOptions: DisplayOptions): Int = 3

            override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int = 7
        }

        configRepository.saveColorProvider(expected)
        val actual: ColorProvider = configRepository.getColorProvider()

        assertThat(actual, Is(expected))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ADDED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(CHANGED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(DEPRECATED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(REMOVED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(FIXED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(SECURITY))
        assertEquals(1, actual.getBackgroundColor())
        assertEquals(2, actual.getDescriptionColor())
        assertEquals(3, actual.getTitleColor(ACTIVITY))
        assertEquals(3, actual.getTitleColor(DIALOG))
    }
}
