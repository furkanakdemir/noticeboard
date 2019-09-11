package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.util.fakes.TestData.TEST_XML_STRING
import net.furkanakdemir.noticeboard.util.io.FileReader

class FakeXmlFileReader : FileReader {
    override fun getFile(filename: String): String? = TEST_XML_STRING
}
