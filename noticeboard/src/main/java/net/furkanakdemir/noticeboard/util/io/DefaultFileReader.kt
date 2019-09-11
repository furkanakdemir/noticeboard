package net.furkanakdemir.noticeboard.util.io

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class DefaultFileReader @Inject constructor(val context: Context) : FileReader {
    override fun getFile(filename: String): String? {
        var bufferedReader: BufferedReader? = null
        var data: String? = null

        try {
            val inputStreamReader = InputStreamReader(
                context.assets?.open(filename),
                Charsets.UTF_8
            )
            bufferedReader = BufferedReader(inputStreamReader)

            data = bufferedReader.readLines().joinToString(separator = "\n")

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                bufferedReader?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return data
    }
}