"# remember-your-lord" 
Link Mock-Up Awal: https://www.figma.com/file/VGTBLh4Wcu8bPCO5lJDaNd/TKTPL?node-id=0%3A1

Komen:
- belum ada yang selesai. Rencananya mau menyelasaikan penambahan aktivitas, tapi pemanggilan fragmen dari fragmennya masih salah.
Ada bagian di fragmen yang muncul, tapi recyclerviewnya masih salah. jadi UI-nya belum kelar ...
- data yang available dari database online dengan tipe sql, sudah diconvert ke sqlite, lalu sudah diconvert ke room. Data ini sudah bisa diakses,
sudah bisa ditampilkan, tapi baru uji coba saja menampilkannya (UI-nya belum selesai).
- set value string cuma pakai locale in (Bahasa).
- implementasi multiple layout-nya cuma switch layout waktu ganti orientasi, tapi ini masih ada bug-nya. 
Jadi, waktu orientasinya diganti, perlu akses fragmen lain di bottom navigasi dulu, baru layoutnya bisa terganti kalau balik ke fragmen sebelumnya.