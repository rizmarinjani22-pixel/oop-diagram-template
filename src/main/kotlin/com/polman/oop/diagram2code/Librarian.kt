package com.polman.oop.diagram2code


/**
 * class Librarian : Person
 * - staffCode wajib non-blank
 * - calculateFee() selalu 0.0 (librarian tidak didenda)
 */
class Librarian(
    id: String,
    name: String,
    val staffCode: String
) : Person(id, name) {

    init {
        require(staffCode.isNotBlank()) { "staffCode tidak boleh kosong" }
    }

    override fun showInfo(): String =
        "Librarian[id=$id, name=$name, staffCode=$staffCode]"

    override fun calculateFee(daysLate: Int): Double {
        require(daysLate >= 0) { "daysLate tidak boleh negatif" }
        return 0.0
    }
}