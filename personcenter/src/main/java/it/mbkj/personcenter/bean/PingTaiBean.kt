package it.mbkj.personcenter.bean

data class PingTaiBean(

    val jinbi: List<JinBi>,
    val pingtaibi: List<Pingtaibi>
)

data class Pingtaibi(
    val number: String,
    val time: String,
    val type: String
)
data class JinBi(
    val number: String,
    val time: String,
    val type: String
)