import com.google.gson.Gson
import java.io.File
import kotlin.random.Random

class Guerrero(nombre: String) {

    private var vida = 50
    private var ataqueMinimo = 1
    private var ataqueMaximo = 6
    var nombre: String = javaClass.name
    private var puntosCuracion = 5
    private var partidasJugadas = 0
    private var partidasGanadas = 0

    init {
        cargarJugador(nombre)
    }

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

    fun guardarVictoria() {
        partidasGanadas++
    }

    fun gestionarFinalPartida() {
        partidasJugadas++
        println("$nombre ha luchado en $partidasJugadas batallas, habiendo ganado $partidasGanadas y perdido ${partidasJugadas - partidasGanadas}")
        guardarJugador()
    }

    private fun cargarJugador(nombre: String) {
        this.nombre = nombre
        try {
            val archivo = File("Guerrero/$nombre.txt")
            val g = fromJson(archivo.readText())
            partidasJugadas = g.partidasJugadas
        } catch (exception : Exception) {
            println("No hay datos disponibles de este jugador")
        }
    }

    private fun fromJson(json: String) : Guerrero {
        val gson = Gson()
        return gson.fromJson(json, Guerrero::class.java)
    }

    private fun toJson(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    private fun guardarJugador(){
        val archivo = File("Guerrero/$nombre.txt")
        archivo.writeText(toJson())
    }


}
