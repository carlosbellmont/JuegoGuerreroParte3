class Juego {

    var listaAdversario = mutableListOf<Adversario>()

    fun iniciarJuego() {
        println("Introduce el nombre de tu jugador: ")
        val guerrero = Guerrero(readLine().toString())
        listaAdversario = crearListaDeAdversarios()

        listaAdversario.forEach { adversario ->
            println("********* Comienza la batalla entre ${guerrero.nombre} y ${adversario.nombre} ********")
            do {
                guerrero.atacar(adversario)
                if (!adversario.haMuerto()) {
                    adversario.atacar(guerrero)
                } else {
                    guerrero.intentarCurarse()
                }
            } while (!(adversario.haMuerto() || guerrero.haMuerto()))
        }
        if (guerrero.haMuerto()) {
            println("El mal ha vencido")
        } else {
            println("El bien ha vencido")
            guerrero.guardarVictoria()
        }
        guerrero.gestionarFinalPartida()
    }


    private fun crearListaDeAdversarios(): MutableList<Adversario> {
        return MutableList(10){
            Adversario(it)
        }
    }

}