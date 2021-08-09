package it.mbkj.login.bean

data class RegisterBean(
    val code: Int,
    val data: List<Any>,
    val msg: String,
    val operation: String
)