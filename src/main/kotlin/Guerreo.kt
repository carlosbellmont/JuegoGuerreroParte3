import kotlin.random.Random

class Guerreo {

    private var vida = 50
    private val ataqueMinimo = 1
    private val ataqueMaximo = 6
    val nombre: String = javaClass.name
    private val puntosCuracion = 5

    fun atacar(adversario: Adversario) {
        val ataque = calcularAtaque()
        println("El $nombre ataca con $ataque puntos de daño")
        adversario.recibirAtaque(ataque)
    }

    fun recibirAtaque(dano : Int) {
        println("El $nombre ha recibido $dano puntos de daño")
        vida -= dano
        if (haMuerto()) println("El $nombre ha muerto") else println("A $nombre le quedan $vida")
    }

    fun haMuerto() : Boolean {
        return vida <= 0
    }

    private fun calcularAtaque(): Int {
        return Random.nextInt(ataqueMinimo, ataqueMaximo)
    }

    fun intentarCurarse() {
        if (Random.nextInt(0, 20) == 0) {
            println("El $nombre se ha curado $puntosCuracion puntos de daño")
            vida += 5
            println("A $nombre le quedan $vida")
        }
    }

}
