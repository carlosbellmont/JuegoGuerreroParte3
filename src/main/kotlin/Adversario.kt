import kotlin.random.Random

class Adversario(numeroDeEnemigo : Int) {

    private var vida = 10
    private val ataqueMinimo = 0
    private val ataqueMaximo = 5
    val nombre = "${javaClass.name} $numeroDeEnemigo"


    fun recibirAtaque(daño : Int) {
        println("El $nombre ha recibido $daño puntos de daño")
        vida -= daño
        if (haMuerto()) println("El $nombre ha muerto") else println("A $nombre le quedan $vida")

    }

    fun atacar(guerreo: Guerreo) {
        val ataque = calcularAtaque()
        println("El $nombre ataca con $ataque puntos de daño")
        guerreo.recibirAtaque(ataque)
    }

    fun haMuerto() : Boolean {
        return vida <= 0
    }

    private fun calcularAtaque(): Int {
        return Random.nextInt(ataqueMinimo, ataqueMaximo)
    }

}
