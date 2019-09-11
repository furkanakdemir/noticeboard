package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.ChangeType
import net.furkanakdemir.noticeboard.Source
import net.furkanakdemir.noticeboard.data.model.Release

object TestData {

    internal val items = listOf(
        Release(
            "16 Sep 2019", "2.0.0", listOf(
                Release.Change(
                    "New Login Page",
                    ChangeType.ADDED.ordinal
                ),
                Release.Change(
                    "Crash in Payment",
                    ChangeType.FIXED.ordinal
                ),
                Release.Change(
                    "Crash in Payment",
                    ChangeType.SECURITY.ordinal
                ),
                Release.Change(
                    "Crash in Payment",
                    ChangeType.DEPRECATED.ordinal
                ),
                Release.Change(
                    "Crash in Payment",
                    ChangeType.REMOVED.ordinal
                ),
                Release.Change(
                    "Crash in Payment",
                    ChangeType.CHANGED.ordinal
                )
            )
        )
    )

    internal const val TEST_XML_STRING = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "    <releases>\n" +
            "        <release>\n" +
            "            <change>\n" +
            "                <description>Lorem</description>\n" +
            "                <type>0</type>\n" +
            "            </change>\n" +
            "            <change>\n" +
            "                <description>Lorem</description>\n" +
            "                <type>1</type>\n" +
            "            </change>\n" +
            "            <date>22 Jun 2019</date>\n" +
            "            <version>7.8.9</version>\n" +
            "        </release>\n" +
            "        <release>\n" +
            "            <change>\n" +
            "                <description>denied</description>\n" +
            "                <type>2</type>\n" +
            "            </change>\n" +
            "            <change>\n" +
            "                <description>file</description>\n" +
            "                <type>3</type>\n" +
            "            </change>\n" +
            "            <date>11 Mar 2019</date>\n" +
            "            <version>4.5.6</version>\n" +
            "        </release>\n" +
            "    </releases>"

    internal const val TEST_JSON_STRING = "[\n" +
            "  {\n" +
            "    \"date\": \"12 Aug 2019\",\n" +
            "    \"version\": \"1.2.3\",\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 0,\n" +
            "        \"description\": \"Lorem\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 1,\n" +
            "        \"description\": \"Lorem\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\": \"10 Aug 2019\",\n" +
            "    \"version\": \"1.2.2\",\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 2,\n" +
            "        \"description\": \"Lorem\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 3,\n" +
            "        \"description\": \"Lorem\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]"

    internal val TEST_JSON_RELEASE = Release(
        "12 Aug 2019",
        "1.2.3",
        listOf(
            Release.Change("Lorem", 0),
            Release.Change("Lorem", 1)
        )
    )

    internal val TEST_XML_RELEASE = Release(
        "22 Jun 2019",
        "7.8.9",
        listOf(
            Release.Change("Lorem", 0),
            Release.Change("Lorem", 1)
        )
    )


    internal const val TEST_FILEPATH = "TEST_FILEPATH"
    internal val TEST_SOURCE_DYNAMIC = Source.Dynamic(items)
    internal val TEST_SOURCE_JSON = Source.Json(TEST_FILEPATH)
    internal val TEST_SOURCE_XML = Source.Xml(TEST_FILEPATH)


}