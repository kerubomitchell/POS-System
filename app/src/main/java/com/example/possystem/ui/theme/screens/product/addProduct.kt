package com.example.possystem.ui.theme.screens.product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.possystem.data.ProductViewModel

// ── Theme colours (mirrors RegisterScreen) ──────────────────────────────────
private val BgDark       = Color(0xFF0F0F1A)
private val SurfaceDark  = Color(0xFF1A1A2E)
private val PurplePrim   = Color(0xFF6C63FF)
private val PurpleSec    = Color(0xFFB06AFF)
private val TextMuted    = Color(0xFF9E9EC8)
private val BorderIdle   = Color(0xFF2E2E4E)

private val GradientBrush = Brush.linearGradient(
    colors = listOf(PurplePrim, PurpleSec)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController) {

    var imageUri       by remember { mutableStateOf<Uri?>(null) }
    var productName    by remember { mutableStateOf("") }
    var price          by remember { mutableStateOf("") }
    var quantity       by remember { mutableStateOf("") }
    var description    by remember { mutableStateOf("") }
    var manufactureDate by remember { mutableStateOf("") }
    var barcodeNumber  by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    val productViewModel: ProductViewModel = viewModel()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
    ) {
        // Decorative glows — same style as RegisterScreen
        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = (-60).dp, y = (-60).dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x336C63FF), Color.Transparent)
                    ),
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 60.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x33B06AFF), Color.Transparent)
                    ),
                    shape = CircleShape
                )
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Add Product",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 100.dp, top = 8.dp)
            ) {

                // ── Header icon ──────────────────────────────────────────────
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .background(
                                    brush = GradientBrush,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Inventory,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "New Product",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Fill in the details below",
                            fontSize = 14.sp,
                            color = TextMuted
                        )
                    }
                }

                // ── Image picker ─────────────────────────────────────────────
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(SurfaceDark)
                                .border(
                                    width = 2.dp,
                                    brush = GradientBrush,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .shadow(4.dp, RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageUri != null) {
                                Image(
                                    painter = rememberAsyncImagePainter(imageUri),
                                    contentDescription = "Product Image",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(
                                    Icons.Default.ImageSearch,
                                    contentDescription = null,
                                    tint = PurplePrim,
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Gradient "Select Image" button
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(44.dp)
                                .background(brush = GradientBrush, shape = RoundedCornerShape(14.dp))
                                .clickable { launcher.launch("image/*") },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    Icons.Default.AddPhotoAlternate,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "Select Image",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }

                // ── Form fields ───────────────────────────────────────────────
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        PurpleTextField(productName,    { productName = it },    "Product Name",   Icons.Default.Inventory)
                        PurpleTextField(price,          { price = it },          "Price",           Icons.Default.AttachMoney, KeyboardType.Number)
                        PurpleTextField(quantity,       { quantity = it },       "Quantity",        Icons.Default.FormatListNumbered, KeyboardType.Number)
                        PurpleTextField(description,    { description = it },    "Description",    Icons.Default.Description)
                        PurpleTextField(manufactureDate,{ manufactureDate = it },"Manufacture Date",Icons.Default.DateRange)
                        PurpleTextField(barcodeNumber,  { barcodeNumber = it },  "Barcode Number", Icons.Default.QrCode)
                    }
                }

                // ── Save button ───────────────────────────────────────────────
                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(brush = GradientBrush, shape = RoundedCornerShape(16.dp))
                            .clickable {
                                productViewModel.uploadProduct(
                                    imageUri,
                                    productName,
                                    price,
                                    quantity,
                                    description,
                                    manufactureDate,
                                    barcodeNumber,
                                    context,
                                    navController
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                Icons.Default.Save,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Save Product",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// ── Reusable themed text field ────────────────────────────────────────────────
@Composable
fun PurpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceDark),
        shape = RoundedCornerShape(14.dp),
        label = {
            Text(text = label, color = TextMuted, fontSize = 13.sp)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PurplePrim,
                modifier = Modifier.size(20.dp)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = PurplePrim,
            unfocusedBorderColor = BorderIdle,
            focusedTextColor     = Color.White,
            unfocusedTextColor   = Color.White,
            cursorColor          = PurplePrim,
            focusedContainerColor   = SurfaceDark,
            unfocusedContainerColor = SurfaceDark
        ),
        singleLine = true
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddProductScreenPreview() {
    MaterialTheme {
        AddProductScreen(rememberNavController())
    }
}