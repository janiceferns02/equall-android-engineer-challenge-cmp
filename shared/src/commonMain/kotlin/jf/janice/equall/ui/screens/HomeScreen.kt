package jf.janice.equall.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import jf.janice.equall.domain.HomeUiState
import jf.janice.equall.ui.HomeViewModel
import jf.janice.equall.ui.components.ActiveLoanPhaseComponent
import jf.janice.equall.ui.components.DiscoveryPhaseComponent
import jf.janice.equall.ui.components.PendingReviewPhaseComponent
import jf.janice.equall.ui.components.SwitchStateComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDevMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (val state = uiState) {
                is HomeUiState.Discovery -> {
                    DiscoveryPhaseComponent(
                        data = state.data,
                        onCheckEligibilityClick = { }
                    )
                }
                is HomeUiState.PendingReview -> {
                    PendingReviewPhaseComponent(data = state.data)
                }
                is HomeUiState.ActiveLoanManagement -> {
                    ActiveLoanPhaseComponent(loan = state.data)
                }
            }

            SmallFloatingActionButton(
                onClick = { showDevMenu = true },
                containerColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Test menu",
                    modifier = Modifier.size(16.dp)
                )
            }

            if(showDevMenu) {
                ModalBottomSheet(onDismissRequest = { showDevMenu = false }) {
                    SwitchStateComponent(
                        currentState = uiState,
                        onStateSelected = { index ->
                            when (index) {
                                0 -> viewModel.switchToDiscoveryPhase()
                                1 -> viewModel.switchToReviewPhase()
                                2 -> viewModel.switchToActiveLoanManagementPhase()
                            }
                            showDevMenu = false
                        }
                    )
                }
            }
        }
    }
}