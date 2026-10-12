package kasirstackqueue;

import java.util.Scanner;

// Program utama: Sistem Kasir Toko (Queue untuk antrian, Stack untuk riwayat)
public class KasirStackQueue {
    static final int MIN_PELANGGAN = 5; // validasi minimal 5 pelanggan

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue antrian = new Queue();
        Stack riwayat = new Stack();
        int pilih;

        do {
            // Menu utama
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilih = bacaAngka(sc);
            System.out.println();

            switch (pilih) {
                case 1: // enqueue
                    String kode;
                    while (true) { // validasi nomor antrian: tidak kosong & tidak duplikat
                        System.out.print("Masukkan Nomor Antrian: ");
                        kode = sc.nextLine().trim();
                        if (kode.isEmpty()) System.out.println("Nomor antrian tidak boleh kosong!");
                        else if (antrian.ada(kode)) System.out.println("Nomor antrian sudah ada!");
                        else break;
                    }
                    String nama = bacaTeks(sc, "Masukkan Nama Pelanggan: ");
                    long total = bacaNominal(sc, "Masukkan Total Belanja: ");
                    antrian.enqueue(kode, nama, total);
                    System.out.println("Data pelanggan ditambahkan ke antrian!");
                    if (antrian.getSize() < MIN_PELANGGAN) {
                        System.out.println("(Info: minimal " + MIN_PELANGGAN
                                + " pelanggan, saat ini " + antrian.getSize() + ")");
                    }
                    break;

                case 2: // dequeue + push ke stack
                    Node dilayani = antrian.dequeue();
                    if (dilayani == null) {
                        System.out.println("Antrian kosong, tidak ada pelanggan untuk dilayani.");
                    } else {
                        System.out.println("Melayani pelanggan " + dilayani.kode
                                + " (" + dilayani.nama + ")");
                        riwayat.push(dilayani);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    antrian.display();
                    break;

                case 4:
                    riwayat.display();
                    break;

                case 5:
                    System.out.println("Terima kasih, program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Masukkan angka 1-5.");
            }
        } while (pilih != 5);

        sc.close();
    }

    // Membaca angka menu dengan aman
    static int bacaAngka(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Membaca teks yang tidak boleh kosong
    static String bacaTeks(Scanner sc, String label) {
        String s;
        do {
            System.out.print(label);
            s = sc.nextLine().trim();
            if (s.isEmpty()) System.out.println("Input tidak boleh kosong!");
        } while (s.isEmpty());
        return s;
    }

    // Membaca total belanja: harus angka dan > 0
    static long bacaNominal(Scanner sc, String label) {
        while (true) {
            System.out.print(label);
            try {
                long n = Long.parseLong(sc.nextLine().trim());
                if (n > 0) return n;
                System.out.println("Total belanja harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("Total belanja harus berupa angka!");
            }
        }
    }
}