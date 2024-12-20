package com.example.ucp2pam.ui.navigasi

interface AlamatNavigasi {
    val route: String
}

object Home : AlamatNavigasi {
    override val route = "home"
}

object ReadDosen : AlamatNavigasi {
    override val route = "read_dosen"
}

object InsertDosen : AlamatNavigasi{
    override val route: String = "insert_dosen"
}

object ReadMk: AlamatNavigasi {
    override val route = "read_mk"
}

object InsertMk : AlamatNavigasi{
    override val route: String = "insert_mk"
}

object DetailMk : AlamatNavigasi {
    override val route = "detail_mk"
    const val KodeMk = "kodeMk"
    val routesWithArg = "$route/{$KodeMk}"
}

object UpdateMk : AlamatNavigasi {
    override val route = "update_mk"
    const val KodeMk = "kodeMk"
    val routesWithArg = "$route/{$KodeMk}"
}