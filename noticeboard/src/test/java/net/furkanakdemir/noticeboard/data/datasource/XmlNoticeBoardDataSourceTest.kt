package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.util.fakes.FakeXmlFileReader
import net.furkanakdemir.noticeboard.util.fakes.TestData.EMPTY
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_FILEPATH
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_RELEASE
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_STRING
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_UNRELEASE
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_WITH_UNRELEASED_STRING
import net.furkanakdemir.noticeboard.util.io.FileReader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class XmlNoticeBoardDataSourceTest {

    private lateinit var fileReader: FileReader
    private lateinit var xmlNoticeBoardDataSource: NoticeBoardDataSource

    @Test
    fun testValidReleases() {
        fileReader = FakeXmlFileReader(TEST_XML_STRING)
        xmlNoticeBoardDataSource = XmlNoticeBoardDataSource(fileReader, TEST_FILEPATH)
        val actual = xmlNoticeBoardDataSource.getReleases()
        assertEquals(1, actual.size)
        assertThat(actual[0].date, Is(TEST_XML_RELEASE.date))
        assertThat(actual[0].version, Is(TEST_XML_RELEASE.version))
        assertThat(actual[0].changes, Is(TEST_XML_RELEASE.changes))
        assertThat(actual[0].isReleased, Is(TEST_XML_RELEASE.isReleased))
    }

    @Test
    fun testValidReleasesWithUnreleased() {
        fileReader = FakeXmlFileReader(TEST_XML_WITH_UNRELEASED_STRING)
        xmlNoticeBoardDataSource = XmlNoticeBoardDataSource(fileReader, TEST_FILEPATH)
        val actual = xmlNoticeBoardDataSource.getReleases()
        assertEquals(4, actual.size)
        assertThat(actual[0].date, Is(TEST_XML_RELEASE.date))
        assertThat(actual[0].version, Is(TEST_XML_RELEASE.version))
        assertThat(actual[0].changes, Is(TEST_XML_RELEASE.changes))
        assertThat(actual[0].isReleased, Is(TEST_XML_RELEASE.isReleased))

        assertThat(actual[1].date, Is(TEST_XML_UNRELEASE.date))
        assertThat(actual[1].version, Is(EMPTY))
        assertThat(actual[1].changes, Is(TEST_XML_UNRELEASE.changes))
        assertThat(actual[1].isReleased, Is(TEST_XML_UNRELEASE.isReleased))

        assertThat(actual[2].date, Is(TEST_XML_RELEASE.date))
        assertThat(actual[2].version, Is(TEST_XML_RELEASE.version))
        assertThat(actual[2].changes, Is(TEST_XML_RELEASE.changes))
        assertThat(actual[2].isReleased, Is(TEST_XML_RELEASE.isReleased))

        assertThat(actual[3].date, Is(TEST_XML_UNRELEASE.date))
        assertThat(actual[3].version, Is(EMPTY))
        assertThat(actual[3].changes, Is(TEST_XML_UNRELEASE.changes))
        assertThat(actual[3].isReleased, Is(TEST_XML_UNRELEASE.isReleased))
    }
}
