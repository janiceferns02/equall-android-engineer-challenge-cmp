package jf.janice.equall.ui

import androidx.lifecycle.ViewModel
import jf.janice.equall.domain.ActiveLoanData
import jf.janice.equall.domain.DiscoveryData
import jf.janice.equall.domain.HomeUiState
import jf.janice.equall.domain.PendingReviewData
import jf.janice.equall.domain.StepStatus
import jf.janice.equall.domain.StepperItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Discovery(
            data = DiscoveryData(
                userName = "Anshul",
                preQualifiedAmount = "2,00,000",
                durationMonths = "12 months"
            )
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun switchToReviewPhase() {
        _uiState.value = HomeUiState.PendingReview(
            data = PendingReviewData(
                loanAmount = "2,00,000",
                steps = listOf(
                    StepperItem(
                        1,
                        "Submitted",
                        "Application received",
                        StepStatus.COMPLETED,
                        "Today • 9:14 am"
                    ),
                    StepperItem(
                        2,
                        "In review",
                        "Underwriting in progress • SLA 30 min",
                        StepStatus.ACTIVE,
                        "Started 9:15 am"
                    ),
                    StepperItem(3, "Decision", "Approved or revised offer", StepStatus.PENDING),
                    StepperItem(4, "Disbursed", "Bank in 30 minutes", StepStatus.PENDING)
                )
            )
        )
    }

    fun switchToActiveLoanManagementPhase() {
        _uiState.value = HomeUiState.ActiveLoanManagement(
            data = ActiveLoanData(
                outstandingAmount = "1,42,000",
                emiAmount = "13,000",
                dueDate = "1 Jul",
                interestRate = "25.16%",
                paidInstallments = 5,
                totalInstallments = 8,
                autoDebitBank = "HDFC ••6411",
                daysUntilNextEmi = 6,
                trustIQScore = 92,
                trustIQMsg = "up 4 this month"
            )
        )
    }

    fun switchToDiscoveryPhase() {
        _uiState.value = HomeUiState.Discovery(
            data = DiscoveryData(
                userName = "Anshul",
                preQualifiedAmount = "2,00,000",
                durationMonths = "12 months"
            )
        )
    }
}