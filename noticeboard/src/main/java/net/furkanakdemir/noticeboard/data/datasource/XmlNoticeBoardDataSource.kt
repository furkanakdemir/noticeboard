package net.furkanakdemir.noticeboard.data.datasource

import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.util.io.FileReader
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.IOException
import javax.xml.parsers.ParserConfigurationException
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory


class XmlNoticeBoardDataSource(
    private val fileReader: FileReader,
    private val filepath: String
) : NoticeBoardDataSource {

    internal var releaseMap = HashMap<String, String>()
    internal var changeMap = HashMap<String, String>()
    internal var changeList = mutableListOf<Release.Change>()

    override fun getReleases(): List<Release> {

        val releases = mutableListOf<Release>()


        val jsonString = fileReader.getFile(filepath)

        try {

            val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()

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
                    if (localName == KEY_RELEASE) {
                        releaseMap.clear()
                        changeMap.clear()
                        changeList = mutableListOf()
                    }

                    if (localName == KEY_CHANGE) {
                        changeMap.clear()
                    }
                }

                override fun endElement(uri: String, localName: String, qName: String) {
                    currentElement = false
                    if (localName.equals(KEY_DATE, ignoreCase = true))
                        releaseMap[KEY_DATE] = currentValue
                    else if (localName.equals(KEY_VERSION, ignoreCase = true))
                        releaseMap[KEY_VERSION] = currentValue
                    else if (localName.equals(KEY_DESCRIPTION, ignoreCase = true))
                        changeMap[KEY_DESCRIPTION] = currentValue
                    else if (localName.equals(KEY_TYPE, ignoreCase = true))
                        changeMap[KEY_TYPE] = currentValue
                    else if (localName.equals(KEY_CHANGE, ignoreCase = true)) {
                        changeList.add(
                            Release.Change(
                                changeMap[KEY_DESCRIPTION].orEmpty(),
                                changeMap[KEY_TYPE]?.toInt()
                                    ?: -1
                            )
                        )
                        changeMap.clear()

                    } else if (localName.equals(KEY_RELEASE, ignoreCase = true)) {
                        releases.add(
                            Release(
                                releaseMap[KEY_DATE].orEmpty(),
                                releaseMap[KEY_VERSION].orEmpty(),
                                changeList
                            )
                        )
                    }
                }

                //overriding the characters() method of DefaultHandler
                override fun characters(ch: CharArray, start: Int, length: Int) {
                    if (currentElement) {
                        currentValue += String(ch, start, length)
                    }

                }
            }
            val inputStream = jsonString?.byteInputStream(Charsets.UTF_8)
            saxParser.parse(inputStream, defaultHandler)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }



        return releases


    }

    companion object {
        private const val KEY_DATE = "date"
        private const val KEY_VERSION = "version"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_TYPE = "type"
        private const val KEY_CHANGE = "change"
        private const val KEY_RELEASE = "release"
    }
}