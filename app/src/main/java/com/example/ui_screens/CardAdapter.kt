import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_screens.CardItem
import com.example.ui_screens.R

class CardAdapter(private val cards: List<CardItem>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardTitleTextView: TextView = itemView.findViewById(R.id.cardTitleTextView)
        private val cardCityTextView: TextView = itemView.findViewById(R.id.cardCityTextView)
        private val cardPurposeTextView: TextView = itemView.findViewById(R.id.cardPurposeTextView)
        private val cardAvailTextView: TextView = itemView.findViewById(R.id.cardAvailTextView)

        fun bind(card: CardItem) {
            cardTitleTextView.text = card.title
            cardCityTextView.text = card.city
            cardPurposeTextView.text = card.purpose
            cardAvailTextView.text = card.avail
        }
    }
}

