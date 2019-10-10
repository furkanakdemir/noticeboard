package net.furkanakdemir.noticeboard.ui

import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.util.fakes.TestData
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class ChangeViewMapperTest {

    private lateinit var changeViewMapper: Mapper<List<Change>, List<ChangeItem>>

    @Before
    fun setUp() {
        changeViewMapper = ChangeViewMapper()
    }

    @Test
    fun testEmptyList() {

        val list = emptyList<Change>()
        val expected = emptyList<ChangeItem>()
        val actual = changeViewMapper.map(list)

        assertThat(actual, Is(expected))
    }

    @Test
    fun testValidList() {

        val expected = TestData.TEST_CHANGE_ITEMS
        val actual = changeViewMapper.map(TestData.TEST_CHANGES)

        assertThat(actual.size, Is(expected.size))
        assertThat(actual[0], instanceOf(ChangeItem::class.java))

        actual.forEachIndexed { index, noticeBoardItem ->
            assertThat(noticeBoardItem.description, Is(expected[index].description))
            assertThat(noticeBoardItem.type, Is(expected[index].type))
        }
    }
}
