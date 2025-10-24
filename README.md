<p align="center">
  <a href ="#" target="_blank" title="Project Sistem Rental Kendaraan">
    <span style="font-size: 80px;">🚗</span>
  </a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Prinsip-OOP-blue?style=for-the-badge" alt="OOP" />
  <img src="https://img.shields.io/badge/Tipe-Aplikasi_Konsol_(CLI)-lightgrey?style=for-the-badge" alt="CLI App" />
</p>

# Sistem Rental Kendaraan (OOP)

Selamat datang di Sistem Rental Kendaraan! Ini adalah aplikasi konsol (CLI) yang dibangun di atas Java untuk mendemonstrasikan prinsip-prinsip inti **Object-Oriented Programming (OOP)**.

Kami telah mengambil konsep sistem rental dasar dan menambahkan fitur modern seperti pelacakan *real-time* menggunakan `java.time.LocalDate` dan perhitungan denda otomatis untuk menciptakan simulasi yang kuat dan logis.

---

**Cara Menjalankan**

* **[Lokal] Compile & Run:** Ini adalah satu-satunya mode operasi. Aplikasi ini dirancang untuk berjalan 100% di mesin lokal Anda tanpa perlu hosting atau infrastruktur server. Anda memiliki kontrol penuh untuk memodifikasi dan menguji kode secara langsung.

Semua fitur, termasuk pelacakan tanggal dan perhitungan denda, sudah termasuk dalam kode sumber.

---

#### Dokumentasi Konsep (OOP)

Berikut adalah konsep-konsep OOP utama yang menjadi fondasi proyek ini.

* **Inheritance & Abstract Class:** `Mobil`, `Motor`, dan `Truk` adalah *subclass* yang mewarisi *template* dari `AbstractVehicle`.
* **Interface:** `Rentable` bertindak sebagai "kontrak" yang menjamin semua kendaraan memiliki kemampuan dasar (`rent`, `returnVehicle`, `displayInfo`).
* **Polymorphism:** `RentalSystem` mengelola satu `ArrayList<Rentable>`, yang dapat menampung objek `Mobil`, `Motor`, dan `Truk` secara bersamaan.
* **Encapsulation:** Data penting (seperti `rentalHistory` di `Customer` atau `vehicle` di `RentalRecord`) bersifat `private` (dan `final`) dan hanya dapat diakses melalui *public method* (getter atau `addRentalRecord`).

---

## [Lokal] Instalasi Cepat & Penggunaan

Semua file kode sumber (`.java`) berada di dalam folder `src`.

Anda dapat mengkompilasi dan menjalankan seluruh proyek dengan perintah berikut dari direktori *root* (folder yang berisi `src`).

```sh
# 1. Buka terminal/CMD dan pastikan Anda berada di
#    folder utama proyek (yang berisi folder 'src').

# 2. Kompilasi semua file .java yang ada di dalam 'src'
#    dan simpan file .class di dalam 'src' juga.
javac src/*.java

# 3. Jalankan file Main dari dalam folder 'src'
#    (Gunakan -cp src untuk memberitahu Java di mana
#     harus mencari file .class Anda)
java -cp src Main
