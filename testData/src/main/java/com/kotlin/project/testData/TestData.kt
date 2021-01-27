package com.kotlin.project.testData

import com.kotlin.project.data.model.BelongsToCollection
import com.kotlin.project.data.model.Genres
import com.kotlin.project.data.model.ProductionCompanies
import com.kotlin.project.data.model.ProductionCountries
import com.kotlin.project.data.model.SearchResultsData
import com.kotlin.project.data.model.SpokenLanguages
import com.kotlin.project.data.model.response.DetailResponse
import com.kotlin.project.data.model.response.SearchResponse

object TestData {

    const val resultsSize = 20
    const val totalPages = 8
    const val totalResults = 147
    const val resultsSizeForTv = 1
    const val totalPagesForTv = 1
    const val totalResultsForTv = 1
    const val errorMessage = "TestError"

    private fun searchList(): ArrayList<SearchResultsData> {
        val arrayList: ArrayList<SearchResultsData> = arrayListOf()
        val list = (0..19).map { dummySearchResultsData }
        list.forEach { arrayList.add(it) }
        return arrayList
    }

    private val dummySearchResultsData = SearchResultsData(
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

    val dummyDetailDataJapanese = DetailResponse(
        adult = false,
        backdropPath = "/zqkmTXzjkAgXmEWLRsY4UpTWCeo.jpg",
        belongsToCollection = BelongsToCollection(
            id = 10,
            name = "スター・ウォーズ シリーズ",
            posterPath = "/r8Ph5MYXL04Qzu4QBbq2KjqwtkQ.jpg",
            backdropPath = "/d8duYyyC9J5T825Hg7grmaabfxQ.jpg"
        ),
        budget = 11000000,
        genres = arrayListOf(
            Genres(
                id = 12, name = "アドベンチャー"
            ),
            Genres(
                id = 28, name = "アクション"
            ),
            Genres(
                id = 878, name = "サイエンスフィクション"
            )
        ),
        homepage = "http://www.starwars.com/films/star-wars-episode-iv-a-new-hope",
        id = 11,
        imdbId = "tt0076759",
        originalLanguage = "en",
        originalTitle = "Star Wars",
        overview = "遠い昔、遙か彼方の銀河では帝国軍の独裁体制が敷かれていた。反乱の機会をうかがう惑星アルデラーンのレイア姫は暗黒卿ダース・ベイダーに捕らえられるが、その寸前に二体のドロイドR2-D2とC-3POを砂漠の惑星タトゥイーンに送り込む。偶然にもそのドロイドを手に入れた青年ルークは、ジェダイの騎士団の一人オビ・ワン・ケノービや密輸船ミレニアム・ファルコンの船長ハン・ソロたちと共に、反乱軍と帝国軍の闘いに巻き込まれていく。",
        popularity = 96.448f,
        posterPath = "/8Ukkz0fbIzwpNEGdhzOdXXWSpXe.jpg",
        productionCompanies = arrayListOf(
            ProductionCompanies(
                id = 1,
                logoPath = "/o86DbpburjxrqAzEDhXZcyE8pDb.png",
                name = "Lucasfilm Ltd.",
                originalCountry = null
            ),
            ProductionCompanies(
                id = 25,
                logoPath = "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
                name = "20th Century Fox",
                originalCountry = null
            )
        ),
        productionCountries = arrayListOf(
            ProductionCountries(
                iso_3166_1 = "US",
                name = "United States of America"
            )
        ),
        releaseDate = "1977-05-25",
        revenue = 775398007,
        runtime = 121,
        spokenLanguages = arrayListOf(
            SpokenLanguages(
                englishName = "English",
                iso_639_1 = "en",
                name = "English"
            )
        ),
        status = "Released",
        tagline = "",
        title = "スター・ウォーズ エピソード４／新たなる希望",
        video = false,
        voteAverage = 8.2f,
        voteCount = 15021
    )

    val testSearchResponse = SearchResponse(
        page = 1, results = searchList(), totalPages = 8, totalResults = 147
    )
}
