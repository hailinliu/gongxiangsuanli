package it.mbkj.login.bean

data class LoginBean(
    val code: Int,
    val data: Data,
    val msg: String,
    val operation: String
)
data class Data(
   val token:String
)