package com.mars.lab2ai.presentation.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke
import com.mars.lab2ai.R
import com.mars.lab2ai.databinding.ActivityChartBinding
import org.koin.android.ext.android.bind


class ChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (intent.extras?.getSerializable(CHART_DATA_KEY) as? HashMap<Float, Float>)?.let {
            createChart(it)
        }
    }

    private fun createChart(data: HashMap<Float, Float>) {

        binding.anyChartView.setProgressBar(binding.progressBar)

        val cartesian: Cartesian = AnyChart.line()

        cartesian.animation(true)

        cartesian.padding(10.0, 20.0, 5.0, 20.0)

        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true) // TODO ystroke
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

//
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData: List<DataEntry> = data.map { ValueDataEntry(it.key, it.value) }

        val set: Set= com.anychart.data.Set.instantiate()
        set.data(seriesData)
        val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")

        val series1: com.anychart.core.cartesian.series.Line = cartesian.line(series1Mapping)
        series1.name("Brandy")
        series1.hovered().markers().enabled(true)
        series1.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        binding.anyChartView.setChart(cartesian)
    }

    companion object {
        private const val CHART_DATA_KEY = "chart_data_key"

        fun start(context: Context, data: HashMap<Float, Float>) {
            Intent(context, ChartActivity::class.java).apply {
                putExtra(CHART_DATA_KEY, data)
            }.let { context.startActivity(it) }
        }
    }
}