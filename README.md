<h1 align="center">PanicLabs-MVVM</h1>
<p align="center">A boilerplate-less Kotlin MVVM with Multi Module Feature</p>
<p align="center">
  <a href="https://circleci.com/gh/panicDev/panicLabs-MVVM/tree/master"><img src="https://circleci.com/gh/panicDev/panicLabs-MVVM/tree/master.svg?style=svg" alt="Build Status"></a>
  <a href="https://coveralls.io/github/panicDev/panicLabs-MVVM"><img src="https://coveralls.io/repos/github/panicDev/panicLabs-MVVM/badge.svg" alt="Coverage"></a>
</p>


## Description

This project is pretty much the same MVVM Kotlin project that I have in my repo. The idea is that we are going to have several modules:

- app: The one that is in charge of building and deploying the App

- core: (Or base) is the one that has all the dependencies and all the common Dagger Modules that are use across the children modules.

- module1: TODO



## Current challenges

- Dependency Injection: I need to make sure that there's no cross-module references, we don't want to rebuild all the modules from the projects if we just changed one. To keep track of this I'm going to be using the [BuildTime Tracker Plugin](https://github.com/passy/build-time-tracker-plugin)  to make sure that the building time only gets faster.

- ViewModel Dependency Injection: The idea is that when we create a new feature module, the Dagger Component for that module should be pretty straightforward to wring, we should only specify the BaseComponent from which depends (that exposes all the features from the AppModule such as Repositories), and we should specify a Dagger Module for this module component which specifies how the ViewModel is created (which dependencies are injected). For now, I kinda "patch" this issue, I'd like to improve it in a way that you only need to declare a @Provides + @Map so the ViewModel entry is automatically injected into the ViewModelFactory. Right now, I'm creating the ViewModelFactory instance, right there in the Dagger Module, which is no good because we are going to have one different ViewModelFactory per each feature module. But this is kinda tricky because remember that we don't want any cross-reference between the feature modules that could slow-down the building time.

Right now the project is working but I need to improve these things.

## Sources

- [GradleBuildExperiment](https://github.com/NikitaKozlov/GradleBuildExperiment)
- [Proandroiddev](https://proandroiddev.com/modules-modules-everywhere-cffa37449f58)
- [MultiModuleExample](https://github.com/MyDogTom/MultiModuleExample)
- [Mindorks](https://medium.com/mindorks/writing-a-modular-project-on-android-304f3b09cb37)
- [Build Time Tracker Plugin](https://github.com/passy/build-time-tracker-plugin)
- [Iadvize-Engineering](https://medium.com/iadvize-engineering/android-multi-module-architecture-a1a7a291a47e)
- [Luigi Papino](https://medium.com/@luigi.papino/dagger2-for-modular-architecture-332e1250a85f)