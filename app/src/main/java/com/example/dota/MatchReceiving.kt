package com.example.dota

import android.util.Log
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class MatchReceiving(private val doc: Document) {

    private lateinit var matchElements: Elements
    private lateinit var firstTeamElements: Elements
    private lateinit var secondTeamElements: Elements
    private lateinit var versusFieldElements: Elements
    private lateinit var otherMatchInfoElements: Elements

    fun matchReceiving(): MatchInfo.Companion.MatchesList {
        initElements()
        val matchesList = mutableListOf<MutableList<String>>()
        matchElements.forEach {
            matchesList.add(fillingTheList(it))
        }
        return MatchInfo.Companion.MatchesList(matchesList = matchesList)
    }

    private fun fillingTheList(element: Element): MutableList<String> {
        val singleMatch = mutableListOf<String>()

        firstTeamElements = element.getElementsByClass("team-left")

        return singleMatch
    }

    private fun initElements() {
        matchElements =
            doc.getElementsByClass("wikitable wikitable-striped infobox_matches_content")
        firstTeamElements = doc.getElementsByClass("team-left")
        versusFieldElements = doc.getElementsByClass("versus")
        secondTeamElements = doc.getElementsByClass("team-right")
        otherMatchInfoElements = doc.getElementsByClass("match-filler")
    }

    fun firstTeam(): MatchInfo.Companion.FirstTeamInfo {
        val firstTeamList = mutableListOf<String>()
        val firstTeamImageList = mutableListOf<String>()
        firstTeamElements = doc.getElementsByClass("team-left")
        firstTeamElements.forEach {
            firstTeamList.add(it.text())
            firstTeamImageList.add(getDataWithAttribute(it, "src"))
        }
        return MatchInfo.Companion.FirstTeamInfo(
            firstTeam = firstTeamList,
            firstTeamImage = firstTeamImageList
        )
    }

    fun versusField(): MatchInfo.Companion.VersusFieldInfo {
        val liveList = mutableListOf<Boolean>()
        val scoreList = mutableListOf<String?>()
        val formatList = mutableListOf<String?>()
        versusFieldElements = doc.getElementsByClass("versus")
        versusFieldElements.forEach {
            if (it.text().contains("vs")) {
                liveList.add(false)
                scoreList.add(null)
            } else {
                liveList.add(true)
                scoreList.add(it.text().substringBefore(" "))
            }
            if (it.text().contains("Bo")) {
                formatList.add(it.text().substringAfter(" "))
            } else {
                formatList.add(null)
            }
        }
        return MatchInfo.Companion.VersusFieldInfo(
            live = liveList,
            score = scoreList,
            format = formatList
        )
    }

    fun secondTeam(): MatchInfo.Companion.SecondTeamInfo {
        val secondTeamList = mutableListOf<String>()
        val secondTeamImageList = mutableListOf<String>()
        secondTeamElements = doc.getElementsByClass("team-right")
        secondTeamElements.forEach {
            secondTeamList.add(it.text())
            secondTeamImageList.add(getDataWithAttribute(it, "src"))
        }
        return MatchInfo.Companion.SecondTeamInfo(
            secondTeam = secondTeamList,
            secondTeamImage = secondTeamImageList
        )
    }

    fun otherMatchInfo(): MatchInfo.Companion.OtherMatchInfo {
        val timeList = mutableListOf<Long>()
        val eventList = mutableListOf<String>()
        val eventImageList = mutableListOf<String>()
        otherMatchInfoElements = doc.getElementsByClass("match-filler")
        otherMatchInfoElements.forEach {
            timeList.add(getDataWithAttribute(it, "data-timestamp").toLong())
            eventList.add(getDataWithAttribute(it, "title"))
            eventImageList.add(getDataWithAttribute(it, "src"))
        }
        return MatchInfo.Companion.OtherMatchInfo(
            time = timeList,
            event = eventList,
            eventImage = eventImageList
        )
    }

    private fun getDataWithAttribute(element: Element, attr: String): String {
        var returnValue = ""
        when (attr) {
            "src" -> returnValue = element.select("img").attr(attr)
            "title" -> returnValue = element.select("a").attr(attr)
            "data-timestamp" -> returnValue =
                element.select("span").attr(attr)
        }
        return returnValue
    }
}