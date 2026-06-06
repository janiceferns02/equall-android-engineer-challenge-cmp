package jf.janice.equall.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jf.janice.equall.domain.ActiveLoanData
import jf.janice.equall.ui.theme.CardBlue
import jf.janice.equall.ui.theme.CardGold
import jf.janice.equall.ui.theme.CardNavyBlue
import jf.janice.equall.ui.theme.CheckGreen
import jf.janice.equall.ui.theme.EmiTextGold
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ActiveLoanPhaseComponent(
    loan: ActiveLoanData
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SectionTitleText("YOUR EXISTING LOAN")
        }

        item {
            OutstandingLoanCard(loan)
        }

        item {
            NextEmiCard(loan)
        }

        item {
            TrustIQScoreCard(score = loan.trustIQScore, message = loan.trustIQMsg)
        }
    }
}

@Composable
fun OutstandingLoanCard(
    loanData: ActiveLoanData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            //Navy Blue Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = CardNavyBlue)
                    .padding(16.dp)
            ) {
                Text(
                    text = "OUTSTANDING",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoanAmountText(loanData.outstandingAmount)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Personal loan • ${loanData.totalInstallments} months • ${loanData.interestRate} p.a.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                SegmentedProgressBar(loanData.totalInstallments, loanData.paidInstallments)

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(label = "EMI", value = "₹${loanData.emiAmount}")
                VerticalDivider()
                StatItem(label = "Due", value = loanData.dueDate)
                VerticalDivider()
                StatItem(label = "Rate p.a.", value = loanData.interestRate)
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).background(CheckGreen, CircleShape))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Autodebit on • ${loanData.autoDebitBank}",
                        style = MaterialTheme.typography.bodySmall,
                        color = CheckGreen,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Details >",
                    style = MaterialTheme.typography.bodySmall,
                    color = CardBlue,
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }
}

@Composable
fun SegmentedProgressBar(
    segments: Int,
    progress: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 1..segments) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .background(
                        color = if (i <= progress) CardBlue else Color.DarkGray,
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column {
        Text(text = value, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black))
        Text(text = label, style = MaterialTheme.typography.bodySmall.copy(color = Color.DarkGray))
    }
}

@Composable
fun NextEmiCard(loan: ActiveLoanData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardGold)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Next EMI • due in ${loan.daysUntilNextEmi} days",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = EmiTextGold,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "₹${loan.emiAmount}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Pay now", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
fun ActiveLoanPhaseComponentPreview() {
    OutstandingLoanCard(ActiveLoanData(
        outstandingAmount = "1,42,000",
        emiAmount = "13,000",
        dueDate = "1 Jul",
        interestRate = "25.16%",
        paidInstallments = 5,
        totalInstallments = 8,
        autoDebitBank = "HDFC ••6411",
        daysUntilNextEmi = 6,
        92,
        "up to 4 months"
    )
    )

//    ActiveLoanPhaseComponent()
}