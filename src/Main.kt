object Main {
    @JvmStatic //Para exponer como un método estático las funciones públicas
    fun main(args: Array<String>) {
        //Declaración de variables
        var  muerto: Boolean = false
        var contadorSalas:  Int = 1

        //Creacion de objetos, segun los personajes y sus atributos
        val gandalf = Mago("Gándalf", true, "mago", generaAleatorio(0, 29))
        val legolas = Elfo("Légolas", true, "elfo", generaAleatorio(0, 19))
        val frodo = Hobbit("Frodo", true, "hobbit", false)

        //Bucle que se repite hasta que supera 36 salas o el personaje muere
        do {
            //Generamos un aleatorio para saber en que sala adentrarnos
            val ale: Int = generaAleatorio(0, 2)
            when (ale) {
                0 -> {
                    val salaMag = SalaMagica(contadorSalas, ale, generaAleatorio(1, 10))
                    muerto = generaSalaMagica(salaMag, gandalf)
                }
                1 -> {
                    val salaAcc =
                        SalaAccion(contadorSalas, ale, generaAleatorio(1, 10), generaAleatorio(1, 10))
                    muerto = generaSalaAccion(salaAcc, legolas)
                }
                2 -> {
                    val salaHabi = Sala(contadorSalas, ale)
                    muerto = generaSalaHabilidad(salaHabi, frodo)
                }
            }
            contadorSalas++
        } while (contadorSalas < 37 && muerto == false)
    }

    //Genera un numero aleatorio segun los parametros que nosotros le pasemos
    private fun generaAleatorio(a: Int, b: Int): Int {
        return Math.floor(Math.random() * (b - a + 1) + a).toInt()
    }

    /*
    * Cuando nos metemos en la sala Magica, Gandalf recarga su vara con un numero aleatorio de 0 a 10.
    * Si el poder de su vara es mayor al poder maligno de la sala magica, superamos esta sala
    * Si estos valores son iguales, tenemos un 60% de superar la sala, de lo contrario.. tenemos que intentar huir (con un 80% de exito)
    * Si el poder de su vara es menor al poder maligno, tenemos un 30% de superar la sala. El otro 70% tendremos que intentar huir (con 80% de exito)*/
    private fun generaSalaMagica(salaMag: SalaMagica, gandalf: Mago): Boolean {
        var muerto = false
        println("            ***************************               ")
        println("Sala : " + salaMag.numSala + " de tipo: " + salaMag.peligro + " con el personaje: " + gandalf.nombre)
        gandalf.recargarVara(generaAleatorio(1, 10))
        if (gandalf.poderVara() > salaMag.poderMaligno) {
            println("HEMOS SUPERADO ESTA SALA")
        } else if (gandalf.poderVara() == salaMag.poderMaligno) {
            val ale = generaAleatorio(1, 100)
            if (ale <= 60) {
                println("HEMOS SUPERADO ESTA SALA")
            } else {
                println("NO SE HA SUPERADO LA SALA.....Intentamos huir a otra...")
                muerto = intentaHuir()
            }
        } else {
            val ale = generaAleatorio(1, 100)
            if (ale <= 30) {
                println("HEMOS SUPERADO ESTA SALA")
            } else {
                println("NO SE HA SUPERADO LA SALA.....Intentamos huir a otra...")
                muerto = intentaHuir()
            }
        }
        return muerto
    }


    //Sacamos aleatorios para ver si podemos huir o no
    private fun intentaHuir(): Boolean {
        var muerto = false
        val ale = generaAleatorio(1, 100)
        muerto = if (ale <= 80) {
            println("HEMOS HUIDO DE LA SALA")
            false
        } else {
            println("NO HEMOS CONSEGUIDO HUIR DE LA SALA")
            println("¡               FIN                !")
            true
        }
        return muerto
    }

    /*
    * Cuando nos metemos en la sala de Accion, Legolas comienza a lanzar flechas hasta que se agotan o hasta que mueren todos los enemigos.
    * Si el carcaj de Legolas se queda sin flechas, intenta huir de la sala.
    * Mientras que si consigue matar a todos los enemigos, supera la sala y recarga su carcaj con las flechas que alli habia
    */
    private fun generaSalaAccion(salaAcc: SalaAccion, legolas: Elfo): Boolean {
        var muerto = false
        println("            ***************************               ")
        println("Sala : " + salaAcc.numSala + " de tipo: " + salaAcc.peligro + " con el personaje: " + legolas.nombre)
        while (salaAcc.enemigos > 0 || legolas.carcajFlechas > 0) {
            legolas.lanzarFlecha()
            var enem = salaAcc.enemigos
            enem--
            salaAcc.enemigos = enem
        }
        if (legolas.carcajFlechas == 0) {
            println("NO SE HA SUPERADO LA SALA.....Intentamos huir a otra...")
            muerto = intentaHuir()
        } else {
            legolas.recargarCarcaj(salaAcc.flechas)
            println("HEMOS SUPERADO LA SALA Y HEMOS RECARGADO EL CARCAJ CON: " + salaAcc.flechas + " FLECHAS")
        }
        return muerto
    }


    /*
    * Cuando nos metemos en la sala Habilidades, generamos un numero aleatorio que nos va a indicar si Frodo se pone en anillo o no (en un 50%).
    * Si consigue ponerse el anillo, se supera el peligro el 90% de las veces. Si no lo supera, intenta huir.
    * Si no se pone el anillo, supera la sala el 20% de las veces, sino intenta huir con un 80% de exito
    * */
    private fun generaSalaHabilidad(salaHabi: Sala, frodo: Hobbit): Boolean {
        var muerto = false
        println("            ***************************               ")
        println("Sala : " + salaHabi.numSala + " de tipo: " + salaHabi.peligro + " con el personaje: " + frodo.nombre)
        val ale = generaAleatorio(1, 100)
        if (ale >= 50) {
            frodo.ponerseAnillo()
            println("FRODO SE HA PUESTO EL ANILLO")
            val peligro = generaAleatorio(1, 100)
            if (peligro <= 90) {
                println("SE HA SUPERADO EL PELIGRO Y LA SALA")
            } else {
                println("NO SE HA SUPERADO EL PELIGRO. Intentamos huir...")
                muerto = intentaHuir()
            }
        } else {
            frodo.quitarseAnillo()
            println("FRODO NO HA CONSEGUIDO PONERSE EL ANILLO")
            val peligro = generaAleatorio(1, 100)
            if (peligro <= 20) {
                println("SE HA SUPERADO EL PELIGRO Y LA SALA")
            } else {
                println("NO SE HA SUPERADO EL PELIGRO. Intentamos huir...")
                muerto = intentaHuir()
            }
        }
        return muerto
    }
}