package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAIN_COLOR_BG
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.utilites.orZero
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.html.*
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import kotlinx.browser.document
import kotlinx.browser.window

private val isVisibleUpBtn = ObservableValue(false)

fun scrollToTop(scrollStep: Double) {
    if (window.scrollY > 0) {
        window.requestAnimationFrame {
            window.scrollBy(0.0, -scrollStep)
            scrollToTop(scrollStep)
        }
    }
}

fun Container.homePage() {
    window.onscroll = {
        if (document.body?.scrollTop.orZero() > 600 || document.documentElement?.scrollTop.orZero() > 600) {
            isVisibleUpBtn.value = true
        } else {
            isVisibleUpBtn.value = false
        }
    }

    div {
        div(className = "fixed flex justify-center items-center bottom-20 right-10 md:right-20 z-[99] w-[60px] h-[60px] cursor-pointer bg-[url('images/ellipseUpBtn.png')] bg-cover bg-no-repeat bg-center").bind(isVisibleUpBtn) {
            id = "up-btn"
            visible = isVisibleUpBtn.value
            image(src = "images/arrUpBtn.png", className = "w-[40px] h-[28px]")
        }
        onClick { ev ->
            ev.preventDefault()
            val scrollStep = window.scrollY / 10
            scrollToTop(scrollStep)
        }
    }

    div(className="mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] bg-[$MAIN_COLOR_BG] text-white") {
        partnersSection()
    }
    div(className="mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] bg-[$MAIN_COLOR_BG] text-white") {
        builtOnSection()
    }

    image(src = "images/bg_logo_zoya.png", className = "max-md:hidden absolute top-[850px] right-0 max-h-[593px]")
    image(src = "images/bg_arrows_from_logo_zoya.png", className = "max-md:hidden absolute top-[1593px] left-0 max-w-[365px]")
    image(src = "images/bg_logo_zoya.png", className = "max-md:hidden absolute top-[2255px] right-0 max-h-[565px]")
    image(src = "images/bg_arrows_from_logo_zoya.png", className = "max-lg:hidden absolute top-[2946px] left-0 max-w-[332px]")
    div(className="max-lg:pt-0 pt-[110px] max-lg:pb-[60px] pb-[110px] mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] bg-[$MAIN_COLOR_BG] text-white") {
        id = "home-page-content"
        image(src = "images/how_it_work_web3.jpg", alt = "How works platform", className = "max-lg:hidden") {
            id = "how_it_work"
        }
        image(src = "images/how_it_work_web3_mob.jpg", alt = "How works platform", className = "pt-14 lg:hidden") {
            id = "how_it_work_mob"
        }

        div("Rental process", className = "md:hidden pt-8 text-center text-[36px] font-['Montserrat',Arial,sans-serif] font-bold") {
            id = "rental_process_mob"
        }
        image(src = "images/rental_process.jpg", alt = "Car rental process of Rentality", className = "pt-[80px] max-md:hidden") {
            id = "rental_process"
        }
        image(src = "images/rental_process_mob.jpg", alt = "Car rental process of Rentality", className = "pt-2 md:hidden")

        h2("Solution", className = "max-md:pt-8 pt-[80px] max-md:text-center max-md:text-[36px] text-[48px] font-['Montserrat',Arial,sans-serif] font-bold") {
            id = "solution"
        }
        image(src = "images/solution_web3.jpg", alt = "Solution of Rentality", className = "pt-[10px] max-md:hidden")
        image(src = "images/solution_web3_mob.jpg", alt = "Solution of Rentality", className = "pt-2 md:hidden")

        h2("Roadmap", className = "max-md:pt-8 pt-[110px] max-md:text-center max-md:text-[36px] text-[48px] font-['Montserrat',Arial,sans-serif] font-bold") {
            id = "roadmap"
        }
        image(src = "images/roadmap.jpg", alt = "Roadmap of Rentality", className = "max-md:hidden")
        image(src = "images/roadmap_mob.jpg", alt = "Roadmap of Rentality", className = "pt-2 md:hidden")
    }

    div(className = "mb-[60px] lg:mb-[110px] w-full bg-[url('images/bg_avto_103.png')] bg-cover bg-no-repeat bg-center") {
        div(className="mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white") {
            whitelistSection()
            demoSection()
        }
    }

}

