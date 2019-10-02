package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.data.mapper.ChangeDomainMapper
import net.furkanakdemir.noticeboard.data.mapper.ReleaseDomainMapper
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.fakes.FakeJsonFileReader
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_FILEPATH
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_JSON_RELEASE
import net.furkanakdemir.noticeboard.util.io.FileReader
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import net.furkanakdemir.noticeboard.util.mapper.RealListMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class JsonNoticeBoardDataSourceTest {

    private lateinit var fileReader: FileReader
    private lateinit var mapper: ListMapper<ReleaseRaw, Release>
    private lateinit var xmlNoticeBoardDataSource: NoticeBoardDataSource

    @Before
    fun setUp() {
        fileReader = FakeJsonFileReader()
        val domainMapper = ReleaseDomainMapper(RealListMapper(ChangeDomainMapper()))
        mapper = RealListMapper(domainMapper)
        xmlNoticeBoardDataSource = JsonNoticeBoardDataSource(fileReader, TEST_FILEPATH, mapper)
    }

    @Test
    fun getReleases() {
        val actual = xmlNoticeBoardDataSource.getReleases()
        assertEquals(2, actual.size)
        assertEquals(actual[0], TEST_JSON_RELEASE)
    }
}
