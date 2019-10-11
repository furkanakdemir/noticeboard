package net.furkanakdemir.noticeboard.data.mapper

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.ReleaseRaw
import net.furkanakdemir.noticeboard.data.model.UnRelease
import net.furkanakdemir.noticeboard.util.mapper.ListMapper
import net.furkanakdemir.noticeboard.util.mapper.Mapper

internal class ReleaseDomainMapper(
    private val changeDomainMapper: ListMapper<ReleaseRaw.ChangeRaw, Release.Change>
) : Mapper<ReleaseRaw, Release> {
    override fun map(input: ReleaseRaw): Release {

        with(input) {
            val changes = changeDomainMapper.map(changes)
            return if (released) {
                Release(date, version, changes, released)
            } else {
                UnRelease(date, changes)
            }
        }
    }
}
