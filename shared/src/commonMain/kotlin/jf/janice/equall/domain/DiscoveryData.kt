package jf.janice.equall.domain

data class DiscoveryData(
    val userName: String,
    val preQualifiedAmount: String,
    val durationMonths: String,
    val badgeText: String = "NEW • PRE-QUALIFIED"
)

data class PendingReviewData(
    val loanAmount: String,
    val statusText: String = "UNDER REVIEW",
    val steps: List<StepperItem>
)

data class ActiveLoanData(
    val outstandingAmount: String,
    val emiAmount: String,
    val dueDate: String,
    val interestRate: String,
    val paidInstallments: Int,
    val totalInstallments: Int,
    val autoDebitBank: String,
    val daysUntilNextEmi: Int,
    val trustIQScore: Int,
    val trustIQMsg: String
)