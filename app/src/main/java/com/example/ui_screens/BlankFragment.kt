package com.example.ui_screens

import CardAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card, container, false)

        recyclerView = view.findViewById(R.id.cardRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cardAdapter = CardAdapter(getSampleCards())
        recyclerView.adapter = cardAdapter


        return view
    }

    private fun getSampleCards(): List<CardItem> {
        val cards = mutableListOf<CardItem>()

        val card1 = CardItem(
            "John Doe",
            "San Francisco, within 1Km",
            "Friendship | Coffee | Hangout",
            "Hi community! I am open to new connections \"😊\""
        )
        val card2 = CardItem(
            "Jane Smith",
            "San Francisco, within 2Km",
            "Friendship | Hangout",
            "Hi community! I am open to new connections \"😊\" \n B Tech - IT \n @KSRCT"
        )
        val card3 = CardItem(
            "Mark Johnson",
            "San Francisco, within 2.5Km",
            "Coffee | Business",
            "Hi community! I am open to new connections \"😊\" \n As a UI/UX designer intern, I have experience creating visually appealing and user-friendly interfaces for web and mobile applications.\n" +
                    "\n" + "During my previous internship, I collaborated with cross-functional teams to gather user requirements, conduct user research, and create wireframes and prototypes to iterate and improve the overall user experience."
        )

        cards.add(card1)
        cards.add(card2)
        cards.add(card3)

        return cards
    }
}
