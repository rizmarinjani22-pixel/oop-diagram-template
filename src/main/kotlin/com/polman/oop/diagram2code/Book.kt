package com.polman.oop.diagram2code


/**
 * class Book : Loanable
 * Invarian:
 * - 0 <= availableCount <= totalCount
 * - year ∈ [1400..2100]
 * - loan(): false jika stok habis (tidak melempar exception)
 * - returnOne(): tidak boleh melebihi totalCount (melempar IllegalArgumentException)
 */
class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val totalCount: Int
) : Loanable {

    // stok tersedia saat ini (private)
    private var availableCount: Int = totalCount

    init {
        // Validasi konstruktor
        require(id.isNotBlank()) { "id tidak boleh kosong" }
        require(title.isNotBlank()) { "title tidak boleh kosong" }
        require(author.isNotBlank()) { "author tidak boleh kosong" }
        require(year in 1400..2100) { "year harus di antara 1400..2100" }
        require(totalCount >= 0) { "totalCount tidak boleh negatif" }

        // Inisialisasi stok awal = totalCount
        availableCount = totalCount
        checkInvariant()
    }

    /** true jika stok masih ada. */
    fun inStock(): Boolean = availableCount > 0

    /**
     * Pinjam satu eksemplar.
     * - Berhasil → stok berkurang 1 dan return true
     * - Habis → return false (tanpa exception)
     */
    override fun loan(to: Member): Boolean {
        if (!inStock()) return false
        availableCount -= 1
        checkInvariant()
        return true
    }

    /**
     * Mengembalikan satu eksemplar ke rak.
     * - Jika menambah melebihi totalCount → lempar IllegalArgumentException
     */
    fun returnOne() {
        if (availableCount >= totalCount) {
            throw IllegalArgumentException("Tidak bisa menambah stok: sudah penuh ($availableCount/$totalCount).")
        }
        availableCount += 1
        checkInvariant()
    }

    /** Mengembalikan stok tersedia saat ini. */
    fun available(): Int = availableCount

    /** Ringkasan informasi buku. */
    fun info(): String =
        "Book[id=$id, title=$title, author=$author, year=$year, stock=${availableCount}/$totalCount]"

    /** Proteksi invarian internal. */
    private fun checkInvariant() {
        require(availableCount in 0..totalCount) {
            "Invarian stok dilanggar: availableCount=$availableCount, totalCount=$totalCount"
        }
    }
}