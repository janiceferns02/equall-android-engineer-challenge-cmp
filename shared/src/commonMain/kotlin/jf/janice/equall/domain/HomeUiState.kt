package jf.janice.equall.domain

sealed interface HomeUiState {
    data class Discovery(val data: DiscoveryData): HomeUiState
    data class PendingReview(val data: PendingReviewData): HomeUiState
    data class ActiveLoanManagement(val data: ActiveLoanData): HomeUiState
}