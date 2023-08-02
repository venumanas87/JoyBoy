package xyz.v.joyboy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import xyz.v.joyboy.Constants
import xyz.v.joyboy.R
import xyz.v.joyboy.models.Emotion
import xyz.v.joyboy.ui.emotions.GenericEmotionsActivity

class EmotionsAdapter(val emoList:List<Emotion>): RecyclerView.Adapter<EmotionsAdapter.EmotionHolder>() {


    class EmotionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTV = view.findViewById<TextView>(R.id.title_tv)
        var mainMC = view.findViewById<MaterialCardView>(R.id.mainCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionHolder {
        val emoCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.emotion_item,parent,false)
        return EmotionHolder(emoCard)
    }

    override fun onBindViewHolder(holder: EmotionHolder, position: Int) {
        val obj = emoList[position]
        holder.titleTV.text = obj.title

        holder.mainMC.setOnClickListener {
            holder.titleTV.context.startActivity(Intent(holder.titleTV.context,GenericEmotionsActivity::class.java).apply {
                putExtra(Constants.EMOTION,obj.title)
            })
        }
    }

    override fun getItemCount(): Int = 5

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}