class Hobbit ( //FRODO
    nombre:String, estado: Boolean, raza:String, //Atributos heredados y sus tipos
    var anillo: Boolean //Atributo nuevo de la clase Hobbit
): Personajes(nombre,estado,raza) { //Nombre de la clase padre y nombre de sus atributos


    //Metodos
    fun ponerseAnillo(){
        anillo = true;
    }

    fun quitarseAnillo(){
        anillo = false;
    }
}