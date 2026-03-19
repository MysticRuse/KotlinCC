package generics

open class BaseBuildingMaterial(val numberNeeded: Int = 1)

class Brick() : BaseBuildingMaterial(numberNeeded = 8)

class Wood() : BaseBuildingMaterial(numberNeeded = 4)

class Building<out T: BaseBuildingMaterial>(val buildingMaterial: T) {
    val baseMaterialsNeeded = 100
    val actualMaterialsNeeded = buildingMaterial.numberNeeded * baseMaterialsNeeded

    fun build() {
        println("$actualMaterialsNeeded ${buildingMaterial::class.simpleName} required")
    }
}

// Generic function
fun <T: BaseBuildingMaterial> isSmallBuilding(building: Building<T>) {
    val buildingName = building.buildingMaterial::class.simpleName
    val buildingSize = if (building.actualMaterialsNeeded < 500) "$buildingName building is small building" else "$buildingName building is large building"
    println(buildingSize)
}

fun main(args: Array<String>) {
    Building(Wood()).build()
    Building(Brick()).build()

    isSmallBuilding(Building(Wood()))
    isSmallBuilding(Building(Brick()))
}