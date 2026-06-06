package jf.janice.equall.domain

enum class StepStatus {
    COMPLETED,
    ACTIVE,
    PENDING
}

data class StepperItem(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val status: StepStatus,
    val badgeText: String? = null
)