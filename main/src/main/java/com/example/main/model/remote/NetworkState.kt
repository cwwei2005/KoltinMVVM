package com.example.main.model.remote

import com.example.main.model.MyRepository

data class NetworkState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val IDEL = NetworkState(Status.IDEL)
        val LOADING = NetworkState(Status.LOADING)
        val LOADED = NetworkState(Status.SUCCESS)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}

enum class Status {
    IDEL,
    LOADING,
    SUCCESS,
    FAILED
}