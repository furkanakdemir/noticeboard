package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.util.io.FileReader

class FakeJsonFileReader(private val response: String) : FileReader {
    override fun getFile(filename: String): String? = response
}
