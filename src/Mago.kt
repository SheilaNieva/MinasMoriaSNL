class Mago (
    nombre:String, estado:Boolean, raza:String,
    var energiaVara: Int
): Personajes(nombre, estado, raza)
{


    //METODOS
    fun recargarVara(energia:Int){
        energiaVara = energia
    }


    //Metodo que va a devolver un entero, como es la energia de la vara
    fun poderVara(): Int = this.energiaVara
}