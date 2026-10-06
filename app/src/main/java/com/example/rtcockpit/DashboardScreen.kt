package com.example.rtcockpit

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rtcockpit.ui.theme.*

// ডাটা মডেল
data class StatItem(
    val title: String,
    val value: String,
    val subtext: String,
    val growth: String,
    val accentColor: Color,
    val icon: ImageVector
)

data class WorkspaceAction(
    val title: String,
    val subtitle: String,
    val badgeCount: Int? = null,
    val icon: ImageVector
)

@Composable
fun DashboardScreen() {
    Scaffold(
        bottomBar = { CustomBottomNavigation() },
        containerColor = BgDark
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            TopHeaderSection()
            AdminStatusCard()
            OverviewStatisticsSection()
            CommandWorkspaceDeckSection()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TopHeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(42.dp)
                .background(CardBgDark, RoundedCornerShape(12.dp))
                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(Brush.radialGradient(listOf(Color(0xFF3D2F08), Color(0xFF141005))))
                    .border(2.dp, GoldAccent, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(10.dp))
                    Text("RT", fontWeight = FontWeight.Black, color = GoldAccent, fontSize = 16.sp)
                }
            }

            Column {
                Text("RT GROWTH", fontWeight = FontWeight.Black, fontSize = 18.sp, color = GoldAccent)
                Text("COCKPIT", fontWeight = FontWeight.ExtraBold, fontSize = 13.sp, color = GoldAccent)
                Text("MASTER COMMAND HUB", fontSize = 8.sp, color = TextMuted, letterSpacing = 1.2.sp)
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(42.dp)
                        .background(CardBgDark, CircleShape)
                        .border(1.dp, Color.White.copy(alpha = 0.12f), CircleShape)
                ) {
                    Icon(Icons.Outlined.Notifications, contentDescription = null, tint = GoldAccent)
                }
                Box(
                    modifier = Modifier
                        .size(9.dp)
                        .background(NeonPink, CircleShape)
                        .align(Alignment.TopEnd)
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(42.dp)
                    .background(CardBgDark, CircleShape)
                    .border(1.dp, GoldAccent.copy(alpha = 0.4f), CircleShape)
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = GoldAccent)
            }
        }
    }
}

@Composable
fun AdminStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CardBgDark),
        border = BorderStroke(1.2.dp, GoldBorder.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Brush.radialGradient(listOf(Color(0xFF4A3A0B), Color(0xFF141005))))
                        .border(1.dp, GoldAccent, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))
                }

                Column {
                    Text("Welcome Back,", fontSize = 11.sp, color = TextMuted)
                    Text("Admin", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = GoldAccent)
                    Text("Have a great day!", fontSize = 11.sp, color = TextMuted)
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(Icons.Default.Storage, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(24.dp))
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Database Status", fontSize = 11.sp, color = TextMuted)
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = NeonGreen.copy(alpha = 0.15f),
                            border = BorderStroke(0.5.dp, NeonGreen)
                        ) {
                            Text("Connected", color = NeonGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text("typing-5c3e4-default-rtdb", fontSize = 9.sp, color = TextMuted)
            }
        }
    }
}

