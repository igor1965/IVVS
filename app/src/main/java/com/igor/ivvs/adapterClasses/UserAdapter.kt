package com.igor.ivvs.adapterClasses

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.igor.ivvs.LoginActivity
import com.igor.ivvs.MessageChatActivity
import com.igor.ivvs.R
import com.igor.ivvs.modelClasses.Users
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_search_item_layout.view.*

class UserAdapter (mContext: Context,mUsers: List<Users>,isChatCheck: Boolean):RecyclerView.Adapter<UserAdapter.ViewHolder?>(){

    private val mContext: Context
    private val  mUsers: List<Users>
    private var isChatCheck: Boolean

    init {
        this.mContext = mContext
        this.mUsers = mUsers
        this.isChatCheck = isChatCheck
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layout,viewGroup,false)
        return UserAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: Users = mUsers[position]
        holder.userNameTxt.text = user!!.getUserName()
       // Picasso.get().load(user.getProfile()).into(profile_image)
         Picasso.get().load(user.getProfile()).placeholder(R.drawable.profile).into(holder.profileImageView)

        holder.itemView.setOnClickListener {
            val option = arrayOf<CharSequence>(
                "Send message",
                "Visit Profile",
                "Show location"
            )
            val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
            builder.setTitle("What do you want?")
            builder.setItems(option,DialogInterface.OnClickListener { dialog, which ->
                if (which == 0)
               {
                   val intent = Intent(mContext, MessageChatActivity::class.java)
                   intent.putExtra("visit_id",user.getUID())
                   mContext.startActivity(intent)

               }
                if (which == 1)
                {

                }
                if (which == 2)
                {

                }
            })
            builder.show()
        }


    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var userNameTxt: TextView
        var profileImageView: CircleImageView
        var onlineImageView: CircleImageView
        var offlineImageView: CircleImageView
        var lastMessageTxt: TextView

        init {
            userNameTxt = itemView.findViewById(R.id.username)
            profileImageView = itemView.findViewById(R.id.profile_image)
            onlineImageView = itemView.findViewById(R.id.image_online)
            offlineImageView = itemView.findViewById(R.id.image_offline)
            lastMessageTxt = itemView.findViewById(R.id.message_last)
        }

    }



}

