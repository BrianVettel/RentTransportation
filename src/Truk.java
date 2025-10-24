public class Truk extends AbstractVehicle {

    // Constructor dengan parameter
    public Truk(String brand, double pricePerDay, int stock) {
        // Panggil constructor dari AbstractVehicle dengan tipe "Truk"
        super("Truk", brand, pricePerDay, stock);
    }

    /**
     * REVISI: Implementasi method abstract 'getCapacity()'
     * yang diwajibkan oleh AbstractVehicle.
     */
    @Override
    public int getCapacity() {
        // Contoh implementasi kapasitas untuk truk
        return 3; // Misal 1 sopir + 2 penumpang/kenek
    }
}