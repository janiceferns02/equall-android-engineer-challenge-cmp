package jf.janice.equall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jf.janice.equall.domain.PendingReviewData
import jf.janice.equall.domain.StepStatus
import jf.janice.equall.domain.StepperItem
import jf.janice.equall.ui.theme.BadgeGray
import jf.janice.equall.ui.theme.CardBlue
import jf.janice.equall.ui.theme.StepperLineGray
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PendingReviewPhaseComponent(
    data: PendingReviewData
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            LoanApplicationTrackerCard(
                data.loanAmount,
                data.statusText,
                data.steps
            )
        }

        item {
            HelperMessage()
        }
    }
}

@Composable
fun StepperRow(
    item: StepperItem,
    isLastItem: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StepperNode(
                item.stepNumber.toString(),
                item.status
            )

            if(!isLastItem) {
                //Vertical Line
                val lineColor = if (item.status == StepStatus.COMPLETED) CardBlue else StepperLineGray

                Spacer(
                    modifier = Modifier
                        .width(2.dp)
                        .background(color = lineColor)
                        .fillMaxHeight()
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 12.dp, bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = if(item.status == StepStatus.ACTIVE) CardBlue else Color.Black
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (item.status == StepStatus.PENDING) Color.Black else Color.Gray
            )
            item.badgeText?.let { badgeText ->
                Spacer(modifier = Modifier.height(6.dp))

                Surface(
                    color = BadgeGray,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = badgeText,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun StepperNode(
    stepNumber: String,
    status: StepStatus
) {

    val containerColor = when(status) {
        StepStatus.COMPLETED -> CardBlue
        StepStatus.ACTIVE -> Color.White
        StepStatus.PENDING -> Color.White
    }

    val borderColor = when(status) {
        StepStatus.COMPLETED -> CardBlue
        StepStatus.ACTIVE -> CardBlue
        StepStatus.PENDING -> StepperLineGray
    }

    val textColor = when(status) {
        StepStatus.COMPLETED -> Color.White
        StepStatus.ACTIVE -> CardBlue
        StepStatus.PENDING -> Color.LightGray
    }

    val strokeWidth = if(status == StepStatus.ACTIVE) 2.dp else 1.dp

    Surface(
        modifier = Modifier
            .size(28.dp),
        shape = CircleShape,
        color = containerColor,
        border = BorderStroke(strokeWidth, borderColor)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if(status == StepStatus.COMPLETED) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Text(
                    stepNumber,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun LoanApplicationTrackerCard(
    loanAmount: String,
    loanStatus: String,
    stepperData: List<StepperItem>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            //Blue Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = CardBlue)
                    .padding(16.dp)
            ) {
                Text(
                    text = "PERSONAL LOAN APPLICATION",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.8f),
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoanAmountText(loanAmount)

                Spacer(modifier = Modifier.height(8.dp))

                LoanStatusBadge(loanStatus = loanStatus)
            }

            //Loan application status steps
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                stepperData.forEachIndexed { index, item ->
                    StepperRow(item = item, isLastItem = index == stepperData.lastIndex)
                }
            }
        }
    }
}

@Composable
fun HelperMessage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F5F8))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = Color.LightGray,
                style = Stroke(
                    width = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                ),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(16.dp.toPx())
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WHILE YOU WAIT",
                style = MaterialTheme.typography.labelMedium,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "We'll push you the decision when it's ready. No need to keep checking.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun PendingReviewPhaseComponentPreview() {

//    LoanApplicationTrackerCard("2,00,000", "UNDER REVIEW", stepperData)
    //PendingReviewPhaseComponent()
    //StepperNode("3", StepStatus.PENDING)
//    StepperRow(StepperItem(1, "Submitted", "Application received", StepStatus.COMPLETED, "Today • 9:14 am"), false)
    HelperMessage()
}