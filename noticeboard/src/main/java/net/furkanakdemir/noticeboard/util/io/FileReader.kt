package net.furkanakdemir.noticeboard.util.io

interface FileReader {
    fun getFile(filename: String): String?
}