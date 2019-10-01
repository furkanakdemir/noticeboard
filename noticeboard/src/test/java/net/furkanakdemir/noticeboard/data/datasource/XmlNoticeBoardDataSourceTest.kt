package net.furkanakdemir.noticeboard.data.datasource

import junit.framework.Assert.assertEquals
import net.furkanakdemir.noticeboard.util.fakes.FakeXmlFileReader
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_FILEPATH
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_RELEASE
import net.furkanakdemir.noticeboard.util.io.FileReader
import org.junit.Before
import org.junit.Test

class XmlNoticeBoardDataSourceTest {

    private lateinit var fileReader: FileReader
    private lateinit var xmlNoticeBoardDataSource: NoticeBoardDataSource

    @Before
    fun setUp() {
        fileReader = FakeXmlFileReader()
        xmlNoticeBoardDataSource = XmlNoticeBoardDataSource(fileReader, TEST_FILEPATH)
    }

    @Test
    fun getReleases() {
        val actual = xmlNoticeBoardDataSource.getReleases()
        assertEquals(2, actual.size)
        assertEquals(actual[0], TEST_XML_RELEASE)
    }
}
