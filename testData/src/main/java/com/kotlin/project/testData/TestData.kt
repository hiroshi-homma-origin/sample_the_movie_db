package com.kotlin.project.testData

import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.model.detail.BelongsToCollection
import com.kotlin.project.data.model.detail.Genres
import com.kotlin.project.data.model.detail.ProductionCompanies
import com.kotlin.project.data.model.detail.ProductionCountries
import com.kotlin.project.data.model.detail.SpokenLanguages
import com.kotlin.project.data.model.response.DetailResponse
import com.kotlin.project.data.model.response.SearchResponse
import com.kotlin.project.data.model.response.SearchTvResponse
import com.kotlin.project.data.model.search.SearchMovieData
import com.kotlin.project.data.model.search.SearchTvData

object TestData {

    const val resultsSize = 20
    const val totalPages = 8
    const val totalResults = 150
    const val resultsSizeForTv = 1
    const val totalPagesForTv = 1
    const val totalResultsForTv = 1
    const val errorMessage = "TestError"

    fun searchList(): ArrayList<SearchMovieData> {
        val arrayList: ArrayList<SearchMovieData> = arrayListOf()
        val list = (0..19).map { dummySearchResultsData }
        list.forEach { arrayList.add(it) }
        return arrayList
    }

    private val dummySearchResultsData = SearchMovieData(
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
        popularity = 96.267f,
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
        voteCount = 15025
    )

    val testSearchResponse = SearchResponse(
        page = 1, results = searchList(), totalPages = 8, totalResults = 147
    )

    val testSearchResponseForTv = SearchTvResponse(
        page = 1,
        results = arrayListOf(
            SearchTvData(
                backdropPath = "/nTvM4mhqNlHIvUkI1gVnW6XP7GG.jpg",
                firstAirDate = "2019-04-06",
                genreIds = arrayListOf(16, 18, 10765, 10759),
                id = 0,
                name = "鬼滅の刃",
                originCountry = arrayListOf("JP"),
                originalLanguage = "ja",
                originalName = "鬼滅の刃",
                overview = "時は大正、日本。炭を売る心優しき少年・炭治郎は、ある日鬼に家族を皆殺しにされてしまう。さらに唯一生き残った妹の禰豆子は鬼に変貌してしまった。絶望的な現実に打ちのめされる炭治郎だったが妹を人間に戻し、家族を殺した鬼を討つため、“鬼狩り”の道を進む決意をする。人と鬼とが織りなす哀しき兄妹の物語が、今、始まる――！",
                popularity = 35.853f,
                posterPath = "/7Uj6vqmznWQ3w3hpQ1eIY9mMyMw.jpg",
                voteAverage = 8.9f,
                voteCount = 1835
            )
        ),
        totalPages = 1, totalResults = 1
    )

    val testResultMovieData = MovieData(
        uid = 0,
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
        voteCount = 14990,
        isFavorite = false
    )
}
