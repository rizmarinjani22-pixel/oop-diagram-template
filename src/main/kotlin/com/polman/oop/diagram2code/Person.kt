package com.polman.oop.diagram2code

/**
 * abstract Person:
 * - id: non-blank
 * - name: 2..100 chars (setiap set: trim + validasi)
 */
abstract class Person(
    val id: String,
    name: String
) {
    var name: String = name
        set(value) {
            val t = value.trim()
            validateName(t)
            field = t
        }

    init {
        require(id.isNotBlank()) { "id tidak boleh kosong" }
        val t = name.trim()
        validateName(t)
        this.name = t // set ulang via setter agar konsisten
    }

    /**
     * Validasi name:
     * - tidak kosong setelah trim
     * - panjang 2..100
     */
    protected fun validateName(n: String) {
        val t = n.trim()
        require(t.isNotEmpty()) { "name tidak boleh kosong" }
        require(t.length in 2..100) { "name harus 2..100 karakter" }
    }

    abstract fun showInfo(): String
    abstract fun calculateFee(daysLate: Int): Double
}