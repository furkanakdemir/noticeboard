package net.furkanakdemir.noticeboard.domain

import net.furkanakdemir.noticeboard.InternalNoticeBoard
import net.furkanakdemir.noticeboard.Position.BOTTOM
import net.furkanakdemir.noticeboard.Position.NONE
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.result.Result.Success
import net.furkanakdemir.noticeboard.util.color.ColorProvider
import net.furkanakdemir.noticeboard.util.fakes.FakeActivity
import net.furkanakdemir.noticeboard.util.fakes.FakeColorProvider
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES_UNRELEASED
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES_UNRELEASED_BOTTOM
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_RELEASES_UNRELEASED_NONE
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_DYNAMIC
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_DYNAMIC_UNRELEASED_BOTTOM
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_DYNAMIC_UNRELEASED_DEFAULT
import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_SOURCE_DYNAMIC_UNRELEASED_NONE
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.hamcrest.CoreMatchers.`is` as Is

@RunWith(RobolectricTestRunner::class)
class ReleaseFetchUseCaseTest {

    private lateinit var releaseFetchUseCase: ReleaseFetchUseCase

    private lateinit var colorProvider: ColorProvider
    private lateinit var activity: FakeActivity
    private lateinit var activityController: ActivityController<FakeActivity>

    @Rule
    @JvmField
    var rule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        colorProvider = FakeColorProvider()
        activityController = Robolectric.buildActivity(FakeActivity::class.java).setup()
        activity = activityController.get()

        InternalNoticeBoard.getInstance(activity)
        releaseFetchUseCase = ReleaseFetchUseCase()
    }

    @Before
    fun setUpMockito() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testFetchReleases() {
        InternalNoticeBoard.getInstance(activity).fetchChanges(TEST_SOURCE_DYNAMIC)
        val actual = releaseFetchUseCase.fetch()
        val expected = Success(TEST_RELEASES)

        assertThat(actual, instanceOf(Success::class.java))
        assertThat((actual as Success), Is(expected))
    }

    @Test
    fun testFetchReleasesWithUnreleased() {
        InternalNoticeBoard.getInstance(activity)
            .fetchChanges(TEST_SOURCE_DYNAMIC_UNRELEASED_DEFAULT)
        val actual = releaseFetchUseCase.fetch()
        val expected = Success(TEST_RELEASES_UNRELEASED)

        assertThat(actual, instanceOf(Success::class.java))
        actual as Success<List<Release>>
        actual.data.forEachIndexed { index, release ->
            assertThat(release.isReleased, Is(expected.data[index].isReleased))
        }
    }

    @Test
    fun testFetchReleasesWithUnreleasedAtBottom() {
        InternalNoticeBoard.getInstance().setUnreleasedPosition(BOTTOM)
        InternalNoticeBoard.getInstance(activity)
            .fetchChanges(TEST_SOURCE_DYNAMIC_UNRELEASED_BOTTOM)
        val actual = releaseFetchUseCase.fetch()
        val expected = Success(TEST_RELEASES_UNRELEASED_BOTTOM)

        actual as Success<List<Release>>
        actual.data.forEachIndexed { index, release ->
            assertThat(release.isReleased, Is(expected.data[index].isReleased))
        }
    }

    @Test
    fun testFetchReleasesWithUnreleasedAtNone() {
        InternalNoticeBoard.getInstance().setUnreleasedPosition(NONE)
        InternalNoticeBoard.getInstance(activity).fetchChanges(TEST_SOURCE_DYNAMIC_UNRELEASED_NONE)
        val actual = releaseFetchUseCase.fetch()
        val expected = Success(TEST_RELEASES_UNRELEASED_NONE)

        actual as Success<List<Release>>

        actual.data.forEachIndexed { index, release ->
            assertThat(release.isReleased, Is(expected.data[index].isReleased))
        }
    }
}