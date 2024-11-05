package com.eigenmethod.rentality.css

import io.kvision.core.FontWeight
import io.kvision.core.Style
import io.kvision.utils.px

const val ccsHoverBgGradientButton = "hover:bg-none hover:bg-white hover:text-[#514eff]"
const val cssGradientButton = "$ccsHoverBgGradientButton bg-gradient-button border w-[220px] h-[50px] rounded-[10px] text-xl font-['Montserrat',Arial,sans-serif] font-semibold"
const val cssGradientButtonDisabled =  "cursor-auto border bg-[#131320] w-[200px] h-[50px] rounded-[10px] text-xl font-['Montserrat',Arial,sans-serif] font-semibold"

const val cssInputFooter = "pl-5 pr-5 bg-black h-[50px] text-base rounded-lg w-full"
const val cssInputElement = "border mt-1 pl-5 pr-5 bg-[#141320] h-[50px] text-base rounded-lg w-full"

const val cssImgCarHostListing = "w-4 ml-6 mr-2"

const val cssBtnRentProcessDisabled =  "cursor-auto border bg-[#131320] w-[140px] h-[32px] rounded-[10px] text-xs font-['Montserrat',Arial,sans-serif]"

val btnRentProcessStyle = Style {
    width = 90.px
    height = 35.px
    fontWeight = FontWeight.NORMAL
    fontSize = 16.px
}