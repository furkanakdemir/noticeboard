package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ReleaseHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_NOTICEBOARD_ITEMS
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_NOTICEBOARD_ITEMS_WITH_UNRELEASED
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES_WITH_UNRELEASED
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class ReleaseViewMapperTest {

    private lateinit var releaseViewMapper: Mapper<List<Release>, List<NoticeBoardItem>>

    @Before
    fun setUp() {
        releaseViewMapper = ReleaseViewMapper()
    }

    @Test
    fun testEmptyList() {

        val list = emptyList<Release>()
        val expected = emptyList<NoticeBoardItem>()
        val actual = releaseViewMapper.map(list)

        assertThat(actual, Is(expected))
    }

    @Test
    fun testValidList() {

        val expected = TEST_NOTICEBOARD_ITEMS
        val actual = releaseViewMapper.map(TEST_RELEASES)

        assertThat(actual.size, Is(expected.size))
        assertThat(actual[0], instanceOf(ReleaseHeader::class.java))
        assertThat(actual[1], instanceOf(ChangeItem::class.java))

        assertThat(
            (actual[0] as ReleaseHeader).date,
            Is((expected[0] as ReleaseHeader).date)
        )

        assertThat(
            (actual[0] as ReleaseHeader).version,
            Is((expected[0] as ReleaseHeader).version)
        )

        assertThat(
            (actual[1] as ChangeItem).description,
            Is((expected[1] as ChangeItem).description)
        )

        assertThat(
            (actual[1] as ChangeItem).type,
            Is((expected[1] as ChangeItem).type)
        )
    }

    @Test
    fun testValidListWithUnreleased() {

        val expected = TEST_NOTICEBOARD_ITEMS_WITH_UNRELEASED
        val actual = releaseViewMapper.map(TEST_RELEASES_WITH_UNRELEASED)

        assertThat(actual.size, Is(expected.size))
        assertThat(actual[0], instanceOf(ReleaseHeader::class.java))
        assertThat(actual[1], instanceOf(ChangeItem::class.java))

        assertThat(
            (actual[0] as ReleaseHeader).date,
            Is((expected[0] as ReleaseHeader).date)
        )

        assertThat(
            (actual[0] as ReleaseHeader).version,
            Is((expected[0] as ReleaseHeader).version)
        )

        assertThat(
            (actual[1] as ChangeItem).description,
            Is((expected[1] as ChangeItem).description)
        )

        assertThat(
            (actual[1] as ChangeItem).type,
            Is((expected[1] as ChangeItem).type)
        )

        assertThat(
            (actual[2] as UnreleasedHeader).title,
            Is((expected[2] as UnreleasedHeader).title)
        )

        assertThat(
            (actual[3] as UnreleasedItem).description,
            Is((expected[3] as UnreleasedItem).description)
        )
    }
}
