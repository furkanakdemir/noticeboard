package net.furkanakdemir.noticeboard.data.datasource

import junit.framework.Assert.assertEquals
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_DYNAMIC
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_JSON
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_XML
import net.furkanakdemir.noticeboard.util.fakes.TestData.items
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NoticeBoardDataSourceFactoryTest {

    private lateinit var noticeBoardDataSourceFactory: NoticeBoardDataSourceFactory

    @Mock
    internal lateinit var fileReader: FileReader

    @Mock
    internal lateinit var mapper: ListMapper<ReleaseRaw, Release>

    @Before
    fun setUp() {
        noticeBoardDataSourceFactory = NoticeBoardDataSourceFactory(fileReader, mapper)
    }

    @Test
    fun testCreateDynamicDataSource() {

        val actual = noticeBoardDataSourceFactory.createDataSource(TEST_SOURCE_DYNAMIC)

        assertThat(actual, instanceOf(DynamicNoticeBoardDataSource::class.java))
        assertEquals(actual.getReleases(), items)
    }

    @Test
    fun testCreateJsonDataSource() {

        val actual = noticeBoardDataSourceFactory.createDataSource(TEST_SOURCE_JSON)

        assertThat(actual, instanceOf(JsonNoticeBoardDataSource::class.java))
    }

    @Test
    fun testCreateXmlDataSource() {
        val actual = noticeBoardDataSourceFactory.createDataSource(TEST_SOURCE_XML)

        assertThat(actual, instanceOf(XmlNoticeBoardDataSource::class.java))
    }
}
