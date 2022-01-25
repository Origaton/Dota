package com.example.dota

data class MatchInfo(
    val firstTeamInfo: FirstTeamInfo,
    val secondTeamInfo: SecondTeamInfo,
    val versusFieldInfo: VersusFieldInfo,
    val otherMatchInfo: OtherMatchInfo,
    val matchesList: MatchesList
) {
    companion object {
        data class FirstTeamInfo(
            val firstTeam: MutableList<String>,
            val firstTeamImage: MutableList<String>
        )
        data class SecondTeamInfo(
            val secondTeam: MutableList<String>,
            val secondTeamImage: MutableList<String>
        )

        data class VersusFieldInfo(
            val live: MutableList<Boolean>,
            val score: MutableList<String?>,
            val format: MutableList<String?>
        )

        data class OtherMatchInfo(
            val time: MutableList<Long>,
            val event: MutableList<String>,
            val eventImage: MutableList<String>
        )

        data class MatchesList(
            val matchesList: MutableList<MutableList<String>>
        )
    }
}
