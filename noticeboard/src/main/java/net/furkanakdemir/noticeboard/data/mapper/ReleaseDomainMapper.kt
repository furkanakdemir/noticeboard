package net.furkanakdemir.noticeboard.data.mapper

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import net.furkanakdemir.noticeboard.util.mapper.Mapper
import javax.inject.Inject

class ReleaseDomainMapper @Inject constructor(
    private val changeDomainMapper: ListMapper<ReleaseRaw.ChangeRaw, Release.Change>
) : Mapper<ReleaseRaw, Release> {
    override fun map(input: ReleaseRaw): Release {

        val changes = changeDomainMapper.map(input.changes)

        return Release(
            input.date,
            input.version,
            changes
        )
    }
}
