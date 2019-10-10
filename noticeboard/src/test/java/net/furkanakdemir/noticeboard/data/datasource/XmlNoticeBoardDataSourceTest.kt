package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.util.fakes.FakeXmlFileReader
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_FILEPATH
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_RELEASE
import net.furkanakdemir.noticeboard.util.io.FileReader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

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
        assertThat(actual[0].date, Is(TEST_XML_RELEASE.date))
        assertThat(actual[0].version, Is(TEST_XML_RELEASE.version))
        assertThat(actual[0].changes, Is(TEST_XML_RELEASE.changes))
    }
}
