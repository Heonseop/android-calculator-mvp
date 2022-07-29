package edu.nextstep.camp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import edu.nextstep.camp.calculator.databinding.ActivityMainBinding
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            presenter.addToExpression(0)
        }
        binding.button1.setOnClickListener {
            presenter.addToExpression(1)
        }
        binding.button2.setOnClickListener {
            presenter.addToExpression(2)
        }
        binding.button3.setOnClickListener {
            presenter.addToExpression(3)
        }
        binding.button4.setOnClickListener {
            presenter.addToExpression(4)
        }
        binding.button5.setOnClickListener {
            presenter.addToExpression(5)
        }
        binding.button6.setOnClickListener {
            presenter.addToExpression(6)
        }
        binding.button7.setOnClickListener {
            presenter.addToExpression(7)
        }
        binding.button8.setOnClickListener {
            presenter.addToExpression(8)
        }
        binding.button9.setOnClickListener {
            presenter.addToExpression(9)
        }
        binding.buttonPlus.setOnClickListener {
            presenter.addToExpression(Operator.Plus)
        }
        binding.buttonMinus.setOnClickListener {
            presenter.addToExpression(Operator.Minus)
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.addToExpression(Operator.Multiply)
        }
        binding.buttonDivide.setOnClickListener {
            presenter.addToExpression(Operator.Divide)
        }
        binding.buttonDelete.setOnClickListener {
            presenter.removeLastFromExpression()
        }
        binding.buttonEquals.setOnClickListener {
            presenter.calculateExpression()
        }
        binding.buttonMemory.setOnClickListener {
            when(binding.recyclerView.isVisible){
                true -> presenter.updateExpression()
                false -> presenter.updateMemory()
            }
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showExpression(result: String) {
        hideViewsExceptExpression()
        binding.textView.visibility = View.VISIBLE
        binding.textView.text = result
    }

    override fun showMemory(memories: List<Memory.MemoryItem>) {
        hideViewsExceptMemory()
        binding.recyclerView.visibility = View.VISIBLE

        val adapter = MemoryAdapter()
        adapter.datalist = memories
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun hideViewsExceptMemory() {
        if (binding.textView.isVisible) {
            binding.textView.visibility = View.INVISIBLE
        }
    }

    override fun hideViewsExceptExpression() {
        if (binding.recyclerView.isVisible) {
            binding.recyclerView.visibility = View.INVISIBLE
        }
    }

}
