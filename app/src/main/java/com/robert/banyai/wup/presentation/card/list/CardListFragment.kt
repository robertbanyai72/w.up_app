package com.robert.banyai.wup.presentation.card.list

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.robert.banyai.wup.R
import com.robert.banyai.wup.domain.event.SelectCardEvent
import com.robert.banyai.wup.presentation.base.BaseFragment
import com.robert.banyai.wup.presentation.base.ToolbarModel
import com.robert.banyai.wup.presentation.main.MainActivity
import kotlinx.android.synthetic.main.fragment_card_list.*
import javax.inject.Inject

class CardListFragment : BaseFragment<CardListViewModel>() {

    private var currentProgress = 0.0f
    private var valueAnimator: ValueAnimator? = null

    companion object {
        private const val animationDuration: Long = 700
        private const val progressStartValue = 1f
    }

    @Inject
    lateinit var cardPagerAdapter: CardPagerAdapter

    override fun viewModelClass(): Class<out CardListViewModel> {
        return CardListViewModel::class.java
    }

    override fun layoutId(): Int {
        return R.layout.fragment_card_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewArrowLeft.setOnClickListener {
            viewPagerCard.currentItem = viewPagerCard.currentItem.dec()
        }

        imageViewArrowRight.setOnClickListener {
            viewPagerCard.currentItem = viewPagerCard.currentItem.inc()
        }

        buttonDetails.setOnClickListener { button ->
            if (cardPagerAdapter.getData().isEmpty()) return@setOnClickListener

            Navigation.findNavController(button)
                .navigate(CardListFragmentDirections.actionCardDetail().apply {
                    cardId = cardPagerAdapter.getData()[viewPagerCard.currentItem].cardId
                })
        }

        viewPagerCard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.eventStream.onNext(
                    SelectCardEvent(
                        cardPagerAdapter.getData()[position].cardId
                    )
                )
            }
        })

        viewPagerCard.adapter = cardPagerAdapter

        TabLayoutMediator(tabLayoutPagerIndicator, viewPagerCard) { _, _ -> }.attach()

        viewModel.fetchCardsStream.observe(viewLifecycleOwner, Observer { uiModel ->
            uiModel?.let {
                cardPagerAdapter.setData(uiModel.cards)
            }
        })

        viewModel.selectedCardDetailStream.observe(viewLifecycleOwner, Observer { uiModel ->
            uiModel?.let {
                textViewAvailableAmount.text = uiModel.availableAmount
                textViewCurrentBalanceCurrency.text = uiModel.currency
                textViewMinPaymentCurrency.text = uiModel.currency
                textViewCurrentBalance.text = uiModel.currentBalance
                textViewMinPayment.text = uiModel.minPayment
                textViewDueDate.text = uiModel.dueDate
                animate(uiModel.currentBalanceProgress)

                textViewAvailableAmount.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (uiModel.hasAvailableAmount) R.color.textColorLightBlue else R.color.applicationRed
                    )
                )

                if (uiModel.hasAvailableAmount) {
                    imageViewAlert.visibility = View.GONE
                } else {
                    imageViewAlert.visibility = View.VISIBLE
                }

                (activity as? MainActivity)?.updateToolbarTitle(
                    ToolbarModel(
                        title = uiModel.issuer
                    )
                )
            }
        })

        viewModel.loadingStream.observe(viewLifecycleOwner, Observer { show ->
            if (show) {
                groupContent.visibility = View.GONE
                progress.visibility = View.VISIBLE
            } else {
                groupContent.visibility = View.VISIBLE
                progress.visibility = View.GONE
            }
        })
    }

    private fun animate(float: Float) {
        valueAnimator = if (currentProgress == 0f) {
            ValueAnimator.ofFloat(progressStartValue, float)
        } else {
            ValueAnimator.ofFloat(currentProgress, float)
        }.apply {
            duration = animationDuration
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                if (guidelineChart == null) return@addUpdateListener

                val lp = guidelineChart.layoutParams as ConstraintLayout.LayoutParams
                lp.guidePercent = animator.animatedValue as Float
                guidelineChart.layoutParams = lp
            }

            start()

            currentProgress = float
        }
    }

    override fun onDestroyView() {
        valueAnimator?.removeAllUpdateListeners()
        valueAnimator?.cancel()
        super.onDestroyView()
    }
}