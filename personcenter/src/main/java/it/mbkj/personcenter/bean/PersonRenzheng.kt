package it.mbkj.personcenter.bean

data class PersonRenzheng(
    val code: Int,
    val data: Datap,
    val msg: String,
    val success: Boolean
)

data class Datap(
    val address: String,
    val birthday: String,
    val desc: String,
    val order_no: String,
    val result: String,
    val sex: String
)