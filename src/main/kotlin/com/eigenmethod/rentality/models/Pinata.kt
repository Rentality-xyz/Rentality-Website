package com.eigenmethod.rentality.models

import kotlinx.coroutines.await
import org.w3c.files.File
import org.w3c.xhr.FormData
import kotlin.js.Promise

private const val PINATA_URL = "https://api.pinata.cloud/pinning/pinFileToIPFS"
private const val JWT = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mb3JtYXRpb24iOnsiaWQiOiI2YjQ4OTMwNC01N2ZiLTQ3ZmQtOWI1NC1jNWFmMWZlMDE3YjYiLCJlbWFpbCI6ImRlbmRpbWE4MEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGluX3BvbGljeSI6eyJyZWdpb25zIjpbeyJpZCI6IkZSQTEiLCJkZXNpcmVkUmVwbGljYXRpb25Db3VudCI6MX0seyJpZCI6Ik5ZQzEiLCJkZXNpcmVkUmVwbGljYXRpb25Db3VudCI6MX1dLCJ2ZXJzaW9uIjoxfSwibWZhX2VuYWJsZWQiOmZhbHNlLCJzdGF0dXMiOiJBQ1RJVkUifSwiYXV0aGVudGljYXRpb25UeXBlIjoic2NvcGVkS2V5Iiwic2NvcGVkS2V5S2V5IjoiYzFhY2Y0MTFhYTVlOGM3YTNiYTgiLCJzY29wZWRLZXlTZWNyZXQiOiI1YjI0YjUxZmUxZDdlMWRiOWViODBiZTFmOWI2OTE1ODNhNTI2ZmQxOWUyYmRhOWExOWY3ODE3NzIwMjBmODU1IiwiaWF0IjoxNjg2NDM3MzI3fQ._Dx5aOPmAFuFNMt1HLCdv6dZDF_-9cbvEPbgFhVDdr8"
class Pinata {
    companion object {
        suspend fun uploadFileToIPFS(image: File): String {
            val formData = FormData()
            formData.append("file", image)

            return try{
                val response = axiosPost(formData).await()
                "https://gateway.pinata.cloud/ipfs/" + response.data.IpfsHash
            } catch (error: Throwable) {
                console.log("error pinata uploadFileToIPFS -> $error")
                ""
            }
        }

        private fun axiosPost(formData: FormData): Promise<dynamic> {
            val _jwt = JWT
            val axios = kotlinext.js.require("axios")
            val _headers = js("({ 'Content-Type': 'multipart/form-data; boundary=\${formData._boundary}', 'Authorization': _jwt })")
            return axios.post(PINATA_URL, formData, js("{ maxBodyLength: Infinity, headers: _headers }")) as Promise<dynamic>
        }

        //это таже ф-ция что и выше, только в другом стиле написана
//    private fun axiosPost(formData: FormData): Promise<dynamic> {
//        val _jwt = JWT
//        val axios = kotlinext.js.require("axios")
//        return js("axios.post(\"https://api.pinata.cloud/pinning/pinFileToIPFS\", formData, { maxBodyLength: \"Infinity\", headers: { 'Content-Type': 'multipart/form-data; boundary=\${formData._boundary}', Authorization: _jwt }})") as Promise<dynamic>
//    }
    }
}