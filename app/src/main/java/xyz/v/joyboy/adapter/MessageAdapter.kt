package xyz.v.joyboy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import xyz.v.joyboy.R
import xyz.v.luffy.models.Chat
import java.text.SimpleDateFormat
import java.util.Date


class MessageAdapter(private val chatList:List<Chat>):RecyclerView.Adapter<MessageAdapter.mvh>() {

    private var lastPosition = -1

    class mvh(view: View):RecyclerView.ViewHolder(view){
        val botCard:RelativeLayout = view.findViewById(R.id.bot_card)
        val  userCard:RelativeLayout = view.findViewById(R.id.userr_text_card)
        val dateCard:CardView = view.findViewById(R.id.dateCard)
        val message:TextView = view.findViewById(R.id.message)
        val time:TextView = view.findViewById(R.id.timemsg)
        val messageB:TextView = view.findViewById(R.id.messageB)
        val timeB:TextView = view.findViewById(R.id.timemsgB)
        val dateCardTimeTv:TextView = view.findViewById(R.id.dateCard_timeTV)
        val dateDayTv:TextView = view.findViewById(R.id.day_date_tv)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mvh {
        var chat = LayoutInflater.from(parent.context).inflate(R.layout.sent_text,parent,false)
        when(viewType){

            1->{
           chat = LayoutInflater.from(parent.context).inflate(R.layout.bot_text,parent,false)
                return mvh(chat)
            }
            2->{
                chat = LayoutInflater.from(parent.context).inflate(R.layout.bot_text,parent,false)
                return mvh(chat)
            }
            3->{
                chat = LayoutInflater.from(parent.context).inflate(R.layout.bot_text,parent,false)
                return mvh(chat)
            }


        }
        return mvh(chat)
    }

    override fun onBindViewHolder(holder: mvh, position: Int) {

        val obj = chatList[position]
        val timeFormat = SimpleDateFormat("hh:mm a")
        when(obj.isUser){
            1->{
                holder.botCard.visibility = View.GONE
                holder.message.text = obj.message
                holder.time.text = timeFormat.format(Date())
                holder.userCard.visibility = View.VISIBLE
                setAnimation(holder.userCard,holder.botCard.context,position)
            }
            2->{
                holder.messageB.text = obj.message
                holder.timeB.text = timeFormat.format(Date())
                setAnimation(holder.botCard,holder.botCard.context,position)
            }
            3->{
                holder.botCard.visibility = View.INVISIBLE
                holder.dateCard.visibility = View.VISIBLE
                holder.dateCardTimeTv.text = timeFormat.format(Date())
                setAnimation(holder.dateCard,holder.botCard.context,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    private fun setAnimation(view: View,context:Context,position:Int) {
        if (position > lastPosition) {
            val anim = AnimationUtils.loadAnimation(context, R.anim.zoom_in_fade_in);
            view.startAnimation(anim)
            lastPosition = position
        }
    }

    override fun getItemViewType(position: Int): Int {
        return chatList[position].isUser
    }
}