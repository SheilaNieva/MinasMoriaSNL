class Elfo( //LEGOLAS
    nombre: String, estado: Boolean, raza: String,  //Atributos heredados y sus tipos
    var carcajFlechas: Int //Atributo nuevo de la clase Elfo
) :Personajes(nombre, estado, raza) { //Nombre de la clase padre y nombre de sus atributos



    //METODOS
    fun lanzarFlecha() {
        carcajFlechas = -1
    }

    fun recargarCarcaj(flechas: Int) {
        carcajFlechas = carcajFlechas + flechas
    }

}