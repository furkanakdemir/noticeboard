![NoticeBoard](art/noticeboard_banner.png)

NoticeBoard
-------

![GitHub](https://img.shields.io/github/license/furkanakdemir/noticeboard) 
![Bintray](https://img.shields.io/bintray/v/furkanakdemir/noticeboard/net.furkanakdemir.noticeboard)
![Codacy](https://img.shields.io/codacy/grade/656e09fc4d2645b4a4966858e539eada)
![CircleCI](https://img.shields.io/circleci/build/github/furkanakdemir/noticeboard/master)
![MinAPI](https://img.shields.io/badge/API-21%2B-blue)



NoticeBoard is a changelog library for Android API 21+. It helps developers display the history of changes in their applications.

It shows a list of `Release` or `UnRelease` which contains a list of `Change`. 

It receives a source of changes and config. 

You can find a sample code of NoticeBoard in this repository.  

`NoticeBoardSample` app is now available on Google Play.

<a href="https://play.google.com/store/apps/details?id=net.furkanakdemir.noticeboardsample"><img src="art/google-play-badge.png" width="323"></a>
 


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

### Tag

The tag of a noticeboard can be set by `tag` function.  
It can be used to reset the number of noticeboard display after an update.

```java
NoticeBoard(this).pin {
    tag(versionCode)
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

### Show Rules

The show rule of a noticeboard can be set by `showRule` function.

Show Rules |
-----------|
Once       |
Always     |
Limited    |

```java
NoticeBoard(this).pin {
    showRule(Once)
    showRule(Always)
    showRule(Limited(3))
}
```

### Color Provider

A color provider can be passed to provide the change type backgrounds.

Override the default color provider


``` java
class CustomColorProvider(private val context: Context) : NoticeBoardColorProvider(context) {
    override var colorAdded: Int = R.color.colorAccent
    override var colorChanged: Int = R.color.colorAccent
    override var colorDeprecated: Int = R.color.colorPrimary
    override var colorRemoved: Int = R.color.colorPrimary
    override var colorFixed: Int = R.color.colorPrimaryDark
    override var colorSecurity: Int = R.color.colorPrimaryDark
    override var colorDescriptionText = R.color.colorDescriptionCustom
    override var colorBackground: Int = R.color.colorBackgroundCustom
    override var colorTitleDialog: Int = R.color.colorTitleCustom
    override var colorTitleActivity: Int = R.color.colorTitleCustom
}
```

Or Implement `ColorProvider` interface

```java
class CustomColorProvider : ColorProvider {
    override fun getChangeTypeBackgroundColor(changeType: ChangeType): Int {

    }

    override fun getBackgroundColor(): Int {

    }

    override fun getDescriptionColor(): Int {

    }

    override fun getTitleColor(displayOptions: DisplayOptions): Int {

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

### Unreleased Section  

An unreleased section can be added to a noticeboard.  

`UnRelease` can be created dynamically  

or  

`released` field can be set to false in JSON or XML file. (default: true)

The position of the unreleased section can be configured. (default: TOP)  

Position  |
----------|
TOP       |
BOTTOM    |
NONE      |

If `TOP` or `BOTTOM` is selected, all of the unreleased items are merged into one list.  
If `NONE` is selected, the items remains as it is.  


```java
NoticeBoard(this).pin {
    unreleasedPosition(BOTTOM)
}

```

### Change Types

There are currently 6 built-in change types.

Change Type | Enum
------------|------------
ADDED       |0
CHANGED     |1
DEPRECATED  |2
REMOVED     |3
FIXED       |4
SECURITY    |5

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
                    Change("Toolbar in Checkout", CHANGED),
                    Change("Old theme will be removed", DEPRECATED),
                    Change("Tutorial page is removed", REMOVED),
                    Change("Crash in Payment", FIXED),
                    Change("HTTPS only requests", SECURITY)
                )
            )
        )

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
    "released": true,
    "changes": [
      {
        "type": 0,
        "description": "New Login Page"
      },
      {
        "type": 1,
        "description": "Toolbar in Checkout"
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
        <released>true</released>
        <change>
            <description>New Login Page</description>
            <type>0</type>
        </change>
        <change>
            <description>Toolbar in Checkout</description>
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
1. Add `Known Issues` section support
2. Add showing rules ( only once, always )
3. Add a custom change types
4. Add `MarkdownDataSource` support
5. Add remote data support
6. Add a GOTO button
7. Enhance Color API
8. New Date formats

Contribution
-------
If you've found an error in the library or sample, please file an issue.

Patches are encouraged, and may be submitted by forking this project and submitting a pull request.

If you contributed to `noticeboard` but your name is not in the list, please feel free to add yourself to it!

- [Furkan Akdemir](https://github.com/furkanakdemir) - Maintainer
- [Nuh Koca](https://github.com/nuhkoca) - Collaborator, CI Improvements


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
