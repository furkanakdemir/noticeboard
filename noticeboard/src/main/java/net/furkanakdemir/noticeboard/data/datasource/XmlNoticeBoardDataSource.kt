package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.data.model.UnRelease
import net.furkanakdemir.noticeboard.util.io.FileReader
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.IOException
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

internal class XmlNoticeBoardDataSource(
    private val fileReader: FileReader,
    private val filepath: String
) : NoticeBoardDataSource {

    internal var releaseMap = HashMap<String, String>()
    internal var changeMap = HashMap<String, String>()
    internal var changeList = mutableListOf<Change>()

    @Throws(IOException::class, SAXException::class)
    override fun getReleases(): List<Release> {

        val releases = mutableListOf<Release>()

        val jsonString = fileReader.getFile(filepath)

        val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
        parserFactory.isNamespaceAware = true

        val saxParser: SAXParser = parserFactory.newSAXParser()
        val defaultHandler = object : DefaultHandler() {
            var currentValue = ""
            var currentElement = false

            override fun startElement(
                uri: String,
                localName: String,
                qName: String,
                attributes: org.xml.sax.Attributes
            ) {
                currentElement = true
                currentValue = ""
                if (isRelease(localName)) {
                    releaseMap.clear()
                    changeMap.clear()
                    changeList = mutableListOf()
                }

                if (isChange(localName)) {
                    changeMap.clear()
                }
            }

            override fun endElement(uri: String, localName: String, qName: String) {
                currentElement = false
                when {
                    isDate(localName) -> releaseMap[KEY_DATE] = currentValue
                    isVersion(localName) -> releaseMap[KEY_VERSION] = currentValue
                    isReleased(localName) -> releaseMap[KEY_RELEASED] = currentValue
                    isDescription(localName) -> changeMap[KEY_DESCRIPTION] = currentValue
                    isType(localName) -> changeMap[KEY_TYPE] = currentValue
                    isChange(localName) -> addChange()
                    isRelease(localName) -> addRelease(releases)
                }
            }

            override fun characters(ch: CharArray, start: Int, length: Int) {
                if (currentElement) {
                    currentValue += String(ch, start, length)
                }
            }
        }

        val inputStream = jsonString?.byteInputStream(Charsets.UTF_8)
        saxParser.parse(inputStream, defaultHandler)

        return releases
    }

    private fun addRelease(releases: MutableList<Release>) {

        val date = releaseMap[KEY_DATE].orEmpty()
        val version = releaseMap[KEY_VERSION].orEmpty()
        val isReleased = releaseMap[KEY_RELEASED]?.toBoolean() ?: true

        if (isReleased) {
            releases.add(Release(date, version, changeList))
        } else {
            releases.add(UnRelease(date, changeList))
        }
    }

    private fun addChange() {

        val desc = changeMap[KEY_DESCRIPTION].orEmpty()
        val type = changeMap[KEY_TYPE]?.toInt() ?: TYPE_DEFAULT

        changeList.add(Change(desc, ChangeType.of(type)))
        changeMap.clear()
    }

    private fun isRelease(localName: String) = localName.equals(KEY_RELEASE, true)

    private fun isChange(localName: String) = localName.equals(KEY_CHANGE, true)

    private fun isType(localName: String) = localName.equals(KEY_TYPE, true)

    private fun isDescription(localName: String): Boolean = localName.equals(KEY_DESCRIPTION, true)

    private fun isVersion(localName: String) = localName.equals(KEY_VERSION, true)

    private fun isDate(localName: String) = localName.equals(KEY_DATE, true)

    private fun isReleased(localName: String) = localName.equals(KEY_RELEASED, true)

    companion object {
        private const val KEY_DATE = "date"
        private const val KEY_VERSION = "version"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_TYPE = "type"
        private const val KEY_CHANGE = "change"
        private const val KEY_RELEASE = "release"
        private const val KEY_RELEASED = "released"

        private const val TYPE_DEFAULT = -1
    }
}
