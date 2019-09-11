package net.furkanakdemir.noticeboard.data.datasource

import junit.framework.Assert.assertEquals
import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
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
    lateinit var fileReader: FileReader

    @Mock
    lateinit var mapper: ListMapper<ReleaseRaw, Release>


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

    companion object {
        private val items = listOf(
            Release(
                "16 Sep 2019", "2.0.0",
                listOf(
                    Release.Change(
                        "New Login Page",
                        ChangeType.ADDED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.FIXED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.SECURITY.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.DEPRECATED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.REMOVED.ordinal
                    ),
                    Release.Change(
                        "Crash in Payment",
                        ChangeType.CHANGED.ordinal
                    )
                )
            )
        )

        private const val TEST_FILEPATH = "TEST_FILEPATH"
        private val TEST_SOURCE_DYNAMIC = Source.Dynamic(items)
        private val TEST_SOURCE_JSON = Source.Json(TEST_FILEPATH)
        private val TEST_SOURCE_XML = Source.Xml(TEST_FILEPATH)
    }
}