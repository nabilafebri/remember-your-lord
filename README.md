"# remember-your-lord" 

- Link Mock-Up Awal: https://www.figma.com/file/VGTBLh4Wcu8bPCO5lJDaNd/TKTPL?node-id=0%3A1
- Injeksinya gagal, aplikasi crash

- Stack Android Framework Standard
- Activity untuk menambahkan Activity. Activity berisi informasi cuaca dan List Activity yang berasal dari WeatherFragment dan ActListFragment

- Service & Remote Method
- Alarm Manager untuk trigger notifikasi setiap hari, Location Manager untuk mendapatkan informasi cuaca, mengambil informasi cuaca dari API Weather

- Content Provider
- Menyimpan Activity pada kalender lokal

- Broadcast Receiver
- Menampilkan notifikasi setelah Alarm Manager selesai

- Async Task
- Mengambil data activity setiap hari

- Multi Layout
- Terdapat dua layout berbeda untuk orientation horizontal dan vertical untuk menampilkan Activity

- Multi Language
- Menggunakan string resource Bahasa Indonesia dan Bahasa Jerman. Defaultnya Bahasa Inggris

- MVVM
- Menggunakan ViewModel untuk Activity dan informasi cuaca. Mencoba menggunakan Hilt, tapi gagal

- Assets
- Menggunakan string resource pada strings.xml dan custom icon launcher

- Data Persistance
- Terdapat entity Activity, Weather, QuranText, En Translation, Id Translation pada Room Database

- Runtime Permission
- Request permission: akses lokasi (memanggil API) dan calendar (menambahkan Activity)

- JNI
- Mengunakan fungsi native C untuk animasi OpenGL pada splash screen

- OpenGL
- Menampilkan animasi OpenGL pada splash screen

- Connectivity Manager
- Mengambil data cuaca hanya jika menggunakan Wifi

- Service Background
- Menampilkan Activity setiap hari meskipun aplikasi sedang tidak dibuka

- Notifikasi
- Memberikan notifikasi Activity setiap hari