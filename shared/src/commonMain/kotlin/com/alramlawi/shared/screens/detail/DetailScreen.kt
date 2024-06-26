package com.alramlawi.shared.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.alramlawi.shared.data.Museum
import com.alramlawi.shared.screens.EmptyScreenContent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import museum.shared.generated.resources.Res
import museum.shared.generated.resources.back
import museum.shared.generated.resources.label_artist
import museum.shared.generated.resources.label_credits
import museum.shared.generated.resources.label_date
import museum.shared.generated.resources.label_department
import museum.shared.generated.resources.label_dimensions
import museum.shared.generated.resources.label_medium
import museum.shared.generated.resources.label_repository
import museum.shared.generated.resources.label_title
import org.jetbrains.compose.resources.stringResource

data class DetailScreen(val objectId: Int) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: DetailScreenModel = getScreenModel()

        val obj by screenModel.getObject(objectId).collectAsState(initial = null)
        AnimatedContent(obj != null) { objectAvailable ->
            if (objectAvailable) {
                MuseumDetails(obj!!, onBackClick = { navigator.pop() })
            } else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MuseumDetails(
    museum: Museum,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.White),
                scrollBehavior = null
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            KamelImage(
                resource = asyncPainterResource(data = museum.primaryImageSmall),
                contentDescription = museum.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            SelectionContainer {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        museum.title,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(6.dp))
                    LabeledInfo(stringResource(Res.string.label_title), museum.title)
                    LabeledInfo(stringResource(Res.string.label_artist), museum.artistDisplayName)
                    LabeledInfo(stringResource(Res.string.label_date), museum.objectDate)
                    LabeledInfo(stringResource(Res.string.label_dimensions), museum.dimensions)
                    LabeledInfo(stringResource(Res.string.label_medium), museum.medium)
                    LabeledInfo(stringResource(Res.string.label_department), museum.department)
                    LabeledInfo(stringResource(Res.string.label_repository), museum.repository)
                    LabeledInfo(stringResource(Res.string.label_credits), museum.creditLine)
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = 4.dp)) {
        Spacer(Modifier.height(6.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(data)
            }
        )
    }
}
