package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.navigation_state.ConduitState
import io.kvision.core.Container
import io.kvision.html.*

fun Container.tripRulesPage(state: ConduitState) {
    div(className="mx-auto mt-4 mb-12 $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white font-['Montserrat',Arial,sans-serif]") {
        id = "main-trip-rules-page"
        image(src = "images/bg_logo_zoya.png", className = "max-xl:hidden absolute top-[150px] right-0 max-h-[593px]")
        image(src = "images/bg_arrows_from_logo_zoya.png", className = "max-xl:hidden absolute top-[993px] left-0 max-w-[365px]")
        h1(content = "Trip rules", className = "text-center text-5xl text-[#24D8D4] font-bold")
        p(className = "mt-8 text-xl") {
            + "At "
            strong("Rentality")
            + ", we prioritize the safety, comfort, and satisfaction of both our guests and hosts. "
            + "To ensure a seamless and enjoyable experience for everyone involved, we have established a set of essential trip rules. "
            + "These guidelines are crafted to protect both parties, maintain fairness, and create a hassle-free environment during your journey."
        }
        p(className = "mt-6 text-xl") {
            + "Understanding and following these rules not only improve your travel experience but also upholds the integrity and reliability of our platform. "
            + "By adhering to these guidelines, you contribute to a community of trust, where every trip is safe, enjoyable, and transparent. "
            + "Whether you're a guest or a host, these rules are in place to protect your interests and make sure that each journey with Rentality is a positive one."
        }

        tripRuleSections()

        h2(content = "What to Do If You Have Questions", className = "text-xl font-bold")

        p(className = "mt-6 text-xl") {
            + "We understand that sometimes questions or concerns may arise during your trip. "
            + "If you need clarification on any of the rules, or if an unexpected situation occurs, don't hesitate to reach out to your host. "
            + "They are there to assist you and ensure that your journey is as smooth as possible."
        }
        p(className = "mt-6 text-xl") {
            + "In conclusion, taking a moment to review and understand these trip rules is an investment in your own peace of mind. "
            + "By doing so, you help maintain the high standards of the Rentality platform, ensuring that every journey is a positive experience for both guests and hosts. "
            + "Safe travels, and thank you for being a part of our community!"
        }
    }
}

private fun Container.tripRuleSections() {
    div(className = "mt-20 mb-12 xl:mx-12 grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-4") {
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_start_return.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Start and Return on time"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "Start and end your trip on time according to the time in the reservation order."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_license.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Keep your license handy"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "Make sure to carry your physical driver's license with you whenever you're behind the wheel."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_refuelling.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Refuel the vehicle"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "Please return the vehicle with the same fuel level you started with. You'll be charged retroactively for any missing fuel/battery charge."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_no_smoking.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "No smoking"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "Guests who violate the no-smoking policy may be imposed by the host, and may be banned from the platform."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_keep_the_vehicle_tidy.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Keep the vehicle tidy"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "If the vehicle is found to be unreasonably dirty upon return, you may be subject to a cleaning fine by the host."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_tolls_and_tickets.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Tolls and tickets"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "You're responsible for paying the cost of any certain tickets, tolls or fees incurred during your trip. Hosts may request reimbursement within 90 days post-trip."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_distance_included.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Distance included in trip"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "In your trip receipt indicates distance included per trip and price per 1 overmile. For any additional miles driven, you'll be charged."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_host_to_guest_communications.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Guest-to-host communication"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "In case of extraordinary situations, immediately contact the host via chat and phone number indicated in the trip card."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
        div(className = "relative w-[380px] mx-auto max-xl:mt-2") {
            div(className = "absolute w-[320px] top-[-14px] left-7 flex text-[#24D8D4] text-sm font-bold") {
                image(src = "images/trip_rules_car_sharing_agreement.png", className = "w-[20px] h-[20px]", alt = "Start and Return on time")
                h3(className = "w-full text-center") {
                    + "Car sharing agreement"
                }
            }
            div(className = "absolute inset-0 flex items-center justify-center px-16 text-sm text-center") {
                + "Check Car sharing agreement and have it handy which can be found and downloaded in Trip details."
            }
            image(src = "images/rectangle_midnight_purple.png", className = "w-[450px] h-[190px]")
        }
    }
}