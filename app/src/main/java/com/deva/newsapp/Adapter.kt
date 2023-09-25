package com.deva.newsapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.prefs.NodeChangeListener

class Adapter(val context:Activity, val newsArrayList:List<Article>):
RecyclerView.Adapter<Adapter.MyViewHolder>()



{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView=LayoutInflater.from(context).inflate(R.layout.item,parent,false)
         val viewHolder= MyViewHolder(itemView)
        itemView.setOnClickListener {



        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //getting the current View
        val currentItem=newsArrayList[position]
        holder.title.text=currentItem.title
        holder.author.text=currentItem.author
        val url=currentItem.content
        //loading image'


        Picasso.get().load(currentItem.urlToImage).into(holder.image);



        //new


    }




    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var title:TextView
        var image:ImageView
        var author:TextView

        init {
            title=itemView.findViewById(R.id.Tittle)
            image=itemView.findViewById(R.id.image)
            author=itemView.findViewById(R.id.Author)
        }
    }

    interface ItemClicked{
        fun onItemClicked(item:String)
    }


}