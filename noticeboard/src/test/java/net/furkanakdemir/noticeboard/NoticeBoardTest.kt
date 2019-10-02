package net.furkanakdemir.noticeboard

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import net.furkanakdemir.noticeboard.NoticeBoard.Companion.TITLE_DEFAULT
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.fakes.FakeActivity
import net.furkanakdemir.noticeboard.util.fakes.FakeColorProvider
import net.furkanakdemir.noticeboard.util.fakes.TestData.EMPTY
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_FILEPATH
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_TITLE
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_TITLE_OTHER
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.hamcrest.CoreMatchers.`is` as Is

@RunWith(RobolectricTestRunner::class)
class NoticeBoardTest {

    private lateinit var colorProvider: ColorProvider
    private lateinit var activity: FakeActivity
    private lateinit var activityController: ActivityController<FakeActivity>

    @Rule
    @JvmField
    var rule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        colorProvider = FakeColorProvider()
        activityController = buildActivity(FakeActivity::class.java).setup()
        activity = activityController.get()
    }

    @Before
    fun setUpMockito() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testCreateWithDefaults() {
        val actual = NoticeBoard(activity)

        assertThat(actual.sourceType, instanceOf(Source.Dynamic::class.java))
        assertThat(actual.displayOptions, Is(DisplayOptions.ACTIVITY))
        assertThat(actual.title, Is("NoticeBoard"))
    }

    @Test
    fun testPin() {
        val actual = NoticeBoard(activity)
        actual.internalNoticeBoard = mock()
        actual.pin { }

        assertThat(actual.displayOptions, Is(DisplayOptions.ACTIVITY))
        assertThat(actual.sourceType, instanceOf(Source.Dynamic::class.java))
        verify(actual.internalNoticeBoard).fetchChanges(actual.sourceType)
    }

    @Test
    fun testMultiPin() {
        val actual = NoticeBoard(activity)

        actual.internalNoticeBoard = mock()
        actual.pin { }

        assertThat(actual.displayOptions, Is(DisplayOptions.ACTIVITY))
        assertThat(actual.sourceType, instanceOf(Source.Dynamic::class.java))
        verify(actual.internalNoticeBoard).fetchChanges(actual.sourceType)

        actual.pin {
            source(Source.Json(TEST_FILEPATH))
            title(TEST_TITLE_OTHER)
            colorProvider(colorProvider)
        }
        assertThat(actual.displayOptions, Is(DisplayOptions.ACTIVITY))
        assertThat(actual.sourceType, instanceOf(Source.Json::class.java))
        assertThat(actual.title, Is(TEST_TITLE_OTHER))
        verify(actual.internalNoticeBoard).saveColorProvider(colorProvider)
        verify(actual.internalNoticeBoard).fetchChanges(actual.sourceType)
    }

    @Test
    fun testSource() {
        val actual = NoticeBoard(activity)

        actual.source(Source.Xml(TEST_FILEPATH))

        assertThat(actual.sourceType, instanceOf(Source.Xml::class.java))

        actual.source(Source.Json(TEST_FILEPATH))

        assertThat(actual.sourceType, instanceOf(Source.Json::class.java))

        actual.source(Source.Dynamic(listOf()))

        assertThat(actual.sourceType, instanceOf(Source.Dynamic::class.java))
    }

    @Test
    fun testDisplayIn() {

        val actual = NoticeBoard(activity)

        actual.displayIn(DisplayOptions.DIALOG)

        assertThat(actual.displayOptions, Is(DisplayOptions.DIALOG))

        actual.displayIn(DisplayOptions.ACTIVITY)

        assertThat(actual.displayOptions, Is(DisplayOptions.ACTIVITY))
    }

    @Test
    fun testValidTitle() {
        val actual = NoticeBoard(activity)

        actual.title(TEST_TITLE)

        assertThat(actual.title, Is(TEST_TITLE))
    }

    @Test
    fun testEmptyTitle() {
        val actual = NoticeBoard(activity)

        actual.title(EMPTY)

        assertThat(actual.title, Is(TITLE_DEFAULT))
    }

    @Test
    fun testColorProvider() {

        val actual = NoticeBoard(activity)
        actual.internalNoticeBoard = mock()

        actual.colorProvider(colorProvider)

        verify(actual.internalNoticeBoard).saveColorProvider(colorProvider)
    }

    @After
    fun tearDownMockito() {
        Mockito.validateMockitoUsage()
    }
}
