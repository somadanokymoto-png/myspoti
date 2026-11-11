// Myspoti Custom guest UI preview. Derived project: https://github.com/kota-zo/myspoti (GPL-3.0).
package com.music.spotui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.music.spotui.ui.theme.AppSans

private val Night = Color(0xFF090913)
private val Panel = Color(0xFF1B192C)
private val Purple = Color(0xFF9874FF)
private val SoftText = Color(0xFFB5B4CB)

private data class PreviewTrack(val title: String, val artist: String, val color: Color)

private val PreviewTracks = listOf(
    PreviewTrack("Atlantis", "Seafret", Color(0xFF7861BC)),
    PreviewTrack("Where's My Love", "SYML", Color(0xFF355C8B)),
    PreviewTrack("The Night We Met", "Lord Huron", Color(0xFF9A5778)),
    PreviewTrack("Someone You Loved", "Lewis Capaldi", Color(0xFF517A80)),
)

/**
 * Guest mode deliberately does not assert a Spotify session, fetch personal data or play protected
 * audio. The track names are illustrative UI fixtures. Real Spotify playback remains signed-in.
 */
@Composable
fun GuestPreviewScreen(onLogin: () -> Unit, onExit: () -> Unit) {
    var tab by remember { mutableIntStateOf(0) }
    var selected by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF141126), Night, Night)))
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text("MYSPOTI CUSTOM", color = Purple, fontFamily = AppSans, fontSize = 12.sp,
                    fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                Text("Modo convidado", color = Color.White, fontFamily = AppSans, fontSize = 26.sp,
                    fontWeight = FontWeight.Bold)
            }
            Text("Sair", color = SoftText, fontFamily = AppSans, fontSize = 14.sp,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onExit).padding(12.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(26.dp))
                        .background(Brush.linearGradient(listOf(Color(0xFF38305A), Panel)))
                        .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(26.dp))
                        .padding(22.dp)
                ) {
                    Text("PRÉVIA VISUAL", color = Color(0xFFD5C8FF), fontFamily = AppSans,
                        fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 2.sp)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        when (tab) {
                            0 -> "Sua música. Seu espaço."
                            1 -> "Encontre novas músicas"
                            else -> "Tudo no seu ritmo"
                        },
                        color = Color.White, fontFamily = AppSans, fontWeight = FontWeight.Bold,
                        fontSize = 26.sp, lineHeight = 30.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Explore o visual e a navegação sem compartilhar senhas. " +
                            "Este modo usa exemplos; não acessa a sua conta Spotify.",
                        color = SoftText, fontFamily = AppSans, fontSize = 13.sp, lineHeight = 19.sp
                    )
                    Spacer(Modifier.height(18.dp))
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(24.dp))
                            .background(Purple).clickable(onClick = onLogin).padding(horizontal = 20.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Entrar no Spotify", color = Night, fontFamily = AppSans,
                            fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            item {
                Text(
                    when (tab) {
                        0 -> "Feito para ouvir"
                        1 -> "Descobrir"
                        else -> "Sua biblioteca"
                    },
                    color = Color.White, fontFamily = AppSans,
                    fontWeight = FontWeight.Bold, fontSize = 21.sp
                )
            }

            if (tab == 2) {
                item {
                    Text("Playlists salvas, favoritos e histórico aparecem depois de autorizar a conta. " +
                        "Abaixo está uma amostra do layout.",
                        color = SoftText, fontFamily = AppSans, fontSize = 14.sp, lineHeight = 21.sp)
                }
            }
            items(PreviewTracks.size) { index ->
                val track = PreviewTracks[index]
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(19.dp))
                        .background(if (selected == index) Color(0xFF25213C) else Panel.copy(alpha = 0.65f))
                        .border(1.dp, Color.White.copy(alpha = if (selected == index) 0.16f else 0.06f),
                            RoundedCornerShape(19.dp))
                        .clickable { selected = index }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(62.dp).clip(RoundedCornerShape(14.dp))
                            .background(Brush.linearGradient(listOf(track.color, Night))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("♫", color = Color.White.copy(alpha = 0.9f), fontSize = 30.sp)
                    }
                    Column(modifier = Modifier.weight(1f).padding(start = 14.dp)) {
                        Text(track.title, color = Color.White, fontFamily = AppSans,
                            fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text(track.artist, color = SoftText, fontFamily = AppSans, fontSize = 13.sp)
                    }
                    Text(if (index == selected) "●" else "›", color = Purple, fontSize = 20.sp)
                }
            }

            item {
                Text("Exemplos para testar a interface. Não são faixas baixadas nem áudio disponível offline.",
                    color = SoftText.copy(alpha = 0.9f), fontFamily = AppSans,
                    fontSize = 12.sp, lineHeight = 18.sp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)
                .clip(RoundedCornerShape(23.dp))
                .background(Color(0xFF29233F))
                .border(1.dp, Color.White.copy(alpha = 0.13f), RoundedCornerShape(23.dp))
                .padding(horizontal = 17.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(43.dp).clip(RoundedCornerShape(12.dp))
                    .background(PreviewTracks[selected].color),
                contentAlignment = Alignment.Center
            ) { Text("♫", color = Color.White, fontSize = 22.sp) }
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(PreviewTracks[selected].title, fontFamily = AppSans, color = Color.White,
                    fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("Prévia visual · sem áudio", fontFamily = AppSans, color = SoftText,
                    fontSize = 11.sp)
            }
            Text("DEMO", color = Color(0xFFD5C8FF), fontFamily = AppSans, fontSize = 11.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Início", "Buscar", "Biblioteca").forEachIndexed { index, label ->
                Text(label, color = if (tab == index) Purple else SoftText,
                    fontFamily = AppSans, fontWeight = if (tab == index) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 14.sp, textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(14.dp))
                        .clickable { tab = index }.padding(12.dp))
            }
        }
    }
}
