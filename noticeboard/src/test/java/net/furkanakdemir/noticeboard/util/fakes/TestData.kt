package net.furkanakdemir.noticeboard.util.fakes

import net.furkanakdemir.noticeboard.ChangeType.ADDED
import net.furkanakdemir.noticeboard.ChangeType.CHANGED
import net.furkanakdemir.noticeboard.ChangeType.DEPRECATED
import net.furkanakdemir.noticeboard.ChangeType.FIXED
import net.furkanakdemir.noticeboard.ChangeType.REMOVED
import net.furkanakdemir.noticeboard.ChangeType.SECURITY
import net.furkanakdemir.noticeboard.Source.Dynamic
import net.furkanakdemir.noticeboard.Source.Json
import net.furkanakdemir.noticeboard.Source.Xml
import net.furkanakdemir.noticeboard.data.model.Release
import net.furkanakdemir.noticeboard.data.model.Release.Change
import net.furkanakdemir.noticeboard.data.model.UnRelease
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ChangeItem
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.ReleaseHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedHeader
import net.furkanakdemir.noticeboard.ui.NoticeBoardItem.UnreleasedItem

object TestData {

    internal const val EMPTY = ""
    internal const val TEST_VERSION = "1.0.0"
    internal const val TEST_DATE = "30 Sep 2019"
    internal const val TEST_TITLE = "TEST_TITLE"
    internal const val TEST_TITLE_OTHER = "TEST_TITLE_OTHER"
    internal const val TEST_FILEPATH = "TEST_FILEPATH"
    internal const val TEST_UNRELEASED_TITLE = "Unreleased"

    internal val TEST_CHANGES = listOf(
        Change("New Login Page", ADDED),
        Change("Toolbar in Checkout", CHANGED),
        Change("Old theme will be removed", DEPRECATED),
        Change("Tutorial page is removed", REMOVED),
        Change("Crash in Payment", FIXED),
        Change("HTTPS only requests", SECURITY)
    )

    internal val TEST_RELEASES = listOf(Release(TEST_DATE, TEST_VERSION, TEST_CHANGES))
    internal val TEST_RELEASES_WITH_UNRELEASED = listOf(
        Release(TEST_DATE, TEST_VERSION, listOf(Change("New Login Page", ADDED))),
        UnRelease(TEST_TITLE, listOf(Change("New Login Page", ADDED)))
    )

    internal val TEST_CHANGE_ITEMS = listOf(
        ChangeItem(ADDED, "New Login Page"),
        ChangeItem(CHANGED, "Toolbar in Checkout"),
        ChangeItem(DEPRECATED, "Old theme will be removed"),
        ChangeItem(REMOVED, "Tutorial page is removed"),
        ChangeItem(FIXED, "Crash in Payment"),
        ChangeItem(SECURITY, "HTTPS only requests")
    )

    internal val TEST_UNRELEASED_CHANGE_ITEMS = listOf(
        UnreleasedItem("New Login Page"),
        UnreleasedItem("Toolbar in Checkout"),
        UnreleasedItem("Old theme will be removed"),
        UnreleasedItem("Tutorial page is removed"),
        UnreleasedItem("Crash in Payment"),
        UnreleasedItem("HTTPS only requests")
    )

    internal val TEST_NOTICEBOARD_ITEMS: List<NoticeBoardItem> =
        listOf(
            ReleaseHeader(TEST_DATE, TEST_VERSION)
        ).plus(
            TEST_CHANGE_ITEMS
        )

    internal val TEST_NOTICEBOARD_ITEMS_WITH_UNRELEASED: List<NoticeBoardItem> =
        listOf(
            ReleaseHeader(TEST_DATE, TEST_VERSION),
            ChangeItem(ADDED, "New Login Page"),
            UnreleasedHeader(TEST_TITLE),
            UnreleasedItem("New Login Page")
        )

