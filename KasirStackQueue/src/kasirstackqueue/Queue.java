package kasirstackqueue;

// Queue (FIFO): antrian pelanggan. Masuk di belakang (rear), keluar dari depan (front)
public class Queue {
    private Node front, rear;
    private int size;

    // enqueue: tambah pelanggan di belakang antrian
    public void enqueue(String kode, String nama, long total) {
        Node baru = new Node(kode, nama, total);
        if (rear == null) {
            front = rear = baru;
        } else {
            rear.next = baru;
            rear = baru;
        }
        size++;
    }

    // dequeue: ambil pelanggan paling depan (yang pertama datang)
    public Node dequeue() {
        if (front == null) return null;
        Node keluar = front;
        front = front.next;
        if (front == null) rear = null; // antrian jadi kosong
        keluar.next = null;             // lepas dari antrian
        size--;
        return keluar;
    }

    // Cek apakah nomor antrian sudah dipakai
    public boolean ada(String kode) {
        for (Node cur = front; cur != null; cur = cur.next) {
            if (cur.kode.equalsIgnoreCase(kode)) return true;
        }
        return false;
    }

    // Tampilkan antrian dari depan ke belakang
    public void display() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("Antrian Saat Ini (depan -> belakang):");
        int no = 1;
        for (Node cur = front; cur != null; cur = cur.next) {
            System.out.println(no++ + ". " + cur.kode + " | " + cur.nama + " | Rp " + cur.total);
        }
        System.out.println("Jumlah pelanggan dalam antrian: " + size);
    }

    public boolean isEmpty() { return front == null; }
    public int getSize() { return size; }
}