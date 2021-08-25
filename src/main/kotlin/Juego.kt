class Juego {

    var guerrero = Guerreo()
    var listaAdversario = mutableListOf<Adversario>()

    fun iniciarJuego() {
        guerrero = Guerreo()
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
        if (guerrero.haMuerto()) println("El mal ha vencido") else println("El bien ha vencido")
    }


    private fun crearListaDeAdversarios(): MutableList<Adversario> {
        return MutableList<Adversario>(10){
            Adversario(it)
        }
    }



}