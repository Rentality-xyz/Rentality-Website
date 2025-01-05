package com.eigenmethod.rentality.views.components

import io.kvision.core.Container
import io.kvision.html.div

fun Container.baseDialog(content: Container.() -> Unit) {
    div(className = "fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50") {
        content()
    }
}