package tk.lorddarthart.weathertest.util.helper

fun getCurrentTag(thing: Any): String {
    return thing::class.java.simpleName
}