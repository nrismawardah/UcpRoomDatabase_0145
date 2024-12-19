package com.example.ucp2pam.ui.navigasi

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiDetailMk : AlamatNavigasi {
    override val route = "detail"
    const val KodeMk = "kodeMk"
    val routesWithArg = "$route/{$KodeMk}"
}

object DestinasiUpdateMk : AlamatNavigasi {
    override val route = "update"
    const val KodeMk = "kodeMk"
    val routesWithArg = "$route/{$KodeMk}"
}