    internal val TEST_RELEASES_UNRELEASED = listOf(
        UnRelease(TEST_TITLE, listOf(Change("New Login Page", ADDED))),
        Release(TEST_DATE, TEST_VERSION, listOf(Change("New Login Page", ADDED)))
    )
    internal val TEST_RELEASES_UNRELEASED_BOTTOM = listOf(
        Release(TEST_DATE, TEST_VERSION, listOf(Change("New Login Page", ADDED))),
        UnRelease(TEST_TITLE, listOf(Change("New Login Page", ADDED)))
    )
    internal val TEST_RELEASES_UNRELEASED_NONE = listOf(
        Release(TEST_DATE, TEST_VERSION, listOf(Change("New Login Page", ADDED))),
        UnRelease(TEST_TITLE, listOf(Change("New Login Page", ADDED))),
        Release(TEST_DATE, TEST_VERSION, listOf(Change("New Login Page", ADDED))),
        UnRelease(TEST_TITLE, listOf(Change("New Login Page", ADDED)))
    )

    internal const val TEST_XML_STRING = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<releases>\n" +
            "    <release>\n" +
            "        <date>30 Sep 2019</date>\n" +
            "        <version>1.0.0</version>\n" +
            "        <change>\n" +
            "            <description>New Login Page</description>\n" +
            "            <type>0</type>\n" +
            "        </change>\n" +
            "        <change>\n" +
            "            <description>Toolbar in Checkout</description>\n" +
            "            <type>1</type>\n" +
            "        </change>\n" +
            "        <change>\n" +
            "            <description>Old theme will be removed</description>\n" +
            "            <type>2</type>\n" +
            "        </change>\n" +
            "        <change>\n" +
            "            <description>Tutorial page is removed</description>\n" +
            "            <type>3</type>\n" +
            "        </change>\n" +
            "        <change>\n" +
            "            <description>Crash in Payment</description>\n" +
            "            <type>4</type>\n" +
            "        </change>\n" +
            "        <change>\n" +
            "            <description>HTTPS only requests</description>\n" +
            "            <type>5</type>\n" +
            "        </change>\n" +
            "    </release>\n" +
            "</releases>"

    internal const val TEST_XML_WITH_UNRELEASED_STRING =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<releases>\n" +
                "    <release>\n" +
                "        <date>30 Sep 2019</date>\n" +
                "        <version>1.0.0</version>\n" +
                "        <released>true</released>\n" +
                "        <change>\n" +
                "            <description>New Login Page</description>\n" +
                "            <type>0</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Toolbar in Checkout</description>\n" +
                "            <type>1</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Old theme will be removed</description>\n" +
                "            <type>2</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Tutorial page is removed</description>\n" +
                "            <type>3</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Crash in Payment</description>\n" +
                "            <type>4</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>HTTPS only requests</description>\n" +
                "            <type>5</type>\n" +
                "        </change>\n" +
                "    </release>\n" +
                "    <release>\n" +
                "        <date>Unreleased</date>\n" +
                "        <released>false</released>\n" +
                "        <change>\n" +
                "            <description>New Login Page</description>\n" +
                "            <type>0</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Toolbar in Checkout</description>\n" +
                "            <type>1</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Old theme will be removed</description>\n" +
                "            <type>2</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Tutorial page is removed</description>\n" +
                "            <type>3</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Crash in Payment</description>\n" +
                "            <type>4</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>HTTPS only requests</description>\n" +
                "            <type>5</type>\n" +
                "        </change>\n" +
                "    </release>\n" +
                "    <release>\n" +
                "        <date>30 Sep 2019</date>\n" +
                "        <version>1.0.0</version>\n" +
                "        <released>true</released>\n" +
                "        <change>\n" +
                "            <description>New Login Page</description>\n" +
                "            <type>0</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Toolbar in Checkout</description>\n" +
                "            <type>1</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Old theme will be removed</description>\n" +
                "            <type>2</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Tutorial page is removed</description>\n" +
                "            <type>3</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Crash in Payment</description>\n" +
                "            <type>4</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>HTTPS only requests</description>\n" +
                "            <type>5</type>\n" +
                "        </change>\n" +
                "    </release>\n" +
                "    <release>\n" +
                "        <date>Unreleased</date>\n" +
                "        <released>false</released>\n" +
                "        <change>\n" +
                "            <description>New Login Page</description>\n" +
                "            <type>0</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Toolbar in Checkout</description>\n" +
                "            <type>1</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Old theme will be removed</description>\n" +
                "            <type>2</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Tutorial page is removed</description>\n" +
                "            <type>3</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>Crash in Payment</description>\n" +
                "            <type>4</type>\n" +
                "        </change>\n" +
                "        <change>\n" +
                "            <description>HTTPS only requests</description>\n" +
                "            <type>5</type>\n" +
                "        </change>\n" +
                "    </release>\n" +
                "</releases>"

