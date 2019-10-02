![NoticeBoard](art/noticeboard_banner.jpg)

NoticeBoard
-------

![GitHub](https://img.shields.io/github/license/furkanakdemir/noticeboard) 
![Bintray](https://img.shields.io/bintray/v/furkanakdemir/noticeboard/net.furkanakdemir.noticeboard)
![Codacy](https://img.shields.io/codacy/grade/656e09fc4d2645b4a4966858e539eada)
![CircleCI](https://img.shields.io/circleci/build/github/furkanakdemir/noticeboard/master)


NoticeBoard is a changelog library for Android API 21+.

Screenshot
-------


|ACTIVITY|DIALOG|
|----------|---------|
|![ACTIVITY](art/noticeboard_activity.png)|![DIALOG](art/noticeboard_dialog.png)|





Download
-------

```
dependencies {
    implementation "net.furkanakdemir:noticeboard:1.0.1"
}

```

Usage
-------

The `pin` function is used to show a change log list. It receives a lambda to make clients config their noticeboards.

Default configs are the following:  
TITLE: NoticeBoard  
DISPLAY_IN: Activity  
SOURCE_TYPE: Dynamic with empty list  


```java
NoticeBoard(this).pin {
    // configs
    title(...)
    displayIn(...)
    colorProvider(...)
    source(...)
}
```
`this` can be `Fragment` or `Activity`

### Title

The title of a noticeboard can be set by `title` function.

```java
NoticeBoard(this).pin {
    title("Release Notes")
}
```

### Display Options

NoticeBoard can be displayed in two ways.

Display Options  |
-----------------|
ACTIVITY         |
DIALOG           |

```java
NoticeBoard(this).pin {
    displayIn(ACTIVITY)
}
```

### Color Provider

A color provider can be passed to provide the change type backgrounds.

Override the default color provider


``` java
class CustomColorProvider(private val context: Context) : NoticeBoardColorProvider(context) {
    override var colorAdded: Int = ContextCompat.getColor(context, R.color.colorAccent)
}
```

Or Implement `ColorProvider` interface

```java
class CustomColorProvider : ColorProvider {
    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {

    }
}

```

Finally, a custom color provider can be set by `colorProvider` function.

```java
val customColorProvider = CustomColorProvider(this)

NoticeBoard(this).pin {
    colorProvider(customColorProvider)
}

```

### Source Types

There are currently 3 data source types.

Source  |
--------|
Dynamic |
Json    |
Xml     |

#### Dynamic

Save releases by creating dynamically

```java
val changes = listOf(
            Release("30 Sep 2019", "1.0.0",
                listOf(
                    Change("New Login Page", ADDED),
                    Change("Crash in Payment", CHANGED),
                    Change("Old theme will be removed", DEPRECATED),
                    Change("Tutorial page is removed", REMOVED),
                    Change("Crash in Payment", FIXED),
                    Change("HTTPS only requests", SECURITY)
                )
            )
        )

val myColorProvider = CustomColorProvider(this)

NoticeBoard(this).pin {
    source(Dynamic(changes))
}
```

#### Json

Store releases by creating a json file to the `/assets` folder.

Here is an example of JSON file
```json
[
  {
    "date": "30 Sep 2019",
    "version": "1.0.0",
    "changes": [
      {
        "type": 0,
        "description": "New Login Page"
      },
      {
        "type": 1,
        "description": "Crash in Payment"
      },
      {
        "type": 2,
        "description": "Old theme will be removed"
      },
      {
        "type": 3,
        "description": "Tutorial page is removed"
      },
      {
        "type": 4,
        "description": "Crash in Payment"
      },
      {
        "type": 5,
        "description": "HTTPS only requests"
      }
    ]
  }
]
```


```java
val filepath = "sample.json"

NoticeBoard(this).pin {
    source(Json(filepath))
}
```

#### Xml

Store releases by creating a xml file to the `/assets` folder.

Here is an example of XML file
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<releases>
    <release>
        <date>30 Sep 2019</date>
        <version>1.0.0</version>
        <change>
            <description>New Login Page</description>
            <type>0</type>
        </change>
        <change>
            <description>Crash in Payment</description>
            <type>1</type>
        </change>
        <change>
            <description>Old theme will be removed</description>
            <type>2</type>
        </change>
        <change>
            <description>Tutorial page is removed</description>
            <type>3</type>
        </change>
        <change>
            <description>Crash in Payment</description>
            <type>4</type>
        </change>
        <change>
            <description>HTTPS only requests</description>
            <type>5</type>
        </change>
    </release>
</releases>

```

```java
val filepath = "sample.xml"

NoticeBoard(this).pin {
    source(Xml(filepath))
}
```

Upcoming
-------


1. Add `Unreleased` section support
2. Add `Known Issues` section support
3. Add showing rules ( only once, always )
4. Add a custom change types
5. Add `MarkdownDataSource` support
6. Add remote data support
7. Add a GOTO button
8. Enhance Color API
9. New Date formats

License
-------

    Copyright 2019 Furkan Akdemir

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
