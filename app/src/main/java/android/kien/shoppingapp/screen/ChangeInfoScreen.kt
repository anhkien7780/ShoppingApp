package android.kien.shoppingapp.screen

import android.annotation.SuppressLint
import android.content.Context
import android.kien.shoppingapp.R
import android.kien.shoppingapp.library.composable.rignteousFont
import android.kien.shoppingapp.models.Date
import android.kien.shoppingapp.models.User
import android.kien.shoppingapp.ui.theme.ShoppingAppTheme
import android.kien.shoppingapp.viewmodel.AvatarImageViewModel
import android.kien.shoppingapp.viewmodel.UserViewModel
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangeInfoScreen(
    userInfo: User,
    username: String,
    context: Context,
    userViewModel: UserViewModel,
    avatarImageViewModel: AvatarImageViewModel,
    onBack: () -> Unit
) {
    var name by remember {
        mutableStateOf(userInfo.name)
    }
    var gender by remember {
        mutableStateOf(userInfo.sex)
    }
    var birthday by remember {
        mutableStateOf(
            Date(
                userInfo.getBirthDay().dayOfMonth,
                userInfo.getBirthDay().monthValue,
                userInfo.getBirthDay().year
            ).toLocalDate()
        )
    }
    var phoneNumber: String by remember {
        mutableStateOf(userInfo.phoneNumber)
    }
    var uri by remember {
        mutableStateOf<Uri?>(Uri.parse(avatarImageViewModel.avatarImage!!.url))
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "CHANGE INFORMATION",
                fontFamily = rignteousFont
            )
        }, navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )

            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            ChangeAvatar(placeholder = R.drawable.default_logo, onUriChange = { uri = it })
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            MyOutlinedTextFiled(
                value = name,
                onValuedChange = { name = it },
                label = "Change name",
                placeholder = name,

                )

            Spacer(modifier = Modifier.padding(10.dp))
            GenderSelection(sex = if (userInfo.sex) "Male" else "Female") { gender = it == "Male" }
            Spacer(modifier = Modifier.padding(10.dp))
            BirthdaySelection(
                birthday = Date(
                    birthday.dayOfMonth,
                    birthday.monthValue,
                    birthday.year
                )
            ) {
                birthday = it.toLocalDate()
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        userViewModel.updateUser(
                            User(
                                name = name,
                                birthDay = birthday.format(DateTimeFormatter.ofPattern("d/M/yyyy")),
                                sex = gender,
                                age = LocalDateTime.now().year - birthday.year,
                                phoneNumber = phoneNumber,
                                username = username
                            )
                        )
                        avatarImageViewModel.changeAvatarImage(
                            uri = Uri.parse(uri.toString()),
                            context = context,
                            username = username
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .fillMaxWidth(0.9f),
                    colors = ButtonDefaults.buttonColors(Color(218, 128, 128))
                ) {
                    Text(text = "Save", fontFamily = rignteousFont, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun ChangeAvatar(placeholder: Int, onUriChange: (Uri?) -> Unit) {
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) {
        uri = it
        onUriChange(it)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = placeholder),
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.padding(30.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                pickMedia.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }, modifier = Modifier.padding(PaddingValues())) {
                Icon(
                    painter = painterResource(id = R.drawable.folder_send_fill_2x),
                    contentDescription = "Folder send fill"
                )
            }
            Text(text = "Image size < 1MB", fontFamily = rignteousFont)
        }
    }
}

@Composable
fun MyOutlinedTextFiled(
    value: String,
    onValuedChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
) {
    OutlinedTextField(
        value = value,
        label = {
            Text(
                text = label, fontFamily = rignteousFont, fontSize = 16.sp
            )
        },
        placeholder = {
            Text(
                text = placeholder, fontFamily = rignteousFont, fontSize = 16.sp
            )
        },
        onValueChange = onValuedChange,
        textStyle = TextStyle(
            fontFamily = rignteousFont, fontSize = 20.sp
        ),
        shape = RectangleShape,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownSelection(
    labelValue: String,
    selectedItem: Int = 0,
    onSelectedItemChange: (Int) -> Unit,
    listItems: List<Int>
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.width(110.dp)
    ) {
        ExposedDropdownMenuBox(expanded = true, onExpandedChange = {
            expanded = !expanded
        }) {
            OutlinedTextField(
                value = selectedItem.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = labelValue, fontFamily = rignteousFont) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.menuAnchor(),
                shape = RectangleShape
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                listItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.toString(), fontFamily = rignteousFont) },
                        onClick = {
                            onSelectedItemChange(item)
                            expanded = false
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun GenderSelection(
    sex: String, onGenderChange: (String) -> Unit
) {
    var maleGenderChecked by remember {
        mutableStateOf(false)
    }
    var femaleGenderChecked by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Gender",
            fontFamily = rignteousFont,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MyCheckBox(text = "Male", checked = maleGenderChecked) {
                maleGenderChecked = it
                onGenderChange("Male")
                femaleGenderChecked = false
            }
            MyCheckBox(text = "Female", checked = femaleGenderChecked) {
                femaleGenderChecked = it
                onGenderChange("Female")
                maleGenderChecked = false
            }

        }
    }
}

@Composable
fun MyCheckBox(
    text: String = "", checked: Boolean = false, onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = text, fontFamily = rignteousFont)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BirthdaySelection(birthday: Date, onBirthDayChange: (Date) -> Unit) {
    var day by remember {
        mutableIntStateOf(birthday.day)
    }
    var month by remember {
        mutableIntStateOf(birthday.month)
    }
    var year by remember {
        mutableIntStateOf(birthday.year)
    }
    val calender = Calendar.getInstance()
    val currentDate = LocalDateTime.of(
        calender.get(Calendar.YEAR),
        calender.get(Calendar.MONTH) + 1,
        calender.get(Calendar.DAY_OF_MONTH),
        0,
        0,
    )
    Column {
        Row {
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Birthday", fontFamily = rignteousFont, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            DropDownSelection(
                labelValue = "Day",
                selectedItem = day,
                onSelectedItemChange = {
                    day = it
                    onBirthDayChange(Date(day, month, year))
                },
                listItems = (1..31).toList()
            )
            DropDownSelection(
                labelValue = "Month",
                selectedItem = month,
                onSelectedItemChange = {
                    month = it
                    onBirthDayChange(Date(day, month, year))
                },
                listItems = (1..12).toList()
            )
            DropDownSelection(
                labelValue = "Year",
                selectedItem = year,
                onSelectedItemChange = {
                    year = it
                    onBirthDayChange(Date(day, month, year))
                },
                listItems = (currentDate.year.downTo(1900)).toList()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyCheckBoxPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
    ShoppingAppTheme {
        MyCheckBox(text = "Check box", checked = checked, onCheckedChange = { checked = it })
    }
}


@Preview(showBackground = true)
@Composable
fun GenderSelectionPreview() {
    var sex by remember {
        mutableStateOf("Male")
    }
    ShoppingAppTheme {
        GenderSelection(sex) { sex = it }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DropDownSelectionPreview() {
    val calender = Calendar.getInstance()
    val currentDate = LocalDateTime.of(
        calender.get(Calendar.YEAR),
        calender.get(Calendar.MONTH) + 1,
        calender.get(Calendar.DAY_OF_MONTH),
        0,
        0,
    )
    val listYears = 1900..currentDate.year
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    ShoppingAppTheme {
        DropDownSelection(
            "Year",
            onSelectedItemChange = { selectedItem = it },
            selectedItem = selectedItem,
            listItems = listYears.toList()
        )
    }
}