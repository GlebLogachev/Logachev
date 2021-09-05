package com.glogachev.developerslife.ui.pages.random

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glogachev.developerslife.App
import com.glogachev.developerslife.R
import com.glogachev.developerslife.databinding.FragmentRandomPageBinding
import com.glogachev.developerslife.domain.NetworkResult
import com.glogachev.developerslife.domain.Repository
import com.glogachev.developerslife.domain.model.RandomPost
import javax.inject.Inject


class RandomPageFragment : Fragment() {

    private val postList = mutableListOf<RandomPost>()

    private var _binding: FragmentRandomPageBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: Repository

    private val viewModel: RandomPostViewModel by viewModels {
        RandomViewModelFactory(repository = repository)
    }
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PostsAdapter()
        App.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.obtainRandomPost()
        binding.apply {
            rvPosts.adapter = adapter
            rvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState === RecyclerView.SCROLL_STATE_IDLE) {
                        val position: Int = getCurrentItem()
                        if (position == 0) {
                            binding.apply {
                                btnPrevious.isEnabled = false
                                btnPrevious.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.btn_previous_disable
                                    )
                                )
                            }
                        } else {
                            binding.apply {
                                btnPrevious.isEnabled = true
                                btnPrevious.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.btn_previous
                                    )
                                )
                            }
                        }
                    }
                }
            })
        }


        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Ошибка запроса", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Success -> {
                    postList.add(it.data)
                    adapter.submitList(postList.toList())
                }
            }

        }
        binding.btnNext.setOnClickListener {
            next()
        }
        binding.btnPrevious.setOnClickListener {
            preview()
        }
    }

    fun next() {
        val position = getCurrentItem()
        val count = adapter.itemCount
        if (position < count - 1) {
            setCurrentItem(position + 1, true)
        } else {
            viewModel.obtainRandomPost()
            setCurrentItem(position, true)
        }
    }

    private fun getCurrentItem(): Int {
        return (binding.rvPosts.layoutManager as LinearLayoutManager)
            .findFirstVisibleItemPosition()
    }

    private fun preview() {
        val position = getCurrentItem()
        if (position > 0) setCurrentItem(position - 1, true)
    }

    private fun setCurrentItem(position: Int, smooth: Boolean) {
        if (smooth) {
            binding.rvPosts.smoothScrollToPosition(position)
        } else {
            binding.rvPosts.scrollToPosition(
                position + 1
            )
        }
    }


}