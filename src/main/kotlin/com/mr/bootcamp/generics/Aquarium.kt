package generics

open class WaterSupply(var needsProcessed: Boolean)

class TapWater: WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessed = false
    }
}

class FishStoreWater: WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessed = false
    }
}

class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        //check(!waterSupply.needsProcessed) { " water supply needs processed"}
        if (waterSupply.needsProcessed) {
            println("Cleaning water in $waterSupply")
            cleaner.clean(waterSupply)
        }
        println("Adding water from $waterSupply")
    }

    inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner: Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicalCleaners()
    }
}
fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("Item added to ${aquarium.waterSupply}")

// Specific function
//fun isWaterClean(aquarium: Aquarium<WaterSupply>) = println("Aquarium water is clean : ${aquarium.waterSupply.needsProcessed}")

// Generic Functions
fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) = println("Aquarium water is clean : ${aquarium.waterSupply.needsProcessed}")

fun genericExample() {
    val aquarium: Aquarium<TapWater> = Aquarium(TapWater())
    addItemTo(aquarium)
    //aquarium.waterSupply.addChemicalCleaners()

//    val aquarium2 = Aquarium(LakeWater())
//    aquarium2.waterSupply.filter()
//    aquarium2.addWater()

    val cleaner = TapWaterCleaner()
    aquarium.addWater(cleaner)
    isWaterClean(aquarium)

    val hasType = aquarium.hasWaterSupplyOfType<TapWater>()
    println("Aquarium has type TapWater: $hasType")
    println("Aquarium has type LakeWater: ${ aquarium.hasWaterSupplyOfType<LakeWater>() }")
}

fun main(args: Array<String>) {
    genericExample()
}