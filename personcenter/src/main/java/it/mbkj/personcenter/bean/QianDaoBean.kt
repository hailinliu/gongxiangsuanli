package it.mbkj.personcenter.bean

data class QianDaoBean(
    val count: Double,
    val get: String,
    val log: List<Log>,
    val today_is_sign: Boolean
)

data class Log(
    val number: String,
    val time: String
)