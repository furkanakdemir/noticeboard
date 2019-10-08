package net.furkanakdemir.noticeboard.data.mapper

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw.ChangeRaw
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ChangeDomainMapper :
    Mapper<ChangeRaw, Change> {
    override fun map(input: ChangeRaw): Change = Change(
        input.description,
        ChangeType.of(input.type)
    )
}
