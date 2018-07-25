<h1 align="center">PanicLabs-MVVM</h1>
<p align="center">A boilerplate-less Kotlin MVVM with Multi Module Feature</p>
<p align="center">
  <a href="https://circleci.com/gh/panicDev/panicLabs-MVVM/tree/master"><img src="https://circleci.com/gh/panicDev/panicLabs-MVVM/tree/master.svg?style=svg" alt="Build Status"></a>
  <a href="https://coveralls.io/github/panicDev/panicLabs-MVVM"><img src="https://coveralls.io/repos/github/panicDev/panicLabs-MVVM/badge.svg" alt="Coverage"></a>
</p>


## Deskripsi

Proyek ini hampir sama dengan proyek MVVM Kotlin yang saya miliki di repo saya. Idenya adalah bahwa kita akan memiliki beberapa modul:

- app: Yang bertanggung jawab membangun dan menyebarkan Aplikasi

- core: (Atau CodeBase) adalah salah satu yang memiliki semua dependensi dan semua Modul Dagger umum yang digunakan di seluruh modul dari proyek ini.

- postlist: TODO



## Tantangan saat ini

- Dependency Injection: Saya perlu memastikan bahwa tidak ada referensi lintas-modul, kami tidak ingin membangun kembali semua modul dari proyek jika kami hanya mengubahnya. Untuk terus melacak ini, saya akan menggunakan [Plugin BuildTime Tracker] (https://github.com/passy/build-time-tracker-plugin) untuk memastikan bahwa waktu pembangunan semakin cepat.

- ViewModel Dependency Injection: Idenya adalah bahwa ketika kita membuat modul fitur baru, Komponen Dagger untuk modul itu harus cukup mudah untuk diperas, kita hanya harus menentukan BaseComponent yang bergantung (yang memperlihatkan semua fitur dari AppModule seperti Repositori), dan kita harus menentukan Modul Dagger untuk komponen modul ini yang menentukan bagaimana ViewModel dibuat (dependensi mana yang disuntikkan). Untuk saat ini, saya agak "menambal" masalah ini, saya ingin memperbaikinya dengan cara yang Anda hanya perlu menyatakan @Provides + @Map sehingga entri ViewModel secara otomatis disuntikkan ke ViewModelFactory. Saat ini, saya sedang membuat instance ViewModelFactory, tepat di Modul Dagger, yang tidak baik karena kita akan memiliki satu ViewModelFactory yang berbeda untuk setiap modul fitur. Tapi ini agak rumit karena ingat bahwa kita tidak ingin ada referensi silang antara modul-modul fitur yang dapat memperlambat waktu pembangunan.

Saat ini proyek sudah berfungsi tetapi saya perlu memperbaiki hal-hal ini.

## Sumber

- [GradleBuildExperiment](https://github.com/NikitaKozlov/GradleBuildExperiment)
- [Proandroiddev](https://proandroiddev.com/modules-modules-everywhere-cffa37449f58)
- [MultiModuleExample](https://github.com/MyDogTom/MultiModuleExample)
- [Mindorks](https://medium.com/mindorks/writing-a-modular-project-on-android-304f3b09cb37)
- [Build Time Tracker Plugin](https://github.com/passy/build-time-tracker-plugin)
- [Iadvize-Engineering](https://medium.com/iadvize-engineering/android-multi-module-architecture-a1a7a291a47e)
- [Luigi Papino](https://medium.com/@luigi.papino/dagger2-for-modular-architecture-332e1250a85f)