package it.mbkj.personcenter.bean

data class RunBean(

    val yijieshu: List<Yijieshu>,
    val yunxingzhong: List<Yunxinghzong>
)

data class Yijieshu(
    val id: Int,
    val jiazhi: String,
    val jieshushijian: String,
    val kaishishijian: String,
    val meirishouyi: Double,
    val name: String,
    val zhouqi: Int,
    val zongshouyi: String
)

data class Yunxinghzong(
    val id: Int,
    val jiazhi: String,
    val kaishishijian: String,
    val meirishouyi: Double,
    val name: String,
    val shengyukanguanggaocishu: Int,
    val yiyunxingtianshu: Int,
    val zhouqi: Int,
    val zongshouyi: String
)