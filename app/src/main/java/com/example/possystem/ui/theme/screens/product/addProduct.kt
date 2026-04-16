package com.example.possystem.ui.theme.screens.product

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.possystem.data.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var product_name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var manufacturedate by remember { mutableStateOf("") }
    var  barcodeNumber by remember { mutableStateOf("") }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    var productViewModel: ProductViewModel = viewModel()
    var context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Product") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
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
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(90.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Select Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            StyledTextField(
                value = product_name,
                onValueChange = { product_name = it },
                label = "Product Name",
                icon = Icons.Default.Inventory
            )

            StyledTextField(
                value = price,
                onValueChange = { price = it },
                label = "Price",
                icon = Icons.Default.AttachMoney,
                keyboardType = KeyboardType.Number
            )

            StyledTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = "Quantity",
                icon = Icons.Default.FormatListNumbered,
                keyboardType = KeyboardType.Number
            )

            StyledTextField(
                value = description,
                onValueChange = { description = it },
                label = "Description",
                icon = Icons.Default.Description
            )

            StyledTextField(
                value = manufacturedate,
                onValueChange = { manufacturedate = it },
                label = "Manufacture Date",
                icon = Icons.Default.DateRange
            )

            StyledTextField(
                value = barcodeNumber,
                onValueChange = { barcodeNumber = it },
                label = "Barcode Number",
                icon = Icons.Default.Code
            )



            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    productViewModel.uploadProduct(
                        imageUri,
                        product_name,
                        price,
                        quantity,
                        description,
                        manufacturedate,
                        barcodeNumber,
                        context,
                        navController,
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Product")
            }
        }
    }
}

@Composable
fun StyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = {
            Icon(icon, contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
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