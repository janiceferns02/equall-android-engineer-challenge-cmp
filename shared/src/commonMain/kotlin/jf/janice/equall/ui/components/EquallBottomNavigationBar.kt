package jf.janice.equall.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jf.janice.equall.navigation.BottomNavDestination
import jf.janice.equall.ui.theme.EquallNavBarBackground
import jf.janice.equall.ui.theme.EquallNavIconContainerInactive
import jf.janice.equall.ui.theme.EquallNavIconInactive
import jf.janice.equall.ui.theme.EquallNavLabelSelected
import jf.janice.equall.ui.theme.EquallNavLabelUnselected
import jf.janice.equall.ui.theme.EquallPrimary

@Composable
fun EquallBottomNavigationBar(
    currentDestination: BottomNavDestination,
    onDestinationSelected: (BottomNavDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(EquallNavBarBackground)
            .navigationBarsPadding()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BottomNavDestination.entries.forEach { destination ->
            EquallBottomNavItem(
                destination = destination,
                selected = destination == currentDestination,
                onClick = { onDestinationSelected(destination) },
            )
        }
    }
}

@Composable
private fun EquallBottomNavItem(
    destination: BottomNavDestination,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val iconContainerColor = if (selected) EquallPrimary else EquallNavIconContainerInactive
    val iconTint = if (selected) Color.White else EquallNavIconInactive
    val labelColor = if (selected) EquallNavLabelSelected else EquallNavLabelUnselected
    val labelWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconContainerColor),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = destination.icon,
                contentDescription = destination.label,
                tint = iconTint,
                modifier = Modifier.size(22.dp),
            )
        }
        Text(
            text = destination.label,
            color = labelColor,
            fontSize = 12.sp,
            fontWeight = labelWeight,
        )
    }
}
