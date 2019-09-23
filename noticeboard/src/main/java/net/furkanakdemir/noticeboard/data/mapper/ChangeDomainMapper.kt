package net.furkanakdemir.noticeboard.data.mapper

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ChangeDomainMapper :
    Mapper<ReleaseRaw.ChangeRaw, Release.Change> {
    override fun map(input: ReleaseRaw.ChangeRaw): Release.Change = Release.Change(
        input.description,
        input.type
    )
}
