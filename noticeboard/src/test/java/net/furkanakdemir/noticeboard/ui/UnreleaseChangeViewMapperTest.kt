package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_CHANGES
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_UNRELEASED_CHANGE_ITEMS
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class UnreleaseChangeViewMapperTest {

    private lateinit var unreleaseChangeViewMapper: Mapper<List<Change>, List<UnreleasedItem>>

    @Before
    fun setUp() {
        unreleaseChangeViewMapper = UnreleaseChangeViewMapper()
    }

    @Test
    fun testEmptyList() {

        val list = emptyList<Change>()
        val expected = emptyList<UnreleasedItem>()
        val actual = unreleaseChangeViewMapper.map(list)

        assertThat(actual, Is(expected))
    }

    @Test
    fun testValidList() {

        val expected = TEST_UNRELEASED_CHANGE_ITEMS
        val actual = unreleaseChangeViewMapper.map(TEST_CHANGES)

        assertThat(actual.size, Is(expected.size))
        assertThat(actual[0], instanceOf(UnreleasedItem::class.java))

        actual.forEachIndexed { index, noticeBoardItem ->
            assertThat(noticeBoardItem.description, Is(expected[index].description))
        }
    }
}
