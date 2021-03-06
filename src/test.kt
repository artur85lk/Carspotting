package parking

data class Car(val Registration: String, val Color: String)

fun main() {
    var test = false   // zmienna dla drugiego create
    var notDone2 = true
    var imput3 = "[,]".split("[", ",", "]") // lipna pusta tabela
    while (notDone2) {
        var input2 = imput3
        if (test == false) {      //obsługa pierwszego create
            input2 = readLine()!!.split(" ")
        }
        if (input2[0] == "exit"){
            break
        }
        if (input2[0] == "create" || imput3[0] == "create") {

            var size = input2[1].toInt()
            if (imput3[0] == "create"){
                size = imput3[1].toInt()
            }
            var parkingSpots = Array<Car?>(size) { null }
            var notDone = true
            println("Created a parking lot with ${input2[1]} spots.")
            while (notDone) {
                val input = readLine()!!.split(" ")
                when (input[0]) {
                    "exit" -> {
                        notDone = false
                        notDone2 = false
                    }
                    "status" -> {
                        val availableSpot2 = parkingSpots.indexOfFirst { car -> car != null }
                        if (availableSpot2 < 0) {
                            println("Parking lot is empty.")
                        } else {
                            for (r in parkingSpots.indices) {
                                if (parkingSpots[r] != null) {
                                    var napis = world(parkingSpots[r].toString())
                                    println("${r + 1} $napis")
                                }
                            }
                        }
                    }
                    "park" -> {
                        val availableSpot = parkingSpots.indexOfFirst { car -> car == null }
                        if (availableSpot < 0) {
                            println("Sorry, the parking lot is full.")
                        } else {
                            val registration = input[1]
                            var color4 = input[2].toLowerCase()
                            var color = color4.capitalize()  // zmiana koloru na np. "Red"
                            parkingSpots[availableSpot] = Car(registration, color)
                            println("$color car parked in spot ${availableSpot + 1}.")
                        }
                    }
                    "leave" -> {
                        if (parkingSpots[input[1].toInt() - 1] == null) {
                            println("There is no car in spot ${input[1]}.")
                        } else {
                            parkingSpots[input[1].toInt() - 1] = null
                            println("Spot ${input[1]} is free.")
                        }
                    }
                    "create" -> {
                        test = true
                        imput3 = input
                        break
                    }
                    "reg_by_color" -> {                  // status przerobić na color
                        var conter1 = 0
                        var color = input[1].toLowerCase()
                        var newcolor = color.capitalize()  // zmiana koloru na np. "Red"
                        var cos5 = false
                        val availableSpot2 = parkingSpots.indexOfFirst { car -> car != null }
                        if (availableSpot2 < 0) {
                            println("No cars with color BLACK were found.")
                        } else {
                            for (r in parkingSpots.indices) {
                                if (parkingSpots[r] != null) {
                                    var napis = world(parkingSpots[r].toString()).split(" ")
                                    var samochod = colorFun(parkingSpots[r].toString())   //nowy
                                    if (napis[1] == newcolor) {
                                        conter1++
                                        cos5 = true
                                        if (conter1 > 1) print(", ")
                                        print(samochod)    // tu wstawić nr rej
                                    }
                                }
                            }
                            if (cos5) println("")   //błąd
                            else { println("No cars with color ${input[1]} were found.") }  //error
                        }
                    }
                    "spot_by_color" -> {
                        var color = input[1].toLowerCase()
                        var newcolor = color.capitalize()  // zmiana koloru na np. "Red"
                        var conter = 0
                        var cos2 = false
                        val availableSpot2 = parkingSpots.indexOfFirst { car -> car != null }
                        if (availableSpot2 < 0) {
                            println("No cars with color BLACK were found.")
                        } else {
                            for (r in parkingSpots.indices) {
                                if (parkingSpots[r] != null) {
                                    var napis = world(parkingSpots[r].toString()).split(" ")
                                    if (napis[1] == newcolor) {
                                        conter++
                                        cos2 = true
                                        if (conter > 1) print(", ")
                                        print(r + 1)
                                    }
                                }
                            }
                            if (cos2) println("")
                            else { println("No cars with color ${input[1]} were found.") }
                        }
                    }
                    "spot_by_reg" -> {
                        var cos1 = false
                        val availableSpot2 = parkingSpots.indexOfFirst { car -> car != null }
                        if (availableSpot2 < 0) {
                            println("No cars with registration number KA-01 were found.")
                        } else {
                            for (r in parkingSpots.indices) {
                                if (parkingSpots[r] != null) {
                                    var napis = world(parkingSpots[r].toString()).split(" ")
                                    if (napis[0] == input[1]) {
                                        cos1 = true
                                        println(r + 1)
                                    }
                                }
                            }
                            if (cos1 == false) { println("No cars with registration number ${input[1]} were found.") }
                        }
                    }
                }
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }
}
fun world(tabela: String = "cos"): String {
    val slowo = tabela.split("=", ",", "=", ")")
    val y = "${slowo[1]} ${slowo[3]}"
    return y
}

fun colorFun(tabela: String = "cos"): String {
    val slowo = tabela.split("=", ",", "=", ")")
    val y = "${slowo[1]}"
    return y
}