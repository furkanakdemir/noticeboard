package net.furkanakdemir.noticeboard.domain

import net.furkanakdemir.noticeboard.InternalNoticeBoard
import net.furkanakdemir.noticeboard.Position.BOTTOM
import net.furkanakdemir.noticeboard.Position.TOP
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.UnRelease
import net.furkanakdemir.noticeboard.data.model.UnRelease.Companion.TITLE_UNRELEASED_DEFAULT
import net.furkanakdemir.noticeboard.result.Result
import net.furkanakdemir.noticeboard.result.Result.Success

internal class ReleaseFetchUseCase {

    fun fetch(): Result<List<Release>> {
        val result = InternalNoticeBoard.getInstance().getChanges()
        val unreleasedPosition = InternalNoticeBoard.getInstance().getUnreleasedPosition()

        if (result is Success) {
            val releases = result.data.toMutableList()
            val releasedItems = releases.filter { it.isReleased }.toMutableList()
            val unreleasedChanges = releases.filter { !it.isReleased }.flatMap { it.changes }

            if (!unreleasedChanges.isNullOrEmpty()) {

                val title =
                    releases.firstOrNull { !it.isReleased }?.date ?: TITLE_UNRELEASED_DEFAULT

                val unrelease = UnRelease(title, unreleasedChanges)

                val resultReleases = when (unreleasedPosition) {
                    TOP -> {
                        releasedItems.add(INDEX_START, unrelease)
                        releasedItems
                    }
                    BOTTOM -> {
                        releasedItems.add(unrelease)
                        releasedItems
                    }
                    else -> {
                        releases
                    }
                }

                return Success(resultReleases)
            }
        }

        return result
    }

    companion object {
        private const val INDEX_START = 0
    }
}
