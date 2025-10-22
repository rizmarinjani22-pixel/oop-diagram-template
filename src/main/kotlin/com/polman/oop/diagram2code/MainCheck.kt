package com.polman.oop.diagram2code

private fun section(title: String) {
    println("\n=== $title ===")
}

fun main() {

    // ------------------------------------------------------------
    // Studi Kasus 1 — Registrasi Peran & Informasi Dasar
    // ------------------------------------------------------------
    section("Studi Kasus 1 — Registrasi Peran & Informasi Dasar")

    val m1 = Member("M001", "Ani", MemberLevel.REGULAR)
    val m2 = Member("M002", "Budi", MemberLevel.PLATINUM)
    val lib = Librarian("L081", "Sari", "LIB-777")

    println(m1.showInfo())
    println(m2.showInfo())
    println(lib.showInfo())

    println("\n-- Uji setter name (trim + validasi) --")
    println("Sebelum: ${m1.name}")
    m1.name = "  Ani Setia  "
    println("Sesudah: ${m1.name}")

    try {
        println("\n-- Uji gagal set name kosong (harus exception) --")
        m2.name = " "
    } catch (e: IllegalArgumentException) {
        println("OK: penolakan validasi name -> ${e.message}")
    }

    // ------------------------------------------------------------
    // Studi Kasus 2 — Denda Polimorfik
    // ------------------------------------------------------------
    section("Studi Kasus 2 — Denda Polimorfik")

    val reg = Member("M010", "Rizma", MemberLevel.REGULAR)
    val gold = Member("M011", "Dien", MemberLevel.GOLD)
    val plat = Member("M012", "Adi", MemberLevel.PLATINUM)

    val daysSet = listOf(0, 1, 3, 10)

    fun printFee(p: Person) {
        println(p.showInfo())
        for (d in daysSet) {
            println("  daysLate=$d -> fee=${p.calculateFee(d)}")
        }
    }

    printFee(reg)
    printFee(gold)
    printFee(plat)
    printFee(lib) // Librarian selalu 0.0

    // ------------------------------------------------------------
    // Studi Kasus 3 — Peminjaman Buku
    // ------------------------------------------------------------
    section("Studi Kasus 3 — Peminjaman Buku")

    val b1 = Book("B10", "Clean Code", "Robert C. Martin", 2008, totalCount = 2)
    val b2 = Book("B11", "Kotlin in Action", "Jemerov", 2017, totalCount = 1)

    println(b1.info())
    println(b2.info())

    println("\n-- Pinjam b1 --")
    println("loan(m1) -> ${b1.loan(m1)} | stock=${b1.available()} | inStock=${b1.inStock()}")
    println("loan(m2) -> ${b1.loan(m2)} | stock=${b1.available()} | inStock=${b1.inStock()}")
    println("loan(m2) -> ${b1.loan(m2)} | stock=${b1.available()} | inStock=${b1.inStock()} (harus false, stok habis)")

    println("\n-- Pinjam b2 --")
    println("loan(m1) -> ${b2.loan(m1)} | stock=${b2.available()} | inStock=${b2.inStock()}")
    println("loan(m2) -> ${b2.loan(m2)} | stock=${b2.available()} | inStock=${b2.inStock()} (harus false, stok habis)")

    // ------------------------------------------------------------
    // Studi Kasus 4 — Pengembalian & Over-Capacity
    // ------------------------------------------------------------
    section("Studi Kasus 4 — Pengembalian & Over-Capacity")

    println("-- returnOne pada b1 (sebelumnya stok 0) --")
    b1.returnOne()
    println("after returnOne b1 -> stock=${b1.available()} / inStock=${b1.inStock()}")

    println("-- Paksa over-capacity dengan return terus menerus (harus exception) --")
    try {
        while (true) b2.returnOne() // b2 stok awal 0/1, return kedua over-capacity
    } catch (e: IllegalArgumentException) {
        println("OK: exception over-capacity -> ${e.message}")
    }

    println("b1 stock=${b1.available()} dari total=2")
    println("b2 stock=${b2.available()} dari total=1")

    // ------------------------------------------------------------
    // Studi Kasus 5 — Laporan Ringkas & Konsistensi
    // ------------------------------------------------------------
    section("Studi Kasus 5 — Laporan Ringkas & Konsistensi")

    val persons: List<Person> = listOf(m1, m2, reg, gold, plat, lib)
    val books: List<Book> = listOf(b1, b2)

    println("=== PERSONS ===")
    persons.forEach { println(it.showInfo()) }

    println("\n=== BOOKS ===")
    books.forEach { println(it.info()) }

    println("\nSelesai")
}
