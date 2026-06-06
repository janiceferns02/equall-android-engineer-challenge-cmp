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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jf.janice.equall.domain.DiscoveryData
import jf.janice.equall.ui.theme.CardGreen
import jf.janice.equall.ui.theme.CheckGreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DiscoveryPhaseComponent (
    data: DiscoveryData,
    onCheckEligibilityClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PersonalizedGreetingCard(data.userName)
        }

        item {
            PreQualifiedCard(
                data.preQualifiedAmount,
                data.durationMonths,
                data.badgeText,
                onCheckEligibilityClick = onCheckEligibilityClick
            )
        }

        item {
            ExploreProductsSection()
        }

        item {
            TrustIQScoreCard(0, null)
        }
    }
}

@Composable
fun PersonalizedGreetingCard(
    name: String
) {
    if(name.isBlank()) return

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.LightGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.first().toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Hi, $name",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Let's get you set up with your first loan.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun PreQualifiedCard(
    loanAmount: String,
    duration: String,
    loanStatus: String,
    onCheckEligibilityClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardGreen)
    ) {
      Column(
          modifier = Modifier
              .padding(16.dp)
      ) {
          LoanStatusBadge(loanStatus)

          Spacer(modifier = Modifier.height(8.dp))

          Text(
              "You're prequalified for",
              style = MaterialTheme.typography.bodyLarge,
              color = Color.White.copy(alpha = 0.7f),
              fontWeight = FontWeight.SemiBold,
          )

          Spacer(modifier = Modifier.height(8.dp))

          LoanAmountText(loanAmount)

          Spacer(modifier = Modifier.height(8.dp))

          Text(
              "Personal loan • $duration • Indicative",
              style = MaterialTheme.typography.bodyMedium,
              fontWeight = FontWeight.Bold,
              color = Color.White.copy(alpha = 0.7f)
          )

          Spacer(modifier = Modifier.height(6.dp))

          Button(
              onClick = onCheckEligibilityClick,
              colors = ButtonDefaults.buttonColors(containerColor = Color.White),
              shape = RoundedCornerShape(12.dp)
          ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Check eligibility",
                    color = CardGreen,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    tint = CardGreen,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
          }
      }
    }
}

@Composable
fun LoanAmountText(
    loanAmount: String
) {
    Text(
        text = "₹$loanAmount",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Black,
        color = Color.White
    )
}

@Composable
fun LoanStatusBadge(
    loanStatus: String
) {
    Surface(
        color = Color.White.copy(alpha = 0.2f),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            loanStatus,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp)
        )
    }
}

@Composable
fun ExploreProductsSection(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        SectionTitleText("EXPLORE PRODUCTS")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProductMiniCard(
                modifier = Modifier.weight(1f),
                label = "PL",
                title = "Personal Loan",
                subtitle = "Up to ₹2L"
            )
            ProductMiniCard(modifier = Modifier.weight(1f), label = "TU", title = "Top-up Loan", subtitle = "For existing")
            ProductMiniCard(modifier = Modifier.weight(1f), label = "CC", title = "Cards", subtitle = "Coming soon")
        }
    }
}

@Composable
fun SectionTitleText(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray
    )
}

@Composable
fun ProductMiniCard(
    modifier: Modifier = Modifier,
    label: String,
    title: String,
    subtitle: String
) {
    Card(
        modifier = modifier.height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                color = Color(0xFFE8EAF6),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier.size(28.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F51B5))
                }
            }
            Column {
                Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(text = subtitle, fontSize = 11.sp, color = Color.Gray , maxLines = 1)
            }
        }
    }
}

@Composable
fun TrustIQScoreCard(
    score: Int,
    message: String?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    color = CheckGreen,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "EXCELLENT",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Column {
                    Text(
                        text = if(score == 0) "Your TrustIQ score" else "TrustIQ $score • $message",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Check your TrustIQ score",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
            }
            Icon(
                imageVector =  Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Preview
@Composable
fun DiscoveryPhaseComponentPreview() {
    //PersonalizedGreeting(name = "Janice")
    //PreQualifiedCard("2,00,000", "12 months", {})
//    ExploreProducts()
    DiscoveryPhaseComponent(data = DiscoveryData("Janice", "10,00,000", "7"), {})
//    ProductMiniCard(label = "cc", title = "Top-up Loan", subtitle = "For existing")
//    TrustIQScoreCard(0, null)
//    TrustIQScoreCard(92, "up 4 this month")
}