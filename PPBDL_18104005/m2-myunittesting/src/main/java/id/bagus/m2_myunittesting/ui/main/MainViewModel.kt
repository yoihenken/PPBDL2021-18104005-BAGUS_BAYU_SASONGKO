package id.bagus.m2_myunittesting.ui.main

import id.bagus.m2_myunittesting.model.CuboidModel

class MainViewModel (private val cuboidModel: CuboidModel) {

    fun getCircumference(): Double = cuboidModel.getCircumference()

    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()

    fun getVolume(): Double = cuboidModel.getVolume()

    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(l, w, h)
    }
}