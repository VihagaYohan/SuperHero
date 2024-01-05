package com.techTribeServices.suerHero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techTribeServices.suerHero.repository.HerosRepository.heros
import com.techTribeServices.suerHero.ui.theme.Shape
import com.techTribeServices.suerHero.ui.theme.SuperHeroTheme
import com.techTribeServices.suerHero.model.Hero

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardList()
                }
            }
        }
    }
}

@Composable
fun CardList(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar  = {
            AppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .padding(horizontal = 16.dp,
                    vertical = 8.dp)) {
            items(heros) {
                CardItem(hero = it)
            }
        }
    }
}

@Composable
fun AppBar() {
    Text(text = "Super Heros",
        textAlign = TextAlign.Center,
         modifier = Modifier
             .fillMaxWidth()
             .padding(vertical = 10.dp))
}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    hero: Hero
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(
                modifier = modifier.weight(1f)

            ) {
                Text(text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )

                Text(text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = modifier.width(16.dp))
            
            Image(
                modifier = modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.nameRes),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    SuperHeroTheme(darkTheme = false) {
        /*CardItem(
            name = stringResource(id = R.string.hero1),
            description = stringResource(id = R.string.description1),
            imageRes = painterResource(id = R.drawable.android_superhero1))*/
        CardList()
    }
}