package vick.tech.adoptify.core

fun <T> customFilter(list: List<T>, predicate: (T) -> Boolean): List<T> {
    return list.filter(predicate)
}
