package com.kotlin.project.testData

import com.kotlin.project.data.model.ResultsData
import com.kotlin.project.data.model.SearchResponse

object TestData {

    const val resultsSize = 20
    const val totalPages = 8
    const val totalResults = 147
    const val errorMessage = "TestError"

    private fun resultsData(): ArrayList<ResultsData> {
        val arrayList: ArrayList<ResultsData> = arrayListOf()
        val list = (0..19).map { dummyData }
        list.forEach { arrayList.add(it) }
        return arrayList
    }

    private val dummyData = ResultsData(
        adult = false,
        backdropPath = "/zqkmTXzjkAgXmEWLRsY4UpTWCeo.jpg",
        genreIds = arrayListOf(12, 28, 878),
        id = 11,
        originalLanguage = "en",
        originalTitle = "Star Wars",
        overview = "遠い昔、遙か彼方の銀河では帝国軍の独裁体制が敷かれていた。反乱の機会をうかがう惑星アルデラーンのレイア姫は暗黒卿ダース・ベイダーに捕らえられるが、その寸前に二体のドロイドR2-D2とC-3POを砂漠の惑星タトゥイーンに送り込む。偶然にもそのドロイドを手に入れた青年ルークは、ジェダイの騎士団の一人オビ・ワン・ケノービや密輸船ミレニアム・ファルコンの船長ハン・ソロたちと共に、反乱軍と帝国軍の闘いに巻き込まれていく。",
        popularity = 99.103f,
        posterPath = "/8Ukkz0fbIzwpNEGdhzOdXXWSpXe.jp",
        releaseDate = "1977-05-25",
        title = "スター・ウォーズ エピソード４／新たなる希望",
        video = false,
        voteAverage = 8.2f,
        voteCount = 14990
    )
    val testSearchResponse = SearchResponse(
        page = 1, results = resultsData(), totalPages = 8, totalResults = 147
    )
}
