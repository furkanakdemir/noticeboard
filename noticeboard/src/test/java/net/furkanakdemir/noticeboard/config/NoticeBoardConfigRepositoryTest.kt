package net.furkanakdemir.noticeboard.config

import junit.framework.Assert.assertEquals
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.fakes.FakeColorProvider
import org.hamcrest.MatcherAssert.assertThat
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
        assertEquals(1, actual.getChangeTypeBackgroundColor(ChangeType.ADDED))
        assertEquals(2, actual.getChangeTypeBackgroundColor(ChangeType.CHANGED))
        assertEquals(3, actual.getChangeTypeBackgroundColor(ChangeType.DEPRECATED))
        assertEquals(4, actual.getChangeTypeBackgroundColor(ChangeType.REMOVED))
        assertEquals(5, actual.getChangeTypeBackgroundColor(ChangeType.FIXED))
        assertEquals(6, actual.getChangeTypeBackgroundColor(ChangeType.SECURITY))
    }

    @Test
    fun testCustomColorProvider() {

        val expected: ColorProvider = object : ColorProvider {
            override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {
                return 7
            }
        }

        configRepository.saveColorProvider(expected)
        val actual: ColorProvider = configRepository.getColorProvider()

        assertThat(actual, Is(expected))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ChangeType.ADDED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ChangeType.CHANGED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ChangeType.DEPRECATED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ChangeType.REMOVED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ChangeType.FIXED))
        assertEquals(7, actual.getChangeTypeBackgroundColor(ChangeType.SECURITY))
    }
}