    internal const val TEST_JSON_STRING = "[\n" +
            "  {\n" +
            "    \"date\": \"30 Sep 2019\",\n" +
            "    \"version\": \"1.0.0\",\n" +
            "    \"released\": true,\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 0,\n" +
            "        \"description\": \"New Login Page\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 1,\n" +
            "        \"description\": \"Toolbar in Checkout\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 2,\n" +
            "        \"description\": \"Old theme will be removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 3,\n" +
            "        \"description\": \"Tutorial page is removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 4,\n" +
            "        \"description\": \"Crash in Payment\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 5,\n" +
            "        \"description\": \"HTTPS only requests\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]"


    internal const val TEST_JSON_WITH_UNRELEASED_STRING = "[\n" +
            "  {\n" +
            "    \"date\": \"30 Sep 2019\",\n" +
            "    \"version\": \"1.0.0\",\n" +
            "    \"released\": true,\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 0,\n" +
            "        \"description\": \"New Login Page\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 1,\n" +
            "        \"description\": \"Toolbar in Checkout\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 2,\n" +
            "        \"description\": \"Old theme will be removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 3,\n" +
            "        \"description\": \"Tutorial page is removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 4,\n" +
            "        \"description\": \"Crash in Payment\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 5,\n" +
            "        \"description\": \"HTTPS only requests\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\": \"Unreleased\",\n" +
            "    \"version\": \"1.0.0\",\n" +
            "    \"released\": false,\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 0,\n" +
            "        \"description\": \"New Login Page\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 1,\n" +
            "        \"description\": \"Toolbar in Checkout\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 2,\n" +
            "        \"description\": \"Old theme will be removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 3,\n" +
            "        \"description\": \"Tutorial page is removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 4,\n" +
            "        \"description\": \"Crash in Payment\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 5,\n" +
            "        \"description\": \"HTTPS only requests\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\": \"30 Sep 2019\",\n" +
            "    \"version\": \"1.0.0\",\n" +
            "    \"released\": true,\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 0,\n" +
            "        \"description\": \"New Login Page\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 1,\n" +
            "        \"description\": \"Toolbar in Checkout\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 2,\n" +
            "        \"description\": \"Old theme will be removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 3,\n" +
            "        \"description\": \"Tutorial page is removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 4,\n" +
            "        \"description\": \"Crash in Payment\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 5,\n" +
            "        \"description\": \"HTTPS only requests\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"date\": \"Unreleased\",\n" +
            "    \"version\": \"1.0.0\",\n" +
            "    \"released\": false,\n" +
            "    \"changes\": [\n" +
            "      {\n" +
            "        \"type\": 0,\n" +
            "        \"description\": \"New Login Page\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 1,\n" +
            "        \"description\": \"Toolbar in Checkout\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 2,\n" +
            "        \"description\": \"Old theme will be removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 3,\n" +
            "        \"description\": \"Tutorial page is removed\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 4,\n" +
            "        \"description\": \"Crash in Payment\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": 5,\n" +
            "        \"description\": \"HTTPS only requests\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]"

    internal val TEST_JSON_RELEASE = Release(TEST_DATE, TEST_VERSION, TEST_CHANGES)
    internal val TEST_JSON_UNRELEASE = UnRelease(TEST_UNRELEASED_TITLE, TEST_CHANGES)

    internal val TEST_XML_RELEASE = Release(TEST_DATE, TEST_VERSION, TEST_CHANGES)
    internal val TEST_XML_UNRELEASE = UnRelease(TEST_UNRELEASED_TITLE, TEST_CHANGES)

    internal val TEST_SOURCE_DYNAMIC = Dynamic(TEST_RELEASES)
    internal val TEST_SOURCE_JSON = Json(TEST_FILEPATH)
    internal val TEST_SOURCE_XML = Xml(TEST_FILEPATH)

    internal val TEST_SOURCE_DYNAMIC_UNRELEASED_DEFAULT = Dynamic(TEST_RELEASES_UNRELEASED)
    internal val TEST_SOURCE_DYNAMIC_UNRELEASED_BOTTOM = Dynamic(TEST_RELEASES_UNRELEASED_BOTTOM)
    internal val TEST_SOURCE_DYNAMIC_UNRELEASED_NONE = Dynamic(TEST_RELEASES_UNRELEASED_NONE)
}
