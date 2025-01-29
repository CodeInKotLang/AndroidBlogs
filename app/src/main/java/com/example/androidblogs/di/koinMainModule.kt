package com.example.androidblogs.di

import com.example.androidblogs.data.local.BlogDatabase
import com.example.androidblogs.data.local.DatabaseFactory
import com.example.androidblogs.data.remote.HttpClientFactory
import com.example.androidblogs.data.remote.KtorRemoteBlogDataSource
import com.example.androidblogs.data.remote.RemoteBlogDataSource
import com.example.androidblogs.data.repository.BlogRepositoryImpl
import com.example.androidblogs.domain.repository.BlogRepository
import com.example.androidblogs.presentation.blog_list.BlogListViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinMainModule = module {

    single { DatabaseFactory.create(get()) }
    single { get<BlogDatabase>().blogDao() }

    single { HttpClientFactory.create(OkHttp.create()) }

    singleOf(::KtorRemoteBlogDataSource).bind<RemoteBlogDataSource>()
    singleOf(::BlogRepositoryImpl).bind<BlogRepository>()

    viewModelOf(::BlogListViewModel)
}