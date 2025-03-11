package site.stanleyzhao.mobiletest.feature.booking.view

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView

import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import site.stanleyzhao.mobiletest.R
import site.stanleyzhao.mobiletest.feature.booking.model.Segment

/**
 * Recyclerview对应的adapter
 * @Stanley
 */
class BookingAdapter : BaseQuickAdapter<Segment, QuickViewHolder>() {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): QuickViewHolder {
        // 返回一个 ViewHolder
        return QuickViewHolder(R.layout.item_booking, parent)
    }

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: Segment?) {
        // 设置item数据
        val tvOriginCode = holder.getView<TextView>(R.id.tv_origin_code)
        val tvOriginName = holder.getView<TextView>(R.id.tv_origin_name)
        val tvDestinationCode = holder.getView<TextView>(R.id.tv_destination_code)
        val tvDestinationName = holder.getView<TextView>(R.id.tv_destination_name)

        val originAndDestinationPair = item?.originAndDestinationPair
        originAndDestinationPair?.let {
            tvOriginCode.text = "${context.getString(R.string.origin_code)}${it.origin.code}"
            tvOriginName.text = "${context.getString(R.string.origin_name)}${it.origin.displayName}"

            tvDestinationCode.text = "${context.getString(R.string.destination_code)}${it.destination.code}"
            tvDestinationName.text = "${context.getString(R.string.destination_name)}${it.destination.displayName}"
        }
    }

}
