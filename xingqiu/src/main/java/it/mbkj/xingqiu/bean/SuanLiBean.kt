package it.mbkj.xingqiu.bean

data class SuanLiBean(
    val code: Int,
    val data: Data,
    val msg: String,
    val operation: String
)

data class Data(
    val list: List<Person>
)

data class Person(
    val chiyouxiangou: Int,
    val danjia: Double,
    val name: String,
    val readmill_id: Int,
    val suanli: String,
    val zhouqi: Int,
    val zongshouyi: Double,
    val meirishouyi:Double
)

/*
data class Data(
    val list: List<>
)

data class (
    val name: String,
    val readmill_id: Int,
    val 单价: String,
    val 周期: Int,
    val 总收益: String,
    val 持有限购: Int,
    val 算力: String
)*/
