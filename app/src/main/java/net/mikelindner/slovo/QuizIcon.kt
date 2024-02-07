package net.mikelindner.slovo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun QuizIcon(scale: Float): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "quiz",
            defaultWidth = 20.0.dp,
            defaultHeight = 20.0.dp,
            viewportWidth = 20.0f,
            viewportHeight = 20.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(22.167f * scale, 19.917f * scale)
                horizontalLineToRelative(2.083f * scale)
                quadToRelative(0f * scale, -0.917f * scale, 0.312f * scale, -1.542f * scale)
                quadToRelative(0.313f * scale, -0.625f * scale, 1.23f * scale, -1.5f * scale)
                quadToRelative(1.083f * scale, -1.083f * scale, 1.541f * scale, -1.896f * scale)
                quadToRelative(0.459f * scale, -0.812f * scale, 0.459f * scale, -1.812f * scale)
                quadToRelative(0f * scale, -1.917f * scale, -1.292f * scale, -3.084f * scale)
                quadToRelative(-1.292f * scale, -1.166f * scale, -3.417f * scale, -1.166f * scale)
                quadToRelative(-1.625f * scale, 0f * scale, -2.854f * scale, 0.854f * scale)
                reflectiveQuadTo(18.5f * scale, 12.125f * scale)
                lineToRelative(1.917f * scale, 0.792f * scale)
                quadToRelative(0.416f * scale, -0.959f * scale, 1.083f * scale, -1.5f * scale)
                quadToRelative(0.667f * scale, -0.542f * scale, 1.583f * scale, -0.542f * scale)
                quadToRelative(1.209f * scale, 0f * scale, 1.917f * scale, 0.646f * scale)
                reflectiveQuadToRelative(0.708f * scale, 1.687f * scale)
                quadToRelative(0f * scale, 0.75f * scale, -0.354f * scale, 1.355f * scale)
                quadToRelative(-0.354f * scale, 0.604f * scale, -1.271f * scale, 1.312f * scale)
                quadToRelative(-1.208f * scale, 1.083f * scale, -1.562f * scale, 1.854f * scale)
                quadToRelative(-0.354f * scale, 0.771f * scale, -0.354f * scale, 2.188f * scale)
                close()
                moveToRelative(1.083f * scale, 4.833f * scale)
                quadToRelative(0.708f * scale, 0f * scale, 1.188f * scale, -0.479f * scale)
                quadToRelative(0.479f * scale, -0.479f * scale, 0.479f * scale, -1.188f * scale)
                quadToRelative(0f * scale, -0.666f * scale, -0.479f * scale, -1.166f * scale)
                quadToRelative(-0.48f * scale, -0.5f * scale, -1.188f * scale, -0.5f * scale)
                reflectiveQuadToRelative(-1.188f * scale, 0.5f * scale)
                quadToRelative(-0.479f * scale, 0.5f * scale, -0.479f * scale, 1.166f * scale)
                quadToRelative(0f * scale, 0.709f * scale, 0.479f * scale, 1.188f * scale)
                quadToRelative(0.48f * scale, 0.479f * scale, 1.188f * scale, 0.479f * scale)
                close()
                moveTo(8.667f * scale, 31.333f * scale)
                verticalLineTo(2.5f * scale)
                horizontalLineTo(37.5f * scale)
                verticalLineToRelative(28.833f * scale)
                close()
                moveToRelative(3.541f * scale, -3.541f * scale)
                horizontalLineToRelative(21.75f * scale)
                verticalLineTo(6.042f * scale)
                horizontalLineToRelative(-21.75f * scale)
                close()
                moveTo(2.5f * scale, 37.5f * scale)
                verticalLineTo(8.667f * scale)
                horizontalLineToRelative(3.542f * scale)
                verticalLineToRelative(25.291f * scale)
                horizontalLineToRelative(25.291f * scale)
                verticalLineTo(37.5f * scale)
                close()
                moveToRelative(9.708f * scale, -9.708f * scale)
                verticalLineTo(6.042f * scale)
                verticalLineToRelative(21.75f * scale)
                close()
            }
        }.build()
    }
}