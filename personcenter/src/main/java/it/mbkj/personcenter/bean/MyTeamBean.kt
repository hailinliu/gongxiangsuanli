package it.mbkj.personcenter.bean


data class MyTeamBean(
    val users: List<User>,
    val users_count: Int,
    val valid_users: List<User>,
    val valid_users_count: Int
)

data class User(
    val phone: String,
    val register_time: String
)









/*(

    val list: List<Demo>,
    val team_machine_sum: Int,
    val team_sum: Int
)

data class Demo(
    val ceng: Int,
    val create_time: String,
    val id: Int,
    val level: String,
    val level_img: String,
    val phone: String,
    val team_machine_sum: Int
)*/