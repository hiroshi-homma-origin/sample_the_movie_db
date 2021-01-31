package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.TvDataDao
import com.kotlin.project.data.entities.transform
import com.kotlin.project.data.model.search.SearchTvData
import com.kotlin.project.data.model.search.transform
import javax.inject.Inject

interface ResultTvDataRepository {
    suspend fun getTv(): List<SearchTvData>
    suspend fun getFavoriteTv(): List<SearchTvData>
    suspend fun findId(id: Int): SearchTvData?
    suspend fun insert(searchTvData: SearchTvData)
    suspend fun updateIsFavorite(id: Int, isUpdate: Boolean)
    suspend fun delete(searchTvData: SearchTvData)
    suspend fun deleteAll()
}

class ResultTvDataRepositoryImpl @Inject constructor(
    private val dao: TvDataDao
) : ResultTvDataRepository {
    override suspend fun getTv(): List<SearchTvData> =
        dao.getTv().map { it.transform() }

    override suspend fun getFavoriteTv(): List<SearchTvData> =
        dao.getFavoriteTv().map { it.transform() }

    override suspend fun findId(id: Int): SearchTvData? = dao.findId(id)?.transform()

    override suspend fun insert(searchTvData: SearchTvData) = dao.insert(searchTvData.transform())

    override suspend fun updateIsFavorite(id: Int, isUpdate: Boolean) =
        dao.updateIsFavorite(id, isUpdate)

    override suspend fun delete(searchTvData: SearchTvData) = dao.delete(searchTvData.transform())

    override suspend fun deleteAll() = dao.deleteAll()
}
