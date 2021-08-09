package it.mbkj.login.bean

data class CodeBean(
    val code: Int,
    val data: Data1,
    val msg: String,
    val operation: String
)

data class Data1(
    val code: String
)