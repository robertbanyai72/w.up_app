package com.robert.banyai.wup.presentation.card.detail

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.robert.banyai.wup.R
import com.robert.banyai.wup.domain.event.GetCardEvent
import com.robert.banyai.wup.presentation.base.BaseFragment
import com.robert.banyai.wup.presentation.base.ToolbarModel
import com.robert.banyai.wup.presentation.main.MainActivity
import kotlinx.android.synthetic.main.fragment_card_detail.*
import kotlinx.android.synthetic.main.fragment_card_list.textViewAvailableAmount
import kotlinx.android.synthetic.main.fragment_card_list.textViewCurrentBalance

class CardDetailFragment : BaseFragment<CardDetailViewModel>() {

    private val args: CardDetailFragmentArgs by navArgs()

    override fun viewModelClass(): Class<out CardDetailViewModel> {
        return CardDetailViewModel::class.java
    }

    override fun layoutId(): Int {
        return R.layout.fragment_card_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.updateToolbarTitle(
            ToolbarModel(
                gravity = Gravity.LEFT,
                backButtonVisibility = true,
                title = getString(R.string.nav_title_card_details)
            )
        )

        viewModel.carDetailStream.observe(viewLifecycleOwner, Observer { uiModel ->
            uiModel?.let {
                textViewCurrentBalance.text = uiModel.currentBalance
                textViewAvailableAmount.text = uiModel.availableBalance
                textViewReservations.text = uiModel.reservations
                textViewBalanceCarried.text = uiModel.balanceCarried
                textViewTotalSpending.text = uiModel.totalSpending
                textViewLatestRePayment.text = uiModel.latestRePayment
                textViewAccountLimit.text = uiModel.cardAccountLimit
                textViewAccountNumber.text = uiModel.cardAccountNumber
                textViewMainCardNumber.text = uiModel.mainCardNumber
                textViewMainCardHolderName.text = uiModel.mainCardHolderName
                textViewSupplementaryCardNumber.text = uiModel.supplementaryCardNumber
                textViewSupplementaryCardHolderName.text = uiModel.supplementaryCardHolderName
                textViewReservationsCurrency.text = uiModel.currency
                textViewBalanceCarriedCurrency.text = uiModel.currency
                textViewTotalSpendingCurrency.text = uiModel.currency
                textViewAccountLimitCurrency.text = uiModel.currency

                //making calcs based on the side guidelines
                guidelineCurrentBalance.setPercentage(if (uiModel.currentBalanceProgress == 0f) uiModel.currentBalanceProgress - 0.04f else uiModel.currentBalanceProgress + 0.04f)
                guidelineAvailableAmount.setPercentage(if (uiModel.availableAmountProgress == 1f) uiModel.availableAmountProgress - 0.04f else uiModel.availableAmountProgress + 0.04f)
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

        viewModel.eventStream.onNext(GetCardEvent(args.cardId))
    }

    private fun Guideline.setPercentage(guidePercent: Float) {
        val currentBalanceLp = layoutParams as ConstraintLayout.LayoutParams
        currentBalanceLp.guidePercent = guidePercent
        layoutParams = currentBalanceLp
    }
}