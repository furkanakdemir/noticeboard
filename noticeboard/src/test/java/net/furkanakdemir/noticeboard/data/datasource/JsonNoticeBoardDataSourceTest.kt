package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.data.mapper.ChangeDomainMapper
import net.furkanakdemir.noticeboard.data.mapper.ReleaseDomainMapper
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.fakes.FakeJsonFileReader
import net.furkanakdemir.noticeboard.util.fakes.TestData.EMPTY
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_FILEPATH
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_JSON_RELEASE
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_JSON_STRING
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_JSON_UNRELEASE
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_JSON_WITH_UNRELEASED_STRING
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import net.furkanakdemir.noticeboard.util.mapper.RealListMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class JsonNoticeBoardDataSourceTest {

    private lateinit var fileReader: FileReader
    private lateinit var mapper: ListMapper<ReleaseRaw, Release>
    private lateinit var jsonNoticeBoardDataSource: NoticeBoardDataSource

    @Before
    fun setUp() {
        val domainMapper = ReleaseDomainMapper(RealListMapper(ChangeDomainMapper()))
        mapper = RealListMapper(domainMapper)
    }

    @Test
    fun testValidReleases() {
        fileReader = FakeJsonFileReader(TEST_JSON_STRING)
        jsonNoticeBoardDataSource = JsonNoticeBoardDataSource(fileReader, TEST_FILEPATH, mapper)
        val actual = jsonNoticeBoardDataSource.getReleases()
        assertEquals(1, actual.size)
        assertThat(actual[0].date, Is(TEST_JSON_RELEASE.date))
        assertThat(actual[0].version, Is(TEST_JSON_RELEASE.version))
        assertThat(actual[0].changes, Is(TEST_JSON_RELEASE.changes))
        assertThat(actual[0].isReleased, Is(TEST_JSON_RELEASE.isReleased))
    }

    @Test
    fun testValidReleasesWithUnreleased() {
        fileReader = FakeJsonFileReader(TEST_JSON_WITH_UNRELEASED_STRING)
        jsonNoticeBoardDataSource = JsonNoticeBoardDataSource(fileReader, TEST_FILEPATH, mapper)
        val actual = jsonNoticeBoardDataSource.getReleases()
        assertEquals(4, actual.size)
        assertThat(actual[0].date, Is(TEST_JSON_RELEASE.date))
        assertThat(actual[0].version, Is(TEST_JSON_RELEASE.version))
        assertThat(actual[0].changes, Is(TEST_JSON_RELEASE.changes))
        assertThat(actual[0].isReleased, Is(TEST_JSON_RELEASE.isReleased))

        assertThat(actual[1].date, Is(TEST_JSON_UNRELEASE.date))
        assertThat(actual[1].version, Is(EMPTY))
        assertThat(actual[1].changes, Is(TEST_JSON_UNRELEASE.changes))
        assertThat(actual[1].isReleased, Is(TEST_JSON_UNRELEASE.isReleased))

        assertThat(actual[2].date, Is(TEST_JSON_RELEASE.date))
        assertThat(actual[2].version, Is(TEST_JSON_RELEASE.version))
        assertThat(actual[2].changes, Is(TEST_JSON_RELEASE.changes))
        assertThat(actual[2].isReleased, Is(TEST_JSON_RELEASE.isReleased))

        assertThat(actual[3].date, Is(TEST_JSON_UNRELEASE.date))
        assertThat(actual[3].version, Is(EMPTY))
        assertThat(actual[3].changes, Is(TEST_JSON_UNRELEASE.changes))
        assertThat(actual[3].isReleased, Is(TEST_JSON_UNRELEASE.isReleased))
    }
}
