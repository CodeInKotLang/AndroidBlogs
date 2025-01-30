package com.example.androidblogs.data.remote

import com.example.androidblogs.data.util.Constant.GITHUB_URL
import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.net.UnknownHostException

class KtorRemoteBlogDataSource(
    private val httpClient: HttpClient
) : RemoteBlogDataSource {

    override suspend fun getAllBlogs(): Result<List<BlogDto>> {
        return try {
            val response = httpClient.get(urlString = GITHUB_URL)
            val blogs = response.body<List<BlogDto>>()
            Result.Success(blogs)
        } catch (e: UnknownHostException) {
            Result.Error(message = "Network error. Please verify your internet connection.")
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(message = "Something went wrong. ${e.message}")
        }
    }

}