@Composable
fun OverviewStatisticsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.BarChart, contentDescription = null, tint = GoldAccent)
                Text("Overview Statistics", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Text("Oct 5, 2026 | 02:03 PM", color = TextMuted, fontSize = 10.sp)
        }

        val statItems = listOf(
            StatItem("Total Users", "919", "Active accounts", "+12%", NeonBlue, Icons.Default.Groups),
            StatItem("Total Deposit", "৳64,597.00", "Today: ৳0.00", "+8%", NeonGreen, Icons.Default.AccountBalanceWallet),
            StatItem("Total Withdraw", "৳57,414.00", "Today: ৳0.00", "+6%", NeonPink, Icons.Default.Payments),
            StatItem("Work Value Done", "৳0.00", "Completed tasks", "+0%", NeonYellow, Icons.Default.ElectricBolt),
            StatItem("User Profits", "৳310,073.00", "Total profits", "+15%", NeonPurple, Icons.Default.TrendingUp),
            StatItem("Asset Volume", "৳53,496.90", "Total assets", "+9%", NeonCyan, Icons.Default.Lock)
        )

        for (i in statItems.indices step 3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (j in 0 until 3) {
                    if (i + j < statItems.size) {
                        StatCard(item = statItems[i + j], modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(item: StatItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(115.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBgDark),
        border = BorderStroke(1.dp, item.accentColor.copy(alpha = 0.4f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(item.accentColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(item.icon, contentDescription = null, tint = item.accentColor, modifier = Modifier.size(18.dp))
            }

            Column {
                Text(item.title, fontSize = 9.5.sp, color = TextMuted, maxLines = 1)
                Text(
                    item.value,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("▲ ${item.growth}", color = item.accentColor, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                Text(item.subtext, color = TextMuted, fontSize = 7.5.sp, maxLines = 1)
            }
        }
    }
}

@Composable
fun CommandWorkspaceDeckSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.GridView, contentDescription = null, tint = GoldAccent)
                Text("Command Workspace Deck", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Text("Manage • Monitor • Grow", color = GoldAccent.copy(alpha = 0.8f), fontSize = 10.sp)
        }

        val row1 = listOf(
            WorkspaceAction("User Directory", "Manage profiles", null, Icons.Default.People),
            WorkspaceAction("Deposits", "Review requests", null, Icons.Default.AccountBalanceWallet),
            WorkspaceAction("Withdrawals", "Pending req.", 2, Icons.Default.Payments),
            WorkspaceAction("Send Money Req", "Handle transfer", 0, Icons.AutoMirrored.Filled.Send)
        )

        val row2 = listOf(
            WorkspaceAction("Recharges", "Mobile recharge", 0, Icons.Default.ElectricBolt),
            WorkspaceAction("Gift Vouchers", "Create voucher", 31, Icons.Default.CardGiftcard),
            WorkspaceAction("Typing Tasks", "Tasks & review", 0, Icons.Default.Keyboard),
            WorkspaceAction("Support Chat", "Conversations", 3, Icons.Default.HeadsetMic)
        )

        ActionGridRow(actions = row1)
        ActionGridRow(actions = row2)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WorkspaceCard(
                action = WorkspaceAction("Balance Reset", "Correction tool", null, Icons.Default.RestartAlt),
                modifier = Modifier.weight(1f)
            )
            WorkspaceCard(
                action = WorkspaceAction("System Settings", "Configure DB", null, Icons.Default.Settings),
                modifier = Modifier.weight(1f)
            )
            TogetherWeGrowBanner(modifier = Modifier.weight(1.3f))
        }
    }
}

@Composable
fun ActionGridRow(actions: List<WorkspaceAction>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        actions.forEach { action ->
            WorkspaceCard(action = action, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun WorkspaceCard(action: WorkspaceAction, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(125.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardBgDark),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.08f))
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            if (action.badgeCount != null) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(if (action.badgeCount > 0) NeonPink else NeonPurple, CircleShape)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${action.badgeCount}", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(action.icon, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))

                Column {
                    Text(action.title, color = TextWhite, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Text(action.subtitle, color = TextMuted, fontSize = 7.5.sp, maxLines = 2, lineHeight = 9.sp)
                }

                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.06f))
                        .align(Alignment.End),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}

@Composable
fun TogetherWeGrowBanner(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(125.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, GoldBorder.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.radialGradient(listOf(Color(0xFF382B0A), Color(0xFF100C02))))
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Together\nWe Grow", color = GoldAccent, fontWeight = FontWeight.Black, fontSize = 13.sp, lineHeight = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Icon(Icons.Default.TrendingUp, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(28.dp))
        }
    }
}

@Composable
fun CustomBottomNavigation() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(26.dp),
        color = CardBgDark,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem("Home", Icons.Default.Home, isSelected = true)
            BottomNavItem("Users", Icons.Default.Group, isSelected = false)
            BottomNavItem("Reports", Icons.Default.SignalCellularAlt, isSelected = false)
            BottomNavItem("Settings", Icons.Default.Settings, isSelected = false)
        }
    }
}

@Composable
fun BottomNavItem(label: String, icon: ImageVector, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { }
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint = if (isSelected) GoldAccent else TextMuted,
            modifier = Modifier.size(22.dp)
        )
        Text(
            label,
            color = if (isSelected) GoldAccent else TextMuted,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
