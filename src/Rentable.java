public interface Rentable {
    void displayInfo();
    boolean isAvailable();

    /**
     * REVISI: Menggantikan reduceStock().
     * Harus sesuai dengan method di AbstractVehicle.
     */
    boolean rent();

    /**
     * REVISI: Method baru untuk pengembalian.
     * Harus sesuai dengan method di AbstractVehicle.
     */
    void returnVehicle();

    double getPricePerDay();
}