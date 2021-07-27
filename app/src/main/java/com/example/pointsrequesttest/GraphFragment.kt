package com.example.pointsrequesttest

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pointsrequesttest.databinding.FragmentGraphBinding
import com.example.pointsrequesttest.databinding.ItemTableBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import javax.inject.Inject

class GraphFragment : Fragment() {
    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    private val args: GraphFragmentArgs by navArgs()

    @Inject
    lateinit var presenter: GraphPresenter

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.requestPoints(args.pointsCount)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }

    fun showProgress() {
        with(binding) {
            textErrorMessage.visibility = View.GONE
            table.visibility = View.GONE
            graph.visibility = View.GONE

            progressBar.visibility = View.VISIBLE
        }
    }

    fun showPoints(points: List<Point>) {
        with(binding) {
            progressBar.visibility = View.GONE
            textErrorMessage.visibility = View.GONE

            setupGraph(points)
            graph.visibility = View.VISIBLE
            setupTable(points)
            table.visibility = View.VISIBLE
        }
    }

    fun showError(errorMessage: String) {
        with(binding) {
            progressBar.visibility = View.GONE
            table.visibility = View.GONE
            graph.visibility = View.GONE

            with(textErrorMessage) {
                text = errorMessage
                visibility = View.VISIBLE
            }
        }
    }

    private fun setupTable(points: List<Point>) {
        with(binding.table) {
            layoutManager = LinearLayoutManager(context)
            adapter = TableAdapter(points)
        }
    }

    private fun setupGraph(points: List<Point>) {
        with(binding.graph) {
            val values = points.map { Entry(it.x, it.y) }
            val set = LineDataSet(values, "Sample data set")
            set.color = Color.BLACK
            set.setDrawCircles(false)
            set.setDrawValues(false)
            data = LineData(listOf(set))

            description.isEnabled = false
            setDrawGridBackground(false)
            setTouchEnabled(false)
            setScaleEnabled(true)
            isDragEnabled = true
            setPinchZoom(true)
            legend.isEnabled = false
            with(xAxis) {
                isEnabled = true
                setDrawGridLines(false)
                setDrawLabels(true)
                position = XAxis.XAxisPosition.BOTTOM
            }
            with(axisLeft) {
                isEnabled = true
                setDrawLabels(true)
                setDrawGridLines(false)
            }
            axisRight.isEnabled = false
        }
    }

    private class TableAdapter(private val points: List<Point>) :
        RecyclerView.Adapter<TableViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
            return TableViewHolder(
                ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
            holder.bind(position + 1, points[position])
        }

        override fun getItemCount(): Int = points.count()
    }

    private class TableViewHolder(private val itemBinding: ItemTableBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(index: Int, point: Point) {
            with(itemBinding) {
                textIndex.text = root.context.getString(R.string.table_index, index)
                textX.text = root.context.getString(R.string.table_x, point.x)
                textY.text = root.context.getString(R.string.table_y, point.y)
            }
        }
    }
}