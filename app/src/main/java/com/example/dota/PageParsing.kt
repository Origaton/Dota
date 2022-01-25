package com.example.dota

import kotlinx.coroutines.*
import okio.IOException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PageParsing {
    private lateinit var doc: Document

    suspend fun creatingDataClass(): MatchInfo {
        docInitial()
        MatchReceiving(doc).matchReceiving()
        return MatchInfo(
            firstTeamInfo = MatchReceiving(doc).firstTeam(),
            secondTeamInfo = MatchReceiving(doc).secondTeam(),
            versusFieldInfo = MatchReceiving(doc).versusField(),
            otherMatchInfo = MatchReceiving(doc).otherMatchInfo()
        )
    }

    private suspend fun docInitial() {
        try {
            val docTask = CoroutineScope(Dispatchers.IO).async {
                Jsoup.connect("https://liquipedia.net/dota2/Liquipedia:Upcoming_and_ongoing_matches").get()
            }
            doc = docTask.await()
        } catch (e: IOException) {
            TODO("{обработать ошибку}")
        }
    }
}