private fun Container.whitelistSection() {
    div(className = "w-full max-lg:pt-4") {
        id = "whitelist-section"
        div(className = "flex max-lg:flex-col") {
            h2(className = "min-w-[380px] lg:ml-20 max-lg:text-center max-lg:text-[36px] text-[48px] font-['Montserrat',Arial,sans-serif] font-bold") {
                + "Join Our "
                br(className = "max-lg:hidden")
                + "Whitelist"
            }
            div(className = "min-w-[380px] w-full flex justify-center lg:justify-end") {
                div(className = "relative") {
                    div(className = "absolute max-[450px]:max-w-[310px] max-[550px]:mt-8 max-[550px]:ml-12 mt-12 ml-20 text-lg font-['Montserrat',Arial,sans-serif] font-bold") {
                        + "Don't miss out on the opportunity to "
                        br(className = "max-[450px]:hidden")
                        + "be among the first to access new "
                        br(className = "max-[450px]:hidden")
                        + "features and exclusive offers."
                    }
                    link(className = "absolute max-[550px]:pl-[50px] pl-[80px] max-[450px]:mt-[155px] mt-36", label = "", url = "https://docs.google.com/forms/d/e/1FAIpQLSdghhZVBqI0xVh8SA1F8O_BjDxuvQnP-sZ4LUR6TahysyOj4g/viewform") {
                        button(text = "", className = "inline-flex items-center border-gradient px-5 rounded-[10px] text-sm h-10 font-['Montserrat',Arial,sans-serif] font-bold") {
                            + "Join Whitelist"
                            span(content = "●", className = "text-[#7F5EE7] ml-3 mb-0.5")
                        }
                    }
                    image(src = "images/rectangle_midnight_purple.png", className = "w-[580px] h-[240px] lg:mr-12")
                }
            }
        }
    }

}

private fun Container.demoSection() {
    div(className = "w-full mt-6 lg:mt-[-20px]") {
        id = "demo-section"
        div(className = "flex max-lg:flex-col") {
            h2(className = "min-w-[380px] lg:ml-20 max-lg:text-center max-lg:text-[36px] text-[48px] font-['Montserrat',Arial,sans-serif] font-bold") {
                + "Explore Our "
                br(className = "max-lg:hidden")
                + "Demo"
            }
            div(className = "min-w-[380px] w-full flex justify-center lg:justify-end") {
                div(className = "relative") {
                    div(className = "absolute max-[450px]:max-w-[310px] max-[550px]:mt-8 max-[550px]:ml-12 mt-12 ml-20 font-['Montserrat',Arial,sans-serif] font-bold") {
                        + "Experience the core features of "
                        br(className = "max-[450px]:hidden")
                        + "Rentality without any obligations by "
                        br(className = "max-[450px]:hidden")
                        + "trying our demo."
                    }
                    link(className = "absolute max-[550px]:pl-[50px] pl-[80px] max-[450px]:mt-[155px] mt-36", label = "", url = "https://demo.rentality.xyz/guest") {
                        button(text = "", className = "inline-flex items-center border-gradient px-5 rounded-[10px] text-sm h-10 font-['Montserrat',Arial,sans-serif] font-bold") {
                            + "Try Demo"
                            span(content = "●", className = "text-[#7F5EE7] ml-3 mb-0.5")
                        }
                    }
                    image(src = "images/rectangle_midnight_purple.png", className = "w-[580px] h-[240px] lg:mr-12")
                }
            }
        }
    }
}

private fun Container.partnersSection() {
    div(className = "w-full") {
        id = "partners-section"
        h2("Partners", className = "md:mb-8 pt-8 md:pt-[85px] text-center text-[40px] lg:text-[48px] font-['Montserrat',Arial,sans-serif] font-bold") {
            id = "partners"
        }
        div(className = "flex flex-col md:flex-row") {
            div (className = "max-md:mb-4 mx-auto") {
                image(src = "images/partners/GoogleCloudOfficialLogo.svg", className = "m-auto mt-6 h-[28px] lg:h-[38px]")
            }
            div (className = "max-md:mb-4 mx-auto") {
                image(src = "images/partners/Eigenmethod-logo-dark-bg.png", className = "m-auto mt-2 h-[48px] lg:h-[58px]")
            }
            div (className = "max-md:mb-4 mx-auto") {
                image(src = "images/partners/civic-logo-blue.svg", className = "m-auto mt-2 h-[48px] lg:h-[58px]")
            }
            div (className = "max-md:mb-4 mx-auto") {
                image(src = "images/partners/ic_zazmic.png", className = "m-auto mt-2 h-[48px] lg:h-[58px]")
            }
        }
    }
}

private fun Container.builtOnSection() {
    div(className = "w-full") {
        id = "built-on-section"
        h2("Built on", className = "md:mb-8 pt-8 md:pt-[110px] text-center text-[40px] lg:text-[48px] font-['Montserrat',Arial,sans-serif] font-bold") {
            id = "built-on"
        }
        div(className = "flex") {
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_base.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_ethereum.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_polygon.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_linea.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_arbitrum.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_avalanche.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_op.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_opbnb.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
            div (className = "mx-auto z-10") {
                image(src = "images/blockchain/ic_zktoken.svg", className = "m-auto h-[38px] lg:h-[68px]")

            }
        }
    }
}