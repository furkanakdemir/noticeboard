package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.util.fakes.TestData.items
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DynamicNoticeBoardDataSourceTest {

    private lateinit var dynamicNoticeBoardDataSource: NoticeBoardDataSource

    @Before
    fun setUp() {
        dynamicNoticeBoardDataSource = DynamicNoticeBoardDataSource(items)
    }

    @Test
    fun getReleases() {
        val actual = dynamicNoticeBoardDataSource.getReleases()
        assertEquals(actual.size, items.size)
        assertEquals(actual, items)
    }
}
