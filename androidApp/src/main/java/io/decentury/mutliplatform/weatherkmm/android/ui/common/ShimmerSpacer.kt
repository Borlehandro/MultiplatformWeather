package io.decentury.mutliplatform.weatherkmm.android.ui.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged

@Composable
fun ShimmerSpacer(
    modifier: Modifier,
) {
    val colors = listOf(
        Colors.shimmerGradientStart,
        Colors.shimmerGradientCenter,
        Colors.shimmerGradientEnd,
    )

    val transition = rememberInfiniteTransition()

    val shimmerWidthPercentage = 0.3f

    var heightPx by remember {
        mutableStateOf(0f)
    }

    var widthPx by remember {
        mutableStateOf(0f)
    }

    Box(
        modifier = Modifier.onSizeChanged { size ->
            heightPx = size.height.toFloat()
            widthPx = size.width.toFloat()
        },
    ) {
        val translateAnim = transition.animateFloat(
            initialValue = 0f,
            targetValue = widthPx * (1 + shimmerWidthPercentage),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
        )

        val brush = Brush.linearGradient(
            colors,
            start = Offset(
                translateAnim.value - (widthPx * shimmerWidthPercentage),
                heightPx,
            ),
            end = Offset(translateAnim.value, heightPx),
        )

        Spacer(
            modifier = modifier
                .background(brush = brush),
        )
    }
}
