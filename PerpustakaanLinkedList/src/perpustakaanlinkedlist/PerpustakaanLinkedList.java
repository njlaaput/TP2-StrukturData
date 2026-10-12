package perpustakaanlinkedlist;

import java.util.Scanner;

// Program utama: Sistem Data Buku Perpustakaan (Single Linked List)
public class PerpustakaanLinkedList {
    static final int MIN_BUKU = 5; // validasi jumlah data minimal

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList daftar = new LinkedList();
        int pilih;

        do {
            // Menu utama
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilih = bacaAngka(sc);
            System.out.println();

            switch (pilih) {
                case 1: // Tambah buku
                    String kode;
                    // Validasi: kode tidak boleh kosong, maks 5 karakter, tidak boleh duplikat
                    while (true) {
                        System.out.print("Masukkan Kode Buku: ");
                        kode = sc.nextLine().trim();
                        if (kode.isEmpty()) {
                            System.out.println("Kode buku tidak boleh kosong!");
                        } else if (kode.length() > 5) {
                            System.out.println("Kode buku maksimal 5 karakter!");
                        } else if (daftar.cari(kode) != null) {
                            System.out.println("Kode buku sudah terdaftar!");
                        } else break;
                    }
                    String judul = bacaTeks(sc, "Masukkan Judul: ");
                    String penulis = bacaTeks(sc, "Masukkan Penulis: ");
                    daftar.push(kode, judul, penulis);
                    System.out.println("Data berhasil ditambahkan!");
                    if (daftar.getSize() < MIN_BUKU) {
                        System.out.println("(Info: minimal " + MIN_BUKU + " buku, saat ini "
                                + daftar.getSize() + " buku)");
                    }
                    break;

                case 2: // Hapus buku terakhir
                    Node hapus = daftar.pop();
                    if (hapus == null) {
                        System.out.println("Tidak ada data untuk dihapus.");
                    } else {
                        System.out.println("Buku " + hapus.kodeBuku + " (" + hapus.judul
                                + ") berhasil dihapus!");
                    }
                    break;

                case 3: // Cari buku
                    System.out.print("Masukkan Kode Buku yang dicari: ");
                    Node hasil = daftar.cari(sc.nextLine().trim());
                    if (hasil == null) {
                        System.out.println("Buku tidak ditemukan.");
                    } else {
                        System.out.println("Buku ditemukan!");
                        System.out.println("Kode   : " + hasil.kodeBuku);
                        System.out.println("Judul  : " + hasil.judul);
                        System.out.println("Penulis: " + hasil.penulis);
                    }
                    break;

                case 4: // Tampilkan semua buku
                    daftar.tampil();
                    if (daftar.getSize() > 0 && daftar.getSize() < MIN_BUKU) {
                        System.out.println("Peringatan: jumlah buku belum memenuhi minimal "
                                + MIN_BUKU + " buku.");
                    }
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

    // Membaca angka menu dengan aman (input huruf tidak membuat program error)
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
}