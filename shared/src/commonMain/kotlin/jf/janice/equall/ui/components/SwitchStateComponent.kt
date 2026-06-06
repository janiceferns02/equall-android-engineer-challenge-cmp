package jf.janice.equall.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jf.janice.equall.domain.DiscoveryData
import jf.janice.equall.domain.HomeUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SwitchStateComponent(
    currentState: HomeUiState,
    onStateSelected: (Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val states = listOf("State A", "State B", "State C")

            states.forEachIndexed { index, label ->
                val isSelected = when (index) {
                    0 -> currentState is HomeUiState.Discovery
                    1 -> currentState is HomeUiState.PendingReview
                    2 -> currentState is HomeUiState.ActiveLoanManagement
                    else -> false
                }

                TextButton(
                    onClick = { onStateSelected(index) },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SwitchStateCompoPreview() {
    SwitchStateComponent(HomeUiState.Discovery(DiscoveryData("Janice", "2,00,000", "7")), {})
}