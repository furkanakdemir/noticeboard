package net.furkanakdemir.noticeboard.util.io

internal interface FileReader {
    fun getFile(filename: String): String?
}
