package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DynamicNoticeBoardDataSourceTest {

    private lateinit var dynamicNoticeBoardDataSource: NoticeBoardDataSource

    @Before
    fun setUp() {
        dynamicNoticeBoardDataSource = DynamicNoticeBoardDataSource(TEST_RELEASES)
    }

    @Test
    fun getReleases() {
        val actual = dynamicNoticeBoardDataSource.getReleases()
        assertEquals(actual.size, TEST_RELEASES.size)
        assertEquals(actual, TEST_RELEASES)
    }
}
