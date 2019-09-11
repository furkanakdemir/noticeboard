package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_JSON_STRING
import net.furkanakdemir.noticeboard.util.io.FileReader

class FakeJsonFileReader : FileReader {
    override fun getFile(filename: String): String? = TEST_JSON_STRING
}
